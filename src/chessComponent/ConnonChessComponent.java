package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class ConnonChessComponent extends ChessComponent{
    public ConnonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 12;
            name = "pao";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\redcannon.png");
        } else {

            chessnumber = 12;
            name = "pao";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\blackcannon.png");
        }
    }
}
