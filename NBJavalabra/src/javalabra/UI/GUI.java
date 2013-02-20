
package javalabra.UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javalabra.domain.Ability;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 *  Graafinen käyttöliittymä, välittää tietoa käyttäjälle ja kysyy toiminnot
 */
public class GUI implements Runnable {
    private JFrame frame;
    private Battle battle;
    private ArrayList<Hero> heroes;
    private ArrayList<JButton> abilities;
    private JTextArea log;
    private JPanel middle;
    
    public GUI() {
        this.abilities = new ArrayList<JButton>();
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
    
    public void startGame(int player) {
        heroes = new ArrayList<Hero>();
        addHeroes();
        
        battle = new Battle(null,null, new TextUI());
        
        selectHero(1);
    }
    
    public void disableButton(Ability ability, JButton button, Hero hero) {
        if (ability.getPowerCost() > 0 && hero.getAtmPower() <= 0) {
            button.setEnabled(false);
        }
    }
    
    public void disableButtons() {
        for (JButton button : abilities) {
            button.setEnabled(false);
        }
    }
    
    public void endBattle() {
        int winner = -99;
        if (battle.checkIfEnds()) {
            winner = battle.checkWinner();
        }
        if (winner == 1 || winner == 2 || winner == 0) {
            congratulate(winner);
            disableButtons();
        }
    }
    
    public void writeToLog(String addition) {
        log.setText(log.getText() + addition + "\n");
    }
    
    public void congratulate(int winner) {
        if (winner == 1) {
            writeToLog("Congratulations player 1, you've won!");
        }
        if (winner == 2) {
            writeToLog("Congratulations player 2, you've won!");
        }
        if (winner == 0) {
            writeToLog("It's a tie!");
        }
    }
    
    public void beginBattle() {
        battle.setStartingHero();
        createGameUI();
        BattleMouseListener bml = new BattleMouseListener(this, abilities.get(0), abilities.get(1), abilities.get(2), 
                abilities.get(3), abilities.get(4), abilities.get(5), log);
        for (JButton button : abilities) {
            button.addActionListener(bml);
        }
        int turn = 0;
        if (battle.getTurn()) {
            turn = 1;
        } else {
            turn = 2;
        }
        String addition = "Player " + turn + "'s " + "turn";
        log.setText(log.getText() + addition + "\n");
    }
    
    public void chooseHero(Container container, int player) {
        JPanel selection = new JPanel();
        //GridLayout selectionLayout = new GridLayout(2,1);
        BorderLayout selectionLayout = new BorderLayout();
        selection.setLayout(selectionLayout);
        
        JPanel titlePanel = new JPanel();
        GridLayout titlePanelLayout = new GridLayout(1,3);
        
        JLabel title = new JLabel("Choose a hero, player "+player);
        
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(title);
        titlePanel.add(Box.createHorizontalGlue());
        
        JPanel heroSelection = createSelectionComponents(player);
        
        selection.add(titlePanel, BorderLayout.NORTH);
        selection.add(heroSelection);
        
        container.add(selection);
    }
    
    public void selectHero(int player) {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,400));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.repaint();
        
        chooseHero(frame.getContentPane(), player);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public JPanel createSelectionComponents(int player) {
        JPanel heroSelection = new JPanel();
        GridLayout frameLayout = new GridLayout(1,heroes.size());
        heroSelection.setLayout(frameLayout);
        
        for (Hero hero : heroes) {
            JPanel heroPanel = new JPanel();
            JLabel heroName = new JLabel(hero.getName());
            
            JPanel heroPicture = new JPanel();
            JButton choose = new JButton("Choose me");
            SelectionMouseListener sml = new SelectionMouseListener(choose, hero, this, player);
            choose.addActionListener(sml);
            
            GridLayout heroPanelLayout = new GridLayout(3,1);
            heroPanel.setLayout(heroPanelLayout);
            
            JPanel heroNamePanel = new JPanel();
            GridLayout heroNamePanelLayout = new GridLayout(1,3);
            
            heroNamePanel.add(Box.createHorizontalGlue());
            heroNamePanel.add(heroName);
            heroNamePanel.add(Box.createHorizontalGlue());
            
            heroPanel.add(heroNamePanel);
            heroPanel.add(heroPicture);
            heroPanel.add(choose);
            
            heroSelection.add(heroPanel);
        }
        return heroSelection;
    }
    
    public void createGameUI() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500,800));
        
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createGameComponents(frame.getContentPane());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    public void createGameComponents(Container container) {
        GridLayout frameLayout = new GridLayout(1,3);
        container.setLayout(frameLayout);
        
        JPanel left = new JPanel();
        GridLayout playerLayout = new GridLayout(2,1);
        left.setLayout(playerLayout);
        
        left.add(createPlayerPicturePanel(1));
        left.add(createPlayerGamePanel(1));
        
        JPanel mid = createMiddleGamePanel();
        
        JPanel right = new JPanel();
        right.setLayout(playerLayout);
        
        right.add(createPlayerPicturePanel(2));
        right.add(createPlayerGamePanel(2));
        
        container.add(left);
        this.middle = mid;
        container.add(mid);
        container.add(right);
    }
    
    public void createGameComponents(Container container, JPanel middle1) {
        GridLayout frameLayout = new GridLayout(1,3);
        container.setLayout(frameLayout);
        
        JPanel left = new JPanel();
        GridLayout playerLayout = new GridLayout(2,1);
        left.setLayout(playerLayout);
        
        left.add(createPlayerPicturePanel(1));
        left.add(createPlayerGamePanel(1));
        
        JPanel right = new JPanel();
        right.setLayout(playerLayout);
        
        right.add(createPlayerPicturePanel(2));
        right.add(createPlayerGamePanel(2));
        
        container.add(left);
        container.add(middle1);
        container.add(right);
    }
    
    public void refreshPlayerPanel(Container container) {
        abilities = new ArrayList<JButton>();
        createGameComponents(container, middle);
        BattleMouseListener bml = new BattleMouseListener(this, abilities.get(0), abilities.get(1), abilities.get(2), 
                abilities.get(3), abilities.get(4), abilities.get(5), log);
        for (JButton button : abilities) {
            button.addActionListener(bml);
        }
    }
    
    public JPanel createPlayerPicturePanel(int i) {
        return new JPanel();
    }
    
    public JPanel createPlayerGamePanel(int i) {
        JPanel panel = new JPanel();

        GridLayout panelLayout = new GridLayout(2,1);
        panel.setLayout(panelLayout);
        
        JPanel topleft = createPlayer(i);

        JPanel bottomleft = createPlayerSkills(i);

        
        panel.add(topleft);
        panel.add(bottomleft);
        return panel;
    }
    
    public JPanel createMiddleGamePanel() {
        JPanel logPanel = new JPanel();
        BoxLayout panelLayout = new BoxLayout(logPanel, BoxLayout.Y_AXIS);
        
        logPanel.setLayout(panelLayout);
        logPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JLabel title = new JLabel("Combat log");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextArea log1 = new JTextArea();
        log1.setEditable(false);
        this.log = log1;
        
        logPanel.add(title);
        logPanel.add(log1);
        return logPanel;
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
        
        if (i == 1) {
            JLabel playerName = new JLabel("Player " + i);
            playerName.setAlignmentX(Component.TOP_ALIGNMENT);

            JLabel playerHero = new JLabel("Hero: " + battle.getPlayer().getName());
            playerHero.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerHealth = new JLabel("Health: " + battle.getPlayer().getAtmHealth() + "/" + battle.getPlayer().getMaxHealth());
            playerHealth.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerPower = new JLabel("Power: " + battle.getPlayer().getAtmPower() + "/" + battle.getPlayer().getMaxPower());
            playerPower.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerSpeed = new JLabel("Speed: " + battle.getPlayer().getAtmSpeed());
            playerSpeed.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerDamage = new JLabel("Damage: " + battle.getPlayer().getAtmDamage());
            playerDamage.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerDR = new JLabel("Damage Reduction: " + (100 - (battle.getPlayer().getDamageTaken() * 100)) + "%");
            playerDR.setAlignmentX(Component.LEFT_ALIGNMENT);

            player.add(playerName);
            player.add(playerHero);
            player.add(playerHealth);
            player.add(playerPower);
            player.add(playerSpeed);
            player.add(playerDamage);
            player.add(playerDR);
        }
        
        if (i == 2) {
            JLabel playerName = new JLabel("Player " + i);
            playerName.setAlignmentX(Component.TOP_ALIGNMENT);

            JLabel playerHero = new JLabel("Hero: " + battle.getPlayer2().getName());
            playerHero.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerHealth = new JLabel("Health: " + battle.getPlayer2().getAtmHealth() + "/" + battle.getPlayer2().getMaxHealth());
            playerHealth.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerPower = new JLabel("Power: " + battle.getPlayer2().getAtmPower() + "/" + battle.getPlayer2().getMaxPower());
            playerPower.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerSpeed = new JLabel("Speed: " + battle.getPlayer2().getAtmSpeed());
            playerSpeed.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerDamage = new JLabel("Damage: " + battle.getPlayer2().getAtmDamage());
            playerDamage.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel playerDR = new JLabel("Damage Reduction: " + (100 - (battle.getPlayer2().getDamageTaken() * 100)) + "%");
            playerDR.setAlignmentX(Component.LEFT_ALIGNMENT);

            player.add(playerName);
            player.add(playerHero);
            player.add(playerHealth);
            player.add(playerPower);
            player.add(playerSpeed);
            player.add(playerDamage);
            player.add(playerDR);
        }
        
        return player;
    }
    
    public JPanel createPlayerSkills(int i) {
        JPanel panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        GridLayout panelLayout = new GridLayout(3,1);
        panel.setLayout(panelLayout);
        
        JLabel abilitiesLabel = new JLabel("Abilities: ");
        titlePanel.add(abilitiesLabel);
        
        if (i == 1) {
            battle.getPlayer().createAbilities();
            
            JButton ability1 = new JButton("1. " + battle.getPlayer().getAbilities().get(0).getName());
            ability1.setToolTipText(battle.getPlayer().getAbilities().get(0).getDescription());
            buttonPanel.add(ability1);
            abilities.add(ability1);
            disableButton(battle.getPlayer().getAbilities().get(0),ability1,battle.getPlayer());
            
            JButton ability2 = new JButton("2. " + battle.getPlayer().getAbilities().get(1).getName());
            ability2.setToolTipText(battle.getPlayer().getAbilities().get(1).getDescription());
            buttonPanel.add(ability2);
            abilities.add(ability2);
            disableButton(battle.getPlayer().getAbilities().get(1),ability2,battle.getPlayer());
            
            JButton ability3 = new JButton("3. " + battle.getPlayer().getAbilities().get(2).getName());
            ability3.setToolTipText(battle.getPlayer().getAbilities().get(2).getDescription());
            buttonPanel.add(ability3);
            abilities.add(ability3);
            disableButton(battle.getPlayer().getAbilities().get(2),ability3,battle.getPlayer());
        }
        if (i == 2) {
            battle.getPlayer2().createAbilities();
            
            JButton ability1 = new JButton("7. " + battle.getPlayer2().getAbilities().get(0).getName());
            ability1.setToolTipText(battle.getPlayer2().getAbilities().get(0).getDescription());
            buttonPanel.add(ability1);
            abilities.add(ability1);
            disableButton(battle.getPlayer2().getAbilities().get(0),ability1,battle.getPlayer2());
            
            JButton ability2 = new JButton("8. " + battle.getPlayer2().getAbilities().get(1).getName());
            ability2.setToolTipText(battle.getPlayer2().getAbilities().get(1).getDescription());
            buttonPanel.add(ability2);
            abilities.add(ability2);
            disableButton(battle.getPlayer2().getAbilities().get(1),ability2,battle.getPlayer2());
            
            JButton ability3 = new JButton("9. " + battle.getPlayer2().getAbilities().get(2).getName());
            ability3.setToolTipText(battle.getPlayer2().getAbilities().get(2).getDescription());
            buttonPanel.add(ability3);
            abilities.add(ability3);
            disableButton(battle.getPlayer2().getAbilities().get(2),ability3,battle.getPlayer2());
        }
        
        JPanel descriptions = new JPanel();
        
        panel.add(titlePanel);
        panel.add(buttonPanel);
        panel.add(descriptions);
        return panel;
    }
    
    public void createInstructionUI() {
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public Battle getBattle() {
        return battle;
    }
    
}
