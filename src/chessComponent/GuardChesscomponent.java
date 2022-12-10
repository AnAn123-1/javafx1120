package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class GuardChesscomponent extends ChessComponent{
    public GuardChesscomponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 10;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 8;
            name = "shi";
            img = new ImageIcon("redguard.png");
        } else {
            chessnumber = 9;
            name = "shi";
            img = new ImageIcon("blackguard.png");
        }
    }
}
