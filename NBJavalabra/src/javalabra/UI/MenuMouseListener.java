
package javalabra.UI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuMouseListener implements ActionListener {
    private JButton startFight;
    private JButton instructions;
    private GUI gui;
    private Container container;
    
    public MenuMouseListener(JButton startFight, JButton instructions, GUI gui) {
        this.startFight = startFight;
        this.instructions = instructions;
        this.gui = gui;
        this.container = gui.getFrame().getContentPane();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == startFight) {
            gui.createGameUI();
        }
        if (ae.getSource() == instructions) {
            gui.createInstructionUI();
        }
    }
    
}
