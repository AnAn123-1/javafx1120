package com.javafx01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

public class JavaFx05Stage extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
