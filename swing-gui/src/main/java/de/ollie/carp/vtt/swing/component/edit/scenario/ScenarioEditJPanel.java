package de.ollie.carp.vtt.swing.component.edit.scenario;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.ScenarioImpl;
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
public class ScenarioEditJPanel extends AbstractEditPanel<Scenario> {

	private JTextField textFieldName;

	public ScenarioEditJPanel(Scenario toEdit, Map<String, ItemProvider<?>> itemProviders) {
		super(toEdit, itemProviders);
	}

	@Override
	protected JPanel createLabelPanel(Scenario toEdit) {
		return createLabelSubPanel("Name:");
	}

	@Override
	protected JPanel createComponentPanel(Scenario toEdit, Map<String, ItemProvider<?>> itemProviders) {
		JPanel p = new JPanel(new GridLayout(1, 1, HGAP, VGAP));
		textFieldName = new JTextField(toEdit.getName(), 40);
		p.add(textFieldName);
		return p;
	}

	@Override
	public Scenario getCurrentContent() {
		Scenario model = new ScenarioImpl();
		model.setId(toEdit.getId());
		model.setName(textFieldName.getText());
		return model;
	}
}
