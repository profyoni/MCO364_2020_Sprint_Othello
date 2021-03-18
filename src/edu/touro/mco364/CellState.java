package edu.touro.mco364;

public enum CellState {
    NONE, BLACK, WHITE;

    public String toIcon() {
        if (this == CellState.WHITE) return "\u25CB";
        if (this == CellState.BLACK) return "\u2B24";
        return "";
    }

    public CellState opponent() {
        switch (this) {
            case BLACK:
                return WHITE;
            case WHITE:
                return BLACK;
            default:
                return NONE;
        }
    }
}
