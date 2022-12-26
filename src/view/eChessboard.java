package view;

import chessComponent.SquareComponent;
import view.Chessboard.*;
import view.ChessGameFrame.*;

import javax.swing.*;
import java.awt.*;

public class eChessboard extends JFrame{
    public eChessboard(){
        setTitle("Dark Chess!"); //设置标题
        setLocationRelativeTo(null); // Center the window.
        setBounds(800,0,400,1000);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(null);

        addPicture(1,0,5);
        addPicture(1,2,1);
        addPicture(1,4,2);
        addPicture(1,6,1);
        addPicture(1,8,2);
        addPicture(1,10,1);
        addPicture(1,12,1);
        addPicture(2,0,5);
        addPicture(2,2,1);
        addPicture(2,4,0);
        addPicture(2,6,1);
        addPicture(2,8,0);
        addPicture(2,10,1);
        addPicture(2,12,2);//只要在ChessGameFrame里计算出被吃的数量

    }
    public void addPicture(int color,int type,int number){
        if(color==1){
            switch (type){
                case (0)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红兵.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,0,80,80);
                        add(label);
                    }
                }
                case (2)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红马.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,80,80,80);
                        add(label);
                    }
                }
                case (4)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红车.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),80,80,80);
                        add(label);
                    }
                }
                case (6)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红象.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,160,80,80);
                        add(label);
                    }
                }
                case (8)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红士.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),160,80,80);
                        add(label);
                    }
                }
                case (10)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红帅.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,240,80,80);
                        add(label);
                    }
                }
                case (12)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红炮.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),240,80,80);
                        add(label);
                    }
                }
            }
        }
        if(color==2){
            switch (type){
                case (0)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑卒.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,320,80,80);
                        add(label);
                    }
                }
                case (2)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑马.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,400,80,80);
                        add(label);
                    }
                }
                case (4)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑车.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),400,80,80);
                        add(label);
                    }
                }
                case (6)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑象.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,480,80,80);
                        add(label);
                    }
                }
                case (8)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑士.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),480,80,80);
                        add(label);
                    }
                }
                case (10)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\黑将.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*n,560,80,80);
                        add(label);
                    }
                }
                case (12)->{
                    ImageIcon img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\GIF\\红炮.gif");
                    img.setImage(img.getImage().getScaledInstance(80, 80, 0));
                    for(int n=0;n<number;n++){
                        JLabel label = new JLabel(img);
                        label.setBounds(80*(n+2),560,80,80);
                        add(label);
                    }
                }
            }
        }
    }
}
