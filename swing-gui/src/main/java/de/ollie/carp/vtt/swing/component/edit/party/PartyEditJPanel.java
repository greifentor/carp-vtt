package de.ollie.carp.vtt.swing.component.edit.party;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.PartyImpl;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.core.service.model.RuleSystem;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditPanel;
import lombok.Generated;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.util.UUID;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class PartyEditJPanel extends AbstractEditPanel<Party> {

	private final UserConfiguration userConfiguration;

	private JTextField textFieldName;
	private JComboBox<RuleSystem> comboBoxRuleSystem;

	public PartyEditJPanel(Party toEdit, Map<String, ItemProvider<?>> itemProviders	, UserConfiguration userConfiguration
) {
		super(toEdit, itemProviders);
		this.userConfiguration = userConfiguration;
		prepare();
	}

	@Override
	protected JPanel createLabelPanel(Party toEdit) {
		return createLabelSubPanel(
				"Name:",
				"Rule System:"
		);
	}

	@Override
	protected JPanel createComponentPanel(Party toEdit, Map<String, ItemProvider<?>> itemProviders) {
		JPanel p = new JPanel(new GridLayout(2, 1, HGAP, VGAP));
		textFieldName = new JTextField(toEdit.getName(), 40);
		p.add(textFieldName);
		comboBoxRuleSystem = new JComboBox<>(RuleSystem.values());
		comboBoxRuleSystem.setSelectedItem(toEdit.getRuleSystem());
		p.add(comboBoxRuleSystem);
		return p;
	}

	@Override
	public Party getCurrentContent() {
		Party model = new PartyImpl();
		model.setId(toEdit.getId());
;
		model.setName(textFieldName .getText());
;
		model.setRuleSystem(((RuleSystem) comboBoxRuleSystem.getSelectedItem()));
		model.setOwner(userConfiguration.getUserId());
		return model;
	}
}
