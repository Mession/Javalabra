/**
 * Luokka määrää, mitä tietoja sankareiden taidoille pitää määritellä
 */

package javalabra.domain;

public class Ability {
    /**
     * Kertoo, kuinka paljon vahinkoa taito tekee
     */
    private int damage;
    /**
     * Taidon tunnus metodeita varten
     */
    private int id;
    /**
     * Lyhyt kuvaus siitä, mitä taito tekee
     */
    private String description;
    /**
     * Taidon nimi
     */
    private String name;
    /**
     * Kertoo, kuinka paljon voimaa taito käyttää
     */
    private int powerCost;
    
    /**
     * Konstruktori
     */
    public Ability(String name, int damage, int id, String description, int powerCost) {
        this.damage = damage;
        this.id = id;
        this.description = description;
        this.name = name;
        this.powerCost = powerCost;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPowerCost() {
        return this.powerCost;
    }

    public int getDamage() {
        return damage;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    
    
}
