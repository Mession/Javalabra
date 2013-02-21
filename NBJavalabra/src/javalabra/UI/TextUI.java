package javalabra.UI;

import java.util.ArrayList;
import java.util.Scanner;
import javalabra.domain.Ability;
import javalabra.domain.Banker;
import javalabra.domain.Hero;
import javalabra.domain.Santa;
import javalabra.domain.Snowman;
import javalabra.logiikka.Battle;

/**
 * Väliaikainen tekstikäyttöliittymä, joka huolehtii käyttäjien syötteiden lukemisesta
 * ja käyttäjälle välitettävistä tiedoista
 * Luokan metodeille ei ole yksityiskohtaisia JavaDoceja tai testejä, sillä luokka korvataan
 * lähitulevaisuudessa graafisella käyttöliittymällä
 */
public class TextUI {
    private Battle battle;
    private Scanner lukija;
    private ArrayList<Hero> heroes;
    
    /**
     * Konstruktori
     */
    public TextUI() {
        lukija = new Scanner(System.in);
        heroes = new ArrayList<Hero>();
    }
    
    public void addHeroes() {
        heroes.add(new Santa());
        heroes.add(new Snowman());
        heroes.add(new Banker());
    }
    
    public void setBattle(Battle battle) {
        this.battle = battle;
    }
    
    public void begin() {
        start();
        while(battle.checkIfAlive(battle.getPlayer()) && battle.checkIfAlive(battle.getPlayer2())) {
//            System.out.println("Round "+battle.getRoundNumber()+"\n");
//            battle.round();
        }
        if (battle.checkIfAlive(battle.getPlayer())) {
            situation();
            System.out.println("Victory for player 1!");
        } else if (battle.checkIfAlive(battle.getPlayer2())) {
            situation();
            System.out.println("Victory for player 2!");
        } else {
            situation();
            System.out.println("It's a tie!");
        }
    }
    
    public void printBankerEffect(int id) {
        if (id == 0) {
            System.out.println("Your speculations yielded a huge profit! Damage increased, power refunded, enemy damaged");
        }
        if (id == 1) {
            System.out.println("Not so bad, enemy damaged");
        }
        if (id == 2) {
            System.out.println("Your investments are going sour. You take damage");
        }
        if (id == 3) {
            System.out.println("How could you have known that it was all a scam? Invisible cars seemed legit from the get-go."
                    + " You take damage and the enemy's health is increased");
        }
    }
    
    public void start() {
        addHeroes();
        System.out.println("Choose a hero, player 1:");
        listHeroes();
        Hero player = chooseHero();
        heroes.remove(player);
        System.out.println("Choose a hero, player 2:");
        listHeroes();
        Hero player2 = chooseHero();
        player.createAbilities();
        player2.createAbilities();
        System.out.println("Let the battle begin!");
        //battle = new Battle(player,player2,this);
    }
    
    public Ability turn(Hero hero) {
        listAbilities(hero);
        return chooseAbility(hero);
    }
    
    public Ability chooseAbility(Hero hero) {
        int vastaus = 0;
        while (true) {
            try {
                vastaus = lukija.nextInt();
                if (vastaus <= hero.getAbilities().size() && vastaus >0) {
                    break;
                }
            } catch(Exception e) {
                System.out.println("Error, input a number to choose an ability");
            }
            if (vastaus <= 0 || vastaus > hero.getAbilities().size()) {
                System.out.println("Please choose a number between 1 and "+hero.getAbilities().size());
            }
        }
        System.out.println("You have chosen "+hero.getAbilities().get(vastaus-1).getName());
        System.out.println();
        return hero.getAbilities().get(vastaus-1);
    }
    
    public void listAbilities(Hero hero) {
        if (hero == battle.getPlayer()) {
            System.out.println("Player 1's turn");
        } else {
            System.out.println("Player 2's turn");
        }
        System.out.println("Which ability will you use?");
        for (int i = 0; i < hero.getAbilities().size(); i++) {
            System.out.println(i+1+". "+hero.getAbilities().get(i).getName()+"\t("+hero.getAbilities().get(i).getDescription()+")");
        }
    }
    
    public void wastedTurn() {
        System.out.println("Not enough power to use that ability, you just wasted your turn.");
    }
    
    public Hero chooseHero() {
        int vastaus = 0;
        while (true) {
            try {
                vastaus = lukija.nextInt();
                if (vastaus <= heroes.size() && vastaus > 0) {
                    break;
                }
            } catch(Exception e) {
                System.out.println("Error, input a number to choose a hero");
            }
            if (vastaus <= 0 || vastaus > heroes.size()) {
                System.out.println("Please choose a number between 1 and "+heroes.size());
            }
        }
        System.out.println("You have chosen "+heroes.get(vastaus-1).getName());
        System.out.println();
        return heroes.get(vastaus-1);
    }
    
    public void listHeroes() {
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(i+1+". "+heroes.get(i).getName());
        }
        System.out.println("Who do you choose? (input number)");
    }
    
    public void situation() {
        System.out.println("\t\tHero\tHealth\t\tPower\tSpeed\tDamage\tDamage Reduction");
        System.out.println("Player 1:\t"+heroStats(battle.getPlayer()));
        System.out.println("Player 2:\t"+heroStats(battle.getPlayer2()));
        System.out.println();
    }

    private String heroStats(Hero hero) {
        return hero.getName()+"\t"+playerHP(hero)+playerPower(hero)+playerSpeed(hero)+playerDamage(hero)+playerDR(hero);
    }
    
    private String playerHP(Hero hero) {
        if (hero.getAtmHealth() < 100 && hero.getAtmHealth() > -10) {
            return hero.getAtmHealth()+"/"+hero.getMaxHealth()+"\t\t";
        }
        return hero.getAtmHealth()+"/"+hero.getMaxHealth()+"\t";
    }
    
    private String playerPower(Hero hero) {
        return hero.getAtmPower()+"/"+hero.getMaxPower()+"\t";
    }
    
    private String playerSpeed(Hero hero) {
        return hero.getAtmSpeed()+"\t";
    }
    
    private String playerDamage(Hero hero) {
        return hero.getAtmDamage()+"\t";
    }
    
    private String playerDR(Hero hero) {
        return (hero.getAtmDamageReduction()-100)+"%\t";
    }
}
