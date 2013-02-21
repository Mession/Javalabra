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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Graafinen käyttöliittymä, välittää tietoa käyttäjälle ja kysyy toiminnot
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

    public void createInstructionUI() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(500, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createInstructionComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createInstructionComponents(Container container) {
        BorderLayout frameLayout = new BorderLayout();
        container.setLayout(frameLayout);

        container.add(instructions(), BorderLayout.CENTER);

        JButton back = new JButton("Back to menu");
        BackToMenuMouseListener btmml = new BackToMenuMouseListener(back, this);
        back.addActionListener(btmml);

        container.add(back, BorderLayout.SOUTH);
    }

    public JPanel instructions() {
        JPanel instructionPanel = new JPanel();
        BorderLayout instructionLayout = new BorderLayout();
        instructionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        instructionPanel.setLayout(instructionLayout);

        JLabel title = new JLabel("Instructions:");
        JTextArea instructions = new JTextArea("Your goal is to defeat the enemy hero.\n\nStarting a new game:\nPlayer 1 picks a hero.\n"
                + "Player 2 picks a hero.\nThe player with the fastest hero has the first turn.\nYou can use one ability each turn.\n\n"
                + "What does x ability do?:\nYou can see the description of the ability in the mouseover tooltip.");
        instructions.setEditable(false);

        instructionPanel.add(title, BorderLayout.NORTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);
        return instructionPanel;
    }

    public void newGame() {
        JFrame newGameFrame = new JFrame();
        newGameFrame.setPreferredSize(new Dimension(150, 100));

        newGameFrame.setResizable(false);
        newGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton newGame = new JButton("New game?");
        NewGameButtonMouseListener ngbml = new NewGameButtonMouseListener(newGame, this, newGameFrame);
        newGame.addActionListener(ngbml);

        newGameFrame.getContentPane().add(newGame);

        newGameFrame.pack();
        newGameFrame.setLocationRelativeTo(null);
        newGameFrame.setVisible(true);
    }

    public void createMenu() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(150, 300));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenuComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createMenuComponents(Container container) {
        BorderLayout frameLayout = new BorderLayout();
        container.setLayout(frameLayout);


        JPanel menuPanel = new JPanel();
        BoxLayout menuPanelLayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
        menuPanel.setLayout(menuPanelLayout);


        menuPanel.add(Box.createRigidArea(new Dimension(25, 25)));


        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(menuLabel);


        menuPanel.add(Box.createRigidArea(new Dimension(25, 25)));


        JButton startFight = new JButton("Start");
        JButton instructions = new JButton("Instructions");
        MenuMouseListener menuml = new MenuMouseListener(startFight, instructions, this);


        startFight.addActionListener(menuml);
        instructions.addActionListener(menuml);


        startFight.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);


        menuPanel.add(startFight);
        menuPanel.add(instructions);


        container.add(menuPanel, BorderLayout.CENTER);
    }

    public void startGame(int player) {
        battle = new Battle(null, null, this);

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
            newGame();
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

        writeToLog("Player " + turn + "'s turn");
    }

    public void chooseHero(Container container, int player) {
        JPanel selection = new JPanel();
        BorderLayout selectionLayout = new BorderLayout();
        selection.setLayout(selectionLayout);


        JPanel titlePanel = new JPanel();
        GridLayout titlePanelLayout = new GridLayout(1, 3);


        JLabel title = new JLabel("Choose a hero, player " + player);


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
        frame.setPreferredSize(new Dimension(600, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.repaint();

        heroes = new ArrayList<Hero>();
        addHeroes();

        chooseHero(frame.getContentPane(), player);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel createHeroSelectionPanel(Hero hero, int player) {
        JPanel heroPanel = new JPanel();
        JLabel heroName = new JLabel(hero.getName());

        JPanel heroPicture = new JPanel();
        JButton choose = new JButton("Choose me");
        SelectionMouseListener sml = new SelectionMouseListener(choose, hero, this, player);
        choose.addActionListener(sml);

        GridLayout heroPanelLayout = new GridLayout(3, 1);
        heroPanel.setLayout(heroPanelLayout);

        JPanel heroNamePanel = new JPanel();
        GridLayout heroNamePanelLayout = new GridLayout(1, 3);

        heroNamePanel.add(Box.createHorizontalGlue());
        heroNamePanel.add(heroName);
        heroNamePanel.add(Box.createHorizontalGlue());

        heroPanel.add(heroNamePanel);
        heroPanel.add(heroPicture);
        heroPanel.add(choose);
        
        return heroPanel;
    }

    public JPanel createSelectionComponents(int player) {
        JPanel heroSelection = new JPanel();
        GridLayout frameLayout = new GridLayout(1, heroes.size());
        heroSelection.setLayout(frameLayout);

        for (Hero hero : heroes) {
            JPanel heroPanel = createHeroSelectionPanel(hero, player);
            heroSelection.add(heroPanel);
        }
        
        return heroSelection;
    }

    public void createGameUI() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(1500, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createGameComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createGameComponents(Container container) {
        GridLayout frameLayout = new GridLayout(1, 3);
        container.setLayout(frameLayout);
        

        JPanel left = new JPanel();
        GridLayout playerLayout = new GridLayout(2, 1);
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
        GridLayout frameLayout = new GridLayout(1, 3);
        container.setLayout(frameLayout);
        

        JPanel left = new JPanel();
        GridLayout playerLayout = new GridLayout(2, 1);
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

        GridLayout panelLayout = new GridLayout(2, 1);
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
    
    public JPanel createPlayer(JPanel player, int whichPlayer) {
        Hero hero = battle.getPlayer();
        if (whichPlayer == 2) {
            hero = battle.getPlayer2();
        }
        createStatLabels(player,hero,whichPlayer);
        return player;
    }
    
    public void createStatLabels(JPanel player, Hero hero, int whichPlayer) {
        JLabel playerName = new JLabel("Player " + whichPlayer);
        JLabel playerHero = new JLabel("Hero: " + hero.getName());
        JLabel playerHealth = new JLabel("Health: " + hero.getAtmHealth() + "/" + hero.getMaxHealth());
        JLabel playerPower = new JLabel("Power: " + hero.getAtmPower() + "/" + hero.getMaxPower());
        JLabel playerSpeed = new JLabel("Speed: " + hero.getAtmSpeed());
        JLabel playerDamage = new JLabel("Damage: " + hero.getAtmDamage());
        JLabel playerDR = new JLabel("Damage Reduction: " + (100 - (hero.getDamageTaken() * 100)) + "%");

        player.add(playerName);
        player.add(playerHero);
        player.add(playerHealth);
        player.add(playerPower);
        player.add(playerSpeed);
        player.add(playerDamage);
        player.add(playerDR);
    }

    public JPanel createPlayer(int i) {
        JPanel player = new JPanel();
        BoxLayout panelLayout = new BoxLayout(player, BoxLayout.Y_AXIS);
        player.setLayout(panelLayout);
        player.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        createPlayer(player,i);

        return player;
    }
    
    public JButton createSkillButton(int skillNumber, Hero hero, JPanel panel) {
        JButton ability = new JButton((skillNumber + 1) + ". " + hero.getAbilities().get(skillNumber).getName());
        ability.setToolTipText(hero.getAbilities().get(skillNumber).getDescription());
        panel.add(ability);
        abilities.add(ability);
        disableButton(hero.getAbilities().get(skillNumber), ability, hero);
        return ability;
    }
    
    public JPanel createSkills(int whichPlayer, Hero hero, JPanel panel) {
        for (int i = 0; i < hero.getAbilities().size(); i++) {
            createSkillButton(i, hero, panel);
        }
        return panel;
    }

    public JPanel createPlayerSkills(int i) {
        JPanel panel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        GridLayout panelLayout = new GridLayout(3, 1);
        panel.setLayout(panelLayout);

        JLabel abilitiesLabel = new JLabel("Abilities: ");
        titlePanel.add(abilitiesLabel);

        Hero hero = battle.getPlayer();
        if (i == 2) {
            hero = battle.getPlayer2();
        }
        hero.createAbilities();
        
        createSkills(i,hero,buttonPanel);

        JPanel descriptions = new JPanel();

        panel.add(titlePanel);
        panel.add(buttonPanel);
        panel.add(descriptions);
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Battle getBattle() {
        return battle;
    }
}
