package de.ollie.carp.vtt.restclient.adapter.security;

import de.ollie.carp.vtt.restclient.port.JwtService;
import jakarta.inject.Named;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Named
public class JwtServiceImpl implements JwtService {

	@Override
	public String generateToken(String username, long offsetMinutes, String secretKey) {
		Instant now = Instant.now();
		Instant expires = now.plusSeconds(offsetMinutes * 60);
		// Header
		String headerJson = """
				{"alg":"HS512","typ":"JWT"}
				""";
		// Payload
		String payloadJson =
			"""
				{
				  "sub":"%s",
				  "iat":%d,
				  "exp":%d
				}
				""".formatted(username, now.getEpochSecond(), expires.getEpochSecond());
		String headerB64 = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
		String payloadB64 = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));
		String unsignedToken = headerB64 + "." + payloadB64;
		String signature = hmacSha512(unsignedToken, secretKey);
		return unsignedToken + "." + signature;
	}

	private String hmacSha512(String data, String secret) {
		try {
			Mac mac = Mac.getInstance("HmacSHA512");
			SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
			mac.init(keySpec);
			byte[] raw = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return base64UrlEncode(raw);
		} catch (Exception e) {
			throw new RuntimeException("Error creating HMAC SHA512 signature", e);
		}
	}

	private String base64UrlEncode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}
