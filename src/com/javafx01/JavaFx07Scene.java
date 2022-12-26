package com.javafx01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//图片跟随鼠标移动
public class JavaFx07Scene extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("证券系统");
        stage.getIcons().add(new Image("目.jpg"));

        stage.setWidth(800);
        stage.setHeight(800);

        BorderPane borderPane = new BorderPane();
        ImageView imageView = new ImageView(new Image("目.jpg", 400, 400, true, true));//设置图像大小
        borderPane.getChildren().add(imageView);

        Scene scene = new Scene(borderPane, 300, 200);//创建场景
        stage.setScene(scene);//场景设置添加到舞台中

        stage.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            imageView.setTranslateX(e.getX() - 200);
            imageView.setTranslateY(e.getY() - 200);//图片随鼠标移动
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
