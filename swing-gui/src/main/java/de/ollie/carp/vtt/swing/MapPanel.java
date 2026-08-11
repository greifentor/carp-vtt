package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

	public static final int OFFSET_IN_PIXELS = GraphicsManager.OFFSET_IN_PIXELS;
	public static final int FIELD_SIZE_IN_PIXELS = GraphicsManager.FIELD_SIZE_IN_PIXELS;

	public interface Observer {
		void tokenHit(MapToken mapToken, Coordinates coordinates);
	}

	private GraphicsManager graphicsManager;
	private ImageIcon mapImage;
	private TokenMap tokens;

	@Getter
	private MapToken selectedToken;

	public MapPanel(ImageIcon mapImage, TokenMap tokens, Observer observer, GraphicsManager graphicsManager) {
		this.graphicsManager = graphicsManager;
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
		graphicsManager.paintBattleMapForScenarioAndParty(
			(Graphics2D) g,
			tokens,
			selectedToken,
			mapImage,
			getFocusCycleRootAncestor()
		);
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
