import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by shamsad on 4/16/17.
 * This class adds the grandchild nodes of the current node.
 * It runs in a thread to keep the Swing GUI responsive.
 */
public class AddNodes implements Runnable{
    private DefaultMutableTreeNode node;

    private FileBrowserModel model;

    public AddNodes(FileBrowserModel model, DefaultMutableTreeNode node) {
        this.model = model;
        this.node = node;
    }

    @Override
    public void run() {
        FileNode fileNode = (FileNode) node.getUserObject();
        if (fileNode.isGenerateGrandchildren()) {
            model.addGrandchildNodes(node);
            fileNode.setGenerateGrandchildren(false);
        }
    }
}
