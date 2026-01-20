package com.upb.agripos;

import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.view.LoginView;
<<<<<<< HEAD
=======

>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    
    @Override
<<<<<<< HEAD
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
=======
    public void start(Stage primaryStage) throws Exception {
        try {
            // Tampilkan login screen terlebih dahulu, database akan diinisialisasi saat login
            System.out.println("Loading login view...");
            LoginView.show(primaryStage);
            System.out.println("Login view displayed");
            
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void stop() throws Exception {
        DatabaseConnection.closeConnection();
        super.stop();
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
    }
    
    public static void main(String[] args) {
        launch(args);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
