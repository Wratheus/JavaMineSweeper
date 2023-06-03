package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {
    public JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, e -> {
        elapsedTime=elapsedTime+1000;
        hours = (elapsedTime/3600000);
        minutes = (elapsedTime/60000) % 60;
        seconds = (elapsedTime/1000) % 60;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText("| Time: " + hours_string+":"+minutes_string+":"+seconds_string+" |");
    });
    @Override
    public void actionPerformed(ActionEvent e) {
        started=true;
        start();
    }
    public Stopwatch(){
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,14));
        timeLabel.setText("| Time: " + hours_string+":"+minutes_string+":"+seconds_string+" |");
    }
    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}
