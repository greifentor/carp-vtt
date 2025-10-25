package de.ollie.carp.vtt.swing.component;

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

	public EditorButtonPanel prepare() {
		return null;
	}
}
