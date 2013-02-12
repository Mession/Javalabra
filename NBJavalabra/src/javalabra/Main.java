/**
 * Käynnistää käyttöliittymän
 */
package javalabra;

import javalabra.UI.GUI;
import javalabra.UI.TextUI;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
//        TextUI ui = new TextUI();
//        ui.begin();
        GUI gui = new GUI();
        SwingUtilities.invokeLater(gui);
    }
}
