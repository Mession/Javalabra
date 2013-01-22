
package javalabra.domain;

public class Santa extends Hero {
    
    public Santa(Team team) {
        super("Santa Claus", 1300, 60, 90, 120, team);
    }
    
    public void createAbilities() {
        
    }
    
    public Active naughty() {
        return new Active("Naughty!", 1, 150);
    }

    @Override
    public void annaVuoro() {
        // kesken, testin vuoks vaan naughty
        naughty();
    }
}
