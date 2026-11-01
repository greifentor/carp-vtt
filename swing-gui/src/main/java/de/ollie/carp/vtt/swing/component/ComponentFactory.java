package de.ollie.carp.vtt.swing.component;

import jakarta.inject.Named;
import javax.swing.JButton;

@Named
public class ComponentFactory {

	public JButton createButton(String label) {
		return new JButton(label);
	}
}
