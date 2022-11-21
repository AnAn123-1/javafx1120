package com.javafx01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFx05Stage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(800);
        stage.setHeight(800);

        BorderPane borderPane = new BorderPane();
        ImageView imageView = new ImageView(new Image("目.jpg",400,400,true,true));//设置图像大小
        imageView.setTranslateX(200);
        imageView.setTranslateY(200);//设置图片原始位置
        borderPane.getChildren().add(imageView);

        Scene scene = new Scene(borderPane, 300, 200);//创建场景
        stage.setScene(scene);//场景设置添加到舞台中
        // 舞台事件
        stage.setOnCloseRequest(e -> System.out.println("系统被关闭了。。。"));//点叉关闭窗口后打印上述内容

        //舞台键盘事件：KEY_TYPED键盘输入
        // A左D右W上S下
        stage.addEventFilter(KeyEvent.KEY_TYPED, e->{
            if(e.getCharacter().equalsIgnoreCase("A")){
                imageView.setTranslateX(imageView.getTranslateX()-1);
            }
            if(e.getCharacter().equalsIgnoreCase("D")){
                imageView.setTranslateX(imageView.getTranslateX()+1);
            }
            if(e.getCharacter().equalsIgnoreCase("W")){
                imageView.setTranslateY(imageView.getTranslateY()-1);
            }
            if(e.getCharacter().equalsIgnoreCase("S")){
                imageView.setTranslateY(imageView.getTranslateY()+1);
            }
        });
        //KET_PRESSED键盘按下
        stage.addEventHandler(KeyEvent.KEY_PRESSED,e->{
            //ESC按下ESC退出程序
            if(27 == e.getCode().getCode()){
                Platform.exit();
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
