package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame.Observer;
import jakarta.inject.Named;
import javax.swing.JButton;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class EditDialogComponentFactory {

	private final ComponentFactory componentFactory;

	public <T> JButton createSaveButton(Observer<T> observer, AbstractEditJInternalFrame<T> internalFrame) {
		JButton b = componentFactory.createButton("Save");
		if (observer != null) {
			b.addActionListener(e -> {
				observer.onSave(internalFrame.getCurrentContent());
				internalFrame.closeDialog();
			});
		}
		return b;
	}
}
