
package javalabra.logiikka;

import java.util.ArrayList;
import javalabra.domain.Hero;
import javalabra.domain.Team;

public class Taistelu {
    private Team leftTeam;
    private Team rightTeam;
    private ArrayList<Hero> taistelijat;
    
    public Taistelu(Team team1, Team team2) {
        this.taistelijat = new ArrayList<Hero>();
        this.leftTeam = team1;
        this.rightTeam = team2;
        this.taistelijat.addAll(team1.getTeam());
        this.taistelijat.addAll(team2.getTeam());
    }
    
    public void kierros() {
        for (Hero hero : taistelijat) {
            System.out.println("Vuorossa " + hero.getNimi() + " joukkueesta " + hero.getTeam().getNimi());
            hero.annaVuoro();
        }
    }
}
