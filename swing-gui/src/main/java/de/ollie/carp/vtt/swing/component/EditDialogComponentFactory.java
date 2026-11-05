package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame.Observer;
import jakarta.inject.Named;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class EditDialogComponentFactory {

	private final ComponentFactory componentFactory;

	public <T> JButton createSaveButton(Observer<T> observer, AbstractEditJInternalFrame<T> internalFrame) {
		JButton b = componentFactory.createButton("Save");
		if (observer != null) {
			b.addActionListener(e -> {
				try {
					observer.onSave(internalFrame.getCurrentContent());
				} catch (Exception ex) {
					JOptionPane.showConfirmDialog(
						null,
						"Error: " + ex.getMessage(),
						"Error",
						JOptionPane.OK_OPTION,
						JOptionPane.ERROR_MESSAGE
					);
					return;
				}
				internalFrame.closeDialog();
			});
		}
		return b;
	}
}
