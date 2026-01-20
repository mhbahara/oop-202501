package com.upb.agripos.view;

<<<<<<< HEAD
import com.upb.agripos.controller.LoginController;
import com.upb.agripos.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
=======
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
import javafx.stage.Stage;

public class LoginView {
    private LoginController loginController;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;
    
<<<<<<< HEAD
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
=======
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
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
