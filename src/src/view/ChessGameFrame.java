package view;

import com.sun.javafx.binding.StringFormatter;
import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;
    private static JLabel blackLabel;
    private static JLabel redLabel;

    private static Player blackplayer;

    private static Player redplayer;

    private Chessboard chessboard;

    public ChessGameFrame(int width, int height) {
        setTitle("Dark Chess!"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
          addLabel();
        addHelloButton();
        addLoadButton();
    }


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("BLACK's TURN");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);

        blackplayer = new Player();
        redplayer = new Player();
        blackplayer.setScore(0);
        redplayer.setScore(0);
        blackLabel = new JLabel(String.format("BLACK`S SCORE : %d",blackplayer.getScore()));
        blackLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 370);
        blackLabel.setSize(250,60);
        blackLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        redLabel = new JLabel(String.format("RED`S SCORE : %d",redplayer.getScore()));
        redLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 420);
        redLabel.setSize(250,60);
        redLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(blackLabel);
        add(redLabel);
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("new game");
        button.addActionListener(e ->  {
            chessboard.Restart();
            blackplayer.setScore(0);
            redplayer.setScore(0);
            redLabel.setText((String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore())));
            blackLabel.setText((String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore())));
            chessboard.setCurrentColor(ChessColor.BLACK);
            statusLabel.setText("BLACK's TURN");
        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }


    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 240);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.loadGameFromFile(path);
        });


    }

    public static JLabel getBlackLabel() {
        return blackLabel;
    }

    public static JLabel getRedLabel() {
        return redLabel;
    }

    public static Player getBlackplayer() {
        return blackplayer;
    }

    public static Player getRedplayer() {
        return redplayer;
    }
}
