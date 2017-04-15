import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by shamsad on 4/15/17.
 */
public class FileBrowserFrame {
    //private DesktopButtonPanel desktopButtonPanel;

    private FileBrowserModel model;

    //private FileDetailPanel fileDetailPanel;

    private JFrame frame;

    private JPanel mainPanel;

    private TableScrollPane tableScrollPane;

    private TreeScrollPane treeScrollPane;

    public FileBrowserFrame(FileBrowserModel model) {
        this.model = model;
        setLookAndFeel();
        createPartControl();
    }

    private void createPartControl() {
        frame = new JFrame();
        frame.setTitle("File Explorer");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        createMainPanel();

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        treeScrollPane = new TreeScrollPane(this, model);
        mainPanel.add(treeScrollPane.getScrollPane(), BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        tableScrollPane = new TableScrollPane(this, model);
        rightPanel.add(tableScrollPane.getPanel(),
                BorderLayout.CENTER);

        mainPanel.add(rightPanel, BorderLayout.CENTER);
    }

    public void exitProcedure() {
        frame.dispose();
        System.exit(0);
    }



    public void setDefaultTableModel(DefaultMutableTreeNode node) {
        tableScrollPane.setDefaultTableModel(node);
    }

    public void clearDefaultTableModel() {
        tableScrollPane.clearDefaultTableModel();
    }

    private void setLookAndFeel() {
        try {
            // Significantly improves the look of the output in
            // terms of the file names returned by FileSystemView!
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch(Exception weTried) {
            weTried.printStackTrace();
        }
    }
}
