package view;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    Dialog(String winner){
        this.setTitle("Congratulations!");
        this.setVisible(true);
        this.setLocation(600,400);
        this.setSize(500,250);
        Container contentpane = this.getContentPane();
        JLabel jl = new JLabel(String.format("%s wins!",winner));
        jl.setFont(new Font("Rockwell", Font.BOLD, 40));
        contentpane.add(jl);
        jl.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
