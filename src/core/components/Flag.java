package core.components;

import core.constants.Cell;

public class Flag {
    Matrix flagMap;
    int countOfClosedCells;
    int countOfFlaggedCells;

    public void init(){
        flagMap = new Matrix(Cell.CLOSED);
        countOfClosedCells = Ranges.getSize().getX() * Ranges.getSize().getY();
        countOfFlaggedCells = 0;
    }

    public Cell get(Coord coord){
        return flagMap.getCell(coord);
    }

    public void setOpenedToCell(Coord coord){
        flagMap.setCell(coord, Cell.OPENED);
        countOfClosedCells--;
    }

    public void setFlaggedToCell(Coord coord){
        if ((flagMap.getCell(coord) == Cell.FLAGGED)) {
            flagMap.setCell(coord, Cell.CLOSED);
            countOfFlaggedCells--;
        } else if(flagMap.getCell(coord) == Cell.CLOSED){
            flagMap.setCell(coord, Cell.FLAGGED);
            countOfFlaggedCells++;
        }
    }

    public void setBombedToCell(Coord coord){
        flagMap.setCell(coord, Cell.BOMBED);
    }
    public void setNoBombToFlaggedCell(Coord coord){
        if(flagMap.getCell(coord) == Cell.FLAGGED)
            flagMap.setCell(coord, Cell.NOBOMB);
    }

    public void setOpenedToClosedBombCell(Coord coord){
        if(flagMap.getCell(coord) == Cell.CLOSED)
            flagMap.setCell(coord, Cell.OPENED);
    }
    public int getCountOfClosedCells(){
        return countOfClosedCells;
    }

    public int getTotalFlags(){
        return countOfFlaggedCells;
    }



    public int getCountOfFlaggedCellsAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord)){
            if(flagMap.getCell(around) == Cell.FLAGGED) {
                count++;
            }
        }
        return count;
    }
}
