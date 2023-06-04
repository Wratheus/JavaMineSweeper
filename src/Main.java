import core.models.Field;
import core.constants.GameDifficulty;
import feature.JavaMineSweeperUI;

public class Main {
    public static void main(String[] args){
        new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.INTERMEDIATE));
    }
}