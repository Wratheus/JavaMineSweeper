package feature.components;

import feature.utils.Stopwatch;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel{
    public final Stopwatch stopwatch = new Stopwatch();
    public JLabel gameStateLabel = new JLabel();

    public StatusPanel(){
        gameStateLabel.setFont(new Font("Verdana",Font.PLAIN,14));
        gameStateLabel.setText("Welcome!");
        JPanel statusPanel = new JPanel();
        statusPanel.add(gameStateLabel);
        JLabel spacer = new JLabel();
        spacer.setFont(new Font("Verdana",Font.PLAIN,14));
        spacer.setText("                                  ");
        statusPanel.add(spacer);
        stopwatch.start();
        statusPanel.add(stopwatch.timeLabel);
        add(statusPanel,BorderLayout.NORTH);
    }
}
