package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    
    public static void show(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        
        // Top Bar
        VBox topBar = new VBox();
        topBar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 10;");
        Label titleLabel = new Label("AgriPOS - Main Dashboard");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
        topBar.getChildren().add(titleLabel);
        
        root.setTop(topBar);
        
        // Center content
        VBox content = new VBox();
        content.setPadding(new Insets(20));
        content.setSpacing(15);
        Label welcome = new Label("Welcome to AgriPOS");
        welcome.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        content.getChildren().add(welcome);
        
        root.setCenter(content);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("AgriPOS - Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
