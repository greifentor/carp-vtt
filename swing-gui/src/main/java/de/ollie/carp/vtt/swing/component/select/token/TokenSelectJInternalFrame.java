package de.ollie.carp.vtt.swing.component.select.token;

import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Token;
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
public class TokenSelectJInternalFrame extends AbstractSelectJInternalFrame<Token> implements SelectionPanelObserver {

	private static final String CLASS_NAME = "Token";

	private final TokenService tokenService;

	public TokenSelectJInternalFrame(
		TokenService tokenService,
		JDesktopPane desktopPane,
		EditDialogComponentFactory editDialogComponentFactory
	) {
		super(desktopPane, CLASS_NAME, editDialogComponentFactory);
		this.tokenService = tokenService;
		finishConstruct();
	}

	@Override
	protected AbstractSelectJPanel<Token> createSelectPanel() {
		return new TokenSelectJPanel(tokenService, CLASS_NAME, desktopPane, editDialogComponentFactory, this);
	}
}
