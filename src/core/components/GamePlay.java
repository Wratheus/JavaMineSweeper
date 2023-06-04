package core.components;

import core.constants.GameState;
import core.constants.Cell;
import core.objects.Coord;
import core.objects.Ranges;
import utils.Stopwatch;
import utils.Writer;

import java.util.HashSet;
import java.util.Set;

public class GamePlay {
    private final Stopwatch stopwatch;
    private final GameField field;
    private GameState state;
    private final Writer writer;

    public GamePlay(GameField field) {
        this.field = field;
        this.stopwatch = new Stopwatch();
        this.writer = new Writer(this);
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public GameField getField() {
        return field;
    }

    public Writer getWriter() {
        return writer;
    }

    public Cell getCell(Coord coord) {
        if(field.getFlag().get(coord) == Cell.OPENED) return field.getBomb().get(coord); // If flag-level is opened show lower-level the bomb level
        else return field.getFlag().get(coord); // else show flag level value
    }

    // gameplay
    private final Set<Coord> checkedCells = new HashSet<>(); // list of checked values
    private void revealAround(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (!checkedCells.contains(around)) {
                checkedCells.add(coord);
                switch (field.getFlag().get(around)){
                    case FLAGGED: {
                        break;
                    }
                    case CLOSED: {
                        switch (field.getBomb().get(around)){
                            case BOMB: {
                                break;
                            }
                            case ZERO: { // reveal ZERO values and go deeper while there is still ZERO values around
                                field.getFlag().setOpenedToCell(around);
                                revealAround(around);
                                break;
                            }
                            default: {
                                field.getFlag().setOpenedToCell(around); // else reveal number-value
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void openCell(Coord coord) {
        switch (state) {
            case START: {
                if (field.getBomb().get(coord) != Cell.BOMB){
                    field.getFlag().setOpenedToCell(coord);
                }
                revealAround(coord);
                setState(GameState.PLAYING);
                break;
            }
            case PLAYING: {
                if (field.getFlag().get(coord) == Cell.OPENED) {
                    setOpenedToClosedBombCellsAround(coord);
                }
                if (field.getFlag().get(coord) == Cell.CLOSED) { // if Cell is not revealed already
                    switch (field.getBomb().get(coord)) {
                        case BOMB: {
                            lose(coord);
                            break;
                        }
                        case ZERO: {
                            field.getFlag().setOpenedToCell(coord);
                            revealAround(coord); // reveal around empty space
                            break;
                        }
                        default: {
                            field.getFlag().setOpenedToCell(coord);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    private void setOpenedToClosedBombCellsAround(Coord coord){
        if(field.getBomb().get(coord) != Cell.BOMB)
            if (field.getFlag().getCountOfFlaggedCellsAround(coord) == field.getBomb().get(coord).getNumber())
                for(Coord around : Ranges.getCoordsAround(coord)){
                    if(field.getFlag().get(around) == Cell.CLOSED) openCell(around);
                }
    }

    private void lose(Coord bombClicked) {
        setState(GameState.LOSE);
        for (Coord coord : Ranges.getCoordsList()) {
            if(field.getBomb().get(coord) == Cell.BOMB)
                field.getFlag().setOpenedToClosedBombCell(coord);
            else
                field.getFlag().setNoBombToFlaggedCell(coord);
        }
        field.getFlag().setBombedToCell(bombClicked);
    }
    public void checkWin() {
        if(state == GameState.PLAYING){
            if (field.getFlag().getCountOfClosedCells() == field.getBomb().getTotalBombs()){
                setState(GameState.WIN);
            }
        }
    }


    // state management
    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
        if(state == GameState.WIN || state == GameState.LOSE) stopwatch.stop();
    }

}
