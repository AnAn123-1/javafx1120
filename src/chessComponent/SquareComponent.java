package chessComponent;

import Music.MusicPlayer;
import Music.MusicTable;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 这个类是一个抽象类，主要表示8*4棋盘上每个格子的棋子情况。
 * 有两个子类：
 * 1. EmptySlotComponent: 空棋子
 * 2. ChessComponent: 表示非空棋子
 */
public abstract class SquareComponent extends JComponent {

    private static final Color squareColor = new Color(255, 215, 215);
    protected static int spacingLength;
    protected static final Font CHESS_FONT = new Font("Rockwell", Font.BOLD, 36);

    /**
     * chessboardPoint: 表示8*4棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0)等等
     * chessColor: 表示这个棋子的颜色，有红色，黑色，无色三种
     * isReversal: 表示是否翻转
     * selected: 表示这个棋子是否被选中
     */
    private ChessboardPoint chessboardPoint;
    protected final ChessColor chessColor;
    protected boolean isReversal;

    protected boolean SaveReaversal;

    public void setSaveReaversal(boolean saveReaversal) {
        SaveReaversal = saveReaversal;
    }

    public boolean isSaveReaversal() {
        return SaveReaversal;
    }

    private boolean selected;

    private boolean canmove;

    public void setCanmove(boolean canmove) {
        this.canmove = canmove;
    }

    public boolean isCanmove() {
        return canmove;
    }

    protected int score;

    public int getChessnumber() {
        return chessnumber;
    }

    protected int chessnumber;

    /**
     * handle click event
     */
    private ClickController clickController;

    public void setClickController(ClickController clickController) {
        this.clickController = clickController;
    }

    public ClickController getClickController() {
        return clickController;
    }

    protected SquareComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        //setSize(60, 60);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
        this.isReversal = false;
    }

    public boolean isReversal() {
        return isReversal;
    }

    public void setReversal(boolean reversal) {
        isReversal = reversal;
    }

    public static void setSpacingLength(int spacingLength) {
        SquareComponent.spacingLength = spacingLength;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(SquareComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
            clickController.onClick(this);
        }
    }

    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 1)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false。
     */
    //todo: Override this method for Cannon
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if (this.getChessnumber() < 12) {
            if (this.getChessnumber() >= 2) {
                if ((destination.getY() == this.chessboardPoint.getY() && (destination.getX() - this.chessboardPoint.getX() == 1 || this.chessboardPoint.getX() - destination.getX() == 1)) ||
                        (destination.getX() == this.chessboardPoint.getX() && (destination.getY() - this.chessboardPoint.getY() == 1 || this.chessboardPoint.getY() - destination.getY() == 1))) {
                    if (destinationChess.isReversal()) {
                        if (destinationChess.getChessnumber() <= this.getChessnumber() && destinationChess.getChessnumber() < 12)
                            return true;
                        else return destinationChess.getChessnumber() >= 12;
                    } else return destinationChess instanceof EmptySlotComponent;
                } else return false;
            } else if ((destination.getY() == this.chessboardPoint.getY() && (destination.getX() - this.chessboardPoint.getX() == 1 || this.chessboardPoint.getX() - destination.getX() == 1)) ||
                    (destination.getX() == this.chessboardPoint.getX() && (destination.getY() - this.chessboardPoint.getY() == 1 || this.chessboardPoint.getY() - destination.getY() == 1)))
                return destinationChess.getChessnumber() == 10 || destinationChess.getChessnumber() == 0;
            else return false;
        } else {
            if (!(destinationChess instanceof EmptySlotComponent)) {
                int i;
                int k = 0;
                if (destination.getX() == this.chessboardPoint.getX() && destination.getY() != this.chessboardPoint.getY()) {
                    if (destination.getY() > this.chessboardPoint.getY()) {
                        for (i = 0; i < destination.getY() - this.chessboardPoint.getY(); i++) {
                            if (!(chessboard[this.chessboardPoint.getX()][this.chessboardPoint.getY() + i] instanceof EmptySlotComponent))
                                k++;
                        }
                    } else {
                        for (i = 0; i < this.chessboardPoint.getY() - destination.getY(); i++) {
                            if (!(chessboard[this.chessboardPoint.getX()][this.chessboardPoint.getY() - i] instanceof EmptySlotComponent))
                                k++;
                        }
                    }
                    return k == 2;
                } else if (destination.getY() == this.chessboardPoint.getY() && destination.getX() != this.chessboardPoint.getX()) {
                    if (destination.getX() > this.chessboardPoint.getX()) {
                        for (i = 0; i < destination.getX() - this.chessboardPoint.getX(); i++) {
                            if (!(chessboard[this.chessboardPoint.getX() + i][this.chessboardPoint.getY()] instanceof EmptySlotComponent))
                                k++;
                        }
                    } else {
                        for (i = 0; i < this.chessboardPoint.getX() - destination.getX(); i++) {
                            if (!(chessboard[this.chessboardPoint.getX() - i][this.chessboardPoint.getY()] instanceof EmptySlotComponent))
                                k++;
                        }
                    }
                    return k == 2;
                } else return false;
            } else return false;
        }
        //todo: complete this method
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        //System.out.printf("repaint chess [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
        g.setColor(squareColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
    }

    public int getScore() {
        return score;
    }

}
