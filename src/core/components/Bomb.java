package core.components;

import core.constants.Cell;

public class Bomb {
    private Matrix bombMap;
    private final int totalBombs;

    public Bomb(int totalBombs){
        this.totalBombs = fixBombsCount(totalBombs);
    }

    public void init(){
        bombMap = new Matrix(Cell.ZERO);
        placeBomb();
    }
    public Cell get(Coord coord) {
        return bombMap.getCell(coord);
    }

    private void placeBomb() {
        int i = 0;
        while (i < totalBombs) {
            Coord bomb = Ranges.getRandomCoord();
            if(get(bomb) != Cell.BOMB){
                bombMap.setCell(bomb, Cell.BOMB);
                placeNumberAroundBombs(bomb);
                i++;
            }


        }
    }
    private int fixBombsCount(int totalBombs){
        int maxBombs = (Ranges.getSize().getX() * Ranges.getSize().getY()) / 2;  // max = square / 2
        return (totalBombs <= maxBombs) ?  totalBombs : maxBombs;
    }
    private void placeNumberAroundBombs(Coord bomb) {
        for(Coord around : Ranges.getCoordsAround(bomb))
            if(get(around) != Cell.BOMB)
                bombMap.setCell(around, get(around).getNextNum());
    }
}

