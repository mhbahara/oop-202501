package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        PosController controller = new PosController();
        PosView view = new PosView(controller, primaryStage);

        Scene scene = new Scene(view.getRoot(), 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AgriPOS - Bunga Maura Aulya");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
