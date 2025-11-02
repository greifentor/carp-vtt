package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.swing.component.CarpVttMenuBar;
import de.ollie.carp.vtt.swing.component.CarpVttMenuBar.MenuItemIdentifier;
import de.ollie.carp.vtt.swing.component.SimplifiedInternalFrameListener;
import de.ollie.carp.vtt.swing.component.SimplifiedInternalFrameListener.EventType;
import de.ollie.carp.vtt.swing.component.SimplifiedWindowListener;
import de.ollie.carp.vtt.swing.localization.ResourceManager;
import de.ollie.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
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

	@Inject
	private BinaryFileAccessPort binaryFileAccessPort;

	@Inject
	private ResourceManager resourceManager;

	@Inject
	private SwingComponentFactory swingComponentFactory;

	private JMenuBar menuBar;
	private JDesktopPane desktopPane;

	public ApplicationFrame() {
		super();
	}

	@PostConstruct
	void postConstruct() {
		setTitle(resourceManager.getResource("ApplicationFrame.title"));
		// OLI: NimbusLookAndFeel does not work with background images.
		addWindowListener(new SimplifiedWindowListener(this));
		setSize(new Dimension(800, 600));
		menuBar = new CarpVttMenuBar(this, swingComponentFactory);
		setJMenuBar(menuBar);
		JLabel labelBackground = new JLabel(
			new ImageIcon("/home/ollie/Rollenspiel/DungeonsAndDragons/Material/5e/Cover-Art-Starterset-Transparent-Large.png")
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
		setVisible(true);
	}

	@Override
	public void menuItemSelected(MenuItemIdentifier selectedMenuItem) {
		if (selectedMenuItem == MenuItemIdentifier.FILE_QUIT) {
			System.exit(0);
		} else if (selectedMenuItem == MenuItemIdentifier.EDIT_TOKEN) {
			new TokenEditJInternalFrame(binaryFileAccessPort, swingComponentFactory, desktopPane).prepare().setVisible(true);
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
