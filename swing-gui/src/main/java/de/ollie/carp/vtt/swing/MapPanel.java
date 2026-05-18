package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Token;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Getter;

public class MapPanel extends JPanel {

	public interface Observer {
		void tokenHit(Token token, Coordinates coordinates);
	}

	private static final int OFFSET_IN_PIXELS = 12;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	private ImageIcon mapImage;
	private Map<Token, Coordinates> tokens;

	@Getter
	private Token selectedToken;

	public MapPanel(ImageIcon mapImage, Map<Token, Coordinates> tokens, Observer observer) {
		this.mapImage = mapImage;
		this.tokens = tokens;
		setPreferredSize(new Dimension(mapImage.getIconWidth(), mapImage.getIconHeight()));
		// Hit-Detection aktivieren
		addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Token t = getTokenAt(e.getX(), e.getY());
					if (t != null) {
						System.out.println("Token getroffen: " + t.getName());
					}
					if (observer != null) {
						observer.tokenHit(t, getFieldCoordinates(e.getX(), e.getY()));
					}
				}
			}
		);
	}

	private Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		String fieldY = ((y - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		return new Coordinates().setFieldX(new BigDecimal(fieldX)).setFieldY(new BigDecimal(fieldY));
	}

	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Sucks");
		super.paintComponent(g);
		g.drawImage(mapImage.getImage(), 0, 0, this);
		for (Token token : tokens.keySet()) {
			Coordinates coordinates = tokens.get(token);
			int x = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int y = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int height = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			int width = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			try {
				System.out.println(
					"add " + token.getName() + " - " + (FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields())
				);
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				g.drawImage(tokenImage, x, y, width, height, this);
				if (selectedToken == token) {
					g.setColor(Color.YELLOW);
					((Graphics2D) g).setStroke(new BasicStroke(3));
					((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g.drawArc(x, y, width, height, 0, 360);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public Token getTokenAt(int x, int y) {
		for (Token token : tokens.keySet()) {
			Coordinates coordinates = tokens.get(token);
			int tokenX = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int tokenY = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			try {
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				int w = tokenImage.getWidth(null);
				int h = tokenImage.getHeight(null);
				Rectangle bounds = new Rectangle(tokenX, tokenY, w, h);
				if (bounds.contains(x, y)) {
					return token;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}

	public void setSelectedToken(Token token) {
		this.selectedToken = token;
		repaint();
	}

	public void updateTokens(Map<Token, Coordinates> newTokens) {
		this.tokens = newTokens;
		repaint();
	}
}
