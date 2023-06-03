package core.models;

import core.constants.Field;

import java.io.Serializable;

public final class Record implements Serializable {
    String name;
    int time;
    String size;
    int mines;

    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }
    public String getSize() {
        return size;
    }

    public int getMines() {
        return mines;
    }


    public Record(String name, int time, Field field) {
        this.name = name;
        this.time = time;
        this.size = field.SIZE.getX() + "x" + field.SIZE.getY();
        this.mines = field.MINES;
    }
}
