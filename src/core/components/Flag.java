package core.components;

import core.constants.Cell;

public class Flag {
    Matrix flagMap;
    int totalFlags;
    int countOfClosedBoxes;

    public void init(){
        flagMap = new Matrix(Cell.CLOSED);
    }

    public Cell get(Coord coord){
        return flagMap.getCell(coord);
    }

    public void setOpenedToCell(Coord coord){
        flagMap.setCell(coord, Cell.OPENED);
    }

    void setBombedToBox(Coord coord){
        flagMap.setCell(coord, Cell.BOMBED);
    }

    public void setFlaggedToBox(Coord coord){
        if ((flagMap.getCell(coord) == Cell.FLAGGED)) {
            flagMap.setCell(coord, Cell.CLOSED);
        } else {
            flagMap.setCell(coord, Cell.FLAGGED);
        }
    }

    void setNoBombToFlaggedSafeBox(Coord coord){
        flagMap.setCell(coord, Cell.NOBOMB);
    }

    void setOpenedToClosedBombBox(Coord coord){
        flagMap.setCell(coord, Cell.OPENED);
    }

    int getCountOfClosedBoxes(Coord coord){
        for (Coord coord1 : Ranges.getCoordsList()) {
            if (flagMap.getCell(coord1) == Cell.CLOSED) countOfClosedBoxes++;
        }
        return countOfClosedBoxes;
    }

    int getCountOfFlaggedBoxesAround(Coord coord){
        int counter = 0;
        for(Coord around : Ranges.getCoordsAround(coord)){
            if (flagMap.getCell(around) == Cell.FLAGGED) counter++;
        }
        return counter;
    }

    int getTotalFlags(Coord coord){
        for (Coord coord1 : Ranges.getCoordsList()) {
            if (flagMap.getCell(coord1) == Cell.CLOSED) totalFlags++;
        }
        return totalFlags;
    }
}
