package de.ollie.carp.vtt.restserver.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.ollie.carp.vtt.restserver.core.service.ScenarioService;
import de.ollie.carp.vtt.restserver.core.service.UserService;
import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.core.service.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(properties = "spring.datasource.url=jdbc:hsqldb:mem:carp-vtt-scenario-security-it")
class ScenarioRestControllerSecurityIntegrationTest {

	private static final String SCENARIOS_URL = "/api/v1/scenarios";
	private static final String SCENARIO_BY_ID_URL = "/api/v1/scenarios/{id}";

	@Autowired
	private WebApplicationContext context;

	@Autowired
	@Qualifier("springSecurityFilterChain")
	private Filter springSecurityFilterChain;

	@Autowired
	private ScenarioService scenarioService;

	@Autowired
	private UserService userService;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}

	private User createUser(String rights) {
		return userService.updateUser(
			new User().setId(UUID.randomUUID()).setKey(UUID.randomUUID().toString()).setRights(rights)
		);
	}

	private Scenario createScenario(UUID owner) {
		return scenarioService.updateScenario(
			new Scenario().setId(UUID.randomUUID()).setName("scenario-" + UUID.randomUUID()).setOwner(owner)
		);
	}

	private String createToken(User user) {
		return createToken(user, user.getKey(), Instant.now().plusSeconds(300));
	}

	private String createToken(User user, String secret, Instant expiration) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		return Jwts
			.builder()
			.header()
			.add("uid", user.getId().toString())
			.and()
			.subject(user.getId().toString())
			.issuedAt(new Date())
			.expiration(Date.from(expiration))
			.signWith(key)
			.compact();
	}

	private MockHttpServletRequestBuilder putScenario(Scenario scenario) {
		String owner = scenario.getOwner() == null ? "null" : "\"" + scenario.getOwner() + "\"";
		return put(SCENARIOS_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\":\"" + scenario.getId() + "\",\"name\":\"" + scenario.getName() + "\",\"owner\":" + owner + "}");
	}

	private static String bearer(String token) {
		return "Bearer " + token;
	}

	@Nested
	class Authentication {

		@Test
		void returnsUnauthorized_whenNoAuthorizationHeaderIsPassed() throws Exception {
			// Prepare
			Scenario scenario = new Scenario().setId(UUID.randomUUID()).setName("name");
			// Run & Check
			mockMvc.perform(putScenario(scenario)).andExpect(status().isUnauthorized());
		}

		@Test
		void returnsUnauthorized_whenAuthorizationHeaderIsNotABearer() throws Exception {
			// Prepare
			Scenario scenario = new Scenario().setId(UUID.randomUUID()).setName("name");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, "Basic dXNlcjpwYXNzd29yZA=="))
				.andExpect(status().isUnauthorized());
		}

		@Test
		void returnsUnauthorized_whenBearerTokenIsMalformed() throws Exception {
			// Prepare
			Scenario scenario = new Scenario().setId(UUID.randomUUID()).setName("name");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer("not-a-jwt")))
				.andExpect(status().isUnauthorized());
		}

		@Test
		void returnsUnauthorized_whenTokenIsSignedWithAWrongKey() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(null);
			String token = createToken(user, UUID.randomUUID().toString(), Instant.now().plusSeconds(300));
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(token)))
				.andExpect(status().isUnauthorized());
		}

		@Test
		void returnsUnauthorized_whenTokenIsExpired() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(null);
			String token = createToken(user, user.getKey(), Instant.now().minusSeconds(60));
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(token)))
				.andExpect(status().isUnauthorized());
		}

		@Test
		void returnsUnauthorized_whenTokenBelongsToAnUnknownUser() throws Exception {
			// Prepare
			User unknownUser = new User()
				.setId(UUID.randomUUID())
				.setKey(UUID.randomUUID().toString())
				.setRights("MANAGE_PUBLIC");
			Scenario scenario = createScenario(null);
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(unknownUser))))
				.andExpect(status().isUnauthorized());
		}
	}

	@Nested
	class UpdateScenario {

		@Test
		void returnsOk_whenUserHasManagePrivateRight_andScenarioHasAnOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(user.getId()).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isOk());
		}

		@Test
		void persistsTheChange_whenUserHasManagePrivateRight_andScenarioHasAnOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(user.getId()).setName("changed-private");
			// Run
			mockMvc.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))));
			// Check
			assertEquals("changed-private", scenarioService.findById(scenario.getId()).get().getName());
		}

		@Test
		void returnsForbidden_whenUserHasManagePrivateRight_andScenarioHasNoOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(null).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsOk_whenUserHasManagePublicRight_andScenarioHasNoOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(null).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isOk());
		}

		@Test
		void returnsForbidden_whenUserHasManagePublicRight_andScenarioHasAnOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(user.getId()).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsOk_whenUserHasBothRights_andScenarioHasNoOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE,MANAGE_PUBLIC");
			Scenario scenario = createScenario(null).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isOk());
		}

		@Test
		void returnsForbidden_whenUserHasNoManageRight() throws Exception {
			// Prepare
			User user = createUser("READ");
			Scenario scenario = createScenario(user.getId()).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsForbidden_whenUserHasManagePrivateRight_andScenarioIsOwnedByAnotherUser() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId()).setName("changed");
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsForbidden_whenUserTriesToTakeOverAScenarioOfAnotherUser() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId()).setOwner(user.getId());
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void keepsTheOwner_whenUserTriesToTakeOverAScenarioOfAnotherUser() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId());
			// Run
			mockMvc.perform(
				putScenario(new Scenario().setId(scenario.getId()).setName(scenario.getName()).setOwner(user.getId()))
					.header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
			);
			// Check
			assertEquals(otherUser.getId(), scenarioService.findById(scenario.getId()).get().getOwner());
		}

		@Test
		void returnsForbidden_whenUserHasManagePublicRight_andTriesToPublishAPrivateScenario() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId()).setOwner(null);
			// Run & Check
			mockMvc
				.perform(putScenario(scenario).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user))))
				.andExpect(status().isForbidden());
		}

		@Test
		void doesNotPersistTheChange_whenUserHasNoManageRight() throws Exception {
			// Prepare
			User user = createUser("READ");
			Scenario scenario = createScenario(null);
			String originalName = scenario.getName();
			// Run
			mockMvc.perform(
				putScenario(scenario.setName("changed")).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
			);
			// Check
			assertEquals(originalName, scenarioService.findById(scenario.getId()).get().getName());
		}
	}

	@Nested
	class RemoveScenario {

		@Test
		void returnsUnauthorized_whenNoAuthorizationHeaderIsPassed() throws Exception {
			// Prepare
			Scenario scenario = createScenario(null);
			// Run & Check
			mockMvc.perform(delete(SCENARIO_BY_ID_URL, scenario.getId())).andExpect(status().isUnauthorized());
		}

		@Test
		void returnsAccepted_whenUserHasManagePrivateRight_andScenarioHasAnOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(user.getId());
			// Run & Check
			mockMvc
				.perform(
					delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
				)
				.andExpect(status().isAccepted());
		}

		@Test
		void deletesTheScenario_whenUserHasManagePublicRight_andScenarioHasNoOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(null);
			// Run
			mockMvc.perform(
				delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
			);
			// Check
			assertFalse(scenarioService.findById(scenario.getId()).isPresent());
		}

		@Test
		void returnsForbidden_whenUserHasManagePrivateRight_andScenarioHasNoOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(null);
			// Run & Check
			mockMvc
				.perform(
					delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
				)
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsForbidden_whenUserHasManagePublicRight_andScenarioHasAnOwner() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PUBLIC");
			Scenario scenario = createScenario(user.getId());
			// Run & Check
			mockMvc
				.perform(
					delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
				)
				.andExpect(status().isForbidden());
		}

		@Test
		void returnsForbidden_whenUserHasManagePrivateRight_andScenarioIsOwnedByAnotherUser() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId());
			// Run & Check
			mockMvc
				.perform(
					delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
				)
				.andExpect(status().isForbidden());
		}

		@Test
		void keepsTheScenario_whenUserHasManagePrivateRight_andScenarioIsOwnedByAnotherUser() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE");
			User otherUser = createUser("MANAGE_PRIVATE");
			Scenario scenario = createScenario(otherUser.getId());
			// Run
			mockMvc.perform(
				delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
			);
			// Check
			assertTrue(scenarioService.findById(scenario.getId()).isPresent());
		}

		@Test
		void returnsNotFound_whenScenarioDoesNotExist() throws Exception {
			// Prepare
			User user = createUser("MANAGE_PRIVATE,MANAGE_PUBLIC");
			// Run & Check
			mockMvc
				.perform(
					delete(SCENARIO_BY_ID_URL, UUID.randomUUID()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
				)
				.andExpect(status().isNotFound());
		}

		@Test
		void returnsUnauthorized_whenScenarioDoesNotExist_andNoAuthorizationHeaderIsPassed() throws Exception {
			mockMvc.perform(delete(SCENARIO_BY_ID_URL, UUID.randomUUID())).andExpect(status().isUnauthorized());
		}

		@Test
		void keepsTheScenario_whenUserHasNoManageRight() throws Exception {
			// Prepare
			User user = createUser("READ");
			Scenario scenario = createScenario(null);
			// Run
			mockMvc.perform(
				delete(SCENARIO_BY_ID_URL, scenario.getId()).header(HttpHeaders.AUTHORIZATION, bearer(createToken(user)))
			);
			// Check
			assertTrue(scenarioService.findById(scenario.getId()).isPresent());
		}
	}
}
