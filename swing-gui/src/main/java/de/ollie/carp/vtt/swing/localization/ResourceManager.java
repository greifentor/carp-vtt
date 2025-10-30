package de.ollie.carp.vtt.swing.localization;

public interface ResourceManager {
	String getResource(String resourceId);

	void setResource(String localization, String resourceId, String resource);
}
