package core;

import core.components.Bomb;
import core.components.Coord;
import core.components.Flag;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;
import core.constants.GameDifficulty;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final Bomb bomb;
    public final Flag flag;
    public Field difficultyValues;
    private GameState state;

    public Game (GameDifficulty difficulty){
        difficultyValues = new Field(difficulty);
        Ranges.setSize(difficultyValues.SIZE); // create field instance to get SIZE for ranges.
        bomb = new Bomb(difficultyValues.MINES); // generate under bomb map.
        flag = new Flag(); // generate closed and flags map.
    }
    public void start(){
        bomb.init();
        flag.init();
        state = GameState.START;
    }

    public Cell getCell(Coord coord) {
        if(flag.get(coord) == Cell.OPENED) return bomb.get(coord); // If flag-level is opened show lower-level the bomb level
        else return flag.get(coord); // else show flag level value
    }

    // gameplay
    public void pressedLeftButton(Coord coord) {
        openCell(coord);
        checkWin();
    }
    public void pressedRightButton(Coord coord) {
        if (state != GameState.LOSE) {
            flag.setFlaggedToCell(coord);
            checkWin();
        }
    }

    // state management
    public GameState getState() {
        return state;
    }
    private void setState(GameState state) {
        this.state = state;
    }

    private void lose(Coord bombClicked) {
        setState(GameState.LOSE);
        for (Coord coord : Ranges.getCoordsList()) {
            if(bomb.get(coord) == Cell.BOMB)
                flag.setOpenedToClosedBombCell(coord);
            else
                flag.setNoBombToFlaggedCell(coord);
        }
        flag.setBombedToCell(bombClicked);
    }
    private void checkWin() {
        if(state == GameState.PLAYING){
            if(flag.getTotalFlags() == bomb.getTotalBombs()) {
                if (flag.getCountOfClosedCells() == bomb.getTotalBombs()){
                    setState(GameState.WIN);
                }
            }
        }
    }

    // gameplay
    private final Set<Coord> checkedCells = new HashSet<>(); // list of checked values
    private void revealAround(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (!checkedCells.contains(around)) {
                checkedCells.add(coord);
                switch (flag.get(around)){
                    case FLAGGED: {
                        break;
                    }
                    case CLOSED: {
                        switch (bomb.get(around)){
                            case BOMB: {
                                break;
                            }
                            case ZERO: { // reveal ZERO values and go deeper while there is still ZERO values around
                                flag.setOpenedToCell(around);
                                revealAround(around);
                                break;
                            }
                            default: {
                                flag.setOpenedToCell(around); // else reveal number-value
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    private void openCell(Coord coord) {
        switch (state) {
            case START: {
                if (bomb.get(coord) != Cell.BOMB){
                    flag.setOpenedToCell(coord);
                }
                revealAround(coord);
                setState(GameState.PLAYING);
                break;
            }
            case PLAYING: {
                if (flag.get(coord) == Cell.OPENED) {
                    setOpenedToClosedBombCellsAround(coord);
                }
                if (flag.get(coord) == Cell.CLOSED) { // if Cell is not revealed already
                    switch (bomb.get(coord)) {
                        case BOMB: {
                            lose(coord);
                            break;
                        }
                        case ZERO: {
                            flag.setOpenedToCell(coord);
                            revealAround(coord); // reveal around empty space
                            break;
                        }
                        default: {
                            flag.setOpenedToCell(coord);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    private void setOpenedToClosedBombCellsAround(Coord coord){
        if(bomb.get(coord) != Cell.BOMB)
            if (flag.getCountOfFlaggedCellsAround(coord) == bomb.get(coord).getNumber())
                for(Coord around : Ranges.getCoordsAround(coord)){
                    if(flag.get(around) == Cell.CLOSED) openCell(around);
                }
    }
}
