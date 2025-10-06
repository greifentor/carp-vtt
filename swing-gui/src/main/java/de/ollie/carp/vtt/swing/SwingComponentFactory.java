package de.ollie.carp.vtt.swing;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.inject.Named;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;

@Named
public class SwingComponentFactory {

	public JButton createButton() {
		return new JButton();
	}

	public JButton createButton(String label, Icon icon, String toolTipText) {
		JButton button = createButton();
		if (icon != null) {
			button.setIcon(icon);
		}
		if (label != null) {
			button.setText(label);
		}
		if (toolTipText != null) {
			button.setToolTipText(toolTipText);
		}
		return button;
	}

	public JTextField createTextField() {
		return new JTextField();
	}

	public JTextField createTextField(String text, Integer columns) {
		ensure((columns == null) || (columns > 0), "columns cannot be lesser than 1!");
		JTextField textField = createTextField();
		if (columns != null) {
			textField.setColumns(columns);
		}
		if (text != null) {
			textField.setText(text);
		}
		return textField;
	}
}
