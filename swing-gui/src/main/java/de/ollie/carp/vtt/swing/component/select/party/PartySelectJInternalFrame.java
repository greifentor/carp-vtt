package de.ollie.carp.vtt.swing.component.select.party;

import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJInternalFrame;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJPanel;
import de.ollie.carp.vtt.swing.component.select.SelectionPanelObserver;
import java.util.UUID;
import javax.swing.JDesktopPane;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class PartySelectJInternalFrame extends AbstractSelectJInternalFrame<Party> implements SelectionPanelObserver {

	private static final String CLASS_NAME = "Party";

	private final PartyService partyService;
	private final UserConfiguration userConfiguration;

	public PartySelectJInternalFrame(
		PartyService partyService,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, CLASS_NAME, editDialogComponentFactory);
		this.partyService = partyService;
		this.userConfiguration = userConfiguration;
		finishConstruct();
	}

	@Override
	protected AbstractSelectJPanel<Party> createSelectPanel() {
		return new PartySelectJPanel(
			partyService,
			CLASS_NAME,
			desktopPane,
			editDialogComponentFactory,
			this,
			userConfiguration
		);
	}
}
