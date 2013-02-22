package javalabra.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalabra.domain.Ability;
import javalabra.domain.Banker;
import javalabra.domain.Hero;
import javalabra.domain.Santa;
import javalabra.domain.Snowman;
import javalabra.logiikka.Battle;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
    /**
     * Käyttöliittymän ikkuna
     */
    private JFrame frame;
    /**
     * Käyttöliittymään liittyvä taistelu
     */
    private Battle battle;
    /**
     * Lista sankareista valitsemista varten
     */
    private ArrayList<Hero> heroes;
    /**
     * Lista kaikista käyttöliittymän taitonappuloista, jotta
     * jokaiselle napille voidaan helposti suorittaa metodi
     */
    private ArrayList<JButton> abilities;
    /**
     * "Combat log", johon tulostetaan käytetyt taidot
     */
    private JTextArea log;
    /**
     * Keskimmäinen paneeli, jossa "combat log" sijaitsee, jotta
     * UI:ta päivittäessä voidaan säilyttää sama keskipaneeli
     */
    private JPanel middle;

    /**
     * Konstruktori
     */
    public GUI() {
        this.abilities = new ArrayList<JButton>();
    }

    /**
     * Suoritetaan käyttöliittymää käynnistäessä, luo menu:n
     */
    @Override
    public void run() {
        createMenu();
    }

    /**
     * Luo ohje-näkymän ikkunan
     */
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

    /**
     * Luo ohje-näkymän komponentit
     */
    public void createInstructionComponents(Container container) {
        BorderLayout frameLayout = new BorderLayout();
        container.setLayout(frameLayout);

        container.add(instructions(), BorderLayout.CENTER);

        JButton back = new JButton("Back to menu");
        BackToMenuMouseListener btmml = new BackToMenuMouseListener(back, this);
        back.addActionListener(btmml);

        container.add(back, BorderLayout.SOUTH);
    }

    /**
     * Luo ohje-paneelin
     */
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

    /**
     * Luo uuden ikkunan pelin päätyttyä, ikkunassa olevaa nappia painamalla
     * voidaan aloittaa uusi peli
     */
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

    /**
     * Luo menu-ikkunan
     */
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

    /**
     * Luo menu-ikkunan komponentit
     */
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

    /**
     * Luo peliä aloittaessa uuden taistelun ja käynnistää sankarin valitsemisen
     */
    public void startGame(int player) {
        battle = new Battle(null, null, this);

        selectHero(1);
    }

    /**
     * Tarkistaa, voiko sankarin tiettyä taitoa enää käyttää, ja jos ei voi,
     * ottaa taitoon liittyvän napin pois käytöstä
     */
    public void disableButton(Ability ability, JButton button, Hero hero) {
        if (ability.getPowerCost() > 0 && hero.getAtmPower() <= 0) {
            button.setEnabled(false);
        }
    }

    /**
     * Ottaa pois käytöstä kaikki taitoihin liittyvät napit (kutsutaan pelin päättyessä)
     */
    public void disableButtons() {
        for (JButton button : abilities) {
            button.setEnabled(false);
        }
    }

    /**
     * Tarkistaa päättyykö peli, ja jos päättyy tulostaa onnitteluviestin voittajalle,
     * ottaa taitonapit pois käytöstä ja kysyy käyttäjältä, haluaako tämä uuden pelin
     */
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

    /**
     * Kirjoittaa parametrina saadun String:in UI:n tekstikenttään
     */
    public void writeToLog(String addition) {
        log.setText(log.getText() + addition + "\n");
    }

    /**
     * Onnittelee voittajaa (parametrina tuodaan voittajan tunnus)
     */
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

    /**
     * Aloittaa taistelun
     */
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

    /**
     * Luo sankarin valitsemis -ikkunan komponentit
     */
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

    /**
     * Luo sankarin valitsemis -ikkunan
     */
    public void selectHero(int player) {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 650));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.repaint();

        heroes = new ArrayList<Hero>();
        addHeroes();

        chooseHero(frame.getContentPane(), player);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Luo tietyn sankarin valitsemis -paneelin, jossa on sankarin kuva
     * ja nappi, jolla sankari valitaan
     */
    public JPanel createHeroSelectionPanel(Hero hero, int player) {
        JPanel heroPanel = new JPanel();

        JPanel heroPicture = this.createPlayerPicturePanel(hero);
        JButton choose = new JButton("Choose " + hero.getName());
        SelectionMouseListener sml = new SelectionMouseListener(choose, hero, this, player);
        choose.addActionListener(sml);

        GridLayout heroPanelLayout = new GridLayout(2, 1);
        heroPanel.setLayout(heroPanelLayout);

        heroPanel.add(heroPicture);
        heroPanel.add(choose);
        
        return heroPanel;
    }

    /**
     * Luo valitsemisnäkymän komponentit
     */
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

    /**
     * Luo peli-ikkunan
     */
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

    /**
     * Luo uuden peli-ikkunan komponentit
     */
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

    /**
     * Luo peli-ikkunan komponentit silloin, jos ikkunaa vain päivitetään
     * (tällöin tarvitaan JPanel middle1, joka siis on vanhan pelinäkymän
     * keskipaneeli)
     */
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

    /**
     * Päivittää peli-ikkunan kummankin pelaajanäkymän (jotta niissä olisi
     * nykyiset ominaisuudet, jotka ovat luultavasti muuttuneet taitojen myötä)
     */
    public void refreshPlayerPanel(Container container) {
        abilities = new ArrayList<JButton>();
        createGameComponents(container, middle);
        BattleMouseListener bml = new BattleMouseListener(this, abilities.get(0), abilities.get(1), abilities.get(2),
                abilities.get(3), abilities.get(4), abilities.get(5), log);

        for (JButton button : abilities) {
            button.addActionListener(bml);
        }
    }
    
    /**
     * Palauttaa kuvatiedoston nimen riippuen sankarin luokasta
     */
    public String pictureName(Hero hero) {
        if (hero.getClass() == new Santa().getClass()) {
            return "images/santa.jpg";
        }
        if (hero.getClass() == new Snowman().getClass()) {
            return "images/snowman.jpg";
        }
        if (hero.getClass() == new Banker().getClass()) {
            return "images/banker.jpg";
        }
        return "";
    }
    
    /**
     * Kutsuu kuvatiedoston nimen palauttavaa metodia
     */
    public String pictureName(int i) {
        String id = "";
        
        if (i == 1) {
            id = pictureName(battle.getPlayer());
        }
        
        if (i == 2) {
            id = pictureName(battle.getPlayer2());
        }
        
        return id;
    }
    
    /**
     * Luo pelaajan kuvan sisältävän paneelin sankarin perusteella, virhetilanteessa lisää kuvan sijaan ilmoituksen virheestä
     */
    public JPanel createPlayerPicturePanel(Hero hero) {
        JPanel picturePanel = new JPanel();
        String id = pictureName(hero);
        BufferedImage heroPicture = null;
        
        try {
            heroPicture = ImageIO.read(this.getClass().getClassLoader().getResource(id));
        } catch (Exception e) {
            
        }
        
        if (heroPicture != null) {
            JLabel picLabel = new JLabel(new ImageIcon(heroPicture));
            picturePanel.add(picLabel);
        } else {
            picturePanel.add(new JLabel("Error loading the picture"));
        }
        
        return picturePanel;
    }

    /**
     * Luo pelaajan kuvan sisältävän paneelin sankarin tunnuksen perusteella, virhetilanteessa lisää kuvan sijaan ilmoituksen virheestä
     */
    public JPanel createPlayerPicturePanel(int i) {
        JPanel picturePanel = new JPanel();
        String id = pictureName(i);
        BufferedImage heroPicture = null;
        
        try {
            heroPicture = ImageIO.read(this.getClass().getClassLoader().getResource(id));
        } catch (Exception e) {
            
        }
        
        if (heroPicture != null) {
            JLabel picLabel = new JLabel(new ImageIcon(heroPicture));
            picturePanel.add(picLabel);
        } else {
            picturePanel.add(new JLabel("Error loading the picture"));
        }
        
        return picturePanel;
    }

    /**
     * Luo paneelin, jossa on pelaajan kuvan ja ominaisuuksien lisäksi pelaajan taidot
     */
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

    /**
     * Luo peli-ikkunan keskimmäisen paneelin, jossa on tekstikenttä, johon kirjoitetaan
     * käyttäjälle tulevat ilmoitukset
     */
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

    /**
     * Lisää sankarilistaan sankarit valitsemista varten
     */
    public void addHeroes() {
        heroes.add(new Santa());
        heroes.add(new Snowman());
        heroes.add(new Banker());
    }
    
    /**
     * Kutsuu pelaajan ominaisuudet lisäävää metodia riippuen pelaajasta
     */
    public JPanel createPlayer(JPanel player, int whichPlayer) {
        Hero hero = battle.getPlayer();
        if (whichPlayer == 2) {
            hero = battle.getPlayer2();
        }
        createStatLabels(player,hero,whichPlayer);
        return player;
    }
    
    /**
     * Lisää pelaajan ominaisuuspaneeliin pelaajan ominaisuudet
     */
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

    /**
     * Luo pelaajan ominaisuudet sisältävän paneelin
     */
    public JPanel createPlayer(int i) {
        JPanel player = new JPanel();
        BoxLayout panelLayout = new BoxLayout(player, BoxLayout.Y_AXIS);
        player.setLayout(panelLayout);
        player.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        createPlayer(player,i);

        return player;
    }
    
    /**
     * Luo taitonapin
     */
    public JButton createSkillButton(int skillNumber, Hero hero, JPanel panel) {
        JButton ability = new JButton((skillNumber + 1) + ". " + hero.getAbilities().get(skillNumber).getName());
        ability.setToolTipText(hero.getAbilities().get(skillNumber).getDescription());
        panel.add(ability);
        abilities.add(ability);
        disableButton(hero.getAbilities().get(skillNumber), ability, hero);
        return ability;
    }
    
    /**
     * Luo tietyn pelaajan kaikki taitonapit
     */
    public JPanel createSkills(int whichPlayer, Hero hero, JPanel panel) {
        for (int i = 0; i < hero.getAbilities().size(); i++) {
            createSkillButton(i, hero, panel);
        }
        return panel;
    }

    /**
     * Luo pelaajan taidot sisältävän paneelin, jossa on taitonapit ja otsikko
     */
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

        panel.add(titlePanel);
        panel.add(buttonPanel);

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
