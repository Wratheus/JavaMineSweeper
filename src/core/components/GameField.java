package core.components;

import core.constants.Field;
import core.objects.Bomb;
import core.objects.Flag;

public class GameField {
    private final Bomb bomb;
    private final Flag flag;
    private final Field fieldSize;

    public Bomb getBomb() {
        return bomb;
    }

    public Flag getFlag() {
        return flag;
    }

    public Field fieldSize() {
        return fieldSize;
    }


    public GameField(Field difficultyValues) {
        fieldSize = difficultyValues;
        bomb = new Bomb(fieldSize.getMINES()); // generate under bomb map.
        flag = new Flag(); // generate closed and flags map.
        bomb.init();
        flag.init();
    }
}
