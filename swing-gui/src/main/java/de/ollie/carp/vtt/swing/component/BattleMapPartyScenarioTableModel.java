package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BattleMapPartyScenarioTableModel extends AbstractTableModel {

	private List<BattleMapPartyScenario> battleMapPartyScenarios;

	public BattleMapPartyScenarioTableModel(List<BattleMapPartyScenario> battleMapPartyScenarios) {
		this.battleMapPartyScenarios = battleMapPartyScenarios;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0:
				return "Party";
			case 1:
				return "Scenario";
			default:
				return "BattleMap";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getRowCount() {
		return battleMapPartyScenarios.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return (battleMapPartyScenarios.get(rowIndex)).getParty().getName();
			case 1:
				return (battleMapPartyScenarios.get(rowIndex)).getScenario().getName();
			default:
				return (battleMapPartyScenarios.get(rowIndex)).getBattleMap().getName();
		}
	}

	public BattleMapPartyScenario getItem(int i) {
		return battleMapPartyScenarios.get(i);
	}
}
