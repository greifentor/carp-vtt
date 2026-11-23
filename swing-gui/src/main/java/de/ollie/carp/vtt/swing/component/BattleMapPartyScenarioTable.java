package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.BattleMapPartyScenarioService;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import javax.swing.JTable;

public class BattleMapPartyScenarioTable extends JTable {

	public BattleMapPartyScenarioTable(BattleMapPartyScenarioService battleMapPartyScenarioService) {
		setModel(new BattleMapPartyScenarioTableModel(battleMapPartyScenarioService.listBattleMapPartyScenarios()));
		getSelectionModel().addListSelectionListener(this);
	}

	public BattleMapPartyScenario getSelectedItem() {
		return ((BattleMapPartyScenarioTableModel) getModel()).getItem(getSelectedRow());
	}

	public void unselect() {
		clearSelection();
	}
}
