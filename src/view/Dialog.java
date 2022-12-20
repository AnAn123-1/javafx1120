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
        JLabel jl = new JLabel();
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220113202.jpg");
        ImageIcon img2 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220113159.jpg");
        if(winner.equals("BLACK")) jl.setIcon(img);
        else jl.setIcon(img2);
        contentpane.add(jl);
        jl.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
