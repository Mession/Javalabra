package javalabra.logiikka;

import java.util.Random;
import javalabra.domain.Ability;
import javalabra.domain.Hero;
import javalabra.domain.Santa;
import javalabra.UI.TextUI;
import javalabra.domain.Banker;
import javalabra.domain.Snowman;

/**
 * Luokka hoitaa taisteluiden logiikkapuolen, esimerkiksi kierroksen tapahtumat
 * ja pelaajan valitsemien taitojen(ability) seuraukset (kuten vastustajan tai oman
 * heron ominaisuuksien muokkaamisen)
 */
public class Battle {
    /**
     * Ensimmäinen pelaaja
     */
    private Hero player;
    /**
     * Toinen pelaaja
     */
    private Hero player2;
    /**
     * Käytettävä käyttöliittymä
     */
    private TextUI ui;
    /**
     * Kertoo monesko kierros on menossa, jotta se voidaan ilmoittaa kierroksen alussa
     */
    private int roundCount;
    /**
     * Random tarvitaan joidenkin sankareiden taitoja varten
     */
    private Random random;
    
    private boolean player1turn;
    
    /**
     * Konstruktori
     */
    public Battle(Hero player, Hero player2, TextUI ui) {
        this.player = player;
        this.player2 = player2;
        this.ui = ui;
        this.roundCount = 1;
        this.random = new Random();
    }
    
    /**
     * Hoitaa yhden kierroksen asiat: käytännössä siis tulostaa tilanteen ja antaa
     * vuorot pelaajille
     */
//    public void round() {
//        roundCount++;
//        //ui.situation();
//        boolean playerHadTurn = false;
//        if (player.getAtmSpeed() >= player2.getAtmSpeed()) {
//            turn(player,player2);
//            playerHadTurn = true;
//        } else {
//            turn(player2,player);
//        }
//        if (!playerHadTurn) {
//            turn(player,player2);
//        } else {
//            turn(player2,player);
//        }
//    }
    
    /**
     * Kysyy pelaajalta taidon, tarkistaa taidon vaikutukset ja varmistaa, että
     * kumpikin sankari on korkeintaan maksimikestossa
     */
//    public void turn(Hero player, Hero enemy) {
//        Ability whichAbility = ui.turn(player);
//        checkEffect(player,enemy,whichAbility);
//        reduceHealthToMax(player, enemy);
//    }
    public void turn(Hero player, Hero enemy, Ability ability) {
        checkEffect(player,enemy,ability);
        reduceHealthToMax(player,enemy);
    }
    
    /**
     * Vähentää parametrina tuodun sankarin kestoa taidossa määrätyn verran
     */
    public void damage(Hero hero, Ability ability) {
        hero.setAtmHealth((int)(Math.round(hero.getAtmHealth()-(ability.getDamage()*hero.getDamageTaken()))));
    }
    
    /**
     * Vähentää parametrina tuodun sankarin kestoa tietyn verran (lähinnä testausta
     * ja erikoisvaikutuksia varten)
     */
    public void damage(Hero hero, int amount) {
        hero.setAtmHealth((int)(Math.round(hero.getAtmHealth()-(amount*hero.getDamageTaken()))));
    }
    
    /**
     * Lisää sankarin nykyistä kestoa taidon määräämän verran (voi mennä ylikin,
     * koska kierroksen lopussa kuitenkin ylimääräinen poistetaan)
     */
    public void heal(Hero hero, Ability ability) {
        hero.setAtmHealth(hero.getAtmHealth()+Math.abs(ability.getDamage()));
    }
    
    /**
     * Lisää sankarin nykyistä kestoa tietyn verran, kuten damage() metodissa, tämäkin
     * lähinnä testausta ja erikoisvaikutuksia varten
     */
    public void heal(Hero hero, int amount) {
        hero.setAtmHealth(hero.getAtmHealth()+amount);
    }
    
    /**
     * Kutsuu parametrissa välitetyn sankarin oikean luokan perusteella
     * sellaista metodia, joka tarkistaa juuri tämän sankarin tämän taidon vaikutukset
     */
    public void checkEffect(Hero hero, Hero enemy, Ability ability) {
        if (hero.getClass() == new Santa().getClass()) {
            checkSantaEffect(hero, enemy, ability);
        } else if (hero.getClass() == new Snowman().getClass()) {
            checkSnowmanEffect(hero, enemy, ability);
        } else if (hero.getClass() == new Banker().getClass()) {
            checkBankerEffect(hero, enemy, ability);
        }
    }

    /**
     * Tarkistaa Pankkiirin taitojen vaikutuksia ja valitun taidon perusteella
     * muokkaa sankareiden ominaisuuksia
     */
    public void checkBankerEffect(Hero hero, Hero enemy, Ability ability) {
        if (ability.getId() == 0) {
            hero.setAtmHealth(hero.getAtmHealth()-100);
            hero.setAtmDamageReduction(hero.getAtmDamageReduction()-10);
            hero.setAtmDamage(hero.getAtmDamage()+25);
            hero.setAtmSpeed(hero.getAtmSpeed()+25);
        } else if (ability.getId() == 1 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            enemy.setAtmPower(enemy.getAtmPower()+1);
            heal(hero,150);
            damage(enemy,150);
            enemy.setAtmDamage(enemy.getAtmDamage()+20);
            hero.setAtmDamage(hero.getAtmDamage()-20);
        } else if (ability.getId() == 1 && hero.getAtmPower() <= 0) {
            //ui.wastedTurn();
        } else if (ability.getId() == 2 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            int effect = random.nextInt(100);
            if (effect > 89) {
                hero.setAtmPower(hero.getAtmPower()+1);
                hero.setAtmDamage(hero.getAtmDamage()+50);
                damage(enemy,hero.getAtmDamage()*4);
                //ui.printBankerEffect(0);
            } else if (effect > 49) {
                damage(enemy,hero.getAtmDamage()*2);
                //ui.printBankerEffect(1);
            } else if (effect > 14) {
                damage(hero,enemy.getAtmDamage()*2);
                //ui.printBankerEffect(2);
            } else if (effect < 15) {
                damage(hero,enemy.getAtmDamage()*2);
                heal(enemy,hero.getAtmDamage());
                //ui.printBankerEffect(3);
            }
        } else if (ability.getId() == 2 && hero.getAtmPower() <= 0) {
            //ui.wastedTurn();
        }
    }
    
    /**
     * Tarkistaa Lumiukon taitojen vaikutuksia ja valitun taidon perusteella
     * muokkaa sankareiden ominaisuuksia
     */
    public void checkSnowmanEffect(Hero hero, Hero enemy, Ability ability) {
        if (ability.getId() == 0) {
            damage(enemy,ability);
            enemy.setAtmSpeed(enemy.getAtmSpeed()-10);
        } else if (ability.getId() == 1 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            heal(hero,ability);
        } else if (ability.getId() == 1 && hero.getAtmPower() <= 0) {
            //ui.wastedTurn();
        } else if (ability.getId() == 2 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            if (enemy.getAtmSpeed() >= 0) {
                damage(enemy,hero.getAtmDamage()*3);
            } else if (enemy.getAtmSpeed() < 0) {
                damage(enemy,hero.getAtmDamage()*7);
            }
        } else if (ability.getId() == 2 && hero.getAtmPower() <= 0) {
            //ui.wastedTurn();
        }
    }
    
    /**
     * Tarkistaa Joulupukin taitojen vaikutuksia ja valitun taidon perusteella
     * muokkaa sankareiden ominaisuuksia
     */
    public void checkSantaEffect(Hero hero, Hero enemy, Ability ability) {
        if (ability.getId() == 1) {
            heal(hero,(int)(Math.round(enemy.getAtmHealth()*0.1)));
        } else if (ability.getId() == 2 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            damage(enemy,ability);
        } else if (ability.getId() == 2 && hero.getAtmPower() <= 0) {
            //ui.wastedTurn();
        } else if (ability.getId() == 0) {
            damage(enemy,ability);
        }
    }
    
    /**
     * Vähentää sankareiden keston maksimiarvoon
     */
    public void reduceHealthToMax(Hero player, Hero enemy) {
        if (player.getAtmHealth() > player.getMaxHealth()) {
            player.setAtmHealth(player.getMaxHealth());
        }
        if (enemy.getAtmHealth() > enemy.getMaxHealth()) {
            enemy.setAtmHealth(enemy.getMaxHealth());
        }
        
    }
    
    /**
     * Tarkistaa, onko sankari elossa (tällä määritellään,
     * tuleeko seuraavaa kierrosta vai ei)
     */
    public boolean checkIfAlive(Hero hero) {
        if (hero.getAtmHealth() > 0) {
            return true;
        }
        return false;
    }
    
    public boolean checkIfEnds() {
        if (checkIfAlive(player) && checkIfAlive(player2)) {
            return false;
        }
        return true;
    }
    
    /**
     * Tarkistaa voittajan, palauttaa 1 jos pelaaja 1 voitti, 2 jos pelaaja 2 voitti,
     * 0 jos tuli tasapeli ja -1 jos peli ei ole vielä ohi
     */
    public int checkWinner() {
        if (player.getAtmHealth() > 0 && player2.getAtmHealth() <= 0) {
            return 1;
        } else if (player.getAtmHealth() <= 0 && player2.getAtmHealth() > 0) {
            return 2;
        } else if (player.getAtmHealth() <= 0 && player2.getAtmHealth() <= 0) {
            return 0;
        }
        return -1;
    }
    
    public int getRoundNumber() {
        return this.roundCount;
    }
    
    public Hero getPlayer() {
        return player;
    }
    
    public Hero getPlayer2() {
        return player2;
    }
    
    public void setPlayer1(Hero player) {
        this.player = player;
    }
    
    public void setPlayer2(Hero player) {
        this.player2 = player;
    }
    
    public boolean getTurn() {
        return player1turn;
    }
    
    public void setTurn(boolean turn) {
        this.player1turn = turn;
    }
    
    public void setStartingHero() {
        if (player.getAtmSpeed() > player2.getAtmSpeed()) {
            setTurn(true);
        } else if (player2.getAtmSpeed() > player.getAtmSpeed()) {
            setTurn(false);
        } else {
            int i = random.nextInt(2);
            if (i == 0) {
                setTurn(true);
            } else {
                setTurn(false);
            }
        }
    }
}
