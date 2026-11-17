package de.ollie.carp.vtt.swing.component.edit.token;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenImpl;
import de.ollie.carp.vtt.core.service.model.TokenSize;
import de.ollie.carp.vtt.swing.UserContext;
import de.ollie.carp.vtt.swing.component.FileUploadField;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditPanel;
import java.awt.GridLayout;
import java.util.Map;
import java.util.UUID;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class TokenEditJPanel extends AbstractEditPanel<Token> {

	private JTextField textFieldName;
	private FileUploadField uploadFieldImage;
	private JComboBox<TokenSize> comboBoxTokenSize;
	private JCheckBox checkBoxPublic;

	public TokenEditJPanel(Token toEdit, Map<String, ItemProvider<?>> itemProviders) {
		super(toEdit, itemProviders);
		prepare();
	}

	@Override
	protected JPanel createLabelPanel(Token toEdit) {
		return UserContext.isManagePublicRightSet()
			? createLabelSubPanel("Name:", "Image:", "Token Size:", "Public:")
			: createLabelSubPanel("Name:", "Image:", "Token Size:");
	}

	@Override
	protected JPanel createComponentPanel(Token toEdit, Map<String, ItemProvider<?>> itemProviders) {
		JPanel p = new JPanel(new GridLayout(UserContext.isManagePublicRightSet() ? 3 + 1 : 3, 1, HGAP, VGAP));
		textFieldName = new JTextField(toEdit.getName(), 40);
		p.add(textFieldName);
		uploadFieldImage = new FileUploadField(toEdit.getImage() != null ? toEdit.getImage().length : -1);
		p.add(uploadFieldImage);
		comboBoxTokenSize = new JComboBox<>(TokenSize.values());
		comboBoxTokenSize.setSelectedItem(toEdit.getTokenSize());
		p.add(comboBoxTokenSize);
		checkBoxPublic = new JCheckBox();
		if (UserContext.isManagePublicRightSet()) {
			checkBoxPublic = new JCheckBox();
			checkBoxPublic.setSelected(toEdit.getOwner() == null);
			p.add(checkBoxPublic);
		}
		return p;
	}

	@Override
	public Token getCurrentContent() {
		Token model = new TokenImpl();
		model.setId(toEdit.getId());
		model.setName(textFieldName.getText());
		model.setImage(uploadFieldImage.hasContent() ? uploadFieldImage.getContent() : toEdit.getImage());
		model.setTokenSize(((TokenSize) comboBoxTokenSize.getSelectedItem()));
		model.setOwner(
			UserContext.isManagePublicRightSet() && checkBoxPublic.isSelected() ? null : UserContext.getUserId()
		);
		return model;
	}
}
