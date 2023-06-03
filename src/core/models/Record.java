package core.models;

import java.io.Serializable;

public final class Record implements Serializable {
    String name;
    int time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int mine;
    int width;
    int height;

    public Record(String name, int time, int mine, int width, int height) {
        this.name = name;
        this.time = mine;
        this.mine = mine;
        this.width = width;
        this.height = height;
    }
}
