package de.ollie.carp.vtt.swing.component.edit.battlemap;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapImpl;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.FileUploadField;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditPanel;
import java.awt.GridLayout;
import java.util.Map;
import java.util.UUID;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class BattleMapEditJPanel extends AbstractEditPanel<BattleMap> {

	private final UserConfiguration userConfiguration;

	private JTextField textFieldName;
	private FileUploadField uploadFieldImageContent;

	public BattleMapEditJPanel(
		BattleMap toEdit,
		Map<String, ItemProvider<?>> itemProviders,
		UserConfiguration userConfiguration
	) {
		super(toEdit, itemProviders);
		this.userConfiguration = userConfiguration;
		prepare();
	}

	@Override
	protected JPanel createLabelPanel(BattleMap toEdit) {
		return createLabelSubPanel("Name:", "Image Content:");
	}

	@Override
	protected JPanel createComponentPanel(BattleMap toEdit, Map<String, ItemProvider<?>> itemProviders) {
		JPanel p = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		textFieldName = new JTextField(toEdit.getName(), 40);
		p.add(textFieldName);
		uploadFieldImageContent =
			new FileUploadField(toEdit.getImageContent() != null ? toEdit.getImageContent().length : -1);
		p.add(uploadFieldImageContent);
		return p;
	}

	@Override
	public BattleMap getCurrentContent() {
		BattleMap model = new BattleMapImpl();
		model.setId(toEdit.getId());
		model.setName(textFieldName.getText());
		model.setImageContent(
			uploadFieldImageContent.hasContent() ? uploadFieldImageContent.getContent() : toEdit.getImageContent()
		);
		model.setOwner(userConfiguration.getUserId());
		return model;
	}
}
