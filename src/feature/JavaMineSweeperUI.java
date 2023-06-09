package feature;

import core.constants.GameState;
import core.models.Coord;
import core.objects.Game;
import core.utils.Ranges;
import core.constants.Cell;
import core.models.Field;
import core.constants.GameDifficulty;
import feature.dialogs.CustomFieldDialog;
import feature.dialogs.RecordsDialog;
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

    public Game getGame() {
        return game;
    }

    public JavaMineSweeperUI(Field difficulty) {
        game = new Game(difficulty);
        IMAGE_SIZE = game.getDifficultyValues().getIMAGE_SIZE();
        game.start();
        setImages();
        statusPanel = new StatusPanel(game.getGameField().getBomb().getTotalBombs(), game.getGamePlay().getStopwatch());
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
                    cell.drawImage((Image) game.getGamePlay().getCell(coord).image,
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
                    statusPanel.setMines(game.getDifficultyValues().getMINES() - game.getGameField().getFlag().getTotalFlags());
                }
                statusPanel.getGameStateLabel().setText(getMessage());
                gameField.repaint();
                if(game.getGamePlay().getState() == GameState.WIN && !winDialog) {
                    new WinDialog(game.getGamePlay().getWriter());
                    winDialog = true;
                }
            }
        });
        this.add(statusPanel,BorderLayout.NORTH);
        this.add(gameField, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void initFrame () {
        initMenuBar();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java MineSweeper");
        setIconImage(getImage("icon"));
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - gameFieldSize.width / 2, dim.height / 3 - gameFieldSize.height / 2);
        pack();
    }

    private void initMenuBar () {
        JMenuBar mb = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenuItem records = new JMenuItem("Show Records");
        records.setFont(new Font("Verdana",Font.PLAIN, 12));
        menu1.setFont(new Font("Verdana",Font.PLAIN, 12));
        menu1.add(records);

        records.addActionListener(ev -> new RecordsDialog(this));

        mb.add(menu1);
        this.setJMenuBar(mb);
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
        switch (game.getGamePlay().getState()){
            case LOSE: {
                game.getGamePlay().getStopwatch().stop();
                return "GAME OVER";
            }
            case WIN: {
                game.getGamePlay().getStopwatch().stop();
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
        buttonPanel.getBeginner().addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.BEGINNER));
        });

        buttonPanel.getIntermediate().addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.INTERMEDIATE));
        });

        buttonPanel.getExpert().addActionListener(e -> {
            dispose();
            new JavaMineSweeperUI(Field.fieldFactory(GameDifficulty.EXPERT));
        });

        buttonPanel.getCustom().addActionListener(e -> {
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