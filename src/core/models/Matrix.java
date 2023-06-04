package core.models;

import core.constants.Cell;
import utils.Ranges;

public class Matrix {
    private final Cell [][] matrix;

    public Matrix(Cell defaulCell){
        matrix = new Cell[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for (Coord coord : Ranges.getCoordsList()) {
            matrix [coord.getX()][coord.getY()] = defaulCell;
        }
    }

    public Cell getCell (Coord coord){
        return Ranges.inRange(coord) ? matrix[coord.getX()][coord.getY()] : null;
    }

    public void setCell (Coord coord, Cell value){
        if (Ranges.inRange(coord)) matrix[coord.getX()][coord.getY()] = value;
    }
}
