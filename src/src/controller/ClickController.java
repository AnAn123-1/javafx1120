package controller;


import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.Chessboard;

import javax.swing.*;

public class ClickController {
    private final Chessboard chessboard;
    private SquareComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(SquareComponent squareComponent) {
        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {
                squareComponent.setSelected(true);
                first = squareComponent;
                System.out.println("here");
                first.repaint();
                int i,j;
                for(i = 0;i < chessboard.getChessComponents().length;i ++){
                    for(j = 0;j < chessboard.getChessComponents()[i].length;j ++){
                        if(first.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))) {
                            if(first.getChessnumber() == 12 && !chessboard.getChessComponents()[i][j].isReversal()) {
                                chessboard.getChessComponents()[i][j].setCanmove(true);
                                chessboard.getChessComponents()[i][j].repaint();
                            }else if (first.getChessnumber() == 12 &&
                                    chessboard.getChessComponents()[i][j].isReversal() &&
                                    chessboard.getChessComponents()[i][j].getChessColor() != first.getChessColor()){
                                chessboard.getChessComponents()[i][j].setCanmove(true);
                                chessboard.getChessComponents()[i][j].repaint();
                            } else if (first.getChessnumber() != 12 &&
                                    chessboard.getChessComponents()[i][j].isReversal() &&
                                    chessboard.getChessComponents()[i][j].getChessColor() != first.getChessColor()){
                                chessboard.getChessComponents()[i][j].setCanmove(true);
                                chessboard.getChessComponents()[i][j].repaint();
                            }
                        }
                    }
                }
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();

                int i,j;
                for(i = 0;i < chessboard.getChessComponents().length;i ++){
                    for(j = 0;j < chessboard.getChessComponents()[i].length;j ++){
                            chessboard.getChessComponents()[i][j].setCanmove(false);
                            chessboard.getChessComponents()[i][j].repaint();
                    }
                }
            }
            else if (handleSecond(squareComponent)) {
                //repaint in swap chess method.
                chessboard.clickController.swapPlayer();
                chessboard.swapChessComponents(first, squareComponent);

                first.setSelected(false);
                first = null;
                int i,j;
                for(i = 0;i < chessboard.getChessComponents().length;i ++){
                    for(j = 0;j < chessboard.getChessComponents()[i].length;j ++){
                        chessboard.getChessComponents()[i][j].setCanmove(false);
                        chessboard.getChessComponents()[i][j].repaint();
                    }
                }
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    public boolean handleFirst(SquareComponent squareComponent) {
        if (!squareComponent.isReversal()&&!(squareComponent instanceof EmptySlotComponent)) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            chessboard.clickController.swapPlayer();
            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(SquareComponent squareComponent) {
        if(first.getChessnumber()<12) {
        //没翻开或空棋子，进入if
        if (!squareComponent.isReversal()) {
            //没翻开且非空棋子不能走
            if (!(squareComponent instanceof EmptySlotComponent)) {
                return false;
            }
        }

            return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
        }
        else {
            if(!squareComponent.isReversal()) {
                return first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
            }
            else return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
        }
    }

    public void swapPlayer() {
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
        ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN", chessboard.getCurrentColor().getName()));
    }
}
