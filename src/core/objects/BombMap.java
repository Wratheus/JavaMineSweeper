package core.objects;

import core.constants.Cell;
import core.models.Coord;
import core.models.Matrix;
import core.utils.Ranges;

public class BombMap {
    private Matrix bombMap;
    private final int totalBombs;

    public BombMap(int totalBombs){
        this.totalBombs = fixBombsCount(totalBombs);
    }

    public void init(){
        bombMap = new Matrix(Cell.ZERO);
        placeBomb();
    }
    public Cell get(Coord coord) {
        return bombMap.getCell(coord);
    }
    public int getTotalBombs(){
        return totalBombs;
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
        return Math.min(totalBombs, maxBombs);
    }
    private void placeNumberAroundBombs(Coord bomb) {
        for(Coord around : Ranges.getCoordsAround(bomb))
            if(get(around) != Cell.BOMB)
                bombMap.setCell(around, get(around).getNextNum());
    }
}

