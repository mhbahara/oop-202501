package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    
    public static void show(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(15);
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Title
        Label titleLabel = new Label("AgriPOS Login");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        
        // Username field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setPrefHeight(35);
        
        // Password field
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefHeight(35);
        
        // Login button
        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setPrefHeight(40);
        loginButton.setStyle("-fx-font-size: 14;");
        
        // Status label
        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;");
        
        // Login action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText();
            
            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Username dan password tidak boleh kosong!");
                statusLabel.setStyle("-fx-text-fill: red;");
            } else {
                // TODO: Authenticate user with database
                // For now, just proceed to main view
                statusLabel.setText("Login successful!");
                statusLabel.setStyle("-fx-text-fill: green;");
                
                try {
                    // Navigate to main dashboard
                    MainView.show(primaryStage);
                } catch (Exception ex) {
                    statusLabel.setText("Error loading main view: " + ex.getMessage());
                    statusLabel.setStyle("-fx-text-fill: red;");
                }
            }
        });
        
        // Add all components
        root.getChildren().addAll(
            titleLabel,
            new Separator(),
            usernameLabel,
            usernameField,
            passwordLabel,
            passwordField,
            loginButton,
            statusLabel
        );
        
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("AgriPOS - Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
