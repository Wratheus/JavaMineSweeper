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
import java.awt.event.*;


    public final class JavaMineSweeperUI extends JFrame implements ActionListener, MouseListener{
    private final JButton beginner = new JButton("Easy");
    private final JButton intermediate = new JButton("Medium");
    private final JButton expert = new JButton("Hard");
    private final JPanel ButtonPanel = new JPanel();
    private JPanel panel;
    private final Game game;
    private JLabel gameStateLabel;
    private final int IMAGE_SIZE;
    public static void main(String[] args) {
        new JavaMineSweeperUI(new Field(Field.GameDifficulty.INTERMEDIATE)).setVisible(true);
    }

    private JavaMineSweeperUI(Field difficultyLevel) {
        game = new Game(difficultyLevel);
        IMAGE_SIZE = difficultyLevel.IMAGE_SIZE;
        game.start();
        setImages();
        initLabel();
        initButtonPanel();
        initPanel();
        initFrame();
    }
    private void initLabel() {
        gameStateLabel = new JLabel("Welcome");
        add(gameStateLabel, BorderLayout.NORTH);
    }
    private void initButtonPanel(){
        setLayout(new BorderLayout());
        add(ButtonPanel,BorderLayout.SOUTH);
        ButtonPanel.add(beginner);
        beginner.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(new Field(Field.GameDifficulty.BEGINNER)).setVisible(true);
        });
        ButtonPanel.add(intermediate);
        intermediate.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(new Field(Field.GameDifficulty.INTERMEDIATE)).setVisible(true);
        });
        ButtonPanel.add(expert);
        expert.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(new Field(Field.GameDifficulty.EXPERT)).setVisible(true);
        });
    }
    private void initPanel () {
        // cells
        panel = new JPanel() {
            // cells
            @Override
            protected void paintComponent(Graphics cell) {
                super.paintComponent(cell);
                for (Coord coord : Ranges.getCoordsList())
                    cell.drawImage((Image) game.getCell(coord).image,
                            coord.getX() * IMAGE_SIZE,
                            coord.getY() * IMAGE_SIZE,
                            IMAGE_SIZE,
                            IMAGE_SIZE,
                            this);
            }
        };
        // the game Field
        final Dimension gameField = new Dimension(
                Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE
        );

        panel.setPreferredSize(gameField);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1) game.pressedLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3) game.pressedRightButton(coord);
                gameStateLabel.setText(getMessage());
                panel.repaint();
            }
        });
        add(panel);
    }

    private void initFrame () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java MineSweeper");
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
    private String getMessage() {
        switch (game.getState()){
            case LOSE: {
                return "GAME OVER";
            }
            case WIN: {
                return "Congratulations!";
            }
            case PLAYING: {
                return "GoodLuck!";
            }
            default: {
                return "Welcome!";
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
