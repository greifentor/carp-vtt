package de.ollie.carp.vtt.swing.component;

import static de.ollie.baselib.util.Check.ensure;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CarpVttMenuBar extends JMenuBar {

	public enum MenuItemIdentifier {
		FILE_QUIT,
		BATTLE_MAP_OPEN,
	}

	public interface Observer {
		void menuItemSelected(MenuItemIdentifier selectedMenuItem);
	}

	private JMenu menuBattleMaps;
	private JMenu menuFile;
	private JMenuItem menuItemOpenBattleMap;
	private JMenuItem menuItemQuit;

	public CarpVttMenuBar(Observer observer) {
		super();
		ensure(observer != null, "observer cannot be null!");
		menuFile = new JMenu("Datei");
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.FILE_QUIT));
		menuFile.add(menuItemQuit);
		add(menuFile);
		menuBattleMaps = new JMenu("Battle Maps");
		menuItemOpenBattleMap = new JMenuItem("Open ...");
		menuItemOpenBattleMap.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.BATTLE_MAP_OPEN));
		menuBattleMaps.add(menuItemOpenBattleMap);
		add(menuBattleMaps);
	}
}
