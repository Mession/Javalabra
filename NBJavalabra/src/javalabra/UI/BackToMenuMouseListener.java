
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Käsittelee "instructions" -näkymän "back" -nappulan toiminnot
 */
public class BackToMenuMouseListener implements ActionListener {
    /**
     * Back -nappi
     */
    private JButton button;
    /**
     * Käyttöliittymä, jonka metodeita kutsutaan napin painamisen jälkeen
     */
    private GUI gui;
    
    /**
     * Konstruktori
     */
    public BackToMenuMouseListener(JButton button, GUI gui) {
        this.button = button;
        this.gui = gui;
    }

    /**
     * Suorittaa toiminnon napin klikkauksen jälkeen
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button) {
            gui.getFrame().setVisible(false);
            gui.createMenu();
        }
    }
}
