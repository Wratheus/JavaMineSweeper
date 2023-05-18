package core.constants;

import core.components.Coord;

public final class Field {
    public int IMAGE_SIZE;

    public Coord SIZE;
    public int MINES;

    public enum GameDifficulty {
        BEGINNER, // 9x9 FIElD, 10 MINES
        INTERMEDIATE, // 16X16, 40 MINES
        EXPERT, // 40X16, 99 MINES
    }

    public Field(GameDifficulty difValue) {
        switch (difValue) {
            default: { // INTERMEDIATE
                SIZE = new Coord(16, 16);
                MINES = 40;
                IMAGE_SIZE = 35;
                break;
            }
            case BEGINNER: {
                SIZE = new Coord(9, 9);
                MINES = 10;
                IMAGE_SIZE = 45;
                break;
            }
            case EXPERT: {
                SIZE = new Coord(30, 16);
                MINES = 99;
                IMAGE_SIZE = 30;
                break;
            }
            case INTERMEDIATE: {
                SIZE = new Coord(16, 16);
                MINES = 40;
                IMAGE_SIZE = 30;
                break;
            }
        }
    }
}
