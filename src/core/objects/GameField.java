package core.objects;

import core.models.Field;

public class GameField {
    private final BombMap bomb;
    private final FlagMap flag;
    private final Field fieldSize;

    public BombMap getBomb() {
        return bomb;
    }

    public FlagMap getFlag() {
        return flag;
    }

    public Field fieldSize() {
        return fieldSize;
    }


    public GameField(Field difficultyValues) {
        fieldSize = difficultyValues;
        bomb = new BombMap(fieldSize.getMINES()); // generate under bomb map.
        flag = new FlagMap(); // generate closed and flags map.
        bomb.init();
        flag.init();
    }
}
