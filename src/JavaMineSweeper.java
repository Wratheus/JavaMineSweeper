import core.components.Coord;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.Field;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JavaMineSweeper extends JFrame {
    private JPanel panel;

    public static void main(String[] args) {
        new JavaMineSweeper().setVisible(true);
    }

    private JavaMineSweeper () {
        Ranges.setSize(Field.BEGINNER);
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel () {
        panel = new JPanel(){
            // cells
            @Override
            protected void paintComponent(Graphics cell) {
                super.paintComponent(cell);
                for (Coord coord : Ranges.getCoordsList())
                    cell.drawImage((Image) Cell.values() [(coord.getX() + coord.getY()) % Cell.values().length].image,
                    coord.getX() * Field.IMAGE_SIZE,
                    coord.getY() * Field.IMAGE_SIZE,
                    this);
                }
        };
        // the game Field
        final Dimension gameField = new Dimension(Ranges.getSize().getX() * Field.IMAGE_SIZE, Ranges.getSize().getY() * Field.IMAGE_SIZE);
        panel.setPreferredSize(gameField);
        add(panel);
    }
    private void initFrame () {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Mine Sweeper");
        setIconImage(getImage("icon"));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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
