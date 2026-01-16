package com.upb.agripos;

import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.view.LoginView;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    
    @Override
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
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
