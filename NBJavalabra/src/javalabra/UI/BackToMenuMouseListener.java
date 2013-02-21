
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BackToMenuMouseListener implements ActionListener {
    private JButton button;
    private GUI gui;
    
    public BackToMenuMouseListener(JButton button, GUI gui) {
        this.button = button;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button) {
            gui.getFrame().setVisible(false);
            gui.createMenu();
        }
    }
}
