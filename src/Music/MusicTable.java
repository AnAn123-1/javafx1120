package Music;

import javax.swing.*;
import java.awt.*;

public class MusicTable extends JFrame {
    public final int MUSICBOARD_SIZE;

    public MusicTable(int width, int height) {
        setTitle("Terrible Music"); //设置标题
        this.MUSICBOARD_SIZE = height * 4 / 5;

        setSize(width, height);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addMusicButton1();
        addMusicButton2();
        addMusicButton3();
        Label label = new Label("Stop playing before switch another one");
        label.setBounds(10,240,220,50);
        add(label);
    }
    //粘贴addMusicButton
    int b;
    public void setB(int b){
        this.b= b;
    }
    public int getB(){
        return b;
    }
    private void addMusicButton1() {
        JButton button = new JButton("▶");
        MusicPlayer player = new MusicPlayer("D:\\IdeaProjects\\javafx1120\\src\\Music\\群青.wav");
        button.addActionListener((e) ->new Thread(()->{ try {
            if (getB()%2==0){player.setVolumn(6f).play();
                setB(b+1);
                button.setText("∎");
                button.addActionListener(e1 -> {
                    setB(b+2);
                    button.setText("▶");
                    player.over();
                });}
            else {
                setB(b+1);
                player.over();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }}).start());
        ImageIcon square=new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
        square.setImage(square.getImage().getScaledInstance(100,100, 0));//设置图片的大小
        button.setIcon(square);//把图片放到按钮上
        button.setPressedIcon(square);
        button.setBounds(60,60,160,60);
        /*button.setLocation(60, 60);
        button.setSize(160, 60);*/
        button.setFont(new Font("音乐1", Font.BOLD, 20));
        add(button);
    }
    private void addMusicButton2() {
        JButton button = new JButton("▶");
        MusicPlayer player = new MusicPlayer("D:\\IdeaProjects\\javafx1120\\src\\Music\\恋爱循环.wav");
        button.addActionListener((e) ->new Thread(()->{ try {
            if (getB()%2==0){player.setVolumn(6f).play();
                setB(b+1);
                button.setText("∎");
                button.addActionListener(e1 -> {
                    setB(b+2);
                    button.setText("▶");
                    player.over();
                });}
            else {
                setB(b+1);
                player.over();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }}).start());
        ImageIcon square=new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
        square.setImage(square.getImage().getScaledInstance(100,100, 0));//设置图片的大小
        button.setIcon(square);//把图片放到按钮上
        button.setPressedIcon(square);
        button.setBounds(60,120,160,60);
        button.setFont(new Font("音乐2", Font.BOLD, 20));
        add(button);
    }
    private void addMusicButton3() {
        JButton button = new JButton("▶");
        MusicPlayer player = new MusicPlayer("D:\\IdeaProjects\\javafx1120\\src\\Music\\夜的钢琴曲五.wav");
        button.addActionListener((e) ->new Thread(()->{ try {
            if (getB()%2==0){player.setVolumn(6f).play();
                setB(b+1);
                button.setText("∎");
                button.addActionListener(e1 -> {
                    setB(b+2);
                    button.setText("▶");
                    player.over();
                });}
            else {
                setB(b+1);
                player.over();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }}).start());
        ImageIcon square=new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\Music\\冷酷的星之卡比.png");//获取一个图片
        square.setImage(square.getImage().getScaledInstance(100,100, 0));//设置图片的大小
        button.setIcon(square);//把图片放到按钮上
        button.setPressedIcon(square);
        button.setBounds(60,180,160,60);
        button.setFont(new Font("音乐3", Font.BOLD, 20));
        add(button);
    }

}

