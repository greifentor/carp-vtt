package de.ollie.carp.vtt.swing.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.util.function.BiConsumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class AbstractSelectionDialog<T> extends JDialog {

	private T selectedItem;

	public AbstractSelectionDialog(Frame owner, T[] items, String headline, BiConsumer<JLabel, T> labelPopulator) {
		super(owner, headline, true);
		setSize(350, 300);
		setLocationRelativeTo(owner);
		JList<T> list = new JList<>(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new ItemRenderer<>(labelPopulator));
		list.setFixedCellHeight(50);
		JScrollPane scroll = new JScrollPane(list);
		JButton ok = new JButton("OK");
		ok.addActionListener(e -> {
			selectedItem = list.getSelectedValue();
			dispose();
		});
		JButton cancel = new JButton("Abbrechen");
		cancel.addActionListener(e -> {
			selectedItem = null;
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

	public T getSelectedItem() {
		return selectedItem;
	}

	private static class ItemRenderer<V> extends JLabel implements ListCellRenderer<V> {

		private BiConsumer<JLabel, V> labelPopulator;

		public ItemRenderer(BiConsumer<JLabel, V> labelPopulator) {
			this.labelPopulator = labelPopulator;
			setOpaque(true);
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}

		@Override
		public Component getListCellRendererComponent(
			JList<? extends V> list,
			V value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
		) {
			labelPopulator.accept(this, value);
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
