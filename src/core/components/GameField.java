package core.components;

import core.constants.Field;
import core.objects.Bomb;
import core.objects.Flag;

public class GameField {
    public final Bomb bomb;
    public final Flag flag;
    public Field fieldSize;

    public GameField(Field difficultyValues) {
        fieldSize = difficultyValues;
        bomb = new Bomb(fieldSize.MINES); // generate under bomb map.
        flag = new Flag(); // generate closed and flags map.
        bomb.init();
        flag.init();
    }
}
