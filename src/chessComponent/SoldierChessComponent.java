package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;

public class SoldierChessComponent extends ChessComponent {

    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        score = 1;
        if (this.getChessColor() == ChessColor.RED) {
            chessnumber = 0;
            name = "bing";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\redsodier.png");
        } else {
            chessnumber = 0;
            name = "zu";
            img = new ImageIcon("D:\\IdeaProjects\\javafx1120\\src\\darkchess\\blacksodier.png");
        }
    }

}
