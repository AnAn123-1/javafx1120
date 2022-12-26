package view;

import Music.Data;
import Music.MusicPlayer;
import Music.MusicTable;
import chessComponent.*;
import controller.CheatingClickController;
import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Dimension windowSize = Toolkit.getDefaultToolkit().getBestCursorSize(this.WIDTH,this.HEIGHT);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910a.jpeg");
        ImageIcon img2 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\2c8e731703a167473583f4c04024910b.jpeg");
        JLabel imgLabel = new JLabel(img);
        JLabel imgLabel2 = new JLabel(img2);
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//置于底层
        this.getLayeredPane().add(imgLabel2, new Integer(Integer.MIN_VALUE));//置于底层
        //setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );//全屏
        imgLabel.setBounds(-8, -8, this.WIDTH, this.HEIGHT);
        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);

        addChessboard();
        addLabel();
        addHelloButton();
        addLoadButton();
        addSaveButton();
        addCheatButton();
        addMusicButton();
        addEatenButton();
    }

    /*修改图片至特定尺寸：运行一下这个main方法*/
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
    }


    /**
     * 在游戏窗体中添加棋盘
     */
    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE,0);
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
        JLabel music = new JLabel("Music");
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
        JButton button = new JButton();
        button.addActionListener(e -> {
            MusicTable.addSoundEffect("按钮");
            chessboard.Restart();
            blackplayer.setScore(0);
            redplayer.setScore(0);
            redLabel.setText((String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore())));
            blackLabel.setText((String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore())));
            chessboard.setCurrentColor(ChessColor.BLACK);
            statusLabel.setText("BLACK's TURN");
        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 80);
        button.setSize(180, 60);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004144.jpg");
        button.setIcon(img);
        add(button);
    }


    private void addLoadButton() {
        JButton button = new JButton();
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 300);
        button.setSize(80, 60);
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004155.jpg");
        button.setIcon(img);
        add(button);

        button.addActionListener(e -> {
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
        JButton button = new JButton();
        button.setLocation(WIDTH * 3 / 5 + 100, HEIGHT / 10 + 300);
        button.setSize(80, 60);

        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220004158.jpg");
        button.setIcon(img);
        add(button);
        button.addActionListener(e -> {
            MusicTable.addSoundEffect("按钮");
            System.out.println("Click save");
            String name = JOptionPane.showInputDialog(this, "Input Name here");
            new Save(name + ".txt", chessboard);
        });
    }

    private void addCheatButton() {
        JButton button = new JButton();
        button.setLocation(WIDTH * 3 / 5, HEIGHT / 10 + 190);
        button.setSize(180, 60);
        ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220110507.jpg");
        ImageIcon img2 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\QQ图片20221220112043.jpg");
        button.setIcon(img);
        add(button);
        ClickController right = chessboard.getSquareComponents()[0][0].getClickController();
        button.addActionListener(e -> {
            MusicTable.addSoundEffect("坏笑");
            if (button.getIcon() == img) {
                System.out.println("Cheating Mode");
                button.setIcon(img2);
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
                button.setIcon(img);
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
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton("⏸");

        button1.addActionListener((e -> {
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
        button2.addActionListener((e -> {
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
        button3.addActionListener((e -> {
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
        button4.addActionListener((e -> {
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
        button1.setIcon(square);//把图片放到按钮上
        button1.setPressedIcon(square);
        button1.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 500, 50, 30);
        button1.setFont(new Font("音乐1", Font.BOLD, 10));
        add(button1);
        button2.setIcon(square);
        button2.setPressedIcon(square);
        button2.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 530, 50, 30);
        button2.setFont(new Font("音乐2", Font.BOLD, 10));
        add(button2);
        button3.setIcon(square);
        button3.setPressedIcon(square);
        button3.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 560, 50, 30);
        button3.setFont(new Font("音乐3", Font.BOLD, 10));
        add(button3);
        button4.setBackground(Color.WHITE);
        button4.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 590, 50, 30);
        button4.setFont(new Font("暂停", Font.BOLD, 10));
        add(button4);
    }

    private Chessboard echessboard=new Chessboard(800,640,1);
    private Chessboard ochessboard=new Chessboard(800,640,0);
    private SquareComponent esquareComponent;
    private SquareComponent[][] esquareComponents = new SquareComponent[8][4];
    public void addEatenButton() {
        JButton button = new JButton();
        button.setBounds(WIDTH * 3 / 5 + 100, HEIGHT / 10 + 500, 50, 30);
        JFrame jFrame = new JFrame();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(400, 400);

        int[][] count = new int[2][7];//第一行红
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 3; j++) {
                //soldier
                if (this.chessboard.getSquareComponents()[i][j].getChessnumber() == 0) {
                    if (this.chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][0] += 1;
                    } else count[1][0] += 1;
                    //squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                }
                //horse
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 2) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][1] += 1;
                    } else count[1][1] += 1;
                }
                //Chariot
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 4) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][2] += 1;
                    } else count[1][2] += 1;
                }
                //Elephant
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 6) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][3] += 1;
                    } else count[1][3] += 1;
                }
                //Guard
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 8) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][4] += 1;
                    } else count[1][4] += 1;
                }
                //General
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 10) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][5] += 1;
                    } else count[1][5] += 1;
                }
                //Connon
                if (chessboard.getSquareComponents()[i][j].getChessnumber() == 12) {
                    if (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.RED) {
                        count[0][6] += 1;
                    } else count[1][6] += 1;
                }
            }
        }
        int a1 = 5 - count[0][0];//被吃的红兵
        int a2 = 5 - count[1][0];
        int b1 = 5 - count[0][1];
        int b2 = 5 - count[1][1];
        int c1 = 5 - count[0][2];
        int c2 = 5 - count[1][2];
        int d1 = 5 - count[0][3];
        int d2 = 5 - count[1][3];
        int e1 = 5 - count[0][4];
        int e2 = 5 - count[1][4];
        int f1 = 5 - count[0][5];
        int f2 = 5 - count[1][5];
        int g1 = 5 - count[0][6];
        int g2 = 5 - count[1][6];

        ImageIcon imga1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红兵.gif");
        imga1.setImage(imga1.getImage().getScaledInstance(80, 80, 0));//设置图片的大小
        JLabel labela1 = new JLabel(imga1);
        labela1.setBounds(0,0,80,80);

        ImageIcon imgb1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红马.gif");
        ImageIcon imgc1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红车.gif");
        ImageIcon imgd1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红象.gif");
        ImageIcon imge1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红士.gif");
        ImageIcon imgf1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红帅.gif");
        ImageIcon imgg1 = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红炮.gif");
        JButton[][] jButtons = new JButton[2][16];
        JButton jButton1 = new JButton();


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

        button.addActionListener(e -> {
            eChessboard eChessboard = new eChessboard();
            System.out.printf("%d,%d",count[0][0],count[0][2]);
            eChessboard.setVisible(true);
        });
        add(button);
    }
}