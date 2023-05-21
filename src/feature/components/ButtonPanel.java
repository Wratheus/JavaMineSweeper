package feature.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    public JButton beginner = new JButton("Easy");
    public JButton intermediate = new JButton("Medium");
    public JButton expert = new JButton("Hard");
    public ButtonPanel(){

        beginner.setFocusPainted(false);
        beginner.setContentAreaFilled(false);
        beginner.setFont(new Font("Verdana",Font.PLAIN,14));

        intermediate.setFocusPainted(false);
        intermediate.setContentAreaFilled(false);
        intermediate.setFont(new Font("Verdana",Font.PLAIN,14));

        expert.setFocusPainted(false);
        expert.setContentAreaFilled(false);
        expert.setFont(new Font("Verdana",Font.PLAIN,14));

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(beginner);
        buttonPanel.add(intermediate);
        buttonPanel.add(expert);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
