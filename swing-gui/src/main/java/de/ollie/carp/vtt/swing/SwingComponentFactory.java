package de.ollie.carp.vtt.swing;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.swing.component.FileNameProvider;
import de.ollie.carp.vtt.swing.localization.ResourceLoader;
import de.ollie.carp.vtt.swing.localization.ResourceManager;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class SwingComponentFactory {

	@Getter
	private final ResourceManager resourceManager;

	private final ResourceLoader resourceLoader;

	@PostConstruct
	void postConstruct() {
		resourceLoader.loadResources(resourceManager);
	}

	public BorderLayout createBorderLayout() {
		return new BorderLayout(SwingConstants.HGAP, SwingConstants.VGAP);
	}

	public JButton createButton() {
		return new JButton();
	}

	public JButton createButton(String resourceId, Icon icon, String toolTipText) {
		JButton button = createButton();
		if (icon != null) {
			button.setIcon(icon);
		}
		if (resourceId != null) {
			button.setText(resourceManager.getResource(resourceId));
		}
		if (toolTipText != null) {
			button.setToolTipText(toolTipText);
		}
		return button;
	}

	public JFileChooser createFileChooser() {
		return new JFileChooser();
	}

	public FileNameProvider createFileNameProvider() {
		return new FileNameProvider(this);
	}

	public FlowLayout createFlowLayout(int alignment) {
		return new FlowLayout(alignment, SwingConstants.HGAP, SwingConstants.VGAP);
	}

	public JLabel createLabel(String resourceId) {
		return new JLabel(resourceManager.getResource(resourceId));
	}

	public JPanel createPanel() {
		return new JPanel();
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
