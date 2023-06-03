package feature;

import core.constants.GameState;
import core.objects.Coord;
import core.Game;
import core.objects.Ranges;
import core.constants.Cell;
import core.constants.Field;
import core.constants.GameDifficulty;
import feature.dialogs.CustomFieldDialog;
import feature.dialogs.WinDialog;
import feature.panels.ButtonPanel;
import feature.panels.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.awt.event.*;


    public final class JavaMineSweeperUI extends JFrame implements MouseListener, ActionListener{
    private boolean winDialog = false;
    private final StatusPanel statusPanel;
    private final ButtonPanel buttonPanel;
    private JPanel gameField = new JPanel();
    private final Game game;
    private final int IMAGE_SIZE;
    private JFrame dialog = null;
    private Dimension gameFieldSize;

    public JavaMineSweeperUI(Field difficulty) {
        game = new Game(difficulty);
        IMAGE_SIZE = game.difficultyValues.IMAGE_SIZE;
        game.start();
        setImages();
        statusPanel = new StatusPanel(game.gameField.bomb.getTotalBombs(), game.gamePlay.stopwatch);
        buttonPanel = new ButtonPanel();
        addActionListeners();
        initPanel();
        initFrame();
        setVisible(true);
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
                    cell.drawImage((Image) game.gamePlay.getCell(coord).image,
                            coord.getX() * IMAGE_SIZE,
                            coord.getY() * IMAGE_SIZE,
                            IMAGE_SIZE,
                            IMAGE_SIZE,
                            this);
            }
        };
        gameFieldSize = new Dimension(
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
                    statusPanel.setMines(game.difficultyValues.MINES - game.gameField.flag.getTotalFlags());
                }
                statusPanel.gameStateLabel.setText(getMessage());
                gameField.repaint();
                if(game.gamePlay.getState() == GameState.WIN && !winDialog) {
                    new WinDialog(game.gamePlay.writer);
                    winDialog = true;
                }
            }
        });
        this.add(statusPanel,BorderLayout.NORTH);
        this.add(gameField, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void initFrame () {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java MineSweeper");
        setIconImage(getImage("icon"));
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - gameFieldSize.width / 2, dim.height / 3 - gameFieldSize.height / 2);
        pack();
    }

    // set Images to Cells
    private void setImages(){
        for (Cell cell : Cell.values()){
            cell.image = getImage(cell.name().toLowerCase());
        }
    }
    public void disposeDialog(){
        dialog = null;
    }
    // get Images from enum resources
    public static Image getImage(String name) {
        String fileName = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(JavaMineSweeperUI.class.getResource(fileName)));
        return icon.getImage();
    }
    private String getMessage() {
        switch (game.gamePlay.getState()){
            case LOSE: {
                game.gamePlay.stopwatch.stop();
                return "GAME OVER";
            }
            case WIN: {
                game.gamePlay.stopwatch.stop();
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
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.BEGINNER));
        });

        buttonPanel.intermediate.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.INTERMEDIATE));
        });

        buttonPanel.expert.addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.EXPERT));
        });

        buttonPanel.custom.addActionListener(e -> {
            if(dialog == null) dialog = new CustomFieldDialog(this);
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