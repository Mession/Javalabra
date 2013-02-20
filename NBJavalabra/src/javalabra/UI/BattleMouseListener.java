
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javalabra.domain.Ability;
import javalabra.domain.Hero;
import javalabra.logiikka.Battle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Käsittelee hiirenklikkaukset taistelunäkymässä
 */
public class BattleMouseListener implements ActionListener {
    private GUI gui;
    private JButton p1a1;
    private JButton p1a2;
    private JButton p1a3;
    private JButton p2a1;
    private JButton p2a2;
    private JButton p2a3;
    private JTextArea log;
    private Battle battle;
    
    public BattleMouseListener(GUI gui, JButton p1a1, JButton p1a2, JButton p1a3, JButton p2a1, JButton p2a2, JButton p2a3, JTextArea log) {
        this.gui = gui;
        this.p1a1 = p1a1;
        this.p1a2 = p1a2;
        this.p1a3 = p1a3;
        this.p2a1 = p2a1;
        this.p2a2 = p2a2;
        this.p2a3 = p2a3;
        this.log = log;
        this.battle = gui.getBattle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == p1a1 && battle.getTurn()) {
            Ability ability = battle.getPlayer().getAbilities().get(0);
            String addition = battle.getPlayer().getName() + " (player 1)" + " used " + ability.getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer(), battle.getPlayer2(), ability);
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(false);
            gui.endBattle();
        }
        if (ae.getSource() == p1a2 && battle.getTurn()) {
            String addition = battle.getPlayer().getName() + " (player 1)"  + " used " + battle.getPlayer().getAbilities().get(1).getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer(), battle.getPlayer2(), battle.getPlayer().getAbilities().get(1));
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(false);
            gui.endBattle();
        }
        if (ae.getSource() == p1a3 && battle.getTurn()) {
            String addition = battle.getPlayer().getName() + " (player 1)"  + " used " + battle.getPlayer().getAbilities().get(2).getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer(), battle.getPlayer2(), battle.getPlayer().getAbilities().get(2));
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(false);
            gui.endBattle();
        }
        if (ae.getSource() == p2a1 && !battle.getTurn()) {
            String addition = battle.getPlayer2().getName() + " (player 2)"  + " used " + battle.getPlayer2().getAbilities().get(0).getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer2(), battle.getPlayer(), battle.getPlayer2().getAbilities().get(0));
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(true);
            gui.endBattle();
        }
        if (ae.getSource() == p2a2 && !battle.getTurn()) {
            String addition = battle.getPlayer2().getName() + " (player 2)"  + " used " + battle.getPlayer2().getAbilities().get(1).getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer2(), battle.getPlayer(), battle.getPlayer2().getAbilities().get(1));
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(true);
            gui.endBattle();
        }
        if (ae.getSource() == p2a3 && !battle.getTurn()) {
            String addition = battle.getPlayer2().getName() + " (player 2)"  + " used " + battle.getPlayer2().getAbilities().get(2).getName();
            log.setText(log.getText() + addition + "\n");
            battle.turn(battle.getPlayer2(), battle.getPlayer(), battle.getPlayer2().getAbilities().get(2));
            gui.getFrame().getContentPane().removeAll();
            gui.refreshPlayerPanel(gui.getFrame().getContentPane());
            battle.setTurn(true);
            gui.endBattle();
        }
        
    }
    
}
