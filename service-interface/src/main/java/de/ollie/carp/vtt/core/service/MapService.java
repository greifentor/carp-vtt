package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.Map;
import java.util.List;

public interface MapService {
	List<Map> findAll();
	Map save(Map toSave);
}
