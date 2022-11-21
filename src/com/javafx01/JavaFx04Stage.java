package com.javafx01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import java.awt.event.KeyEvent;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class JavaFx04Stage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //stage.setWidth(300);
        //stage.setHeight(300);//设置界面长宽

        //stage.setTitle("目");
        //stage.getIcons().add(new Image("目.jpg"));//设置左上角标题栏图标。记得把图片拖到src文件夹里

        //stage.setX(600);
        //stage.setY(200);//设置舞台在屏幕中的位置
        /*VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setFullScreen(true);//全屏
        stage.setTitle("证券系统");
        stage.getIcons().add(new Image("目.jpg"));
        stage.setWidth(300);
        stage.setHeight(200);*/

        //舞台风格：带上场景
        //设置了一个退出按钮
        BorderPane borderPane = new BorderPane();
        Button close = new Button("退出");
        close.setOnAction(e-> Platform.exit());
        borderPane.setCenter(close);

        //borderPane.setCenter(new Button("操作股票"));//在中间添加按钮
        Scene scene = new  Scene(borderPane,300,200);//创建场景
        stage.setScene(scene);//场景设置添加到舞台中
        //stage.initStyle(StageStyle.UNDECORATED);//使框框消失

        //stage.setFullScreen(true);//全屏模式

        //舞台事件
        stage.setOnCloseRequest(e-> System.out.println("系统被关闭了。。。"));//点叉关闭窗口后打印上述内容

        //舞台键盘事件：退出
        stage.addEventFilter(KeyEvent.KEY_TYPED, e-> System.out.println(e.getCharacter()));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
