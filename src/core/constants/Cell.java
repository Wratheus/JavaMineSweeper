package core.constants;

public enum Cell {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGGED,
    BOMBED,
    NOBOMB;

    public Object image;
    public Cell getNextNum() {
        return Cell.values()[this.ordinal() + 1];
    }
    public int getNumber() { return this.ordinal(); }
}
