
package javalabra.domain;

import java.util.ArrayList;

public class Santa extends Hero {
    
    public Santa() {
        super("Santa Claus", 1300, 60, 90, 120, 2);
    }
    
    public Ability giftShower() {
        return new Ability("Gift Shower",this.getAtmDamage()*2,0,"Bomb the enemy with presents",0);
    }
    
    public Ability xmasSpirit() {
        return new Ability("Xmas Spirit",0,1,"Get a share of your enemy's health",0);
    }
    
    public Ability slayRide() {
        return new Ability("Santa's Slay Ride",this.getAtmDamage()*5,2,"Run over the enemy with your sleigh",1);
    }

    @Override
    public void createAbilities() {
        ArrayList<Ability> abilities = this.getAbilities();
        abilities.add(giftShower());
        abilities.add(xmasSpirit());
        abilities.add(slayRide());
        this.setAbilities(abilities);
    }
    
}
