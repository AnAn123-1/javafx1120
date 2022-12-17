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

    public SquareComponent[][] getSquareComponents() {
        return squareComponents;
    }

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
                ChessGameFrame.getRedplayer().setScore(ChessGameFrame.getRedplayer().getScore() + chess2.getScore());
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
        if(ChessGameFrame.getRedplayer().getScore() >= 60 || ChessGameFrame.getBlackplayer().getScore() >= 60 ){

            Restart();
            ChessGameFrame.getRedplayer().setScore(0);
            ChessGameFrame.getBlackplayer().setScore(0);
            ChessGameFrame.getRedLabel().setText((String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore())));
            ChessGameFrame.getBlackLabel().setText((String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore())));
            this.setCurrentColor(ChessColor.BLACK);
            ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
            Dialog dial = new Dialog(ChessGameFrame.getRedplayer().getScore()>ChessGameFrame.getBlackplayer().getScore()?"RED":"BLACK");
        }
    }


    //FIXME:   Initialize chessboard for testing only.
    public void initAllChessOnBoard() {
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
                    case 0 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    case 1 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    case 2 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    case 3 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new ElephantChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    case 4 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new GuardChesscomponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    case 5 -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
                    default -> {
                        if (color == ChessColor.RED) {
                            a[1][x]--;
                        } else {
                            a[2][x]--;
                        }
                        squareComponent = new ConnonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                    }
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
        chessData.add("-2");
        chessData.add("-2");
        chessData.add("-2");
        chessData.forEach(System.out::println);
        int[][] a = new int[][]{{5, 2, 2, 2, 2, 2, 1},{5, 2, 2, 2, 2, 2, 1}};
        if (chessData.get(0).equals("100")) this.setCurrentColor(ChessColor.BLACK);
        else if(chessData.get(0).equals("101"))this.setCurrentColor(ChessColor.RED);
        else{
            JOptionPane.showMessageDialog(null,"104:FILE CONTENT ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        ChessGameFrame.getStatusLabel().setText(String.format("%s's TURN",this.getCurrentColor().getName()));
        ChessGameFrame.getBlackplayer().setScore(Integer.parseInt(chessData.get(1)));
        ChessGameFrame.getRedplayer().setScore(Integer.parseInt(chessData.get(2)));
        ChessGameFrame.getBlackLabel().setText((String.format("BLACK`S SCORE: %d", ChessGameFrame.getBlackplayer().getScore())));
        ChessGameFrame.getRedLabel().setText(String.format("RED`S SCORE: %d", ChessGameFrame.getRedplayer().getScore()));
        int p= 3;
        int i,j;
        boolean b1 = false;
        boolean b2 = false;
        for(i = 0;i < this.getSquareComponents().length;i ++){
            for(j = 0;j < this.getSquareComponents()[i].length;j ++){
                SquareComponent squareComponent;
                if(chessData.get(p)==null){
                    JOptionPane.showMessageDialog(null,"102:FILE CONTENT ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
                    b2 = true;
                    break;
                }
                int x = 0;
                switch (chessData.get(p)){
                    case "-1" ->{
                        squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE);
                        p++;
                        x = 1;
                    }
                    case "0" ->{
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][0]--;
                        else a[1][0]--;
                        p+=3;
                    }
                    case "2" ->{
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][1]--;
                        else a[1][1]--;
                        p+=3;
                    }
                    case "4" ->{
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][2]--;
                        else a[1][2]--;
                        p+=3;
                    }
                    case "6" ->{
                        squareComponent = new ElephantChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][3]--;
                        else a[1][3]--;
                        p+=3;
                    }
                    case "8" ->{
                        squareComponent = new GuardChesscomponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][4]--;
                        else a[1][4]--;
                        p+=3;
                    }
                    case "10" ->{
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][6]--;
                        else a[1][6]--;
                        p+=3;
                    }
                    case "12" ->{
                        squareComponent = new ConnonChessComponent(new ChessboardPoint(i,j),calculatePoint(i,j), chessData.get(p + 1).equals("1") ?ChessColor.BLACK:ChessColor.RED,clickController,CHESS_SIZE);
                        squareComponent.setReversal(chessData.get(p + 2).equals("1"));
                        if(chessData.get(p + 1).equals("1")) a[0][5]--;
                        else a[1][5]--;
                        p+=3;
                    }
                    default -> {
                        JOptionPane.showMessageDialog(null,"103:FILE CONTENT ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
                        b1 = true;
                        b2 = true;
                        squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE);
                    }
                }
                if(x == 0) {
                    if (!(chessData.get(p - 2).equals("0") || chessData.get(p - 2).equals("1"))) {
                        JOptionPane.showMessageDialog(null, "102:FILE CONTENT ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                        b2 = true;
                        break;
                    }
                    if (!(chessData.get(p - 1).equals("0") || chessData.get(p - 1).equals("1"))) {
                        JOptionPane.showMessageDialog(null, "102:FILE CONTENT ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                        b2 = true;
                        break;
                    }
                }
                int o,q;
                for(o = 0;o < 2;o ++){
                    for(q = 0;q < 7;q ++){
                        if(a[o][q] < 0) {
                            JOptionPane.showMessageDialog(null, "103:FILE CONTENT ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                            b1 = true;
                            b2 = true;
                            break;
                        }
                    }
                    if(b1) break;
                }
                if(b1) {
                    break;
                }
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);

            }
            if(b2) break;
        }
        if(!chessData.get(p).equals("-2")){
            JOptionPane.showMessageDialog(null,"102:FILE CONTENT ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        for(i = 0;i < this.getSquareComponents().length;i ++) {
            for (j = 0; j < this.getSquareComponents()[i].length; j++) {
                this.squareComponents[i][j].repaint();
            }
        }
    }

    public void Restart(){
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                this.squareComponents[i][j].setVisible(false);
                remove(squareComponents[i][j]);
            }
        }
        initAllChessOnBoard();
    }
}
