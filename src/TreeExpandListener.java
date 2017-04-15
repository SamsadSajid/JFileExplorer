import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

/**
 * Created by shamsad on 4/15/17.
 * This listener is triggered when you expand or contract a node on the JTree.
 * When a node is expanded, the grandchild nodes of the expanded directory are created.
 * This is run in a separate thread, so as not to slow down the GUI.
 */
public class TreeExpandListener implements TreeWillExpandListener {
    private FileBrowserModel model;

    public TreeExpandListener(FileBrowserModel model) {
        this.model = model;
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event)
            throws ExpandVetoException {
    }

    @Override
    public void treeWillExpand(TreeExpansionEvent event)
            throws ExpandVetoException {
        TreePath path = event.getPath();
        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) path.getLastPathComponent();

        AddNodes addNodes = new AddNodes(model, node);
        new Thread(addNodes).start();
    }
}
