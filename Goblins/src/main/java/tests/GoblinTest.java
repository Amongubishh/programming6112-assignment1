package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Goblin;

public class GoblinTest {

    @Test
    public void testSearchGoblinFound() {
        Goblin app = new Goblin(0, "", 0, true);
        Goblin g1 = new Goblin(1, "Goblin One", 50, true);
        Goblin g2 = new Goblin(2, "Goblin Two", 80, false);
        app.goblinList.add(g1);
        app.goblinList.add(g2);

        Goblin result = app.searchGoblinById(2);
        assertNotNull(result);
        assertEquals("Goblin Two", result.getName());
    }

    @Test
    public void testSearchGoblinNotFound() {
        Goblin app = new Goblin(0, "", 0, true);
        Goblin g1 = new Goblin(1, "Goblin One", 50, true);
        app.goblinList.add(g1);

        Goblin result = app.searchGoblinById(5);
        assertNull(result);
    }

    @Test
    public void testUpdateGoblin() {
        Goblin app = new Goblin(0, "", 0, true);
        Goblin g = new Goblin(1, "Old Goblin", 40, true);
        app.goblinList.add(g);

        boolean updated = app.updateGoblinById(1, "New Goblin", 60, false);
        assertTrue(updated);
        Goblin updatedGoblin = app.searchGoblinById(1);
        assertEquals("New Goblin", updatedGoblin.getName());
        assertEquals(60, updatedGoblin.getStrength());
        assertFalse(updatedGoblin.isAlive());
    }

    @Test
    public void testDeleteGoblin() {
        Goblin app = new Goblin(0, "", 0, true);
        Goblin g = new Goblin(1, "Delete Me", 20, true);
        app.goblinList.add(g);

        boolean deleted = app.deleteGoblinById(1);
        assertTrue(deleted);
        assertNull(app.searchGoblinById(1));
    }

    @Test
    public void testDeleteGoblinNotFound() {
        Goblin app = new Goblin(0, "", 0, true);
        boolean deleted = app.deleteGoblinById(99);
        assertFalse(deleted);
    }

    @Test
    public void testStrengthValidation() {
        Goblin app = new Goblin(0, "", 0, true);
        assertTrue(app.isValidStrength(1));
        assertTrue(app.isValidStrength(100));
        assertFalse(app.isValidStrength(0));
        assertFalse(app.isValidStrength(101));
    }
}
