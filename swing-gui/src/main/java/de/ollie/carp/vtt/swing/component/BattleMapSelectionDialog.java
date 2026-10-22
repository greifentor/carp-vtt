package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class BattleMapSelectionDialog extends JDialog {

	private BattleMap selectedBattleMap;

	public BattleMapSelectionDialog(Frame owner, List<BattleMap> tokens) {
		super(owner, "Token auswählen", true);
		setSize(350, 300);
		setLocationRelativeTo(owner);
		JList<BattleMap> list = new JList<>(tokens.toArray(new BattleMap[0]));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new BattleMapRenderer());
		list.setFixedCellHeight(50);
		JScrollPane scroll = new JScrollPane(list);
		JButton ok = new JButton("OK");
		ok.addActionListener(e -> {
			selectedBattleMap = list.getSelectedValue();
			dispose();
		});
		JButton cancel = new JButton("Abbrechen");
		cancel.addActionListener(e -> {
			selectedBattleMap = null;
			dispose();
		});
		JPanel buttons = new JPanel();
		buttons.add(ok);
		buttons.add(cancel);
		add(scroll, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		setModal(true);
		setVisible(true);
	}

	public BattleMap getSelectedItem() {
		return selectedBattleMap;
	}

	private static class BattleMapRenderer extends JLabel implements ListCellRenderer<BattleMap> {

		public BattleMapRenderer() {
			setOpaque(true);
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}

		@Override
		public Component getListCellRendererComponent(
			JList<? extends BattleMap> list,
			BattleMap value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
		) {
			setText(value.getName());
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			return this;
		}
	}
}
