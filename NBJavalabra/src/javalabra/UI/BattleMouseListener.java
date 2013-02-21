
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javalabra.domain.Ability;
import javalabra.domain.Hero;
import javalabra.logiikka.Battle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

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
    
    public void afterAction(boolean changeTurn) {
        gui.getFrame().getContentPane().removeAll();
        gui.refreshPlayerPanel(gui.getFrame().getContentPane());
        battle.setTurn(changeTurn);
        gui.endBattle();
    }
    
    public void announceAction(Hero hero, Ability ability, int player) {
        String announce = hero.getName() + " (player " + player + ") used " + ability.getName();
        gui.writeToLog(announce);
    }
    
    public void useAbility(int whichAbility, int whoseTurn) {
        Hero hero = battle.getPlayer();
        Hero enemy = battle.getPlayer2();
        if (whoseTurn == 2) {
            hero = battle.getPlayer2();
            enemy = battle.getPlayer();
        }
        
        Ability ability = hero.getAbilities().get(whichAbility);
        announceAction(hero, ability, whoseTurn);
        battle.turn(hero, enemy, ability);
        
        if (battle.getTurn()) {
            afterAction(false);
        } else {
            afterAction(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == p1a1 && battle.getTurn()) {
            useAbility(0,1);
        }
        if (ae.getSource() == p1a2 && battle.getTurn()) {
            useAbility(1,1);
        }
        if (ae.getSource() == p1a3 && battle.getTurn()) {
            useAbility(2,1);
        }
        if (ae.getSource() == p2a1 && !battle.getTurn()) {
            useAbility(0,2);
        }
        if (ae.getSource() == p2a2 && !battle.getTurn()) {
            useAbility(1,2);
        }
        if (ae.getSource() == p2a3 && !battle.getTurn()) {
            useAbility(2,2);
        }
        
    }
    
}
