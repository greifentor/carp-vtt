package de.ollie.carp.vtt.swing.component.edit.token;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.ItemProvider;
import de.ollie.carp.vtt.swing.component.edit.AbstractEditJInternalFrame;
import java.util.Map;
import java.util.UUID;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class TokenEditJInternalFrame extends AbstractEditJInternalFrame<Token> {

	public TokenEditJInternalFrame(
		Token toEdit,
		EditDialogComponentFactory editDialogComponentFactory,
		Observer<Token> observer,
		JDesktopPane desktopPane
	) {
		super(desktopPane, "Token", toEdit, editDialogComponentFactory, observer, Map.of());
		prepare();
	}

	@Override
	protected JPanel createEditorPanel(Token toEdit, Map<String, ItemProvider<?>> itemProviders) {
		editPanel = new TokenEditJPanel(toEdit, itemProviders);
		return editPanel;
	}
}
