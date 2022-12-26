package view;

import chessComponent.SquareComponent;
import view.Chessboard.*;
import view.ChessGameFrame.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class eChessboard extends JFrame {
    public eChessboard(int[][] i) {
        setTitle("Eaten pawns QAQ");
        try {
            Image image = ImageIO.read(new File("D:\\IdeaProjects\\javafx1120\\src\\Music\\图标的星之卡比.png"));
            setIconImage(image);
            //setIconImage(new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\狂炫的星之卡比.gif").getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLocationRelativeTo(null); // Center the window.
        setBounds(750, 38, 400, 800);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        //setLayout(null);

        addPicture(1, 0, i[0][0]);
        addPicture(1, 2, i[0][1]);
        addPicture(1, 4, i[0][2]);
        addPicture(1, 6, i[0][3]);
        addPicture(1, 8, i[0][4]);
        addPicture(1, 10, i[0][5]);
        addPicture(1, 12, i[0][6]);
        addPicture(2, 0, i[1][0]);
        addPicture(2, 2, i[1][1]);
        addPicture(2, 4, i[1][2]);
        addPicture(2, 6, i[1][3]);
        addPicture(2, 8, i[1][4]);
        addPicture(2, 10, i[1][5]);
        addPicture(2, 12, i[1][6]);//只要在ChessGameFrame里计算出被吃的数量

        final ImageIcon imageIcon = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\爆米花星之卡比.jpg");
        final JLabel jl = new JLabel(imageIcon);
        jl.setBounds(-50, -100, 500, 1000);
        add(jl);
        setFocusableWindowState(false);//虽然可以得到美丽白框，但会在屏幕程序里置于底层。。

        setResizable(false);//不允许放大hhh
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(getContentPane());
        setVisible(true);

    }

    public void addPicture(int color, int type, int number) {
        if (color == 1) {
            switch (type) {
                case (0) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红兵.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 0, 80, 80);
                        add(label);
                    }
                }
                case (2) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红马.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 80, 80, 80);
                        add(label);
                    }
                }
                case (4) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红车.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 80, 80, 80);
                        add(label);
                    }
                }
                case (6) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红象.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 160, 80, 80);
                        add(label);
                    }
                }
                case (8) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红士.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 160, 80, 80);
                        add(label);
                    }
                }
                case (10) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红帅.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 240, 80, 80);
                        add(label);
                    }
                }
                case (12) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红炮.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 240, 80, 80);
                        add(label);
                    }
                }
            }
        }
        if (color == 2) {
            switch (type) {
                case (0) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑卒.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 320, 80, 80);
                        add(label);
                    }
                }
                case (2) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑马.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 400, 80, 80);
                        add(label);
                    }
                }
                case (4) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑车.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 400, 80, 80);
                        add(label);
                    }
                }
                case (6) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑象.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 480, 80, 80);
                        add(label);
                    }
                }
                case (8) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑士.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 480, 80, 80);
                        add(label);
                    }
                }
                case (10) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑将.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * n, 560, 80, 80);
                        add(label);
                    }
                }
                case (12) -> {
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑炮.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for (int n = 0; n < number; n++) {
                        JLabel label = new JLabel(img);
                        label.setBounds(80 * (n + 2), 560, 80, 80);
                        add(label);
                    }
                }
            }
        }
    }
}
