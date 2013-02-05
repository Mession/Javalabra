/**
 * Luokka määrää pankkiirin taidot ja ominaisuudet
 */

package javalabra.domain;

import java.util.ArrayList;

public class Banker extends Hero {
    
    /**
     * Konstruktori
     */
    public Banker() {
        super("Banker", 2000, 30, 50, 135, 10);
    }
    
    /**
     * Palauttaa taidon "Short term profit", joka vähentää pelaajan kestoa ja puolustusta, mutta nostaa vahinkoa ja nopeutta
     */
    public Ability profit() {
        return new Ability("Short term profit",0,0,"Decrease your health and damage reduction to gain damage and speed",0);
    }
    
    /**
     * Palauttaa taidon "Trade", joka vaihtaa pelaajan voimaa ja vahinkoa vastustajan kestoon
     */
    public Ability trade() {
        return new Ability("Trade",0,1,"Trade power and damage to the enemy in exchange for health (heals you, costs power and damage)",1);
    }

    /**
     * Palauttaa taidon "Speculate", joka satunnaisesti tekee vahinkoa vastustajaan tai pelaajaan itseensä, ja satunnaisesti muuttaa joitain
     * pelaajan tai vastustajan ominaisuuksia
     */
    public Ability speculate() {
        return new Ability("Speculate",0,2,"Speculate on stock prices (randomly damages either you or the enemy, "
                + "and possibly has other side effects)",1);
    }

    /**
     * Luo pankkiirin taidot ja asettaa ne yläluokan oliomuuttujaan
     */
    @Override
    public void createAbilities() {
        ArrayList<Ability> abilities = this.getAbilities();
        abilities.add(profit());
        abilities.add(trade());
        abilities.add(speculate());
        this.setAbilities(abilities);
    }
}
