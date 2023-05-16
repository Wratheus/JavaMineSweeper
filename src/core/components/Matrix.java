package core.components;

import core.constants.Cell;

class Matrix {
    private final Cell [][] matrix;

    Matrix (Cell defaulCell){
        matrix = new Cell[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for (Coord coord : Ranges.getCoordsList()) {
            matrix [coord.getX()][coord.getY()] = defaulCell;
        }
    }

    Cell getCell (Coord coord){
        return Ranges.inRange(coord) ? matrix[coord.getX()][coord.getY()] : null;
    }

    void setCell (Coord coord, Cell value){
        if (Ranges.inRange(coord)) matrix[coord.getX()][coord.getY()] = value;
    }
}
