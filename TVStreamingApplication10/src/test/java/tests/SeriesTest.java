package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Series;

public class SeriesTest {

    @Test
    public void testSearchSeriesFound() {
        Series app = new Series(0, "", 0, 0);
        Series s1 = new Series(1, "Series One", 12, 10);
        Series s2 = new Series(2, "Series Two", 15, 8);
        app.seriesList.add(s1);
        app.seriesList.add(s2);

        Series result = app.searchSeriesById(2);
        assertNotNull(result);
        assertEquals("Series Two", result.getName());
    }

    @Test
    public void testSearchSeriesNotFound() {
        Series app = new Series(0, "", 0, 0);
        Series s1 = new Series(1, "Series One", 12, 10);
        app.seriesList.add(s1);

        Series result = app.searchSeriesById(5);
        assertNull(result);
    }

    @Test
    public void testUpdateSeries() {
        Series app = new Series(0, "", 0, 0);
        Series s = new Series(1, "Old Name", 10, 5);
        app.seriesList.add(s);

        boolean updated = app.updateSeriesById(1, "New Name", 12, 8);
        assertTrue(updated);
        Series updatedSeries = app.searchSeriesById(1);
        assertEquals("New Name", updatedSeries.getName());
        assertEquals(12, updatedSeries.getAgeRestriction());
        assertEquals(8, updatedSeries.getNumberOfEpisodes());
    }

    @Test
    public void testDeleteSeries() {
        Series app = new Series(0, "", 0, 0);
        Series s = new Series(1, "Delete Me", 10, 5);
        app.seriesList.add(s);

        boolean deleted = app.deleteSeriesById(1);
        assertTrue(deleted);
        assertNull(app.searchSeriesById(1));
    }

    @Test
    public void testDeleteSeriesNotFound() {
        Series app = new Series(0, "", 0, 0);
        boolean deleted = app.deleteSeriesById(99);
        assertFalse(deleted);
    }

    @Test
    public void testSeriesAgeRestrictionValid() {
        Series app = new Series(0, "", 0, 0);
        assertTrue(app.isValidAgeRestriction(5));
        assertTrue(app.isValidAgeRestriction(18));
        assertTrue(app.isValidAgeRestriction(2));
    }

    @Test
    public void testSeriesAgeRestrictionInvalid() {
        Series app = new Series(0, "", 0, 0);
        assertFalse(app.isValidAgeRestriction(1));
        assertFalse(app.isValidAgeRestriction(19));
        assertFalse(app.isValidAgeRestriction(100));
    }
}
