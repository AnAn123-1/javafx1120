package com.javafx01;

import javafx.application.Application;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFx06Scene extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(new TextField(), new PasswordField(), new Button("登录"));

        Scene scene = new Scene(vBox, 300, 200);
        //scene.setCursor(Cursor.OPEN_HAND);//鼠标变成张开的手（输入栏还是竖线）
        //scene.setCursor(Cursor.CLOSED_HAND);//鼠标变成握紧的手（输入栏还是竖线）
        //scene.setCursor(Cursor.MOVE);//鼠标变成拖动（输入栏还是竖线）
        //scene.setCursor(Cursor.TEXT);//鼠标变成竖线

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
