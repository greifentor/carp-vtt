package de.ollie.carp.vtt.swing.component.select.battlemap;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapImpl;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.edit.battlemap.BattleMapEditJInternalFrame;
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
public class BattleMapSelectJPanel extends AbstractSelectJPanel<BattleMap> implements SelectionPanelObserver {

	private final BattleMapService battleMapService;
	private final UserConfiguration userConfiguration;

	public BattleMapSelectJPanel(
		BattleMapService battleMapService,
		String className,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		SelectionPanelObserver observer,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, className + "s", editDialogComponentFactory, observer);
		this.battleMapService = battleMapService;
		this.userConfiguration = userConfiguration;
		updateTableSelection();
	}

	@Override
	protected List<BattleMap> getObjectsToSelect() {
		return battleMapService != null ? battleMapService.listBattleMaps().stream().toList() : List.of();
	}

	@Override
	protected AbstractSelectionTableModel<BattleMap> createSelectionModel() {
		return new AbstractSelectionTableModel<BattleMap>(getObjectsToSelect(), "Name") {
			@Override
			protected Object getColumnValueFor(BattleMap t, int columnIndex) {
				return switch (columnIndex) {
					case 0 -> t.getName();
					default -> null;
				};
			}
		};
	}

	@Override
	protected void createEditInternalFrame(BattleMap selected) {
		new BattleMapEditJInternalFrame(
			selected,
			getEditDialogComponentFactory(),
			this,
			getDesktopPane(),
			userConfiguration
		);
	}

	@Override
	protected BattleMap createNewObject() {
		BattleMap model = new BattleMapImpl();
		model.setId(UUID.randomUUID());
		model.setImageContent(null);
		model.setName("");
		model.setOwner(null);
		return model;
	}

	@Override
	protected void delete(BattleMap toDelete) {
		battleMapService.deleteAndPushDeleteBattleMap(toDelete.getId());
	}

	@Override
	protected void save(BattleMap toSave) {
		battleMapService.updateAndPushBattleMap(toSave);
	}
}
