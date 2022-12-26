package com.javafx01.javafx02;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

public class javaFx04 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("线条示例");
        Group group = new Group();
        Scene scene = new Scene(group, 300, 150, Color.GRAY);
        //直线
        /*Line line = new Line();
        line.setStartX(10.0f);
        line.setStartY(10.0f);
        line.setEndX(150.0f);
        line.setEndY(100.0f);*/
        //线段
        Line line = new Line(10, 10, 200, 10);
        line.setStroke(Color.RED);
        line.setStrokeWidth(10);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().addAll(50d, 5d, 15d, 15d, 20d);//每段长度
        line.setStrokeDashOffset(10);

        group.getChildren().add(line);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

