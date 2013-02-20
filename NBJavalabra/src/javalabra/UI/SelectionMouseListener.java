
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javalabra.domain.Hero;
import javax.swing.JButton;

/**
 * Käsittelee sankarinvalitsemisvaiheen hiirenklikkaukset
 */
public class SelectionMouseListener implements ActionListener {
    private JButton choose;
    private Hero hero;
    private GUI gui;
    private int player;
    
    public SelectionMouseListener(JButton choose, Hero hero, GUI gui, int player) {
        this.choose = choose;
        this.hero = hero;
        this.gui = gui;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == choose && player == 1) {
            gui.getBattle().setPlayer1(hero);
            gui.selectHero(2);
        }
        if (ae.getSource() == choose && player == 2) {
            gui.getBattle().setPlayer2(hero);
            gui.beginBattle();
        }
    }
}
