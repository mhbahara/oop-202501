package com.upb.agripos;

import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Test database connection
        if (!DatabaseConnection.getInstance().testConnection()) {
            System.err.println("Failed to connect to database. Please check configuration.");
            System.exit(1);
        }
        
        // Show login view
        LoginView loginView = new LoginView();
        loginView.show(primaryStage);
    }
    
    @Override
    public void stop() {
        // Close database connection when app closes
        DatabaseConnection.getInstance().closeConnection();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}