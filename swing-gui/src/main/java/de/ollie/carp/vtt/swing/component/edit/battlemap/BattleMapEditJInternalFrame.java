package de.ollie.carp.vtt.swing.component.edit.battlemap;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.swing.UserConfiguration;
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
public class BattleMapEditJInternalFrame extends AbstractEditJInternalFrame<BattleMap> {

	private final UserConfiguration userConfiguration;

	public BattleMapEditJInternalFrame(
		BattleMap toEdit,
		EditDialogComponentFactory editDialogComponentFactory,
		Observer<BattleMap> observer,
		JDesktopPane desktopPane,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, "Battle Map", toEdit, editDialogComponentFactory, observer, Map.of());
		this.userConfiguration = userConfiguration;
		prepare();
	}

	@Override
	protected JPanel createEditorPanel(BattleMap toEdit, Map<String, ItemProvider<?>> itemProviders) {
		editPanel = new BattleMapEditJPanel(toEdit, itemProviders, userConfiguration);
		return editPanel;
	}
}
