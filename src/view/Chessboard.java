package view;


import chessComponent.*;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);

        initAllChessOnBoard();
    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            if(chess2.getChessColor() == ChessColor.RED) {
                ChessGameFrame.getBlackplayer().setScore(ChessGameFrame.getBlackplayer().getScore() + chess2.getScore());
                ChessGameFrame.getBlackLabel().setText(String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore()));
            }
            else{
                ChessGameFrame.getRedplayer().setScore(ChessGameFrame.getBlackplayer().getScore() + chess2.getScore());
                ChessGameFrame.getRedLabel().setText(String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore()));
            }
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;

        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
    }


    //FIXME:   Initialize chessboard for testing only.
    private void initAllChessOnBoard() {
        Random random = new Random();
        int[][] a = new int[][]{{0, 1, 2, 3, 4, 5, 6}, {5, 2, 2, 2, 2, 1, 2},{5, 2, 2, 2, 2, 1, 2}};
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessColor color = random.nextInt(2) == 0 ? ChessColor.RED : ChessColor.BLACK;
                int y = 0;
                int p;
                for(p = 0;p < 7;p ++){
                    y += a[1][p];
                }
                if(y == 0) color = ChessColor.BLACK;
                y = 0;
                for(p = 0;p < 7;p ++){
                    y += a[2][p];
                }
                if(y == 0) color = ChessColor.RED;
                SquareComponent squareComponent;
                    int x = random.nextInt(7);
                    if(color == ChessColor.RED) {
                        while (a[1][x] == 0) {
                            x = random.nextInt(7);
                        }
                    }
                    if(color == ChessColor.BLACK) {
                        while (a[2][x] == 0) {
                            x = random.nextInt(7);
                        }
                    }
                    switch (x) {
                        case 0:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        case 1:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        case 2:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        case 3:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new ElephantChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new ElephantChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        case 4:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new GuardChesscomponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new GuardChesscomponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        case 5:
                            if (color == ChessColor.RED ) {
                                a[1][x]--;
                                squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                        default:
                            if (color == ChessColor.RED) {
                                a[1][x]--;
                                squareComponent = new ConnonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            } else {
                                a[2][x]--;
                                squareComponent = new ConnonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                            }
                            break;
                    }
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);
            }
        }

    }

    /**
     * 绘制棋盘格子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     * @param chessData
     */
    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
    }
}
