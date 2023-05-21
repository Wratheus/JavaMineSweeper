import core.components.Coord;
import core.Game;
import core.components.Ranges;
import core.constants.Cell;
import core.constants.GameDifficulty;
import feature.components.ButtonPanel;
import feature.components.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.awt.event.*;


    public final class JavaMineSweeperUI extends JFrame implements MouseListener, ActionListener{
    private final StatusPanel statusPanel;
    private final ButtonPanel buttonPanel;
    private JPanel gameField = new JPanel();
    private final Game game;
    private final int IMAGE_SIZE;
    public static void main(String[] args) {
        new JavaMineSweeperUI(GameDifficulty.INTERMEDIATE).setVisible(true);
    }

    public JavaMineSweeperUI(GameDifficulty difficultyLevel) {
        game = new Game(difficultyLevel);
        IMAGE_SIZE = game.difficultyValues.IMAGE_SIZE;
        game.start();
        setImages();
        statusPanel = new StatusPanel(game.difficultyValues.MINES);
        buttonPanel = new ButtonPanel();
        addActionListeners();
        initPanel();
        initFrame();
    }
    private void initPanel () {
        setLayout(new BorderLayout());
        // cells
        gameField = new JPanel() {
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
        final Dimension gameFieldSize = new Dimension(
                Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE
        );

        gameField.setPreferredSize(gameFieldSize);
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1) game.pressedLeftButton(coord); // left click
                if(e.getButton() == MouseEvent.BUTTON3) { // right click
                    game.pressedRightButton(coord);
                    statusPanel.setMines(game.difficultyValues.MINES - game.flag.getTotalFlags());
                }
                statusPanel.gameStateLabel.setText(getMessage());
                gameField.repaint();
            }
        });
        add(statusPanel,BorderLayout.NORTH);
        add(gameField, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
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
        String fileName = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));
        return icon.getImage();
    }
    private String getMessage() {
        switch (game.getState()){
            case LOSE: {
                statusPanel.stopwatch.stop();
                return "GAME OVER";
            }
            case WIN: {
                statusPanel.stopwatch.stop();
                return "YOU WIN!";
            }
            case PLAYING: {
                return "GoodLuck!";
            }
            default: {
                return "Welcome!";
            }
        }
    }
    private void addActionListeners() {
        buttonPanel.beginner.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(GameDifficulty.BEGINNER).setVisible(true);
        });

        buttonPanel.intermediate.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(GameDifficulty.INTERMEDIATE).setVisible(true);
        });

        buttonPanel.expert.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(GameDifficulty.EXPERT).setVisible(true);
        });


    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void actionPerformed(ActionEvent e) {}
    }