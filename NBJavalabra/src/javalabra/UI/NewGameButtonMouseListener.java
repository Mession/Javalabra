
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class NewGameButtonMouseListener implements ActionListener {
    private JButton button;
    private GUI gui;
    private JFrame newGameFrame;
    
    public NewGameButtonMouseListener(JButton button, GUI gui, JFrame newGameFrame) {
        this.button = button;
        this.gui = gui;
        this.newGameFrame = newGameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button) {
            gui.getFrame().setVisible(false);
            newGameFrame.setVisible(false);
            gui.run();
        }
    }
}
