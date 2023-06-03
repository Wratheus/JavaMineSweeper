package core.constants;

import core.objects.Coord;

public class Field {
    private final int IMAGE_SIZE;
    private final Coord SIZE;
    private final int MINES;

    public Field(Coord SIZE, int MINES, int IMAGE_SIZE) {
        this.SIZE = SIZE;
        this.MINES = MINES;
        this.IMAGE_SIZE = IMAGE_SIZE;
    }

    public static Field fieldFactory(GameDifficulty difValue) {
        Field field;
        switch (difValue) {
            default: { // INTERMEDIATE
                field = new Field(new Coord(16, 16), 40, 35);
                break;
            }
            case BEGINNER: {
                field = new Field(new Coord(9, 9), 10, 45);
                break;
            }
            case EXPERT: {
                field = new Field(new Coord(30, 16),99, 30);
                break;
            }
            case INTERMEDIATE: {
                field = new Field(new Coord(16, 16), 40, 30);
                break;
            }
        }
        return field;
    }

    public int getIMAGE_SIZE() {
        return IMAGE_SIZE;
    }

    public Coord getSIZE() {
        return SIZE;
    }

    public int getMINES() {
        return MINES;
    }
}
