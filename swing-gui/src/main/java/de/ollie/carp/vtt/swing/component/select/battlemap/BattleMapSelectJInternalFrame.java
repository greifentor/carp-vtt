package de.ollie.carp.vtt.swing.component.select.battlemap;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJInternalFrame;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJPanel;
import de.ollie.carp.vtt.swing.component.select.SelectionPanelObserver;
import java.util.UUID;
import javax.swing.JDesktopPane;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class BattleMapSelectJInternalFrame
	extends AbstractSelectJInternalFrame<BattleMap>
	implements SelectionPanelObserver {

	private static final String CLASS_NAME = "BattleMap";

	private final BattleMapService battleMapService;
	private final UserConfiguration userConfiguration;

	public BattleMapSelectJInternalFrame(
		BattleMapService battleMapService,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, CLASS_NAME, editDialogComponentFactory);
		this.battleMapService = battleMapService;
		this.userConfiguration = userConfiguration;
		finishConstruct();
	}

	@Override
	protected AbstractSelectJPanel<BattleMap> createSelectPanel() {
		return new BattleMapSelectJPanel(
			battleMapService,
			CLASS_NAME,
			desktopPane,
			editDialogComponentFactory,
			this,
			userConfiguration
		);
	}
}
