
package javalabra.domain;

import java.util.ArrayList;

public class Team {
    private ArrayList<Hero> members;
    private String nimi;
    
    public Team(String nimi) {
        this.nimi = nimi;
        this.members = new ArrayList<Hero>();
    }
    
    public void lisaaJoukkueeseen(Hero hero) {
        this.members.add(hero);
    }
    
    public ArrayList<Hero> getTeam() {
        return this.members;
    }
    
    public String getNimi() {
        return this.nimi;
    }
}
