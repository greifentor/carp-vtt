package de.ollie.carp.vtt.swing.component.edit.party;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.swing.UserConfiguration;
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
public class PartyEditJInternalFrame extends AbstractEditJInternalFrame<Party> {

	private final UserConfiguration userConfiguration;

	public PartyEditJInternalFrame(
		Party toEdit,
		EditDialogComponentFactory editDialogComponentFactory,
		Observer<Party> observer,
		JDesktopPane desktopPane,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, "Party", toEdit, editDialogComponentFactory, observer, Map.of());
		this.userConfiguration = userConfiguration;
		prepare();
	}

	@Override
	protected JPanel createEditorPanel(Party toEdit, Map<String, ItemProvider<?>> itemProviders) {
		editPanel = new PartyEditJPanel(toEdit, itemProviders, userConfiguration);
		return editPanel;
	}
}
