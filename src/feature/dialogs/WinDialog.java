package feature.dialogs;

import feature.JavaMineSweeperUI;
import feature.components.TextField;
import utils.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WinDialog extends JFrame implements ActionListener {
    private JButton OK;
    private JButton CANCEL;
    private TextField name;
    private final Writer writer;
    public WinDialog(Writer writer){
        this.writer = writer;
        initDialog();
    }
    private void initDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.name = new TextField();
        name.setToolTipText("Name");
        name.setText("Name");
//        try{
//            writer.showRecord();
//        }catch (IOException | ClassNotFoundException ignored) {
//
//        }

        JLabel nameLabel = new JLabel("  Input name");
        nameLabel.setFont(new Font("Verdana",Font.PLAIN,12));


        JPanel buttonPanel = initButtons();
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(name, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        this.setTitle("Save record");
        this.setIconImage(JavaMineSweeperUI.getImage("icon"));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/3-this.getSize().height/2);
        this.add(panel);
        this.setPreferredSize(new Dimension(260, 130));
        this.pack();
        this.setVisible(true);
    }

    private JPanel initButtons() {
        this.OK = new JButton("OK");
        this.CANCEL = new JButton("Cancel");

        OK.setFocusPainted(false);
        OK.setContentAreaFilled(false);
        OK.setFont(new Font("Verdana",Font.PLAIN,14));

        CANCEL.setFocusPainted(false);
        CANCEL.setContentAreaFilled(false);
        CANCEL.setFont(new Font("Verdana",Font.PLAIN,14));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(OK);
        buttonPanel.add(CANCEL);
        addActionListeners();
        return buttonPanel;
    }

    private void addActionListeners() {
        OK.addActionListener(e -> {
            try {
                writer.writeRecord(name.getText());
                writer.showRecord();
                dispose();
            }catch (RuntimeException | IOException | ClassNotFoundException error) {
                error.printStackTrace();
                dispose();
            }
        });
        CANCEL.addActionListener(e -> dispose());
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

