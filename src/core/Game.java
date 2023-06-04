package core;

import core.components.GameField;
import core.components.GamePlay;
import core.constants.GameState;
import core.objects.Coord;
import core.objects.Ranges;
import core.constants.Field;

public class Game {
    private final Field difficultyValues;
    private final GameField gameField;
    private final GamePlay gamePlay;

    public Field getDifficultyValues() {
        return difficultyValues;
    }

    public GameField getGameField() {
        return gameField;
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public Game (Field difficulty){
        difficultyValues = difficulty;
        Ranges.setSize(difficultyValues.getSIZE()); // create field instance to get SIZE for ranges.
        gameField = new GameField(difficultyValues);
        gamePlay = new GamePlay(gameField);
    }

    public void start(){
        gamePlay.setState(GameState.START);
    }

    public void pressedLeftButton(Coord coord) {
        gamePlay.openCell(coord);
        gamePlay.checkWin();
    }
    public void pressedRightButton(Coord coord) {
        if (gamePlay.getState() == GameState.PLAYING) {
            gameField.getFlag().setFlaggedToCell(coord);
            gamePlay.checkWin();
        }
    }
}
