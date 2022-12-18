package Music;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MusicTable extends JFrame {
    public final int MUSICBOARD_SIZE;
    private final int WIDTH;
    private final int HEIGHT;

    public MusicTable(int width, int height) {
        setTitle("Terrible Music"); //设置标题
        this.MUSICBOARD_SIZE = height * 4 / 5;
        this.WIDTH = width;
        this.HEIGHT = height;
        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addMusicButton();
    }

    //粘贴addMusicButton
    public void addMusicButton() {
        Thread thread1 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\pro_try\\src\\Music\\群青.wav"), 0.5);
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\pro_try\\src\\Music\\恋爱循环.wav"), 0.5);
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {
                Data.playMusic(new File("D:\\IdeaProjects\\pro_try\\src\\Music\\夜的钢琴曲五.wav"), 0.5);
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

        ImageIcon square = new ImageIcon("D:\\IdeaProjects\\pro_try\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
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
        Label label = new Label("Music");
        label.setBounds(WIDTH * 3 / 5, HEIGHT / 10 + 620, 50, 30);
        add(label);
    }

    /*声音模糊。方式笨重
    int b;public void setB(int b) {this.b = b;}public int getB() {return b;}
    private void addMusicButton2() {
        JButton button = new JButton("▶");
        MusicPlayer player = new MusicPlayer("D:\\IdeaProjects\\pro_try\\src\\Music\\恋爱循环.wav");
        button.addActionListener((e) -> new Thread(() -> {
            try {
                if (getB() % 2 == 0) {
                    player.setVolumn(6f).play();
                    setB(b + 1);
                    button.setText("∎");
                    button.addActionListener(e1 -> {
                        setB(b + 2);
                        button.setText("▶");
                        player.over();
                    });
                } else {
                    setB(b + 1);
                    player.over();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }).start());
        ImageIcon square = new ImageIcon("D:\\IdeaProjects\\pro_try\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
        square.setImage(square.getImage().getScaledInstance(100, 100, 0));//设置图片的大小
        button.setIcon(square);//把图片放到按钮上
        button.setPressedIcon(square);
        button.setBounds(60, 120, 160, 60);
        button.setFont(new Font("音乐2", Font.BOLD, 20));
        add(button);
    }*/
}

