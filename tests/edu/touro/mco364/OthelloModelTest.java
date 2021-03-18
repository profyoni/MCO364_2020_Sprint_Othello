package edu.touro.mco364;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class OthelloModelTest {
    private OthelloModel model = new OthelloModel();

    @Test
    void isLocationAvailable() {
        boolean actual = model.isLocationInBoundsAndUnoccupied(2,3);
        assertEquals(true, actual );
    }

    @Test
    void isLocationAvailableOffGrid() {
        boolean actual = model.isLocationInBoundsAndUnoccupied(-4,4);
        assertEquals(false, actual );
    }

    @Test
    void isMoveLegal1() {
        boolean actual = model.isMoveLegal(3,5, CellState.WHITE);
        assertEquals(true, actual );

        actual = model.isMoveLegal(2,4, CellState.WHITE);
        assertEquals(true, actual );
    }


    @Test
    void makeMove1() {
        int actual = model.makeMove(3,5, CellState.WHITE,true);
        assertEquals(1, actual );
        assertEquals( CellState.WHITE, model.getCellState(3,4));

        actual = model.makeMove(4,5, CellState.BLACK, true);
        assertEquals(1, actual );
        assertEquals( CellState.BLACK, model.getCellState(4,4));
        assertEquals( CellState.WHITE, model.getCellState(3,3));
        assertEquals( CellState.WHITE, model.getCellState(3,4));
        assertEquals( CellState.WHITE, model.getCellState(3,5));
    }

    @Test
    void makeMoveMultipleFlips() {
        model.grid[1][0]=CellState.WHITE;
        model.grid[1][1]=CellState.BLACK;
        model.grid[1][2]=CellState.BLACK;

        int actual = model.makeMove(1,3, CellState.WHITE, true);
        assertEquals(2, actual );
        assertEquals( CellState.WHITE, model.getCellState(1,1));
        assertEquals( CellState.WHITE, model.getCellState(1,2));
    }
    @Test
    void makeMoveIllegal1() {
        int actual = model.makeMove(0,0, CellState.WHITE, true);
        assertEquals(0, actual );
        assertEquals( CellState.NONE, model.getCellState(0,0));
    }
    @Test

    void findBestMove() {
        model.grid[0][0]=CellState.WHITE;
        model.grid[0][1]=CellState.BLACK;
        model.grid[0][2]=CellState.BLACK;
        Point actual = model.findBestMove();
        assertEquals(new Point(0,3), actual );
    }
}