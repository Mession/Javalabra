package javalabra;

import javalabra.UI.GUI;
import javax.swing.SwingUtilities;

/**
 * Käynnistää käyttöliittymän
 */
public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        SwingUtilities.invokeLater(gui);
    }
}
