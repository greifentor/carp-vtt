package de.ollie.carp.vtt.swing.component.select.token;

import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenImpl;
import de.ollie.carp.vtt.swing.UserConfiguration;
import de.ollie.carp.vtt.swing.component.EditDialogComponentFactory;
import de.ollie.carp.vtt.swing.component.edit.token.TokenEditJInternalFrame;
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
public class TokenSelectJPanel extends AbstractSelectJPanel<Token> implements SelectionPanelObserver {

	private final TokenService tokenService;
	private final UserConfiguration userConfiguration;

	public TokenSelectJPanel(
		TokenService tokenService,
		String className,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory,
		SelectionPanelObserver observer,
		UserConfiguration userConfiguration
	) {
		super(desktopPane, className + "s", editDialogComponentFactory, observer);
		this.tokenService = tokenService;
		this.userConfiguration = userConfiguration;
		updateTableSelection();
	}

	@Override
	protected List<Token> getObjectsToSelect() {
		return tokenService != null ? tokenService.listTokens().stream().toList() : List.of();
	}

	@Override
	protected AbstractSelectionTableModel<Token> createSelectionModel() {
		return new AbstractSelectionTableModel<Token>(getObjectsToSelect(), "Name", "Image", "Token Size") {
			@Override
			protected Object getColumnValueFor(Token t, int columnIndex) {
				return switch (columnIndex) {
					case 0 -> t.getName();
					case 1 -> t.getImage();
					case 2 -> t.getTokenSize();
					default -> null;
				};
			}
		};
	}

	@Override
	protected void createEditInternalFrame(Token selected) {
		new TokenEditJInternalFrame(selected, getEditDialogComponentFactory(), this, getDesktopPane());
	}

	@Override
	protected Token createNewObject() {
		Token model = new TokenImpl();
		model.setId(UUID.randomUUID());
		model.setImage(null);
		model.setName("");
		model.setOwner(userConfiguration.getUserId() != null ? userConfiguration.getUserId() : null);
		model.setTokenSize(null);
		return model;
	}

	@Override
	protected void delete(Token toDelete) {
		tokenService.deleteAndPushDeleteToken(toDelete.getId());
	}

	@Override
	protected void save(Token toSave) {
		tokenService.updateAndPushToken(toSave);
	}
}
