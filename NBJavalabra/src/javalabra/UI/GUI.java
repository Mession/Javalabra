
package javalabra.UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javalabra.domain.Banker;
import javalabra.domain.Hero;
import javalabra.domain.Santa;
import javalabra.domain.Snowman;
import javalabra.logiikka.Battle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class GUI implements Runnable {
    private JFrame frame;
    private Battle battle;
    private ArrayList<Hero> heroes;
    
    public GUI() {
        
    }
    
    @Override
    public void run() {
        createMenu();
    }
    
    
    
    private void createMenu() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(150,300));
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createMenuComponents(frame.getContentPane());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void createMenuComponents(Container container) {
        BorderLayout frameLayout = new BorderLayout();
        container.setLayout(frameLayout);
        JPanel menuPanel = new JPanel();
        BoxLayout menuPanelLayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
        menuPanel.setLayout(menuPanelLayout);
        
        menuPanel.add(Box.createRigidArea(new Dimension(25,25)));
        
        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(menuLabel);
        
        menuPanel.add(Box.createRigidArea(new Dimension(25,25)));
        
        JButton startFight = new JButton("Start");
        JButton instructions = new JButton("Instructions");
        MenuMouseListener menuml = new MenuMouseListener(startFight,instructions,this);
        startFight.addActionListener(menuml);
        instructions.addActionListener(menuml);
        startFight.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(startFight);
        menuPanel.add(instructions);
        
        container.add(menuPanel, BorderLayout.CENTER);
    }
    
    public void createGameUI() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800,600));
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        heroes = new ArrayList<Hero>();
        addHeroes();
        
        createGameComponents(frame.getContentPane());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void createGameComponents(Container container) {
        //Layout frameLayout = new BoxLayout(container, BoxLayout.X_AXIS);
        GridLayout frameLayout = new GridLayout(1,3);
        container.setLayout(frameLayout);
        
        JPanel left = createPlayerGamePanel(1);
        //left.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel mid = createMiddleGamePanel();
        //mid.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel right = createPlayerGamePanel(2);
        //right.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        //JPanel filler1 = new JPanel();
        //filler1.setBorder(BorderFactory.createEmptyBorder());
        
        //JPanel filler2 = new JPanel();
        //filler1.setBorder(BorderFactory.createEmptyBorder());
        
        container.add(left);
        //container.add(filler1);
        container.add(mid);
        //container.add(filler2);
        container.add(right);
    }
    
    public JPanel createPlayerGamePanel(int i) {
        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(panelLayout);
        
        JPanel topleft = createPlayer(i);
        topleft.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel abilities = new JLabel("Abilities: ");
        abilities.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel bottomleft = createPlayerSkills(i);
        bottomleft.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(topleft);
        panel.add(abilities);
        panel.add(bottomleft);
        return panel;
    }
    
    public JPanel createMiddleGamePanel() {
        JPanel log = new JPanel();
        BoxLayout panelLayout = new BoxLayout(log, BoxLayout.Y_AXIS);
        log.setLayout(panelLayout);
        log.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JLabel title = new JLabel("Combat log");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel filler = new JLabel();
        log.add(title);
        log.add(filler);
        return log;
    }
    
    public void addHeroes() {
        heroes.add(new Santa());
        heroes.add(new Snowman());
        heroes.add(new Banker());
    }
    
    public JPanel createPlayer(int i) {
        JPanel player = new JPanel();
        BoxLayout panelLayout = new BoxLayout(player, BoxLayout.Y_AXIS);
        player.setLayout(panelLayout);
        player.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JLabel playerName = new JLabel("Player " + i);
        playerName.setAlignmentX(Component.TOP_ALIGNMENT);
        JLabel playerHero = new JLabel("Hero: ");
        playerHero.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel playerHealth = new JLabel("Health: ");
        playerHealth.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel playerPower = new JLabel("Power: ");
        playerPower.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel playerSpeed = new JLabel("Speed: ");
        playerSpeed.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel playerDamage = new JLabel("Damage: ");
        playerDamage.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel playerDR = new JLabel("Damage Reduction: ");
        playerDR.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        player.add(playerName);
        player.add(playerHero);
        player.add(playerHealth);
        player.add(playerPower);
        player.add(playerSpeed);
        player.add(playerDamage);
        player.add(playerDR);
        return player;
    }
    
    public JPanel createPlayerSkills(int i) {
        JPanel panel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(panelLayout);
        
        
        if (i == 1) {
            JButton ability1 = new JButton("1. ");
            panel.add(ability1);
            JButton ability2 = new JButton("2. ");
            panel.add(ability2);
            JButton ability3 = new JButton("3. ");
            panel.add(ability3);
        }
        if (i == 2) {
            JButton ability1 = new JButton("8. ");
            panel.add(ability1);
            JButton ability2 = new JButton("9. ");
            panel.add(ability2);
            JButton ability3 = new JButton("0. ");
            panel.add(ability3);
        }
        return panel;
    }
    
    public void createInstructionUI() {
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
}
