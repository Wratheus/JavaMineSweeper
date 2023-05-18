package core.constants;

import core.components.Coord;

public final class Field {
    public static int IMAGE_SIZE = 50;

    public Coord SIZE;
    public int MINES;

    public enum GameDifficulty {
        BEGINNER, // 9x9 FIElD, 10 MINES
        INTERMEDIATE, // 16X16, 40 MINES
        EXPERT, // 40X16, 99 MINES
        CUSTOM
    }

    public Field(GameDifficulty difValue) {
        switch (difValue) {
            default: { // INTERMEDIATE
                SIZE = new Coord(16, 16);
                MINES = 40;
                break;
            }
            case BEGINNER: {
                SIZE = new Coord(9, 9);
                MINES = 10;
                break;
            }
            case EXPERT: {
                SIZE = new Coord(30, 16);
                MINES = 99;
                break;
            }
            // TODO: custom dif
            case CUSTOM: {
                SIZE = null;
                MINES = 0;
                break;
            }
        }
    }
}
