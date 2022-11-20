package com.javafx01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFx02Stage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("登录注册");//界面标题
        // 在界面中加一个按钮
        // 1.需要布局
        HBox hBox = new HBox();//水平布局
        TextField userNameField = new TextField();
        Button helloWorld = new Button("HelloWorld!");
        helloWorld.setOnAction(e->{
            System.out.println("用户名："+userNameField.getText());
            System.out.println("Hello");
        });//用户输入名字后点击按钮，就会打印以上内容
        //将节点添加到布局中
        //hBox.getChildren().add(helloWorld);
        hBox.getChildren().addAll(userNameField,helloWorld);
        //2.创建场景
        Scene scene = new Scene(hBox,300,200);
        //3.将场景放到舞台中
        stage.setScene(scene);
        //4.展示舞台


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
