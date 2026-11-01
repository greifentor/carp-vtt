package de.ollie.carp.vtt.swing.component.select.scenario;

import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJInternalFrame;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJPanel;
import de.ollie.carp.vtt.swing.component.select.SelectionPanelObserver;
import javax.swing.JDesktopPane;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class ScenarioSelectJInternalFrame
	extends AbstractSelectJInternalFrame<Scenario>
	implements SelectionPanelObserver {

	private static final String CLASS_NAME = "Scenario";

	private final ScenarioService scenarioService;

	public ScenarioSelectJInternalFrame(
		ScenarioService scenarioService,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory
	) {
		super(desktopPane, CLASS_NAME, editDialogComponentFactory);
		this.scenarioService = scenarioService;
		finishConstruct();
	}

	@Override
	protected AbstractSelectJPanel<Scenario> createSelectPanel() {
		return new ScenarioSelectJPanel(scenarioService, CLASS_NAME, desktopPane, editDialogComponentFactory, this);
	}
}
