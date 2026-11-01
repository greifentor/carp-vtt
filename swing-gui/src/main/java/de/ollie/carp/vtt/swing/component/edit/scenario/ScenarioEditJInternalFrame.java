package de.ollie.carp.vtt.swing.component.edit.scenario;

import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame;
import java.util.Map;
import java.util.UUID;
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

	public ScenarioEditJInternalFrame(
		Scenario toEdit,
		EditDialogComponentFactory editDialogComponentFactory,
		Observer<Scenario> observer,
		JDesktopPane desktopPane
	) {
		super(desktopPane, "Scenario", toEdit, editDialogComponentFactory, observer, Map.of());
	}

	@Override
	protected JPanel createEditorPanel(Scenario toEdit, Map<String, ItemProvider<?>> itemProviders) {
		editPanel = new ScenarioEditJPanel(toEdit, itemProviders);
		return editPanel;
	}
}
