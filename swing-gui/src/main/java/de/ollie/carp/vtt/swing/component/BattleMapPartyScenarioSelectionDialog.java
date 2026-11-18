package de.ollie.carp.vtt.swing.component;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.BattleMapPartyScenarioService;
import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.swing.BattleMapListCellRenderer;
import de.ollie.carp.vtt.swing.PartyListCellRenderer;
import de.ollie.carp.vtt.swing.ScenarioListCellRenderer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class BattleMapPartyScenarioSelectionDialog extends JDialog implements ActionListener {

	private final BattleMapService battleMapService;
	private final BattleMapPartyScenarioService battleMapPartyScenarioService;
	private final PartyService partyService;
	private final ScenarioService scenarioService;

	private JButton buttonCancel;
	private JComboBox<BattleMap> comboBoxBattleMaps;
	private JComboBox<Party> comboBoxParties;
	private JComboBox<Scenario> comboBoxScenarios;
	private JTable tableBattleMapPartyScenario;

	public BattleMapPartyScenarioSelectionDialog(
		Frame owner,
		BattleMapService battleMapService,
		BattleMapPartyScenarioService battleMapPartyScenarioService,
		PartyService partyService,
		ScenarioService scenarioService
	) {
		super(owner, "Select BattleMap for Party & Scenario", true);
		this.battleMapPartyScenarioService = battleMapPartyScenarioService;
		this.battleMapService = battleMapService;
		this.partyService = partyService;
		this.scenarioService = scenarioService;
		setContentPane(createMainPanel());
		pack();
		setVisible(true);
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		p.setBorder(new EmptyBorder(VGAP, HGAP, VGAP, HGAP));
		p.add(createSelectionPanel(), BorderLayout.NORTH);
		p.add(createTableBattleMapPartyScenario());
		p.add(createButtonPanel(), BorderLayout.SOUTH);
		return p;
	}

	private JPanel createSelectionPanel() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		p.add(createSelectionLabelPanel(), BorderLayout.WEST);
		p.add(createSelectionComboBoxPanel(), BorderLayout.CENTER);
		return p;
	}

	private JPanel createSelectionLabelPanel() {
		JPanel p = new JPanel(new GridLayout(3, 1, HGAP, VGAP));
		p.add(new JLabel("Party:"));
		p.add(new JLabel("Scenario:"));
		p.add(new JLabel("Battle Map:"));
		return p;
	}

	private JPanel createSelectionComboBoxPanel() {
		JPanel p = new JPanel(new GridLayout(3, 1, HGAP, VGAP));
		p.add(createPartyComboBox());
		p.add(createScenarioComboBox());
		p.add(createBattleMapComboBox());
		return p;
	}

	private JComboBox<Party> createPartyComboBox() {
		JComboBox<Party> cb = new JComboBox<>();
		List<Party> parties = partyService.listPartys();
		cb.setModel(new DefaultComboBoxModel<Party>(parties.toArray(new Party[parties.size()])));
		cb.setRenderer(new PartyListCellRenderer());
		return cb;
	}

	private JComboBox<Scenario> createScenarioComboBox() {
		JComboBox<Scenario> cb = new JComboBox<>();
		List<Scenario> scenarios = scenarioService.listScenarios();
		cb.setModel(new DefaultComboBoxModel<Scenario>(scenarios.toArray(new Scenario[scenarios.size()])));
		cb.setRenderer(new ScenarioListCellRenderer());
		return cb;
	}

	private JComboBox<BattleMap> createBattleMapComboBox() {
		JComboBox<BattleMap> cb = new JComboBox<>();
		List<BattleMap> battleMaps = battleMapService.findAll();
		cb.setModel(new DefaultComboBoxModel<BattleMap>(battleMaps.toArray(new BattleMap[battleMaps.size()])));
		cb.setRenderer(new BattleMapListCellRenderer());
		return cb;
	}

	private JScrollPane createTableBattleMapPartyScenario() {
		tableBattleMapPartyScenario = new JTable();
		tableBattleMapPartyScenario.setModel(new BattleMapPartyScenarioTableModel(battleMapPartyScenarioService.findAll()));
		return new JScrollPane(tableBattleMapPartyScenario);
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, HGAP, VGAP));
		buttonCancel = createCancelButton();
		p.add(buttonCancel);
		return p;
	}

	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");
		b.addActionListener(this);
		return b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonCancel) {
			cancel();
		}
	}

	private void cancel() {
		setVisible(false);
		dispose();
	}
}
