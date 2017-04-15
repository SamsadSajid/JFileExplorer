import java.io.File;

/**
 * Created by shamsad on 4/15/17.
 *  FileNode the class that is stored in the DefaultMutableTreeNode nodes of the DefaultTreeModel.
 *  The file object can be directories, folder, sub-folder, and normal files
 */
public class FileNode {
    private boolean generateGrandchildren;

    private File file;

    public FileNode(File file) {
        this.file = file;
        this.generateGrandchildren = true;
    }

    public File getFile() {
        return file;
    }

    public boolean isGenerateGrandchildren() {
        return generateGrandchildren;
    }

    public void setGenerateGrandchildren(boolean generateGrandchildren) {
        this.generateGrandchildren = generateGrandchildren;
    }

    @Override
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath(); //directories
        } else {
            return name;
        }
    }
}
