import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.Enumeration;

/**
 * Created by shamsad on 4/15/17.
 */
public class TableScrollPane {
    private FileBrowserFrame frame;

    private FileBrowserModel model;

    private FileTableModel ftModel;

    private JLabel countLabel;

    private JPanel panel;

    private JScrollPane scrollPane;

    private JTable table;

    private TableSelectionListener tsListener;

    public TableScrollPane(FileBrowserFrame frame,
                           FileBrowserModel model) {
        this.frame = frame;
        this.model = model;
        createPartControl();
    }

    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel countPanel = new JPanel();

        countLabel = new JLabel(" ");

        countPanel.add(countLabel);
        panel.add(countPanel, BorderLayout.NORTH);

        ftModel = new FileTableModel();

        table = new JTable(ftModel);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setColumnSelectionAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tsListener = new TableSelectionListener(frame, table);
        tsListener.setRowCount(ftModel.getRowCount());

        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(tsListener);

        int width = ftModel.setColumnWidths(table);
        table.setPreferredScrollableViewportSize(
                new Dimension(width, table.getRowHeight() * 12));

        scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public String buildLabelString(int count) {
        NumberFormat nf = NumberFormat.getInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(nf.format(count));
        builder.append(" files / directories");
        return builder.toString();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void clearDefaultTableModel() {
        ftModel.removeRows();
        countLabel.setText(" ");
        ftModel.fireTableDataChanged();
    }

    /**
     * In the setDefaultTableModel, FileTableModel was set using the DefaultMutableTreeNode nodes
     * from the JTree DefaultTreeModel. This keeps the JTree consistent with the JTable.
     * @param node
     *
     */


    public void setDefaultTableModel(DefaultMutableTreeNode node) {
        ftModel.removeRows();

        FileNode fileNode = (FileNode) node.getUserObject();
        File file = fileNode.getFile();
        if (file.isDirectory()) {
            Enumeration<?> enumeration = node.children();
            while (enumeration.hasMoreElements()) {
                DefaultMutableTreeNode childNode =
                        (DefaultMutableTreeNode)
                                enumeration.nextElement();
                FileNode childFileNode =
                        (FileNode) childNode.getUserObject();
                ftModel.addRow(model, childFileNode);
            }
        }

        tsListener.setRowCount(ftModel.getRowCount());
        countLabel.setText(buildLabelString(ftModel.getRowCount()));
        ftModel.fireTableDataChanged();
        scrollPane.getVerticalScrollBar().setValue(0);
    }
}
