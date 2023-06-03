package core.components;

import core.constants.GameState;
import core.constants.Cell;
import core.objects.Coord;
import core.objects.Ranges;
import feature.dialogs.WinDialog;
import utils.Stopwatch;
import utils.Writer;

import java.util.HashSet;
import java.util.Set;

public class GamePlay {
    public Stopwatch stopwatch;
    public GameField field;
    private GameState state;
    public final Writer writer;

    public GamePlay(GameField field) {
        this.field = field;
        this.stopwatch = new Stopwatch();
        this.writer = new Writer(this);
    }


    public Cell getCell(Coord coord) {
        if(field.flag.get(coord) == Cell.OPENED) return field.bomb.get(coord); // If flag-level is opened show lower-level the bomb level
        else return field.flag.get(coord); // else show flag level value
    }

    // gameplay
    private final Set<Coord> checkedCells = new HashSet<>(); // list of checked values
    private void revealAround(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (!checkedCells.contains(around)) {
                checkedCells.add(coord);
                switch (field.flag.get(around)){
                    case FLAGGED: {
                        break;
                    }
                    case CLOSED: {
                        switch (field.bomb.get(around)){
                            case BOMB: {
                                break;
                            }
                            case ZERO: { // reveal ZERO values and go deeper while there is still ZERO values around
                                field.flag.setOpenedToCell(around);
                                revealAround(around);
                                break;
                            }
                            default: {
                                field.flag.setOpenedToCell(around); // else reveal number-value
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
                if (field.bomb.get(coord) != Cell.BOMB){
                    field.flag.setOpenedToCell(coord);
                }
                revealAround(coord);
                setState(GameState.PLAYING);
                break;
            }
            case PLAYING: {
                if (field.flag.get(coord) == Cell.OPENED) {
                    setOpenedToClosedBombCellsAround(coord);
                }
                if (field.flag.get(coord) == Cell.CLOSED) { // if Cell is not revealed already
                    switch (field.bomb.get(coord)) {
                        case BOMB: {
                            lose(coord);
                            break;
                        }
                        case ZERO: {
                            field.flag.setOpenedToCell(coord);
                            revealAround(coord); // reveal around empty space
                            break;
                        }
                        default: {
                            field.flag.setOpenedToCell(coord);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    private void setOpenedToClosedBombCellsAround(Coord coord){
        if(field.bomb.get(coord) != Cell.BOMB)
            if (field.flag.getCountOfFlaggedCellsAround(coord) == field.bomb.get(coord).getNumber())
                for(Coord around : Ranges.getCoordsAround(coord)){
                    if(field.flag.get(around) == Cell.CLOSED) openCell(around);
                }
    }

    private void lose(Coord bombClicked) {
        setState(GameState.LOSE);
        for (Coord coord : Ranges.getCoordsList()) {
            if(field.bomb.get(coord) == Cell.BOMB)
                field.flag.setOpenedToClosedBombCell(coord);
            else
                field.flag.setNoBombToFlaggedCell(coord);
        }
        field.flag.setBombedToCell(bombClicked);
    }
    public void checkWin() {
        if(state == GameState.PLAYING){
            if (field.flag.getCountOfClosedCells() == field.bomb.getTotalBombs()){
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
