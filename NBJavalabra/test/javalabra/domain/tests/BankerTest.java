
package javalabra.domain.tests;

import javalabra.domain.Ability;
import javalabra.domain.Banker;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankerTest {
    Banker banker;
    
    public BankerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        banker = new Banker();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("Banker", banker.getName());
    }
    
    @Test
    public void konstruktoriAsettaaKestonOikein() {
        assertTrue(2000 == banker.getMaxHealth());
    }
    
    @Test
    public void konstruktoriAsettaaNopeudenOikein() {
        assertTrue(30 == banker.getMaxSpeed());
    }
    
    @Test
    public void konstruktoriAsettaaDamagenOikein() {
        assertTrue(50 == banker.getMaxDamage());
    }
    
    @Test
    public void metodiDamageTakenToimii() {
        assertTrue(0.65 == banker.getDamageTaken());
    }
    
    @Test
    public void profitDamageOikein() {
        Ability testAbility = banker.profit();
        assertTrue(testAbility.getDamage() == 0);
    }
    
    @Test
    public void profitPowerCost() {
        Ability testAbility = banker.profit();
        assertTrue(testAbility.getPowerCost() == 0);
    }
    
    @Test
    public void profitId() {
        Ability testAbility = banker.profit();
        assertTrue(testAbility.getId() == 0);
    }
    
    @Test
    public void tradeDamageOnNolla() {
        Ability testAbility = banker.trade();
        assertTrue(testAbility.getDamage() == 0);
    }
    
    @Test
    public void tradePowerCost() {
        Ability testAbility = banker.trade();
        assertTrue(testAbility.getPowerCost() == 1);
    }
    
    @Test
    public void tradeId() {
        Ability testAbility = banker.trade();
        assertTrue(testAbility.getId() == 1);
    }
    
    @Test
    public void speculateDamageOikein() {
        Ability testAbility = banker.speculate();
        assertTrue(testAbility.getDamage() == 0);
    }
    
    @Test
    public void speculatePowerCost() {
        Ability testAbility = banker.speculate();
        assertTrue(testAbility.getPowerCost() == 1);
    }
    
    @Test
    public void speculateId() {
        Ability testAbility = banker.speculate();
        assertTrue(testAbility.getId() == 2);
    }
    
}
