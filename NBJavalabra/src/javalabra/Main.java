
package javalabra;

import javalabra.domain.Santa;
import javalabra.domain.Team;
import javalabra.logiikka.Taistelu;

public class Main {

    public static void main(String[] args) {
        Team ekaT = new Team("eka tiimi");
        Team tokaT = new Team("toka tiimi");
        Santa eka = new Santa(ekaT);
        Santa toka = new Santa(tokaT);
        ekaT.lisaaJoukkueeseen(eka);
        tokaT.lisaaJoukkueeseen(toka);
        Taistelu taistelu = new Taistelu(ekaT,tokaT);
        taistelu.kierros();
    }
}
