package de.ollie.carp.vtt.swing.component;

import java.util.List;

@FunctionalInterface
public interface ItemProvider<T> {
	List<T> getItem();
}
