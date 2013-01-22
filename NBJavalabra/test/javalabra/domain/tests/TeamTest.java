package javalabra.domain.tests;


import javalabra.domain.Santa;
import javalabra.domain.Team;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {
    Team team;
    
    public TeamTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaOikeanNimen() {
        assertEquals(team.getNimi(),"Test team");
    }
    
    @Test
    public void konstruktoriAlustaaJasenlistan() {
        assertTrue(team.getTeam() != null);
    }
    
    @Test
    public void joukkueeseenLisaaminenLisaaHahmon() {
        Santa santa = new Santa(team);
        team.lisaaJoukkueeseen(santa);
        assertTrue(santa == team.getTeam().get(0));
    }
    
    @Test
    public void joukkueeseenLisaaminenLisaaHahmonKerran() {
        Santa santa = new Santa(team);
        team.lisaaJoukkueeseen(santa);
        assertTrue(team.getTeam().size() == 1);
    }
}
