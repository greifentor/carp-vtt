package de.ollie.carp.vtt.swing.component.select.party;

import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.PartyImpl;
import de.ollie.carp.vtt.core.service.model.RuleSystem;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.edit.party.PartyEditJInternalFrame;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectJPanel;
import de.ollie.carp.vtt.swing.component.select.AbstractSelectionTableModel;
import de.ollie.carp.vtt.swing.component.select.SelectionPanelObserver;
import java.util.List;
import java.util.UUID;
import javax.swing.JDesktopPane;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public class PartySelectJPanel extends AbstractSelectJPanel<Party> implements SelectionPanelObserver {

	private final PartyService partyService;
	private final UserConfiguration userConfiguration;

	public PartySelectJPanel(
		PartyService partyService,
		String className,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		SelectionPanelObserver observer,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, className + "s", editDialogComponentFactory, observer);
		this.partyService = partyService;
		this.userConfiguration = userConfiguration;
		updateTableSelection();
	}

	@Override
	protected List<Party> getObjectsToSelect() {
		return partyService != null ? partyService.listPartys().stream().toList() : List.of();
	}

	@Override
	protected AbstractSelectionTableModel<Party> createSelectionModel() {
		return new AbstractSelectionTableModel<Party>(getObjectsToSelect(), "Name", "Rule System") {
			@Override
			protected Object getColumnValueFor(Party t, int columnIndex) {
				return switch (columnIndex) {
					case 0 -> t.getName();
					case 1 -> t.getRuleSystem();
					default -> null;
				};
			}
		};
	}

	@Override
	protected void createEditInternalFrame(Party selected) {
		new PartyEditJInternalFrame(selected, getEditDialogComponentFactory(), this, getDesktopPane(), userConfiguration);
	}

	@Override
	protected Party createNewObject() {
		Party model = new PartyImpl();
		model.setId(UUID.randomUUID());
		model.setName("");
		model.setOwner(null);
		model.setRuleSystem(null);
		return model;
	}

	@Override
	protected void delete(Party toDelete) {
		partyService.deleteAndPushDeleteParty(toDelete.getId());
	}

	@Override
	protected void save(Party toSave) {
		partyService.updateAndPushParty(toSave);
	}
}
