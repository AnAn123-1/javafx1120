package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class ElephantChessComponent extends ChessComponent {
    public ElephantChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 6;
            name = "xiang";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\redelephant.png");
        } else {
            chessnumber = 6;
            name = "xiang";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\blackelephant.png");
        }
    }
}
