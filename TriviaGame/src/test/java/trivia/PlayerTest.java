
package trivia;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {

    @Test
    public void testPlayerCreation() {

        Player player = new Player("John");

        assertEquals("John", player.getName());
        assertEquals(1, player.getPlayerPosition());
        assertEquals(0, player.getScore());
        assertFalse(player.getInPenaltyBox());
    }

    @Test
    public void testSetPlayerPosition() {
        Player player = new Player("Jane");

        player.setPlayerPosition(5);
        assertEquals(5, player.getPlayerPosition());

        player.setPlayerPosition(11);
        assertEquals(11, player.getPlayerPosition());
    }

    @Test
    public void testSetScore() {
        Player player = new Player("Alice");
        player.setScore(3);
        assertEquals(3, player.getScore());
        player.setScore(6);
        assertEquals(6, player.getScore());
    }

    @Test
    public void testIncrementScore() {
        Player player = new Player("Bob");
        player.incrementScore();
        assertEquals(1, player.getScore());
        player.incrementScore();
        assertEquals(2, player.getScore());
    }

    @Test
    public void testSetInPenaltyBox() {
        Player player = new Player("Charlie");
        assertFalse(player.getInPenaltyBox());
        player.setInPenaltyBox(true);
        assertTrue(player.getInPenaltyBox());
        player.setInPenaltyBox(false);
        assertFalse(player.getInPenaltyBox());
    }

    @Test
    public void testToString() {
        Player player = new Player("Dave");

        assertEquals("Dave", player.toString());
    }

    @Test
    public void testMultipleOperations() {
        Player player = new Player("Eve");
        player.setPlayerPosition(3);
        player.incrementScore();
        player.setInPenaltyBox(true);

        assertEquals("Eve", player.getName());
        assertEquals(3, player.getPlayerPosition());
        assertEquals(1, player.getScore());
        assertTrue(player.getInPenaltyBox());
    }
}
