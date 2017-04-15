import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.io.File;

/**
 * Created by shamsad on 4/16/17.
 *
 */
public class FileTreeCellRenderer implements TreeCellRenderer{
    private FileBrowserModel model;

    private JLabel label;

    public FileTreeCellRenderer(FileBrowserModel model) {
        this.model = model;
        this.label = new JLabel(" ");
        label.setOpaque(true);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {

        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) value;
        FileNode fileNode = (FileNode) node.getUserObject();
        if (fileNode != null) {
            File file = fileNode.getFile();
            label.setIcon(model.getFileIcon(file));
            label.setText(model.getFileText(file));
        } else {
            label.setText(value.toString());
        }

        if (selected) {
            label.setBackground(Color.BLUE);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }
}
