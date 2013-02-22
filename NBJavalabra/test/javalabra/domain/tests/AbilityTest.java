
package javalabra.domain.tests;

import javalabra.domain.Ability;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbilityTest {
    Ability dmgAbility;
    Ability healAbility;
    Ability muuAbility;
    public AbilityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
            dmgAbility = new Ability("Test damage",100,0,"Testing",0);
            healAbility = new Ability("Test heal",-100,1,"Testing",0);
            muuAbility = new Ability("Test other",0,2,"Testing",1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void saakoDamagen() {
        assertTrue(dmgAbility.getDamage() == 100);
        assertTrue(healAbility.getDamage() == -100);
        assertTrue(muuAbility.getDamage() == 0);
    }
    
    @Test
    public void saakoPowerCostin() {
        assertTrue(dmgAbility.getPowerCost() == 0);
        assertTrue(healAbility.getPowerCost() == 0);
        assertTrue(muuAbility.getPowerCost() == 1);
    }
}
