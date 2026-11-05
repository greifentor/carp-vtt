package de.ollie.carp.vtt.swing.component.select.scenario;

import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.ScenarioImpl;
import de.ollie.carp.vtt.swing.UserContext;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.edit.scenario.ScenarioEditJInternalFrame;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJPanel;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectionTableModel;
import de.ollie.carp.vtt.swing.component.select.SelectionPanelObserver;
import java.util.List;
import java.util.UUID;
import javax.swing.JDesktopPane;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class ScenarioSelectJPanel extends AbstractSelectJPanel<Scenario> implements SelectionPanelObserver {

	private final ScenarioService scenarioService;

	public ScenarioSelectJPanel(
		ScenarioService scenarioService,
		String className,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		SelectionPanelObserver observer
	) {
		super(desktopPane, className + "s", editDialogComponentFactory, observer);
		this.scenarioService = scenarioService;
		updateTableSelection();
	}

	@Override
	protected List<Scenario> getObjectsToSelect() {
		return scenarioService != null ? scenarioService.listScenarios().stream().toList() : List.of();
	}

	@Override
	protected AbstractSelectionTableModel<Scenario> createSelectionModel() {
		return new AbstractSelectionTableModel<Scenario>(getObjectsToSelect(), "Name", "Private") {
			@Override
			protected Object getColumnValueFor(Scenario t, int columnIndex) {
				return switch (columnIndex) {
					case 0 -> t.getName();
					case 1 -> t.getOwner() != null;
					default -> null;
				};
			}
		};
	}

	@Override
	protected void createEditInternalFrame(Scenario selected) {
		new ScenarioEditJInternalFrame(selected, getEditDialogComponentFactory(), this, getDesktopPane());
	}

	@Override
	protected Scenario createNewObject() {
		return new ScenarioImpl().setId(UUID.randomUUID()).setName("").setOwner(UserContext.getUserId());
	}

	@Override
	protected void delete(Scenario toDelete) {
		scenarioService.deleteScenario(toDelete.getId());
		scenarioService.pushDeleteScenario(toDelete.getId());
	}

	@Override
	protected void save(Scenario toSave) {
		scenarioService.updateAndPushScenario(toSave);
	}
}
