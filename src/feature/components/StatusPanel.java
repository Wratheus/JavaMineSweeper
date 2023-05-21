package feature.components;

import feature.utils.Stopwatch;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel{
    public final Stopwatch stopwatch = new Stopwatch();
    public JLabel gameStateLabel = new JLabel();
    public JLabel minesLeft = new JLabel();

    public StatusPanel(int overallMines){
        gameStateLabel.setFont(new Font("Verdana",Font.PLAIN,14));
        gameStateLabel.setText("Welcome!\t");
        JPanel statusPanel = new JPanel();
        statusPanel.add(gameStateLabel);
        stopwatch.start();
        statusPanel.add(stopwatch.timeLabel);
        minesLeft.setFont(new Font("Verdana",Font.PLAIN,14));
        minesLeft.setText("\tMines: " + overallMines);
        statusPanel.add(minesLeft);
        add(statusPanel,BorderLayout.NORTH);
    }

    public void setMines(int mines){
        if(mines >= 0) minesLeft.setText("Mines: " + mines);
    }
}
