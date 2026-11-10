package de.ollie.carp.vtt.swing.component.select.scenario;

import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.ScenarioImpl;
import de.ollie.carp.vtt.swing.UserConfiguration;
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
	private final UserConfiguration userConfiguration;

	public ScenarioSelectJPanel(
		ScenarioService scenarioService,
		String className,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		SelectionPanelObserver observer,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, className + "s", editDialogComponentFactory, observer);
		this.scenarioService = scenarioService;
		this.userConfiguration = userConfiguration;
		updateTableSelection();
	}

	@Override
	protected List<Scenario> getObjectsToSelect() {
		return scenarioService != null ? scenarioService.listScenarios().stream().toList() : List.of();
	}

	@Override
	protected AbstractSelectionTableModel<Scenario> createSelectionModel() {
		return new AbstractSelectionTableModel<Scenario>(getObjectsToSelect(), "Name") {
			@Override
			protected Object getColumnValueFor(Scenario t, int columnIndex) {
				return switch (columnIndex) {
					case 0 -> t.getName();
					default -> null;
				};
			}
		};
	}

	@Override
	protected void createEditInternalFrame(Scenario selected) {
		new ScenarioEditJInternalFrame(
			selected,
			getEditDialogComponentFactory(),
			this,
			getDesktopPane(),
			userConfiguration
		);
	}

	@Override
	protected Scenario createNewObject() {
		Scenario model = new ScenarioImpl();
		model.setId(UUID.randomUUID());
		model.setName("");
		model.setOwner(null);
		return model;
	}

	@Override
	protected void delete(Scenario toDelete) {
		scenarioService.deleteAndPushDeleteScenario(toDelete.getId());
	}

	@Override
	protected void save(Scenario toSave) {
		scenarioService.updateAndPushScenario(toSave);
	}
}
