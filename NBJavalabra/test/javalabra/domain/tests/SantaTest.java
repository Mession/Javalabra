package javalabra.domain.tests;


import javalabra.domain.Ability;
import javalabra.domain.Santa;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SantaTest {
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
        santa = new Santa();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("Santa", santa.getName());
    }
    
    @Test
    public void konstruktoriAsettaaKestonOikein() {
        assertTrue(1300 == santa.getMaxHealth());
    }
    
    @Test
    public void konstruktoriAsettaaNopeudenOikein() {
        assertTrue(60 == santa.getMaxSpeed());
    }
    
    @Test
    public void konstruktoriAsettaaDamagenOikein() {
        assertTrue(90 == santa.getMaxDamage());
    }
    
    @Test
    public void metodiDamageTakenToimii() {
        assertTrue(0.8 == santa.getDamageTaken());
    }
    
    @Test
    public void giftShowerDamageOikein() {
        Ability testAbility = santa.giftShower();
        assertTrue(testAbility.getDamage() == santa.getAtmDamage()*2);
    }
    
    @Test
    public void giftShowerPowerCost() {
        Ability testAbility = santa.giftShower();
        assertTrue(testAbility.getPowerCost() == 0);
    }
    
    @Test
    public void giftShowerId() {
        Ability testAbility = santa.giftShower();
        assertTrue(testAbility.getId() == 0);
    }
    
    @Test
    public void xmasSpiritDamageOnNolla() {
        Ability testAbility = santa.xmasSpirit();
        assertTrue(testAbility.getDamage() == 0);
    }
    
    @Test
    public void xmasSpiritNoPowerCost() {
        Ability testAbility = santa.xmasSpirit();
        assertTrue(testAbility.getPowerCost() == 0);
    }
    
    @Test
    public void xmasSpiritId() {
        Ability testAbility = santa.xmasSpirit();
        assertTrue(testAbility.getId() == 1);
    }
    
    @Test
    public void slayRideDamageOikein() {
        Ability testAbility = santa.slayRide();
        assertTrue(testAbility.getDamage() == santa.getAtmDamage()*5);
    }
    
    @Test
    public void slayRidePowerCost() {
        Ability testAbility = santa.slayRide();
        assertTrue(testAbility.getPowerCost() == 1);
    }
    
    @Test
    public void slayRideId() {
        Ability testAbility = santa.slayRide();
        assertTrue(testAbility.getId() == 2);
    }
    
}
