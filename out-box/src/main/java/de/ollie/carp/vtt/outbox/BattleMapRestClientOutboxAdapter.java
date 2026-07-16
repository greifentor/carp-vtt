package de.ollie.carp.vtt.outbox;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.outbox.BattleMapOutboxPort;
import de.ollie.carp.vtt.restclient.ApiException;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link BattleMapOutboxPort} implementation which forwards a saved battle map to a (remote) CARP VTT instance via the
 * REST client.
 */
@Slf4j
@Named
@RequiredArgsConstructor
public class BattleMapRestClientOutboxAdapter implements BattleMapOutboxPort {

	private final BattleMapApi battleMapApi;

	@Override
	public void battleMapSaved(BattleMap battleMap) {
		try {
			BattleMapDto dto = new BattleMapDto()
				.id(battleMap.getId())
				.name(battleMap.getName())
				.imageContent(battleMap.getImageContent());
			battleMapApi.updateBattleMap(dto);
		} catch (ApiException e) {
			log.error("could not forward saved battle map (id={}) via rest client: {}", battleMap.getId(), e.getMessage(), e);
		}
	}
}
