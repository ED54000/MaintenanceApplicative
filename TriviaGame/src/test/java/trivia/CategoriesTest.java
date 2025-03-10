package trivia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriesTest {

    @Test
    public void testEnumValues() {
        assertEquals(4, Categories.values().length);
        assertEquals(Categories.POP, Categories.valueOf("POP"));
        assertEquals(Categories.SCIENCE, Categories.valueOf("SCIENCE"));
        assertEquals(Categories.SPORT, Categories.valueOf("SPORT"));
        assertEquals(Categories.ROCK, Categories.valueOf("ROCK"));
    }

    @Test
    public void testStringRepresentation() {
        assertEquals("Pop", Categories.POP.toString());
        assertEquals("Science", Categories.SCIENCE.toString());
        assertEquals("Sports", Categories.SPORT.toString());
        assertEquals("Rock", Categories.ROCK.toString());
    }

    @Test
    public void testOrdinals() {
        assertEquals(0, Categories.POP.ordinal());
        assertEquals(1, Categories.SCIENCE.ordinal());
        assertEquals(2, Categories.SPORT.ordinal());
        assertEquals(3, Categories.ROCK.ordinal());
    }

    @Test
    public void testCompareTo() {
        assertTrue(Categories.POP.compareTo(Categories.SCIENCE) < 0);
        assertTrue(Categories.SCIENCE.compareTo(Categories.POP) > 0);
        assertEquals(0, Categories.ROCK.compareTo(Categories.ROCK));
    }

    @Test
    public void testEquality() {
        assertSame(Categories.POP, Categories.valueOf("POP"));
        assertEquals(Categories.SCIENCE, Categories.valueOf("SCIENCE"));
        assertNotEquals(Categories.SPORT, Categories.ROCK);
    }
}