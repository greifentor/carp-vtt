package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import de.ollie.carp.vtt.swing.component.EditorButtonPanel;
import de.ollie.carp.vtt.swing.component.EditorButtonPanel.ButtonType;
import de.ollie.carp.vtt.swing.component.Upload;
import de.ollie.carp.vtt.swing.localization.ResourceManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TokenEditJInternalFrame extends JInternalFrame implements EditorButtonPanel.Observer {

	public interface Observer {
		void deleted();
		void updated(Token tokenToSave);
	}

	private final Token objectToEdit;
	private final SwingComponentFactory swingComponentFactory;
	private final JDesktopPane desktopPane;
	private final BinaryFileAccessPort binaryFileAccessPort;
	private final Observer observer;

	private ResourceManager resourceManager;
	private JTextField textFieldName;
	private Upload uploadImage;

	public TokenEditJInternalFrame(
		Token objectToEdit,
		SwingComponentFactory swingComponentFactory,
		JDesktopPane desktopPane,
		BinaryFileAccessPort binaryFileAccessPort,
		Observer observer
	) {
		super("", true, true, true, true);
		this.binaryFileAccessPort = binaryFileAccessPort;
		this.desktopPane = desktopPane;
		this.objectToEdit = objectToEdit;
		this.observer = observer;
		this.swingComponentFactory = swingComponentFactory;
		resourceManager = swingComponentFactory.getResourceManager();
	}

	TokenEditJInternalFrame prepare() {
		setTitle(resourceManager.getResource("TokenEditJInternalFrame.title"));
		setContentPane(createMainPanel());
		try {
			setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		setBounds(50, 50, 640, 480);
		pack();
		desktopPane.add(this);
		return this;
	}

	JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout(SwingConstants.HGAP, SwingConstants.VGAP));
		panel.add(createEditPanel(), BorderLayout.NORTH);
		panel.add(new EditorButtonPanel(this, swingComponentFactory).prepare(), BorderLayout.SOUTH);
		return panel;
	}

	JPanel createEditPanel() {
		JPanel panel = new JPanel(new BorderLayout(SwingConstants.HGAP, SwingConstants.VGAP));
		panel.setBorder(
			new EmptyBorder(SwingConstants.VGAP, SwingConstants.HGAP, SwingConstants.VGAP, SwingConstants.HGAP)
		);
		panel.add(createPanelLabels(), BorderLayout.WEST);
		panel.add(createPanelFields(), BorderLayout.CENTER);
		return panel;
	}

	JPanel createPanelLabels() {
		JPanel panel = new JPanel(new GridLayout(2, 1, SwingConstants.HGAP, SwingConstants.VGAP));
		panel.add(swingComponentFactory.createLabel("TokenEditJInternalFrame.field.name.label"));
		panel.add(swingComponentFactory.createLabel("TokenEditJInternalFrame.field.content.label"));
		return panel;
	}

	JPanel createPanelFields() {
		JPanel panel = new JPanel(new GridLayout(2, 1, SwingConstants.HGAP, SwingConstants.VGAP));
		textFieldName = new JTextField(objectToEdit.getName(), 40);
		uploadImage = new Upload(binaryFileAccessPort, swingComponentFactory, objectToEdit.getImage()).build();
		panel.add(new JTextField(40));
		panel.add(uploadImage);
		return panel;
	}

	@Override
	public void buttonPressed(ButtonType buttonTyp) {
		if (observer != null) {
			if (buttonTyp == ButtonType.OK) {
				observer.updated(copyValueFromField());
			} else if (buttonTyp == ButtonType.DELETE) {
				observer.deleted();
			}
		}
	}

	Token copyValueFromField() {
		objectToEdit.setName(textFieldName.getText());
		objectToEdit.setImage(uploadImage.getValue());
		return objectToEdit;
	}
}
