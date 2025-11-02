package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import de.ollie.carp.vtt.swing.component.CarpVttMenuBar.MenuItemIdentifier;
import de.ollie.carp.vtt.swing.component.CarpVttMenuBar.Observer;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class CarpVttMenuBarTest {

	@Mock
	private Observer observer;

	@Mock
	private SwingComponentFactory swingComponentFactory;

	private CarpVttMenuBar unitUnderTest;

	@Nested
	class constructor_Observer {

		@Test
		void throwsAnException_passingANullValue_asObserver() {
			assertThrows(IllegalArgumentException.class, () -> new CarpVttMenuBar(null, swingComponentFactory));
		}

		@Test
		void throwsAnException_passingANullValue_asSwingComponentFactory() {
			assertThrows(IllegalArgumentException.class, () -> new CarpVttMenuBar(observer, null));
		}
	}

	@Nested
	class MenuClicks {

		private static final String LABEL = "label";

		@BeforeEach
		void beforeEach() {
			when(swingComponentFactory.createMenu(CarpVttMenuBar.RES_ID_MENU_EDIT)).thenReturn(new JMenu(LABEL));
			when(swingComponentFactory.createMenu(CarpVttMenuBar.RES_ID_MENU_FILE)).thenReturn(new JMenu(LABEL));
			when(swingComponentFactory.createMenuItem(CarpVttMenuBar.RES_ID_MENU_EDIT_ITEM_TOKEN))
				.thenReturn(new JMenuItem(LABEL));
			when(swingComponentFactory.createMenuItem(CarpVttMenuBar.RES_ID_MENU_FILE_ITEM_QUIT))
				.thenReturn(new JMenuItem(LABEL));
			unitUnderTest = new CarpVttMenuBar(observer, swingComponentFactory);
		}

		@Test
		void obsererReturnsCorrectIdentifier_whenEditTokensMenuItemClicked() {
			// Prepare
			JMenuItem menuItem = (JMenuItem) ReflectionTestUtils.getField(unitUnderTest, "menuItemEditTokens");
			// Run
			menuItem.doClick();
			// Check
			verify(observer, times(1)).menuItemSelected(MenuItemIdentifier.EDIT_TOKEN);
		}

		@Test
		void obsererReturnsCorrectIdentifier_whenFileQuitMenuItemClicked() {
			// Prepare
			JMenuItem menuItem = (JMenuItem) ReflectionTestUtils.getField(unitUnderTest, "menuItemFileQuit");
			// Run
			menuItem.doClick();
			// Check
			verify(observer, times(1)).menuItemSelected(MenuItemIdentifier.FILE_QUIT);
		}
	}
}
