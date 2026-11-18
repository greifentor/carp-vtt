package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Party;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PartyListCellRenderer implements ListCellRenderer<Party> {

	@Override
	public Component getListCellRendererComponent(
		JList<? extends Party> list,
		Party value,
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
