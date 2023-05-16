import core.components.Coord;
import core.Game;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class JavaMineSweeperUI extends JFrame {
    private JPanel panel;
    private final Game game;

    public static void main(String[] args) {
        new JavaMineSweeperUI().setVisible(true);
    }

    private JavaMineSweeperUI() {
        Field.GameDifficulty difficulty = Field.GameDifficulty.BEGINNER;
        game = new Game(difficulty);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel () {
        // cells
        panel = new JPanel() {
            // cells
            @Override
            protected void paintComponent(Graphics cell) {
                super.paintComponent(cell);
                for (Coord coord : Ranges.getCoordsList())
                    cell.drawImage((Image) game.getBox(coord).image,
                            coord.getX() * Field.IMAGE_SIZE,
                            coord.getY() * Field.IMAGE_SIZE,
                            this);
            }
        };
        // the game Field
        final Dimension gameField = new Dimension(
                Ranges.getSize().getX() * Field.IMAGE_SIZE,
                Ranges.getSize().getY() * Field.IMAGE_SIZE
        );
        panel.setPreferredSize(gameField);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                int x = e.getX() / Field.IMAGE_SIZE;
                int y = e.getY() /Field.IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1) game.pressedLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3) game.pressedRightButton(coord);
                panel.repaint();
            }
        });
        add(panel);
    }
    private void initFrame () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Mine Sweeper");
        setIconImage(getImage("icon"));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        pack();
    }

    // set Images to Cells
    private void setImages(){
        for (Cell cell : Cell.values()){
            cell.image = getImage(cell.name().toLowerCase());
        }
    }
    // get Images from enum resources
    private Image getImage(String name) {
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));
        return icon.getImage();
    }

}
