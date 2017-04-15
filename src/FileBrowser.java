import javax.swing.*;

/**
 * Created by shamsad on 4/15/17.
 */
public class FileBrowser implements Runnable{
    @Override
    public void run() {
        new FileBrowserFrame(new FileBrowserModel());
    }

    /**
     * Puts the Swing components on the Event Dispatch thread (EDT)
     * by executing the invokeLater method of SwingUtilities.
     * @param args
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new FileBrowser());
    }
}
