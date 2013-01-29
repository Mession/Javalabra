
package javalabra.domain;

public class Ability {
    private int damage;
    private int id;
    private String description;
    private String name;
    private int powerCost;
    
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
