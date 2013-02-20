package javalabra.domain;

import java.util.ArrayList;

/**
 * Luokka määrää joulupukin taidot ja ominaisuudet
 */
public class Santa extends Hero {
    
    /**
     * Konstruktori
     */
    public Santa() {
        super("Santa", 1300, 60, 90, 120, 2);
    }
    
    /**
     * Palauttaa taidon "Gift Shower", joka aiheuttaa vahinkoa vastuastajaan
     */
    public Ability giftShower() {
        return new Ability("Gift Shower",this.getAtmDamage()*2,0,"Bomb the enemy with presents (damages enemy)",0);
    }
    
    /**
     * Palauttaa taidon "Xmas Spirit", joka nostaa pelaajan kestoa 10% vastustajan kestosta
     */
    public Ability xmasSpirit() {
        return new Ability("Xmas Spirit",0,1,"Get a share of your enemy's health (heals you)",0);
    }

    /**
     * Palauttaa taidon "Santa's Slay Ride, joka aiheuttaa vahinkoa vastustajaan
     */
    public Ability slayRide() {
        return new Ability("Santa's Slay Ride",this.getAtmDamage()*5,2,"Run over the enemy with your sleigh (damages enemy, costs power)",1);
    }

    /**
     * Luo joulupukin taidot ja asettaa ne yläluokan oliomuuttujaan
     */
    @Override
    public void createAbilities() {
        ArrayList<Ability> abilities = this.getAbilities();
        abilities.add(giftShower());
        abilities.add(xmasSpirit());
        abilities.add(slayRide());
        this.setAbilities(abilities);
    }
    
}
