package de.ollie.carp.vtt.swing.component;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CarpVttMenuBar extends JMenuBar {

	static final String RES_ID_MENU_EDIT = "MainMenuBar.menu.edit.label";
	static final String RES_ID_MENU_EDIT_ITEM_BATTLE_MAP = "MainMenuBar.menu.edit.item.battleMaps.label";
	static final String RES_ID_MENU_EDIT_ITEM_TOKEN = "MainMenuBar.menu.edit.item.tokens.label";
	static final String RES_ID_MENU_FILE = "MainMenuBar.menu.file.label";
	static final String RES_ID_MENU_FILE_ITEM_QUIT = "MainMenuBar.menu.file.item.quit.label";
	static final String RES_ID_MENU_MAP = "MainMenuBar.menu.map.label";
	static final String RES_ID_MENU_MAP_OPEN = "MainMenuBar.menu.map.open.label";
	static final String RES_ID_MENU_SYNC = "MainMenuBar.menu.sync.label";
	static final String RES_ID_MENU_SYNC_BATTLE_MAPS = "MainMenuBar.menu.sync.battlemaps.label";
	static final String RES_ID_MENU_SYNC_TOKENS = "MainMenuBar.menu.sync.tokens.label";

	public enum MenuItemIdentifier {
		EDIT_BATTLE_MAP,
		EDIT_TOKEN,
		FILE_QUIT,
		MAP_OPEN,
		SYNC_BATTLE_MAPS,
		SYNC_TOKENS,
	}

	public interface Observer {
		void menuItemSelected(MenuItemIdentifier selectedMenuItem);
	}

	private JMenu menuEdit;
	private JMenu menuFile;
	private JMenu menuMap;
	private JMenu menuSync;
	private JMenuItem menuItemEditBattleMaps;
	private JMenuItem menuItemEditTokens;
	private JMenuItem menuItemFileQuit;
	private JMenuItem menuItemMapOpen;
	private JMenuItem menuItemSyncBattleMaps;
	private JMenuItem menuItemSyncTokens;

	public CarpVttMenuBar(Observer observer, SwingComponentFactory swingComponentFactory) {
		super();
		ensure(observer != null, "observer cannot be null!");
		ensure(swingComponentFactory != null, "swing component factory cannot be null!");
		menuFile = swingComponentFactory.createMenu(RES_ID_MENU_FILE);
		menuItemFileQuit = swingComponentFactory.createMenuItem(RES_ID_MENU_FILE_ITEM_QUIT);
		menuItemFileQuit.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.FILE_QUIT));
		menuFile.add(menuItemFileQuit);
		add(menuFile);
		menuEdit = swingComponentFactory.createMenu(RES_ID_MENU_EDIT);
		menuItemEditBattleMaps = swingComponentFactory.createMenuItem(RES_ID_MENU_EDIT_ITEM_BATTLE_MAP);
		menuItemEditBattleMaps.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.EDIT_BATTLE_MAP));
		menuEdit.add(menuItemEditBattleMaps);
		menuItemEditTokens = swingComponentFactory.createMenuItem(RES_ID_MENU_EDIT_ITEM_TOKEN);
		menuItemEditTokens.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.EDIT_TOKEN));
		menuEdit.add(menuItemEditTokens);
		add(menuEdit);
		menuMap = swingComponentFactory.createMenu(RES_ID_MENU_MAP);
		menuItemMapOpen = swingComponentFactory.createMenuItem(RES_ID_MENU_MAP_OPEN);
		menuItemMapOpen.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.MAP_OPEN));
		menuMap.add(menuItemMapOpen);
		add(menuMap);
		menuSync = swingComponentFactory.createMenu(RES_ID_MENU_SYNC);
		menuItemSyncBattleMaps = swingComponentFactory.createMenuItem(RES_ID_MENU_SYNC_BATTLE_MAPS);
		menuItemSyncBattleMaps.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.SYNC_BATTLE_MAPS));
		menuSync.add(menuItemSyncBattleMaps);
		menuItemSyncTokens = swingComponentFactory.createMenuItem(RES_ID_MENU_SYNC_TOKENS);
		menuItemSyncTokens.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.SYNC_TOKENS));
		menuSync.add(menuItemSyncTokens);
		add(menuSync);
	}
}
