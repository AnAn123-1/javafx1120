package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class GeneralChessComponent extends ChessComponent{

    public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 30;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 10;
            name = "shuai";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\redgeneral.png");
        } else {
            chessnumber = 10;
            name = "jiang";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\blackgeneral.png");
        }
    }
}
