
package javalabra.logiikka.tests;

import javalabra.UI.GUI;
import javalabra.domain.Ability;
import javalabra.domain.Santa;
import javalabra.logiikka.Battle;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BattleTest {
    Battle battle;
    public BattleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        battle = new Battle(new Santa(),new Santa(),new GUI());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void checkIfAliveToimiiJosElossa() {
        assertTrue(battle.checkIfAlive(battle.getPlayer()));
    }
    
    @Test
    public void checkIfAliveToimiiJosKuollut() {
        battle.getPlayer().setAtmHealth(0);
        assertTrue(!battle.checkIfAlive(battle.getPlayer()));
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(battle.getPlayer() != null);
        assertTrue(battle.getPlayer2() != null);
    }
    
    @Test
    public void healthReductionToimii() {
        battle.getPlayer().setAtmHealth(20000);
        battle.getPlayer2().setAtmHealth(20000);
        battle.reduceHealthToMax(battle.getPlayer(), battle.getPlayer2());
        assertTrue(battle.getPlayer().getAtmHealth() == battle.getPlayer().getMaxHealth());
        assertTrue(battle.getPlayer2().getAtmHealth() == battle.getPlayer2().getMaxHealth());
    }
    
    @Test
    public void damageToimiiVakiolla() {
        battle.damage(battle.getPlayer(), 100);
        assertTrue(battle.getPlayer().getAtmHealth() == battle.getPlayer().getMaxHealth()-
                100*battle.getPlayer().getDamageTaken());
    }
    
    @Test
    public void damageToimiiAbilitylla() {
        battle.damage(battle.getPlayer(), new Ability("",100,0,"",0));
        assertTrue(battle.getPlayer().getAtmHealth() == battle.getPlayer().getMaxHealth()-
                100*battle.getPlayer().getDamageTaken());
    }
    
    @Test
    public void healToimiiVakiolla() {
        battle.getPlayer().setAtmHealth(50);
        battle.heal(battle.getPlayer(), 100);
        assertTrue(battle.getPlayer().getAtmHealth() == 150);
    }
    
    @Test
    public void healToimiiAbilitylla() {
        battle.getPlayer().setAtmHealth(50);
        battle.heal(battle.getPlayer(), new Ability("",-100,0,"",0));
        assertTrue(battle.getPlayer().getAtmHealth() == 150);
    }
}
