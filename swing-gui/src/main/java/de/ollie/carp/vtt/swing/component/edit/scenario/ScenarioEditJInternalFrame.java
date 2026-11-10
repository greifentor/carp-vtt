package de.ollie.carp.vtt.swing.component.edit.scenario;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class ScenarioEditJInternalFrame extends AbstractEditJInternalFrame<Scenario> {

	private final UserConfiguration userConfiguration;

	public ScenarioEditJInternalFrame(
		Scenario toEdit,
		EditDialogComponentFactory editDialogComponentFactory,
		Observer<Scenario> observer,
		JDesktopPane desktopPane,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, "Scenario", toEdit, editDialogComponentFactory, observer, Map.of());
		this.userConfiguration = userConfiguration;
		prepare();
	}

	@Override
	protected JPanel createEditorPanel(Scenario toEdit, Map<String, ItemProvider<?>> itemProviders) {
		editPanel = new ScenarioEditJPanel(toEdit, itemProviders, userConfiguration);
		return editPanel;
	}
}
