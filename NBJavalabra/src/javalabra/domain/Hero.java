
package javalabra.domain;

import java.util.ArrayList;

public abstract class Hero {
    private String name;
    private final int maxHealth;
    private int atmHealth;
    private final int maxPower;
    private int atmPower;
    private final int maxSpeed;
    private int atmSpeed;
    private final int maxDamage;
    private int atmDamage;
    private final int maxDamageReduction;
    private int atmDamageReduction;
    private ArrayList<Ability> abilities;
    
    public Hero(String name, int kesto, int nopeus, int damage, int damageReduction, int maxPower) {
        this.name = name;
        this.maxHealth = kesto;
        this.atmHealth = this.maxHealth;
        this.maxPower = maxPower;
        this.atmPower = this.maxPower;
        this.maxSpeed = nopeus;
        this.atmSpeed = this.maxSpeed;
        this.maxDamage = damage;
        this.atmDamage = this.maxDamage;
        this.maxDamageReduction = damageReduction;
        this.atmDamageReduction = this.maxDamageReduction;
        this.abilities = new ArrayList<Ability>();
    }
    
    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }
    
    public ArrayList<Ability> getAbilities() {
        return this.abilities;
    }
    
    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    
    public abstract void createAbilities();
    
    public String getName() {
        return this.name;
    }
    
    public double getDamageTaken() {
        double damageReductionPercentage = 1.0*this.atmDamageReduction - 100;
        if ( damageReductionPercentage < 0) {
            return 100.0;
        }
        return (100 - damageReductionPercentage)/100;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMaxDamageReduction() {
        return maxDamageReduction;
    }
    
    public int getAtmHealth() {
        return this.atmHealth;
    }

    public void setAtmHealth(int hp) {
        this.atmHealth = hp;
    }

    public int getAtmSpeed() {
        return atmSpeed;
    }

    public void setAtmSpeed(int atmSpeed) {
        this.atmSpeed = atmSpeed;
    }

    public int getAtmDamage() {
        return atmDamage;
    }

    public void setAtmDamage(int atmDamage) {
        this.atmDamage = atmDamage;
    }

    public int getAtmDamageReduction() {
        return atmDamageReduction;
    }

    public void setAtmDamageReduction(int atmDamageReduction) {
        this.atmDamageReduction = atmDamageReduction;
    }
    
    public int getMaxPower() {
        return this.maxPower;
    }

    public int getAtmPower() {
        return atmPower;
    }

    public void setAtmPower(int atmPower) {
        this.atmPower = atmPower;
    }
}
