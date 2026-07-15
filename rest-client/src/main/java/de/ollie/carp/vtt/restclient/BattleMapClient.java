package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;

@Named
public class BattleMapClient {

	public void uploadBattleMap(BattleMap battleMap) {
		ApiClient client = new ApiClient();
		client.setBasePath("http://localhost:8080");
		BattleMapApi api = new BattleMapApi(client);
		BattleMapDto dto = null;
		try {
			api.updateBattleMap(dto);
		} catch (ApiException ae) {
			throw new UploadException("battle map upload error: " + ae.getMessage());
		}
	}
}
