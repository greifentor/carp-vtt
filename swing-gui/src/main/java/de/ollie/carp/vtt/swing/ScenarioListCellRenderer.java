package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Scenario;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ScenarioListCellRenderer implements ListCellRenderer<Scenario> {

	@Override
	public Component getListCellRendererComponent(
		JList<? extends Scenario> list,
		Scenario value,
		int index,
		boolean isSelected,
		boolean cellHasFocus
	) {
		JLabel label = new JLabel(value.getName());
		if (isSelected) {
			label.setBackground(list.getSelectionBackground());
			label.setForeground(list.getSelectionForeground());
		} else {
			label.setBackground(list.getBackground());
			label.setForeground(list.getForeground());
		}
		return label;
	}
}
