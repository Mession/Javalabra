
package javalabra.domain.tests;

import javalabra.domain.Ability;
import javalabra.domain.Snowman;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SnowmanTest {
    Snowman snowman;
    
    public SnowmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        snowman = new Snowman();
    }
    
    @After
    public void tearDown() {
    }
        @Test
    public void konstruktoriAsettaaNimenOikein() {
        assertEquals("Snowman", snowman.getName());
    }
    
    @Test
    public void konstruktoriAsettaaKestonOikein() {
        assertTrue(1000 == snowman.getMaxHealth());
    }
    
    @Test
    public void konstruktoriAsettaaNopeudenOikein() {
        assertTrue(45 == snowman.getMaxSpeed());
    }
    
    @Test
    public void konstruktoriAsettaaDamagenOikein() {
        assertTrue(130 == snowman.getMaxDamage());
    }
    
    @Test
    public void metodiDamageTakenToimii() {
        assertTrue(0.75 == snowman.getDamageTaken());
    }
    
    @Test
    public void snowballDamageOikein() {
        Ability testAbility = snowman.snowball();
        assertTrue(testAbility.getDamage() == snowman.getAtmDamage()*2);
    }
    
    @Test
    public void snowballPowerCost() {
        Ability testAbility = snowman.snowball();
        assertTrue(testAbility.getPowerCost() == 0);
    }
    
    @Test
    public void snowballId() {
        Ability testAbility = snowman.snowball();
        assertTrue(testAbility.getId() == 0);
    }
    
    @Test
    public void eatNoseDamageOikein() {
        Ability testAbility = snowman.eatNose();
        assertTrue(testAbility.getDamage() == -500);
    }
    
    @Test
    public void eatNosePowerCost() {
        Ability testAbility = snowman.eatNose();
        assertTrue(testAbility.getPowerCost() == 1);
    }
    
    @Test
    public void eatNoseId() {
        Ability testAbility = snowman.eatNose();
        assertTrue(testAbility.getId() == 1);
    }
    
    @Test
    public void snowstormDamageOnNolla() {
        Ability testAbility = snowman.snowstorm();
        assertTrue(testAbility.getDamage() == 0);
    }
    
    @Test
    public void snowstormPowerCost() {
        Ability testAbility = snowman.snowstorm();
        assertTrue(testAbility.getPowerCost() == 1);
    }
    
    @Test
    public void snowstormId() {
        Ability testAbility = snowman.snowstorm();
        assertTrue(testAbility.getId() == 2);
    }
}
