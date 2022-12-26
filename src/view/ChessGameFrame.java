package view;

import Music.Data;
import Music.MusicTable;
import chessComponent.*;
import controller.CheatingClickController;
import controller.ClickController;
import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ChessColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.*;

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
    private static JLabel music;

    private Chessboard chessboard;
    private JButton helloButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton cheatButton;
    private JButton musicButton[];
    private JButton eatenButton;


    public ChessGameFrame(int width, int height) {
        setTitle("Dark Chess!"); //设置标题
        //width=800;height=800;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;
        //setFocusableWindowState(false);
        setForeground(Color.PINK);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        //setResizable(false);//不允许放大hhh
        //setLayout(null);//固定格式？？

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension windowSize = Toolkit.getDefaultToolkit().getBestCursorSize(this.WIDTH,this.HEIGHT);
/*
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910a.jpeg");
        JLabel imgLabel = new JLabel(img);
        imgLabel.setBounds(-8, -8, this.WIDTH, this.HEIGHT);
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//置于底层
*/
        addChessboard();
        addLabel();
        addHelloButton();
        addLoadButton();
        addSaveButton();
        addCheatButton();
        addMusicButton();
        addEatenButton();

        setSize(width,height);
        final ImageIcon i = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910a.jpeg");
        final JPanel jp1=new JPanel();
        jp1.setLayout(null);
        final JLabel jl = new JLabel(i);
        jp1.add(jl, new Integer(Integer.MIN_VALUE));

        ComponentListener listener=new ComponentListener() {
            @Override
            public void componentShown(ComponentEvent e) {
                // TODO 自动生成的方法存根
            }
            @Override
            public void componentResized(ComponentEvent e) {
                int x=jp1.getWidth();    //这里就比较关键
                int y=jp1.getHeight();
                i.setImage(i.getImage().getScaledInstance(Math.max(x,y),Math.max(x,y),Image.SCALE_DEFAULT));
                jl.setBounds(0, 0, x, y);
                chessboard.setLocation(x/2-chessboard.getWidth(),y/10);
                statusLabel.setLocation(x * 3 / 5 - 10, y / 10);
                redLabel.setLocation(x * 3 / 5, y / 10 + 425);
                blackLabel.setLocation(x * 3 / 5, y / 10 + 375);
                music.setLocation(x * 3 / 5, y / 10 + 620);
                helloButton.setLocation(x * 3 / 5, y / 10 + 80);
                loadButton.setLocation(x * 3 / 5, y / 10 + 300);
                saveButton.setLocation(x * 3 / 5 + 100, y / 10 + 300);
                cheatButton.setLocation(x * 3 / 5, y / 10 + 190);
                musicButton[0].setLocation(x * 3 / 5, y / 10 + 500);
                musicButton[1].setLocation(x * 3 / 5, y / 10 + 530);
                musicButton[2].setLocation(x * 3 / 5, y / 10 + 560);
                musicButton[3].setLocation(x * 3 / 5, y / 10 + 590);
                eatenButton.setLocation(x * 3 / 5 + 80, y / 10 + 500);

                // TODO 自动生成的方法存根
            }
            @Override
            public void componentMoved(ComponentEvent e) {
                // TODO 自动生成的方法存根
            }
            @Override
            public void componentHidden(ComponentEvent e) {
                // TODO 自动生成的方法存根
            }
        };


        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);


        add(jp1);
        addComponentListener(listener);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键
    }

    /*修改图片至特定尺寸：运行一下这个main方法*//*
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            //读取原始图片
            BufferedImage image = ImageIO.read(new FileInputStream("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910a.jpeg"));
            //调整图片大小
            BufferedImage newImage = resizeImage(image, screenSize.width, screenSize.width);
            //图像缓冲区图片保存为图片文件(文件不存在会自动创建文件保存，文件存在会覆盖原文件保存)
            ImageIO.write(newImage, "jpeg", new File("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910b.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE, 0);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);
    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("BLACK's TURN");
        statusLabel.setLocation(WIDTH * 3 / 5 - 10, HEIGHT / 10);
        statusLabel.setSize(230, 60);
        statusLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
        add(statusLabel);

        blackplayer = new Player();
        redplayer = new Player();
        blackplayer.setScore(0);
        redplayer.setScore(0);
        blackLabel = new JLabel(String.format("BLACK`S SCORE : %d", blackplayer.getScore()));
        blackLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 375);
        blackLabel.setSize(250, 60);
        blackLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
        redLabel = new JLabel(String.format("RED`S SCORE : %d", redplayer.getScore()));
        redLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 425);
        redLabel.setSize(250, 60);
        redLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
        add(blackLabel);
        add(redLabel);
        music = new JLabel("Music");
        music.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 620, 50, 30);
        music.setFont(new Font("Showcard Gothic", Font.BOLD, 12));
        add(music);
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        helloButton = new JButton();
        helloButton.addActionListener(e -> {
            MusicTable.addSoundEffect("按钮");
            chessboard.Restart();
            blackplayer.setScore(0);
            redplayer.setScore(0);
            redLabel.setText((String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore())));
            blackLabel.setText((String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore())));
            chessboard.setCurrentColor(ChessColor.BLACK);
            statusLabel.setText("BLACK's TURN");
        });
        helloButton.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 80);
        helloButton.setSize(180, 60);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004144.jpg");
        helloButton.setIcon(img);
        add(helloButton);
    }


    private void addLoadButton() {
        loadButton = new JButton();
        loadButton.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 300);
        loadButton.setSize(80, 60);
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004155.jpg");
        loadButton.setIcon(img);
        add(loadButton);

        loadButton.addActionListener(e -> {
            MusicTable.addSoundEffect("按钮");
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            if (path.endsWith(".txt")) {
                gameController.loadGameFromFile(path);
            } else {
                JOptionPane.showMessageDialog(null, "101:FILE FORMAT ERROE!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    private void addSaveButton() {
        saveButton = new JButton();
        saveButton.setLocation(WIDTH * 3 / 5 + 100, HEIGHT / 10 + 300);
        saveButton.setSize(80, 60);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004158.jpg");
        saveButton.setIcon(img);
        add(saveButton);
        saveButton.addActionListener(e -> {
            MusicTable.addSoundEffect("按钮");
            System.out.println("Click save");
            String name = JOptionPane.showInputDialog(this, "Input Name here");
            new Save(name + ".txt", chessboard);
        });
    }

    private void addCheatButton() {
        cheatButton = new JButton();
        cheatButton.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 190);
        cheatButton.setSize(180, 60);
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220110507.jpg");
        ImageIcon img2 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220112043.jpg");
        cheatButton.setIcon(img);
        add(cheatButton);
        ClickController right = chessboard.getSquareComponents()[0][0].getClickController();
        cheatButton.addActionListener(e -> {
            if (cheatButton.getIcon() == img) {
                MusicTable.addSoundEffect("坏笑");
                System.out.println("Cheating Mode");
                cheatButton.setIcon(img2);
                int i, j;
                for (i = 0; i < chessboard.getSquareComponents().length; i++) {
                    for (j = 0; j < chessboard.getSquareComponents()[i].length; j++) {
                        if (!(chessboard.getSquareComponents()[i][j] instanceof EmptySlotComponent)) {
                            chessboard.getSquareComponents()[i][j].setSaveReaversal(chessboard.getSquareComponents()[i][j].isReversal());
                            chessboard.getSquareComponents()[i][j].setClickController(new CheatingClickController(chessboard));
                        }
                    }
                }
            } else {
                System.out.println("Normal Mode");
                cheatButton.setIcon(img);
                int i, j;
                for (i = 0; i < chessboard.getSquareComponents().length; i++) {
                    for (j = 0; j < chessboard.getSquareComponents()[i].length; j++) {

                        if (!(chessboard.getSquareComponents()[i][j] instanceof EmptySlotComponent)) {
                            chessboard.getSquareComponents()[i][j].setReversal(chessboard.getSquareComponents()[i][j].isSaveReaversal());
                            chessboard.getSquareComponents()[i][j].setClickController(right);
                            chessboard.getSquareComponents()[i][j].repaint();
                        }
                    }
                }
            }
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

    public void addMusicButton() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\javafx1120\\src\\Music\\群青.wav"), 0.5);
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\javafx1120\\src\\Music\\恋爱循环.wav"), 0.5);
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\javafx1120\\src\\Music\\夜的钢琴曲五.wav"), 0.5);
            }
        });
        musicButton = new JButton[4];
        musicButton[0]=new JButton();
        musicButton[1]=new JButton();
        musicButton[2]=new JButton();
        musicButton[3] = new JButton("⏸");

        musicButton[0].addActionListener((e -> {
            if (thread1.getState() == Thread.State.NEW) {
                thread1.start();
            } else {
                thread1.resume();
            }
            if (thread2.getState() == Thread.State.TIMED_WAITING) {
                thread2.suspend();
            }
            if (thread3.getState() == Thread.State.TIMED_WAITING) {
                thread3.suspend();
            }
        }));
        musicButton[1].addActionListener((e -> {
            if (thread2.getState() == Thread.State.NEW) {
                thread2.start();
            } else {
                thread2.resume();
            }
            if (thread1.getState() == Thread.State.TIMED_WAITING) {
                thread1.suspend();
            }
            if (thread3.getState() == Thread.State.TIMED_WAITING) {
                thread3.suspend();
            }
        }));
        musicButton[2].addActionListener((e -> {
            if (thread3.getState() == Thread.State.NEW) {
                thread3.start();
            } else {
                thread3.resume();
            }
            if (thread2.getState() == Thread.State.TIMED_WAITING) {
                thread2.suspend();
            }
            if (thread1.getState() == Thread.State.TIMED_WAITING) {
                thread1.suspend();
            }
        }));
        musicButton[3].addActionListener((e -> {
            if (thread1.getState() == Thread.State.TIMED_WAITING) {
                thread1.suspend();
            }
            if (thread2.getState() == Thread.State.TIMED_WAITING) {
                thread2.suspend();
            }
            if (thread3.getState() == Thread.State.TIMED_WAITING) {
                thread3.suspend();
            }
        }));

        ImageIcon square = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
        square.setImage(square.getImage().getScaledInstance(50, 50, 0));//设置图片的大小
        musicButton[0].setIcon(square);//把图片放到按钮上
        musicButton[0].setPressedIcon(square);
        musicButton[0].setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 500, 50, 30);
        musicButton[0].setFont(new Font("音乐1", Font.BOLD, 10));
        add(musicButton[0]);
        musicButton[1].setIcon(square);
        musicButton[1].setPressedIcon(square);
        musicButton[1].setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 530, 50, 30);
        musicButton[1].setFont(new Font("音乐2", Font.BOLD, 10));
        add(musicButton[1]);
        musicButton[2].setIcon(square);
        musicButton[2].setPressedIcon(square);
        musicButton[2].setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 560, 50, 30);
        musicButton[2].setFont(new Font("音乐3", Font.BOLD, 10));
        add(musicButton[2]);
        musicButton[3].setBackground(Color.WHITE);
        musicButton[3].setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 590, 50, 30);
        musicButton[3].setFont(new Font("暂停", Font.BOLD, 10));
        add(musicButton[3]);
    }

    private SquareComponent esquareComponent;
    private SquareComponent[][] esquareComponents = new SquareComponent[8][4];

    public void addEatenButton() {
        eatenButton = new JButton();
        eatenButton.setBounds(WIDTH * 3 / 5 + 80, HEIGHT / 10 + 500, 100, 60);
        JFrame jFrame = new JFrame();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(400, 400);

        /*for (int i = 0; i < esquareComponents.length; i++) {
            for (int j = 0; j < esquareComponents[i].length; j++) {
                ChessColor color = esquareComponents[i][j].getChessColor();
                int x = esquareComponents[i][j].getChessnumber();
                switch (x) {
                    case 0 -> {
                        esquareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                    case 2 -> {
                        esquareComponent = new HorseChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                    case 4 -> {
                        esquareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                    case 6 -> {
                        esquareComponent = new ElephantChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                    case 8 -> {
                        esquareComponent = new GuardChesscomponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                    case 10 -> {
                        esquareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController,CHESSBOARD_SIZE);
                    }
                    default -> {
                        esquareComponent = new ConnonChessComponent(new ChessboardPoint(i, j), echessboard.calculatePoint(i, j), color, echessboard.clickController, CHESSBOARD_SIZE);
                    }
                }
                esquareComponent.setVisible(true);
                echessboard.putChessOnBoard(esquareComponent);
            }
        }*/

        eatenButton.addActionListener(e -> {
            int[][] count = {{5, 2, 2, 2, 2, 1, 2}, {5, 2, 2, 2, 2, 1, 2}};//应该显示在被吃窗口的棋子数量。第一行红
            for (int i = 0; i < chessboard.getSquareComponents().length; i++) {
                for (int j = 0; j < chessboard.getSquareComponents()[i].length; j++) {
                    //soldier
                    if (chessboard.getSquareComponents()[i][j] instanceof SoldierChessComponent) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][0] -= 1;
                        } else count[1][0] -= 1;
                        //squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    //horse
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 2) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][1] -= 1;
                        } else count[1][1] -= 1;
                    }
                    //Chariot
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 4) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][2] -= 1;
                        } else count[1][2] -= 1;
                    }
                    //Elephant
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 6) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][3] -= 1;
                        } else count[1][3] -= 1;
                    }
                    //Guard
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 8) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][4] -= 1;
                        } else count[1][4] -= 1;
                    }
                    //General
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 10) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][5] -= 1;
                        } else count[1][5] -= 1;
                    }
                    //Connon
                    if (chessboard.getSquareComponents()[i][j].getChessnumber() == 12) {
                        if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                            count[0][6] -= 1;
                        } else count[1][6] -= 1;
                    }
                }
            }
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[i].length; j++) {
                    System.out.print(count[i][j]);
                }
            }
            eChessboard eChessboard = new eChessboard(count);
            eChessboard.setVisible(true);
        });
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\狂炫的星之卡比.gif");
        img.setImage(img.getImage().getScaledInstance(100, 100*338/500, 0));//设置图片的大小
        eatenButton.setIcon(img);
        add(eatenButton);
    }
}