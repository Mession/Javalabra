package javalabra.domain;

import java.util.ArrayList;

/**
 * Luokka määrää lumiukon taidot ja ominaisuudet
 */
public class Snowman extends Hero {
    
    /**
     * Konstruktori
     */
    public Snowman() {
        super("Snowman", 1000, 45, 130, 125, 1);
    }
    
    /**
     * Palauttaa taidon "Snowball", joka aiheuttaa vahinkoa vastustajaan ja vähentää vastuastajan nopeutta
     */
    public Ability snowball() {
        return new Ability("Snowball",this.getAtmDamage()*2,0,"Throw a snowball at the target (damages enemy, lowers their speed)",0);
    }
    
    /**
     * Palauttaa taidon "Eat nose", joka nostaa pelaajan kestoa
     */
    public Ability eatNose() {
        return new Ability("Eat nose",-500,1,"Carroty taste (heals you, costs power)",1);
    }

    /**
     * Palauttaa taidon "Snowstorm", joka aiheuttaa vahinkoa vastustajaan (enemmän jos vastuastajan nopeus on alle 0)
     */
    public Ability snowstorm() {
        return new Ability("Snowstorm",0,2,"Summon a powerful blizzard (damages enemy, deals more damage on"
                + " targets with subzero speed, costs power)",1);
    }

    /**
     * Luo lumiukon taidot ja asettaa ne yläluokan oliomuuttujaan
     */
    @Override
    public void createAbilities() {
        ArrayList<Ability> abilities = new ArrayList<Ability>();
        abilities.add(snowball());
        abilities.add(eatNose());
        abilities.add(snowstorm());
        this.setAbilities(abilities);
    }
}
