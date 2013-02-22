
package javalabra.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Käsittelee uusi peli -nappulan klikkaukset
 */
public class NewGameButtonMouseListener implements ActionListener {
    /**
     * Nappi, jota painamalla uusi peli alkaa
     */
    private JButton button;
    /**
     * Käyttöliittymä, jonka metodeita kutsutaan nappia painettua
     */
    private GUI gui;
    /**
     * Ikkuna, joka sisältää uusi peli -napin
     */
    private JFrame newGameFrame;
    
    /**
     * Konstruktori
     */
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
