package de.ollie.carp.vtt.swing.component;

import javax.swing.JButton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditorButtonPanel {

	public enum ButtonType {
		CANCEL,
		DELETE,
		OK,
	}

	public interface Observer {
		void buttonPressed(ButtonType buttonTyp);
	}

	private final Observer observer;

	private JButton buttonCancel;
	private JButton buttonOk;

	public EditorButtonPanel prepare() {
		return this;
	}
}
