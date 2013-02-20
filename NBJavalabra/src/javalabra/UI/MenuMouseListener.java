
package javalabra.UI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Käsittelee hiirenklikkaukset menu:ssa
 */
public class MenuMouseListener implements ActionListener {
    private JButton startFight;
    private JButton instructions;
    private GUI gui;
    
    public MenuMouseListener(JButton startFight, JButton instructions, GUI gui) {
        this.startFight = startFight;
        this.instructions = instructions;
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startFight) {
            gui.startGame(1);
        }
        if (ae.getSource() == instructions) {
            gui.createInstructionUI();
        }
    }
    
}
