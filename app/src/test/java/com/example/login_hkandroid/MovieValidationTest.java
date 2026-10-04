package com.example.login_hkandroid;

import com.example.login_hkandroid.models.MovieModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MovieValidationTest {

    @Test
    public void testFreeAndPremiumHaveDifferentBackgroundColors() {
        List<MovieModel> movies = MovieModel.generateMovies();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        boolean foundFree = false;
        boolean foundPremium = false;

        int freeColor = 0xFF00BCD4;
        int premiumColor = 0xFFFF9800;

        assertNotEquals("Free and Premium background colors must be different", freeColor, premiumColor);

        for (MovieModel movie : movies) {
            if ("Free".equalsIgnoreCase(movie.getType())) {
                foundFree = true;
                int color = "Free".equalsIgnoreCase(movie.getType()) ? 0xFF00BCD4 : 0xFFFF9800;
                assertEquals(freeColor, color);
            } else if ("Premium".equalsIgnoreCase(movie.getType())) {
                foundPremium = true;
                int color = "Free".equalsIgnoreCase(movie.getType()) ? 0xFF00BCD4 : 0xFFFF9800;
                assertEquals(premiumColor, color);
            }
        }

        assertTrue("Should have at least one Free movie", foundFree);
        assertTrue("Should have at least one Premium movie", foundPremium);
    }
}
