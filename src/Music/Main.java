package Music;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusicTable musicTable = new MusicTable(250,480);
            musicTable.setVisible(true);
        });
    }
}
