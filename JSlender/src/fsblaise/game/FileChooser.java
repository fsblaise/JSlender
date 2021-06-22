package fsblaise.game;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;


public class FileChooser{
    private String path = "";

    /**Létrehoz egy ablakot, ahol ki lehet választani egy fájlt.
     * Majd az osztály ennek az útvonalát elmenti a path változóba.
     * Ha nem választunk fájlt és cancelt nyomunk, akkor a path üres marad:
     * Ez le van kezelve az Upload osztályban.
     */
    public FileChooser() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = fileChooser.showOpenDialog(null);
        if(r == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public String getPath() {
        return path;
    }
}
