package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditorButtonPanel extends JPanel {

	public enum ButtonType {
		CANCEL,
		DELETE,
		OK,
	}

	public interface Observer {
		void buttonPressed(ButtonType buttonTyp);
	}

	private final Observer observer;
	private final SwingComponentFactory swingComponentFactory;

	private JButton buttonCancel;
	private JButton buttonOk;

	public EditorButtonPanel prepare() {
		setLayout(swingComponentFactory.createBorderLayout());
		return this;
	}
}
