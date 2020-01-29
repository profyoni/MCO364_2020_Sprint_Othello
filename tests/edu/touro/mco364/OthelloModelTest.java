package edu.touro.mco364;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OthelloModelTest {

    @Test
    void isLocationAvailable() {
        OthelloModel model = new OthelloModel(); // Arrange

        boolean actual = model.isLocationAvailable(4,4);
        assertEquals(true, actual );
    }

    @Test
    void isLocationAvailableOffGrid() {
        OthelloModel model = new OthelloModel(); // Arrange

        boolean actual = model.isLocationAvailable(-4,4);
        assertEquals(false, actual );
    }
}