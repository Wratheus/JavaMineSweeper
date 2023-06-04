package feature.dialogs;

import core.models.Record;
import core.utils.Writer;
import feature.JavaMineSweeperUI;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RecordsDialog {
    private final JavaMineSweeperUI context;
    public RecordsDialog(JavaMineSweeperUI parentContext) {
        this.context = parentContext;
        initDialog();
    }

    public void initDialog() {
        JFrame frame = new JFrame("Generated Window");
        frame.setTitle("Records");
        frame.setIconImage(JavaMineSweeperUI.getImage("icon"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        Writer writer = context.getGame().getGamePlay().getWriter();
        ArrayList<Record> records = writer.showRecord();
        for (Record record : records) {

            JLabel textLine1 = new JLabel(String.format("  %s", record.getName()));
            textLine1.setFont(new Font("Verdana",Font.PLAIN,14));
            JLabel textLine2 = new JLabel(String.format("    Size: %s    |    Mines: %s    |    Time (seconds): %s    ", record.getSize(), record.getMines(), record.getTime() / 1000));
            textLine2.setFont(new Font("Verdana",Font.PLAIN,12));


            JPanel customComponent = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.anchor = GridBagConstraints.WEST;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            customComponent.add(textLine1, constraints);

            constraints.gridy = 1;
            customComponent.add(textLine2, constraints);

            listPanel.add(customComponent);
        }

        frame.setVisible(true);
    }
}