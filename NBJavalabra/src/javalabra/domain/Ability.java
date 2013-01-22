
package javalabra.domain;

public abstract class Ability {
    private String nimi;
    private int paikka;
    
    public Ability(String nimi, int paikka) {
        this.nimi = nimi;
        this.paikka = paikka;
    }
}
