package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
	private JButton buttonDelete;
	private JButton buttonOk;

	public EditorButtonPanel prepare() {
		setLayout(swingComponentFactory.createBorderLayout());
		add(createLeftButtonPanel(), BorderLayout.WEST);
		add(createRightButtonPanel(), BorderLayout.EAST);
		return this;
	}

	JPanel createLeftButtonPanel() {
		JPanel panel = swingComponentFactory.createPanel();
		buttonDelete = swingComponentFactory.createButton("EditorPanel.button.delete.label", null, null);
		buttonDelete.addActionListener(e -> buttonClicked(ButtonType.DELETE));
		panel.setLayout(swingComponentFactory.createFlowLayout(FlowLayout.LEFT));
		panel.add(buttonDelete);
		return panel;
	}

	private void buttonClicked(ButtonType buttonType) {
		if (observer != null) {
			observer.buttonPressed(buttonType);
		}
	}

	JPanel createRightButtonPanel() {
		JPanel panel = swingComponentFactory.createPanel();
		buttonCancel = swingComponentFactory.createButton("EditorPanel.button.cancel.label", null, null);
		buttonCancel.addActionListener(e -> buttonClicked(ButtonType.CANCEL));
		buttonOk = swingComponentFactory.createButton("EditorPanel.button.ok.label", null, null);
		buttonOk.addActionListener(e -> buttonClicked(ButtonType.OK));
		panel.setLayout(swingComponentFactory.createFlowLayout(FlowLayout.RIGHT));
		panel.add(buttonOk);
		panel.add(buttonCancel);
		return panel;
	}
}
