import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by shamsad on 4/16/17.
 * This listener is triggered when you select one of the JTable rows.
 * This listener updates the file detail model and the desktop button model.


 */
public class TableSelectionListener implements ListSelectionListener{
    private int rowCount;

    private FileBrowserFrame frame;

    private JTable table;

    public TableSelectionListener(FileBrowserFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            ListSelectionModel lsm =
                    (ListSelectionModel) event.getSource();
            int row = lsm.getMinSelectionIndex();

            if ((row >= 0) && (row < rowCount)) {
                row = table.convertRowIndexToModel(row);
                FileNode fileNode = (FileNode) table.getModel()
                        .getValueAt(row, 10);
                //frame.updateFileDetail(fileNode);
                //frame.setDesktopButtonFileNode(fileNode);
            }
        }
    }
}
