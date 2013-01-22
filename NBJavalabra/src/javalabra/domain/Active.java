
package javalabra.domain;

public class Active extends Ability {
    private int vahinko;
    
    public Active(String nimi, int paikka, int vahinko) {
        super(nimi, paikka);
        this.vahinko = vahinko;
    }
    
    public int use() {
        return vahinko;
    }
}
