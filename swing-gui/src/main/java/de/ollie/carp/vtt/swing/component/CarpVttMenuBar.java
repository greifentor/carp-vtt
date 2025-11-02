package de.ollie.carp.vtt.swing.component;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CarpVttMenuBar extends JMenuBar {

	static final String RES_ID_MENU_EDIT = "MainMenuBar.menu.edit.label";
	static final String RES_ID_MENU_EDIT_ITEM_TOKEN = "MainMenuBar.menu.edit.item.tokens.label";
	static final String RES_ID_MENU_FILE = "MainMenuBar.menu.file.label";
	static final String RES_ID_MENU_FILE_ITEM_QUIT = "MainMenuBar.menu.file.item.quit.label";

	public enum MenuItemIdentifier {
		EDIT_TOKEN,
		FILE_QUIT,
	}

	public interface Observer {
		void menuItemSelected(MenuItemIdentifier selectedMenuItem);
	}

	private JMenu menuEdit;
	private JMenu menuFile;
	private JMenuItem menuItemEditTokens;
	private JMenuItem menuItemFileQuit;

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
		menuItemEditTokens = swingComponentFactory.createMenuItem(RES_ID_MENU_EDIT_ITEM_TOKEN);
		menuItemEditTokens.addActionListener(e -> observer.menuItemSelected(MenuItemIdentifier.EDIT_TOKEN));
		menuEdit.add(menuItemEditTokens);
		add(menuEdit);
	}
}
