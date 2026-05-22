package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.swing.TokenMap.MapToken;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Getter;

public class MapPanel extends JPanel {

	public interface Observer {
		void tokenHit(MapToken mapToken, Coordinates coordinates);
	}

	private static final int OFFSET_IN_PIXELS = 12;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	private ImageIcon mapImage;
	private TokenMap tokens;

	@Getter
	private MapToken selectedToken;

	public MapPanel(ImageIcon mapImage, TokenMap tokens, Observer observer) {
		this.mapImage = mapImage;
		this.tokens = tokens;
		setPreferredSize(new Dimension(mapImage.getIconWidth(), mapImage.getIconHeight()));
		// Hit-Detection aktivieren
		addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MapToken mt = getTokenAt(e.getX(), e.getY());
					if (mt != null) {
						System.out.println("Token hit: " + mt.token().getName() + " - " + mt.counter());
					}
					if (observer != null) {
						observer.tokenHit(mt, getFieldCoordinates(e.getX(), e.getY()));
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
		super.paintComponent(g);
		g.drawImage(mapImage.getImage(), 0, 0, this);
		for (MapToken mapToken : tokens.keySet()) {
			Token token = mapToken.token();
			Coordinates coordinates = tokens.get(mapToken);
			int x = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int y = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int height = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			int width = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			try {
				System.out.println(
					"add " +
					token.getName() +
					" (" +
					mapToken.counter() +
					")" +
					" - " +
					(FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields())
				);
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				g.drawImage(tokenImage, x, y, width, height, this);
				if (selectedToken == mapToken) {
					g.setColor(Color.YELLOW);
					((Graphics2D) g).setStroke(new BasicStroke(3));
					((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g.drawArc(x, y, width, height, 0, 360);
				}
				if (tokens.hasTokenMoreThanOneTimes(token)) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x + 3, y + 3, 15, 12);
					g.setColor(Color.RED);
					g.drawString("" + mapToken.counter(), x + 4, y + 8);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public MapToken getTokenAt(int x, int y) {
		for (MapToken mapToken : tokens.keySet()) {
			Coordinates coordinates = tokens.get(mapToken);
			Token token = mapToken.token();
			int tokenX = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int tokenY = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			try {
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				int w = tokenImage.getWidth(null);
				int h = tokenImage.getHeight(null);
				Rectangle bounds = new Rectangle(tokenX, tokenY, w, h);
				if (bounds.contains(x, y)) {
					return mapToken;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}

	public void setSelectedToken(MapToken mapToken) {
		this.selectedToken = mapToken;
		repaint();
	}

	public void updateTokens(TokenMap newTokens) {
		this.tokens = newTokens;
		repaint();
	}
}
