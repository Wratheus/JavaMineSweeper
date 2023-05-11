package core.constants;

import core.components.Coord;
import core.components.Ranges;

public final class Field {

    // Prevents instantiation
    private Field() {}
    public enum GameDifficulty{
        BEGINNER, // 9x9 FIElD, 10 MINES
        INTERMEDIATE, // 16X16, 40 MINES
        EXPERT // 40X16, 99 MINES
    }
    public static final int IMAGE_SIZE = 50;
    public static final Coord BEGINNER = new Coord(9, 9);
    public static final int BEGINNER_MINES = 10;

    public static final Coord INTERMEDIATE = new Coord(16, 16);
    public static final int INTERMEDIATE_MINES = 40;

    public static final Coord EXPERT = new Coord(30, 16);
    public static final int EXPERT_MINES = 99;
}
