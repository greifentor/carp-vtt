package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.awt.Frame;
import java.util.List;

public class BattleMapSelectionDialog extends AbstractSelectionDialog<BattleMap> {

	public BattleMapSelectionDialog(Frame owner, List<BattleMap> battleMaps) {
		super(
			owner,
			battleMaps.toArray(new BattleMap[0]),
			"BattleMap auswählen",
			(label, value) -> {
				label.setText(value.getName());
			}
		);
	}
}
