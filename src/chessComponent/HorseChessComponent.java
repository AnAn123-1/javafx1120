package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class HorseChessComponent extends ChessComponent{
    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 32;
            name = "ma";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\redhorse.png");
        } else {
            chessnumber = 3;
            name = "ma";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\blackhorse.png");
        }
    }
}
