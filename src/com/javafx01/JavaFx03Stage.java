package com.javafx01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFx03Stage extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        //让舞台赋值给静态属性
        JavaFx03Stage.stage = stage;
        stage.setTitle("登录注册");//界面标题
        // 1.需要布局
        HBox hBox = new HBox();
        TextField userNameField = new TextField();
        Button login = new Button("登录！");
        Button regist = new Button("注册！");
        //跳转注册界面
        regist.setOnAction(e -> JavaFx03Stage.regist());
        hBox.getChildren().addAll(login, regist);
        //2.创建场景
        Scene scene = new Scene(hBox, 300, 200);
        //3.将场景放到舞台中
        stage.setScene(scene);
        //4.展示舞台


        stage.show();
    }

    public static void regist() {
        VBox vBOX = new VBox();//垂直布局
        Label label = new Label("注册界面");
        vBOX.getChildren().add(label);
        Scene scene = new Scene(vBOX, 300, 200);
        //设置新场景
        JavaFx03Stage.stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}
