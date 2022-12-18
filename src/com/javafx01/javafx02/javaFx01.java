package com.javafx01.javafx02;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class javaFx01 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group,300,250, Color.WHITE);//创建场景
        /*stage.setTitle("矩形示例");
        Rectangle rectangle = new Rectangle(20,20,200,200);//创建矩形
        rectangle.setX(50);
        rectangle.setY(50);
        rectangle.setHeight(100);
        rectangle.setWidth(200);
        //圆角
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        group.getChildren().add(rectangle);*/
        stage.setTitle("椭圆示例");
        DropShadow dropShadow = new DropShadow();//给图形添加阴影 好看
        dropShadow.setOffsetX(3.0);
        dropShadow.setColor(Color.color(0.4,0.4,0.4));

        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(50.0f);
        ellipse.setCenterY(50.0f);
        ellipse.setRadiusX(50.0f);
        ellipse.setRadiusY(25.0f);
        ellipse.setEffect(dropShadow);

        group.getChildren().add(ellipse);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

