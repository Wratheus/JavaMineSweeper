package core.models;

import java.io.Serializable;

public final class Record implements Serializable {
    private final String name;
    private final int time;
    private final String size;
    private final int mines;

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
        this.size = field.getSIZE().getX() + "x" + field.getSIZE().getY();
        this.mines = field.getMINES();
    }
}
