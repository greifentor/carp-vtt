package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.model.Token;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class TokenSelectionDialog extends JDialog {

	private Token selectedToken;

	public TokenSelectionDialog(Frame owner, List<Token> tokens) {
		super(owner, "Token auswählen", true);
		setSize(350, 300);
		setLocationRelativeTo(owner);
		JList<Token> list = new JList<>(tokens.toArray(new Token[0]));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new TokenRenderer());
		list.setFixedCellHeight(50);
		JScrollPane scroll = new JScrollPane(list);
		JButton ok = new JButton("OK");
		ok.addActionListener(e -> {
			selectedToken = list.getSelectedValue();
			dispose();
		});
		JButton cancel = new JButton("Abbrechen");
		cancel.addActionListener(e -> {
			selectedToken = null;
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

	public Token getSelectedToken() {
		return selectedToken;
	}

	private static class TokenRenderer extends JLabel implements ListCellRenderer<Token> {

		public TokenRenderer() {
			setOpaque(true);
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		}

		@Override
		public Component getListCellRendererComponent(
			JList<? extends Token> list,
			Token value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
		) {
			setIcon(iconFromBytes(value.getImage()));
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

	public static ImageIcon iconFromBytes(byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			Image img = javax.imageio.ImageIO.read(bais);
			if (img != null) {
				return new ImageIcon(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
