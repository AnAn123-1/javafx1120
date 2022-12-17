package controller;

import chessComponent.EmptySlotComponent;
import chessComponent.SquareComponent;
import view.Chessboard;

public class CheatingClickController extends ClickController{

    public CheatingClickController(Chessboard chessboard) {
        super(chessboard);
    }
    public boolean handleFirst(SquareComponent squareComponent) {
        if (!squareComponent.isReversal()&&!(squareComponent instanceof EmptySlotComponent)) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
        }
        return false;
    }

    @Override
    public void onClick(SquareComponent squareComponent) {
        handleFirst(squareComponent);
    }
}
