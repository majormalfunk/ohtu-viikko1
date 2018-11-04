/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author jaakkovilenius
 */
public class StatisticsTest {

    private Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    private Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchForExistingPlayer() {
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
    }

    @Test
    public void searchForNonExistentPlayer() {
        Player player = stats.search("Selänne");
        assertTrue(player == null);
    }
    
    @Test
    public void getCorrectNumberofPlayersOfTeam() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }
    
    @Test
    public void getTwoBestScorers() {
        List<Player> players = stats.topScorers(2);
        assertEquals(2, players.size());
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        assertEquals("Gretzky and Lemieux", player1.getName() + " and " +player2.getName());
    }
    
    @Test
    public void ignoreNegativeNumberOfPlayers() {
        List<Player> players = stats.topScorers(-1);
        assertEquals(0, players.size());
    }
}
