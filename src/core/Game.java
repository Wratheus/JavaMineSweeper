package core;

import core.components.GameField;
import core.components.GamePlay;
import core.constants.GameState;
import core.objects.Coord;
import core.objects.Ranges;
import core.constants.Field;

public class Game {
    public Field difficultyValues;
    public GameField gameField;
    public GamePlay gamePlay;


    public Game (Field difficulty){
        difficultyValues = difficulty;
        Ranges.setSize(difficultyValues.SIZE); // create field instance to get SIZE for ranges.
        gameField = new GameField(difficultyValues);
        gamePlay = new GamePlay(gameField);
    }

    public void start(){
        gamePlay.setState(GameState.START);
    }

    // gameplay
    public void pressedLeftButton(Coord coord) {
        gamePlay.openCell(coord);
        gamePlay.checkWin();
    }
    public void pressedRightButton(Coord coord) {
        if (gamePlay.getState() == GameState.PLAYING) {
            gameField.flag.setFlaggedToCell(coord);
            gamePlay.checkWin();
        }
    }
}
