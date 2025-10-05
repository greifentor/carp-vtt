package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.swing.component.CarpVttMenuBar;
import de.ollie.carp.vtt.swing.component.CarpVttMenuBar.MenuItemIdentifier;
import de.ollie.carp.vtt.swing.component.SimplifiedInternalFrameListener;
import de.ollie.carp.vtt.swing.component.SimplifiedInternalFrameListener.EventType;
import de.ollie.carp.vtt.swing.component.SimplifiedWindowListener;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.event.InternalFrameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
public class ApplicationFrame
	extends JFrame
	implements CarpVttMenuBar.Observer, SimplifiedInternalFrameListener.Observer, SimplifiedWindowListener.Observer {

	private static final Logger LOG = LogManager.getLogger(ApplicationFrame.class);

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	private JMenuBar menuBar;
	private JDesktopPane desktopPane;

	public ApplicationFrame() {
		super("CARP VTT");
	}

	@PostConstruct
	void postConstruct() {
		addWindowListener(new SimplifiedWindowListener(this));
		setSize(new Dimension(800, 600));
		menuBar = new CarpVttMenuBar(this);
		setJMenuBar(menuBar);
		JLabel labelBackground = new JLabel(
			new ImageIcon("/home/ollie/Rollenspiel/DungeonsAndDragons/Material/5e/Cover-Art-Starterset-Transparent.png")
		);
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		labelBackground.setLayout(new BorderLayout());
		labelBackground.add(desktopPane, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		setContentPane(labelBackground);
		setVisible(true);
		LOG.info("post constructed");
	}

	public void showFrame() {
		//		try {
		//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		//			SwingUtilities.updateComponentTreeUI(this);
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		setVisible(true);
	}

	@Override
	public void menuItemSelected(MenuItemIdentifier selectedMenuItem) {
		if (selectedMenuItem == MenuItemIdentifier.FILE_QUIT) {
			System.exit(0);
		} else if (selectedMenuItem == MenuItemIdentifier.BATTLE_MAP_OPEN) {
			System.out.println("Save battle map menu item selected!");
		}
	}

	@Override
	public void internalFrameEvent(EventType eventType, InternalFrameEvent event) {
		if (eventType == EventType.CLOSED) {
			desktopPane.remove(event.getInternalFrame());
		}
	}

	@Override
	public void windowEvent(SimplifiedWindowListener.EventType eventType, WindowEvent e) {
		if (SimplifiedWindowListener.EventType.CLOSING == eventType) {
			dispose();
			System.exit(0);
		}
	}
}
