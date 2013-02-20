package javalabra.domain;

import java.util.ArrayList;

/**
 * Luokka määrää, mitä metodeita ja ominaisuuksia jokaisella herolla tulee olla
 */
public abstract class Hero {
    /**
     * Sankarin nimi
     */
    private String name;
    /**
     * Sankarin maksimikesto
     */
    private final int maxHealth;
    /**
     * Nykyinen kesto, kesto kertoo, onko sankari kuollut vai elossa (alle 0 nykyinen kesto = kuollut)
     */
    private int atmHealth;
    /**
     * Maksimivoima
     */
    private final int maxPower;
    /**
     * Nykyinen voima, voimaa käytetään "parhaisiin" taitoihin
     */
    private int atmPower;
    /**
     * Maksiminopeus
     */
    private final int maxSpeed;
    /**
     * Nykyinen nopeus, nopeus määrää kierroksen aloittajan
     */
    private int atmSpeed;
    /**
     * Maksimivahinko
     */
    private final int maxDamage;
    /**
     * Nykyinen vahinko, vahinko määrää joidenkin taitojen voimakkuutta
     */
    private int atmDamage;
    /**
     * Maksimipuolustus
     */
    private final int maxDamageReduction;
    /**
     * Nykyinen puolustus, puolustus määrää, kuinka paljon vahingosta sankari ottaa vastaan
     */
    private int atmDamageReduction;
    /**
     * Sisältää sankarin taidot
     */
    private ArrayList<Ability> abilities;

    /**
     * Konstruktori
     */
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
    
    /**
     * Lisää yksittäisen taidon tämän sankarin taitoihin
     */
    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }
    
    public ArrayList<Ability> getAbilities() {
        return this.abilities;
    }
    
    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }
    
    /**
     * Määrää, että kaikilla aliluokilla tulee olla metodi, jossa
     * aliluokan eli tietyn sankarin taidot luodaan
     */
    public abstract void createAbilities();
    
    public String getName() {
        return this.name;
    }
    
    /**
     * Paluttaa double arvon, joka määrää, kuinka paljon sankari ottaa
     * vastaan vahinkoa. Jos sankarin damageReduction ominaisuus on alle 100,
     * sankari ottaa kaiken vahingon täytenä. Muuten metodi laskee kertoimen,
     * jonka arvo on siis alle 1.0, ja kaikki sankariin kohdistetut hyökkäykset
     * kerrotaan tällä kertoimella
     */
    public double getDamageTaken() {
        double damageReductionPercentage = 1.0*this.atmDamageReduction - 100;
        if ( damageReductionPercentage < 0) {
            return 1.0;
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
