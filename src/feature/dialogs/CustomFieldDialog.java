package feature.dialogs;

import core.objects.Coord;
import core.constants.Field;
import feature.JavaMineSweeperUI;
import feature.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomFieldDialog extends JFrame implements ActionListener {
    private JButton OK;
    private JButton CANCEL;
    private TextField width;
    private TextField height;
    private TextField mines;
    private final JavaMineSweeperUI parentContext;

    public CustomFieldDialog(JavaMineSweeperUI context) {
        parentContext = context;
        initDialog();
    }

    private void initDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.width = new TextField();
        width.setToolTipText("Width");
        width.setText("50");
        JLabel w = new JLabel("  Input width");
        w.setFont(new Font("Verdana",Font.PLAIN,12));
        w.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        width.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        this.height = new TextField();
        height.setToolTipText("Height");
        height.setText("30");
        JLabel h = new JLabel("  Input height");
        h.setFont(new Font("Verdana",Font.PLAIN,12));
        h.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        height.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        this.mines = new TextField();
        mines.setToolTipText("Mines");
        mines.setText("300");
        JLabel m = new JLabel("  Input mines");
        m.setFont(new Font("Verdana",Font.PLAIN,12));
        mines.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        m.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        JPanel buttonPanel = initButtons();
        panel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        panel.add(w);
        panel.add(width);
        panel.add(h);
        panel.add(height);
        panel.add(m);
        panel.add(mines);
        panel.add(buttonPanel);

        this.setTitle("Java MS");
        this.setIconImage(JavaMineSweeperUI.getImage("icon"));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/3-this.getSize().height/2);
        this.add(panel);
        this.setPreferredSize(new Dimension(260, 250));
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
                int width = Integer.parseInt(this.width.getText());
                int height = Integer.parseInt(this.height.getText());
                final int MINES = Integer.parseInt(this.mines.getText());

                if(width > 55) // max width
                    width = 55;

                if(width - height < 0) // max height
                    height = (int) (width / 2);


                int IMAGE_SIZE;
                if (width >= 50 || height >= 50)
                    IMAGE_SIZE = 17;
                else if(width >= 40 || height >= 40)
                    IMAGE_SIZE = 25;
                // TODO:
//                else if (width <= 9 || height <= 9) {
//                    width = 9;
//                    height = 9;
//                    IMAGE_SIZE = 45;
//                }
                else
                    IMAGE_SIZE = 30;

                final Coord SIZE = new Coord(width, height);
                dispose();
                this.parentContext.dispose();
                new JavaMineSweeperUI(new Field(SIZE, MINES, IMAGE_SIZE));
            }catch (RuntimeException error) {
                error.printStackTrace();
                dispose();
            }

        });
        CANCEL.addActionListener(e -> dispose());
    }

    @Override
    public void dispose() {
        parentContext.disposeDialog();
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
