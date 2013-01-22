package javalabra.domain.tests;


import javalabra.domain.Santa;
import javalabra.domain.Team;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SantaTest {
    Team team;
    Santa santa;
    
    public SantaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        team = new Team("Test team");
        santa = new Santa(team);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("Santa Claus", santa.getNimi());
    }
    
    @Test
    public void konstruktoriAsettaaKestonOikein() {
        assertTrue(1300 == santa.getKesto());
    }
    
    @Test
    public void konstruktoriAsettaaNopeudenOikein() {
        assertTrue(60 == santa.getNopeus());
    }
    
    @Test
    public void konstruktoriAsettaaDamagenOikein() {
        assertTrue(90 == santa.getDamage());
    }
    
    @Test
    public void metodiDamageTakenToimii() {
        assertTrue(0.8 == santa.getDamageTaken());
    }
    
    @Test
    public void naughtyToimii() {
        assertTrue(150 == santa.naughty().use());
    }
}
