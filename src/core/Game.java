package core;

import core.components.Bomb;
import core.components.Coord;
import core.components.Flag;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;

import java.util.ArrayList;

// TODO: fix flagged after game LOSE
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

    public void pressedLeftButton(Coord coord) {
        switch (state) {
            case START -> {
                if (bomb.get(coord) == Cell.BOMB) flag.setFlaggedToCell(coord); // extra chance for first click
                else flag.setOpenedToCell(coord);
                revealAround(coord);
                setState(GameState.PLAYING);
            }
            case PLAYING -> {
                switch (bomb.get(coord)){
                    case BOMB -> { // lose-condition
                        flag.setBombedToCell(coord);
                        setState(GameState.LOSE);
                    }
                    case ZERO -> { // if already opened do nothing else reveal around empty space
                        if(!(flag.get(coord) == Cell.OPENED)) {
                            revealAround(coord);
                        }
                    }
                    default -> flag.setOpenedToCell(coord);

                }
            }
        }
    }
    private ArrayList<Coord> checked = new ArrayList<Coord>(); // stackoverflow saver do not check twice same Coords [if(!checked.contains(around))]
    public void revealAround(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (!checked.contains(around)) {
                switch (bomb.get(around)) {
                    case ZERO -> { // reveal ZERO values and go deeper while there is still ZERO values around
                        flag.setOpenedToCell(around);
                        checked.add(coord);
                        revealAround(around);
                    }
                    case BOMB -> {
                        if (state == GameState.START) {
                            if (!(flag.get(around) == Cell.FLAGGED)) { // check if BOMB is already flagged do not cancel it
                                flag.setFlaggedToCell(around);
                                checked.add(coord);
                            }
                        } else {
                            flag.setOpenedToCell(around); // else reveal number-value
                            checked.add(coord);
                        }
                    }
                    default -> {
                        flag.setOpenedToCell(around); // else reveal number-value
                        checked.add(coord);
                    }
                }
            }
        }
    }
    public void pressedRightButton(Coord coord) {
        flag.setFlaggedToCell(coord);
    }


    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }
}
