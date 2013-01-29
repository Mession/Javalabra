
package javalabra.logiikka;

import javalabra.domain.Ability;
import javalabra.domain.Hero;
import javalabra.domain.Santa;
import javalabra.UI.TextUI;

public class Battle {
    private Hero player;
    private Hero player2;
    private TextUI ui;
    private int roundCount;
    
    public Battle(Hero player, Hero player2, TextUI ui) {
        this.player = player;
        this.player2 = player2;
        this.ui = ui;
        this.roundCount = 1;
    }
    
    public void round() {
        roundCount++;
        ui.situation();
        boolean playerHadTurn = false;
        if (player.getAtmSpeed() >= player2.getAtmSpeed()) {
            turn(player,player2);
            playerHadTurn = true;
        } else {
            turn(player2,player);
        }
        if (!playerHadTurn) {
            turn(player,player2);
        } else {
            turn(player2,player);
        }
    }
    
    public void turn(Hero player, Hero enemy) {
        Ability whichAbility = ui.turn(player);
        checkSpecialEffect(player,enemy,whichAbility);
        reduceHealthToMax(player, enemy);
    }
    
    public void damage(Hero hero, Ability ability) {
        hero.setAtmHealth((int)(Math.round(hero.getAtmHealth()-(ability.getDamage()*hero.getDamageTaken()))));
    }
    
    public void damage(Hero hero, int amount) {
        hero.setAtmHealth((int)(Math.round(hero.getAtmHealth()-(amount*hero.getDamageTaken()))));
    }
    
    public void heal(Hero hero, Ability ability) {
        hero.setAtmHealth(hero.getAtmHealth()+Math.abs(ability.getDamage()));
    }
    
    public void heal(Hero hero, int amount) {
        hero.setAtmHealth(hero.getAtmHealth()+amount);
    }
    
    public void checkSpecialEffect(Hero hero, Hero enemy, Ability ability) {
        if (hero.getClass() == new Santa().getClass()) {
            checkSantaEffect(hero, enemy, ability);
        }
    }
    
    public void checkSantaEffect(Hero hero, Hero enemy, Ability ability) {
        if (ability.getId() == 1) {
            heal(hero,(int)(Math.round(enemy.getAtmHealth()*0.1)));
        } else if (ability.getId() == 2 && hero.getAtmPower() > 0) {
            hero.setAtmPower(hero.getAtmPower()-1);
            damage(enemy,ability);
        } else if (ability.getId() == 2 && hero.getAtmPower() <= 0) {
            ui.wastedTurn();
        } else if (ability.getId() == 0) {
            damage(enemy,ability);
        }
    }
    
    public void reduceHealthToMax(Hero player, Hero enemy) {
        if (player.getAtmHealth() > player.getMaxHealth()) {
            player.setAtmHealth(player.getMaxHealth());
        }
        if (enemy.getAtmHealth() > enemy.getMaxHealth()) {
            enemy.setAtmHealth(enemy.getMaxHealth());
        }
        
    }
    
    public boolean checkIfAlive(Hero hero) {
        if (hero.getAtmHealth() > 0) {
            return true;
        }
        return false;
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
    
}
