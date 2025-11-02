package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditorButtonPanel extends JPanel {

	static final String RES_ID_BUTTON_CANCEL = "EditorPanel.button.cancel.label";
	static final String RES_ID_BUTTON_DELETE = "EditorPanel.button.delete.label";
	static final String RES_ID_BUTTON_OK = "EditorPanel.button.ok.label";

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
		buttonDelete = swingComponentFactory.createButton(RES_ID_BUTTON_DELETE, null, null);
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
		buttonCancel = swingComponentFactory.createButton(RES_ID_BUTTON_CANCEL, null, null);
		buttonCancel.addActionListener(e -> buttonClicked(ButtonType.CANCEL));
		buttonOk = swingComponentFactory.createButton(RES_ID_BUTTON_OK, null, null);
		buttonOk.addActionListener(e -> buttonClicked(ButtonType.OK));
		panel.setLayout(swingComponentFactory.createFlowLayout(FlowLayout.RIGHT));
		panel.add(buttonOk);
		panel.add(buttonCancel);
		return panel;
	}
}
