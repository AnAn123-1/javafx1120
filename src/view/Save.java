package view;

import chessComponent.EmptySlotComponent;
import model.ChessColor;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    private String path;
    private String name;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Save(String name,Chessboard chessboard){
        File save = new File(name);
        try {
            if(save.createNewFile()){
                FileWriter writer = new FileWriter(name);
                if(chessboard.getCurrentColor()== ChessColor.BLACK) writer.write("0\n");
                else writer.write("1\n");
                writer.write(ChessGameFrame.getBlackplayer().getScore()+"\n"+ChessGameFrame.getRedplayer().getScore()+"\n");
                int i,j;
                for(i = 0;i < chessboard.getSquareComponents().length;i ++){
                    for(j = 0;j < chessboard.getSquareComponents()[i].length;j ++){
                        if(chessboard.getSquareComponents()[i][j] instanceof EmptySlotComponent){
                            writer.write("-1\n");
                        }else {
                            writer.write(chessboard.getSquareComponents()[i][j].getChessnumber() + "\n"
                                    + (chessboard.getSquareComponents()[i][j].getChessColor() == ChessColor.BLACK?"1":"0")+ "\n"
                                    + (chessboard.getSquareComponents()[i][j].isReversal() ? "1" : "0")+"\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"THIS FILE IS SAFELY SAVED\nPath:"+save.getAbsolutePath(),"SAVED",JOptionPane.INFORMATION_MESSAGE);
                writer.close();
            }
            else{
                JOptionPane.showMessageDialog(null,"THIS FILE ALREADY EXISTS!","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
