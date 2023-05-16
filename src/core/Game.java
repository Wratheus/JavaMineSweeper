package core;

import core.components.Bomb;
import core.components.Coord;
import core.components.Flag;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;
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
        state = GameState.PLAYING;
    }



    public Cell getBox(Coord coord) {
        if(flag.get(coord) == Cell.OPENED) return bomb.get(coord); // If flag-level is opened show lower-level the bomb level
        else return flag.get(coord); // else show flag level value
    }

    public void pressedLeftButton(Coord coord) {
        flag.setOpenedToCell(coord);
    }
    public void pressedRightButton(Coord coord) {
        flag.setFlaggedToBox(coord);
    }


    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }
}
