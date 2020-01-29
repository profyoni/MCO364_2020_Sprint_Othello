package edu.touro.mco364;

public class OthelloModel
{
    private CellState [][]grid;
    private final int GRID_SIZE = 8;
    public OthelloModel()
    {
        grid = new CellState[GRID_SIZE][GRID_SIZE];
        for (int i=0;i<grid.length;i++)
            for (int j=0;j<grid[i].length;j++)
                grid[i][j] = CellState.NONE;
    }


    public boolean makeMove(int row, int col, CellState state)
    {
        //TODO validity check
        grid[row][col] = state;
        return true;
    }
    public boolean isMoveLegal(int row, int col, CellState state)
    {
        return isLocationAvailable(row, col) && isMoveFlippable(row, col, state);
    }

    boolean isMoveFlippable(int row, int col, CellState state) {
        throw new UnsupportedOperationException();
    }

    boolean isLocationAvailable(int row, int col)
    {
        if (row < 0 || row >= GRID_SIZE)
            return false;
        return grid[row][col] == CellState.NONE;
    }

}