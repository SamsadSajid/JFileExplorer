import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shamsad on 4/15/17.
 *  The getColumnClass method tells the JTable what types of Objects are in each column.
 *  The addRow method adds all of the column objects as one List row.
 *  The setColumnWidths method not only sets the column widths,
 *  but uses a special DateRenderer for the 3rd column, the last modified date.
 */

public class FileTableModel extends AbstractTableModel {

    private List<List<Object>> rows;

    private String[] columns = {"Icon", "File", "Size",
            "Last Modified"};

    public FileTableModel() {
        this.rows = new ArrayList<>();
        DateFormat.getDateInstance(DateFormat.DEFAULT);
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return rows.get(row).get(column);
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 2:
                return Long.class;
            case 3:
                return Date.class;
            default:
                return String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void addRow(FileBrowserModel model, FileNode fileNode) {
        File file = fileNode.getFile();

        List<Object> list = new ArrayList<>();
        list.add(model.getFileIcon(file));
        list.add(model.getFileText(file));
        list.add(file.length());
        list.add(new Date(file.lastModified()));
        list.add(fileNode);

        this.rows.add(list);
    }

    public void removeRows() {
        this.rows.clear();
    }

    public int setColumnWidths(JTable table) {
        DefaultTableCellRenderer centerRenderer =
                new DateRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(3)
                .setCellRenderer(centerRenderer);

        int width = setColumnWidth(table, 0, 35);
        width += setColumnWidth(table, 1, 200);
        width += setColumnWidth(table, 2, 70);
        width += setColumnWidth(table, 3, 80);
        return width + 30;
    }

    private int setColumnWidth(JTable table, int column, int width) {
        TableColumn tableColumn = table.getColumnModel()
                .getColumn(column);
        JLabel label = new JLabel((String) tableColumn.getHeaderValue());
        Dimension preferred = label.getPreferredSize();
        width = Math.max(width, (int) preferred.getWidth() + 14);
        tableColumn.setPreferredWidth(width);
        tableColumn.setMinWidth(width);

        return width;
    }
}
