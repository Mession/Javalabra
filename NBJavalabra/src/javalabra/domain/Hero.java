
package javalabra.domain;

import java.util.ArrayList;

public abstract class Hero {
    private String nimi;
    private int kesto;
    private int nopeus;
    private int damage;
    private int damageReduction;
    private Team team;
    public ArrayList<Ability> abilities;
    
    public Hero(String nimi, int kesto, int nopeus, int damage, int damageReduction, Team team) {
        this.nimi = nimi;
        this.kesto = kesto;
        this.nopeus = nopeus;
        this.damage = damage;
        this.damageReduction = damageReduction;
        this.team = team;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public Team getTeam() {
        return this.team;
    }
    
    public int getKesto() {
        return this.kesto;
    }
    
    public int getNopeus() {
        return this.nopeus;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public double getDamageTaken() {
        double damageReductionPercentage = 1.0*this.damageReduction - 100;
        if ( damageReductionPercentage < 0) {
            return 100.0;
        }
        return (100 - damageReductionPercentage)/100;
    }
    
    public abstract void annaVuoro();
}
