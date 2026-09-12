package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Getter;

public class MapPanel extends JPanel {

	public static final int OFFSET_IN_PIXELS = GraphicsManager.OFFSET_IN_PIXELS;
	public static final int FIELD_SIZE_IN_PIXELS = GraphicsManager.FIELD_SIZE_IN_PIXELS;

	public interface Observer {
		void tokenHit(MapToken mapToken, Coordinates coordinates);
	}

	private CoordinatesToFieldConverter coordinatesToFieldConverter;
	private GraphicsManager graphicsManager;
	private ImageIcon mapImage;
	private TokenMap tokenMap;

	@Getter
	private MapToken selectedToken;

	public MapPanel(
		ImageIcon mapImage,
		TokenMap tokens,
		Observer observer,
		GraphicsManager graphicsManager,
		TokenHitManager tokenHitManager,
		CoordinatesToFieldConverter coordinatesToFieldConverter
	) {
		this.coordinatesToFieldConverter = coordinatesToFieldConverter;
		this.graphicsManager = graphicsManager;
		this.mapImage = mapImage;
		this.tokenMap = tokens;
		setPreferredSize(new Dimension(mapImage.getIconWidth(), mapImage.getIconHeight()));
		// Hit-Detection aktivieren
		addMouseListener(
			new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MapToken mt = tokenHitManager.getTokenAt(tokenMap, e.getX(), e.getY());
					if (observer != null) {
						observer.tokenHit(mt, coordinatesToFieldConverter.getFieldCoordinates(e.getX(), e.getY()));
					}
				}
			}
		);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphicsManager.paintBattleMapForScenarioAndParty(
			(Graphics2D) g,
			tokenMap,
			selectedToken,
			mapImage,
			getFocusCycleRootAncestor(),
			this::isSelectedTokenSelected
		);
	}

	private boolean isSelectedTokenSelected(MapToken mapToken, MapToken selectedToken) {
		return mapToken.getId().equals(selectedToken != null ? selectedToken.getId() : null);
	}

	public void setSelectedToken(MapToken mapToken) {
		this.selectedToken = mapToken;
		repaint();
	}

	public void updateTokens(TokenMap newTokens) {
		this.tokenMap = newTokens;
		repaint();
	}
}
