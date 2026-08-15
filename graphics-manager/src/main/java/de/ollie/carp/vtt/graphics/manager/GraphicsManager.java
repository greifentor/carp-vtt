package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import jakarta.inject.Named;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

@Named
public class GraphicsManager {

	public static final int OFFSET_IN_PIXELS = 12;
	public static final int FIELD_SIZE_IN_PIXELS = 50;

	public void paintBattleMapForScenarioAndParty(
		Graphics2D g,
		TokenMap tokens,
		MapToken selectedToken,
		ImageIcon mapImage,
		ImageObserver imageObserver
	) {
		g.drawImage(mapImage.getImage(), 0, 0, imageObserver);
		for (MapToken mapToken : tokens.keySet()) {
			TokenInfoProvider token = mapToken.token();
			CoordinatesInfoProvider coordinates = tokens.get(mapToken);
			int x = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int y = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int height = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			int width = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			try {
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				g.drawImage(tokenImage, x, y, width, height, imageObserver);
				if (selectedToken == mapToken) {
					g.setColor(Color.YELLOW);
					g.setStroke(new BasicStroke(3));
					g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g.drawArc(x, y, width, height, 0, 360);
				}
				if (tokens.hasTokenMoreThanOneTimes(token)) {
					g.setColor(Color.BLACK);
					g.setStroke(new BasicStroke(1));
					g.drawRect(x + 3, y + 3, 15, 12);
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(x + 3, y + 3, 15, 12);
					g.setColor(Color.RED);
					g.setFont(new Font("Serif", Font.BOLD, 12));
					g.drawString("" + mapToken.counter(), x + 4, y + 12);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
