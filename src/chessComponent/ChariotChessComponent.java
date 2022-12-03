package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

/**
 * 表示黑红车
 */
public class ChariotChessComponent extends ChessComponent {

    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 5;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 4;
            name = "ju";
            img = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\darkchess\\redchariot.png");
        } else {
            chessnumber = 5;
            name = "che";
            img = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\darkchess\\blackchariot.png");
        }
    }

}
