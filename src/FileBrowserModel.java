import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Enumeration;

/**
 * Created by shamsad on 4/15/17.
 */


/**
 * The FileBrowserModel class  creates the DefaultTreeModel for the JTree.
 * It does a bfs, and gets the children and grandchildren of the root file.
 */

public class FileBrowserModel {
    private FileSystemView fileSystemView;

    public FileBrowserModel() {
        this.fileSystemView = FileSystemView.getFileSystemView();
    }

    /**
     * DefaultTreeModel is a simple tree data model that uses TreeNodes.
     * @return a Tree.
     */

    /** Documentation for DefaultMutableTreeModel
     * A tree node may have at most one parent and 0 or more children.
     * DefaultMutableTreeNode provides operations for examining and modifying a node's
     * parent and children and also operations for examining the tree that the node is a part of.
     * A node's tree is the set of all nodes that can be reached by starting at the node and
     * following all the possible links to parents and children.
     * A node with no parent is the root of its tree; a node with no children is a leaf.
     * A tree may consist of many subtrees, each node acting as the root for its own subtree.

     *This class provides enumerations for efficiently traversing a tree or subtree in various orders
     *or for following the path between two nodes.
     *A DefaultMutableTreeNode may also hold a reference to a user object,
     *the use of which is left to the user.
     *Asking a DefaultMutableTreeNode for its string representation with toString()
     *returns the string representation of its user object.
     *
     * DefaultMutableTreeNode(Object obj) {
     * @return Creates a tree node with no parent,  no children, but which allows children,
     * and initializes it with the specified user object.
     * }
     */

    public DefaultTreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (File file : fileSystemView.getRoots()) {
            root.add(new DefaultMutableTreeNode(new FileNode(file)));
        }

        addChildNodes(root);
        addGrandchildNodes(root);

        return new DefaultTreeModel(root);
    }

    /**
     *
     * @param root
     * If it encounters a subfolder, and the subfolder contains some files,
     * those files are the GrandchildNodes of the Folder, where the sub-folder
     * is the child of the folder. addChildNodes(node) is adding those file in the sub-folders.
     */

    public void addGrandchildNodes(DefaultMutableTreeNode root) {
        Enumeration<?> enumeration = root.children();
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) enumeration.nextElement();
            addChildNodes(node);
        }
    }

    /**
     *
     * @param root
     *makes the directory children of the root, makes the folder children of the directories,
     * makes the sub-folder children of the folder.....
     */

    private void addChildNodes(DefaultMutableTreeNode root) {
        Enumeration<?> enumeration = root.children(); //takes all the children of the root node
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) enumeration.nextElement();
            FileNode fileNode = (FileNode) node.getUserObject();
            File file = fileNode.getFile(); //returns the file object
            if (file.isDirectory() && file.listFiles() != null) {
                for (File child : file.listFiles()) {
                    node.add(new DefaultMutableTreeNode(
                            new FileNode(child)));
                }
            }
        }
    }

    public FileSystemView getFileSystemView() {
        return fileSystemView;
    }

    public Icon getFileIcon(File file) {
        return fileSystemView.getSystemIcon(file);
    }

    public String getFileText(File file) {
        return fileSystemView.getSystemDisplayName(file);
    }
}
