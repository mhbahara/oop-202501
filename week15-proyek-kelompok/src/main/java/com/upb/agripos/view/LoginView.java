package com.upb.agripos.view;

import com.upb.agripos.controller.LoginController;
import com.upb.agripos.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginView {
    private LoginController loginController;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;
    
    public LoginView() {
        this.loginController = new LoginController();
    }
    
    public void show(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Title
        Label titleLabel = new Label("AGRI-POS");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: #2e7d32;");
        
        Label subtitleLabel = new Label("Sistem Kasir Toko Pertanian");
        subtitleLabel.setFont(Font.font("Arial", 14));
        subtitleLabel.setStyle("-fx-text-fill: #666;");
        
        // Login form
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(400);
        formBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        
        // Username
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        usernameField = new TextField();
        usernameField.setPromptText("Masukkan username");
        usernameField.setPrefHeight(35);
        
        // Password
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        passwordField = new PasswordField();
        passwordField.setPromptText("Masukkan password");
        passwordField.setPrefHeight(35);
        
        // Message label
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");
        messageLabel.setWrapText(true);
        
        // Login button
        Button loginButton = new Button("LOGIN");
        loginButton.setPrefWidth(200);
        loginButton.setPrefHeight(40);
        loginButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; " +
                           "-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;");
        loginButton.setOnMouseEntered(e -> 
            loginButton.setStyle("-fx-background-color: #1b5e20; -fx-text-fill: white; " +
                               "-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;"));
        loginButton.setOnMouseExited(e -> 
            loginButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; " +
                               "-fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;"));
        
        loginButton.setOnAction(e -> handleLogin(stage));
        
        // Press Enter to login
        passwordField.setOnAction(e -> handleLogin(stage));
        
        // Demo credentials info
        Label infoLabel = new Label("Demo: admin/admin123 atau kasir1/kasir123");
        infoLabel.setFont(Font.font("Arial", 10));
        infoLabel.setStyle("-fx-text-fill: #999;");
        
        formBox.getChildren().addAll(
            usernameLabel, usernameField,
            passwordLabel, passwordField,
            messageLabel,
            loginButton,
            infoLabel
        );
        
        root.getChildren().addAll(titleLabel, subtitleLabel, formBox);
        
        Scene scene = new Scene(root, 500, 550);
        stage.setTitle("AGRI-POS - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private void handleLogin(Stage stage) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        try {
            User user = loginController.login(username, password);
            
            // Login successful - show main view
            MainView mainView = new MainView();
            mainView.show(stage);
            
        } catch (Exception ex) {
            messageLabel.setText(ex.getMessage());
            passwordField.clear();
        }
    }
}