package core;

import core.components.Bomb;
import core.components.Coord;
import core.components.Flag;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// TODO: show all bombs after LOSE
// TODO: num click
// FACADE main game class
public class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState state;

    public Game (Field.GameDifficulty difficulty){
        Field difficultyLevel = new Field(difficulty); // set difficulty constants depends on chosen diff.
        Ranges.setSize(difficultyLevel.SIZE); // create field instance to get SIZE for ranges.
        bomb = new Bomb(difficultyLevel.MINES); // generate under bomb map.
        flag = new Flag(); // generate closed and flags map.
    }
    public void start(){
        bomb.init();
        flag.init();
        state = GameState.START;
    }


    public Cell getBox(Coord coord) {
        if(flag.get(coord) == Cell.OPENED) return bomb.get(coord); // If flag-level is opened show lower-level the bomb level
        else return flag.get(coord); // else show flag level value
    }

    // gameplay
    public void pressedLeftButton(Coord coord) {
        switch (state) {
            case START -> {
                if (bomb.get(coord) != Cell.BOMB){
                    flag.setOpenedToCell(coord);
                }
                revealAround(coord);
                setState(GameState.PLAYING);
            }
            case PLAYING -> {
                if (flag.get(coord) == Cell.CLOSED) { // if Cell is not revealed already
                    switch (bomb.get(coord)) {
                        case BOMB -> { // lose-condition
                            flag.setBombedToCell(coord);
                            setState(GameState.LOSE);
                        }
                        case ZERO -> {
                            flag.setOpenedToCell(coord);
                            revealAround(coord); // reveal around empty space
                        }
                        default -> flag.setOpenedToCell(coord);
                    }
                }
            }
        }
    }
    private final Set<Coord> checkedCells = new HashSet<Coord>(); // list of checked values
    public void revealAround(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (!checkedCells.contains(around)) {
                checkedCells.add(coord);
                switch (flag.get(around)){
                    case FLAGGED -> {
                        return;
                    }
                    case CLOSED -> {
                        switch (bomb.get(around)){
                            case BOMB -> {
                                if(state == GameState.START) flag.setFlaggedToCell(around);
                            }
                            case ZERO -> { // reveal ZERO values and go deeper while there is still ZERO values around
                                flag.setOpenedToCell(around);
                                revealAround(around);
                            }
                            default -> flag.setOpenedToCell(around); // else reveal number-value

                        }
                    }
                }
            }
        }
    }
    public void pressedRightButton(Coord coord) {
        if (Objects.requireNonNull(state) == GameState.PLAYING) {
            flag.setFlaggedToCell(coord);
        }
    }


    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }
}
