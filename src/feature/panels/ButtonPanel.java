package feature.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    private final JButton beginner = new JButton("Easy");
    private final JButton intermediate = new JButton("Medium");
    private final JButton expert = new JButton("Hard");
    private final JButton custom = new JButton("Custom");

    public JButton getBeginner() {
        return beginner;
    }

    public JButton getIntermediate() {
        return intermediate;
    }

    public JButton getExpert() {
        return expert;
    }

    public JButton getCustom() {
        return custom;
    }

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

        custom.setFocusPainted(false);
        custom.setContentAreaFilled(false);
        custom.setFont(new Font("Verdana",Font.PLAIN,14));

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(beginner);
        buttonPanel.add(intermediate);
        buttonPanel.add(expert);
        buttonPanel.add(custom);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
