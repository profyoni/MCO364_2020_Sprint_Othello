package edu.touro.mco364;

import java.awt.*;
import java.util.Arrays;

public class OthelloModel
{
    CellState [][]grid;
    public static final int GRID_SIZE = 8;
    private CellState playerTurn = CellState.WHITE;
    int moveCount = 0;

    private Point[] deltas = new Point[]{
            new Point(0,1), new Point(1,0),
            new Point(0,-1), new Point(-1,0),
            new Point(1,1), new Point(-1,-1),
            new Point(1,-1), new Point(-1,1)};

    public OthelloModel()
    {
        grid = new CellState[GRID_SIZE][GRID_SIZE];
        for (int i=0;i<grid.length;i++)
            Arrays.fill(grid[i],CellState.NONE);

        grid[3][4] = grid[4][3] = CellState.BLACK;
        grid[3][3] = grid[4][4] = CellState.WHITE;
    }

    private CellState toggleTurn()
    {
        playerTurn = getCurrentPlayer().opponent();
        return playerTurn;
    }

    public int makeMove(int row, int col, CellState state, boolean actual)
    {
        if (state == CellState.NONE)
            throw new IllegalArgumentException("CellState may not be set to NONE during game");
        if ( ! isMoveLegal(row, col, state))
            return -1;

        Point loc = new Point(row, col);
        boolean flippable = false;
        int totalFlips = 0;
        for (Point dir: deltas)
        {
            if (isDirectionFlippable(loc, state, dir))
            {
                totalFlips += makeFlips(loc, state, dir, actual);
            }
        }
        if (totalFlips == 0)
            return 0;

        if (actual)
        {
            grid[row][col] = state;
            toggleTurn();
            moveCount++;
        }
        return totalFlips;
    }
    public boolean isMoveLegal(int row, int col, CellState state)
    {
        return isLocationInBoundsAndUnoccupied(row, col);
    }

    /**
     * Precondition that dir is flippable
     * @param loc
     * @param state
     * @param dir
     * @param actual
     * @return
     */
    private int makeFlips(Point loc, CellState state, Point dir, boolean actual) {
        CellState opponent = getCurrentPlayer().opponent();
        int dirFlipped = 0;
        Point loc2 = new Point(loc);
        loc2.translate(dir.x, dir.y);
        while (isInBounds(loc2.x, loc2.y) && grid[loc2.x][loc2.y] == opponent) {
            if (actual)
            {
                grid[loc2.x][loc2.y] = playerTurn;
            }
            dirFlipped++;
            loc2.translate(dir.x, dir.y);
        }
        return dirFlipped;
    }
    private boolean isDirectionFlippable(Point loc, CellState state, Point dir) {
        CellState opponent = getCurrentPlayer().opponent();
        int dirFlipped = -1;
        Point loc2 = new Point(loc);
        do {
            loc2.translate(dir.x, dir.y);
            dirFlipped++;
        } while (isInBounds(loc2.x, loc2.y) && grid[loc2.x][loc2.y] == opponent);
        if (dirFlipped > 0 && isInBounds(loc2.x, loc2.y) && grid[loc2.x][loc2.y] == playerTurn)
            return true;
        return false;
    }

//    public boolean isMoveFlippable(int row, int col, CellState state) {
//        Point loc = new Point(row, col);
//        for (Point d: deltas)
//        {
//            if (isDirectionFlippable(loc,state, d))
//                return true;
//        }
//        return false;
//    }

    private boolean isInBounds(int row, int col) {
        return (row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE);
    }

    public boolean isLocationInBoundsAndUnoccupied(int row, int col)
    {
        return isInBounds(row, col) && grid[row][col] == CellState.NONE;
    }


    public CellState getCellState(int row, int col) {
        if (! isInBounds(row, col))
        {
            throw new IllegalArgumentException(String.format("row [%d], col [%d] not in bounds 0-7",row,col));
        }
        return grid[row][col];
    }
    public boolean isGameOver()
    {
        return moveCount == GRID_SIZE * GRID_SIZE;
    }
    public CellState winning()
    {
        int whites=0, blacks=0;
        for (int i=0;i<grid.length;i++)
        for (int j=0;j<grid.length;j++) {
            if (grid[i][j] == CellState.BLACK)
                blacks++;
            if (grid[i][j] == CellState.WHITE)
                whites++;
        }
        if (blacks > whites) return CellState.BLACK;
        if (blacks < whites) return CellState.WHITE;
        return CellState.NONE;
    }

    public CellState getCurrentPlayer() {
        return playerTurn;
    }

    public Point findBestMove()
    {
        Point bestMove = null;
        int flips = -1;
        for (int i=0;i<grid.length;i++)
        for (int j=0;j<grid.length;j++) {
            Point currentMove = new Point(i, j);
            if (! isMoveLegal(i,j,getCurrentPlayer()))
                continue;
            int totalFlips = 0;

            for (Point dir : deltas) {
                if (! isDirectionFlippable(currentMove, getCurrentPlayer(), dir))
                    continue;
                totalFlips += makeFlips(currentMove, getCurrentPlayer(), dir, false);
            }
            if (totalFlips > flips) {
                flips = totalFlips;
                bestMove = currentMove;
            }

        }
        return bestMove;
    }
}