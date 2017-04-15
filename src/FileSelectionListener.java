import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/**
 * Created by shamsad on 4/15/17.
 */
public class FileSelectionListener implements TreeSelectionListener {
    private FileBrowserFrame frame;

    private FileBrowserModel model;

    public FileSelectionListener(FileBrowserFrame frame,
                                 FileBrowserModel model) {
        this.frame = frame;
        this.model = model;
    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
        FileNode fileNode = (FileNode) node.getUserObject();

        AddNodes addNodes = new AddNodes(model, node);
        new Thread(addNodes).start();

        File file = fileNode.getFile();
        //frame.updateFileDetail(fileNode);
        //frame.setDesktopButtonFileNode(fileNode);
        if (file.isDirectory()) {
            frame.setDefaultTableModel(node);
        } else {
            frame.clearDefaultTableModel();
        }
    }
}
