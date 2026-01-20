package com.upb.agripos.controller;

<<<<<<< HEAD
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;
=======
import com.upb.agripos.exception.AuthenticationException;
import com.upb.agripos.exception.ValidationException;
import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;
import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.util.SessionManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5

public class LoginController {
    private AuthService authService;
    
<<<<<<< HEAD
    public LoginController() {
        this.authService = new AuthService();
    }
    
    public User login(String username, String password) 
            throws ValidationException, Exception {
        return authService.login(username, password);
    }
    
    public void logout() {
        authService.logout();
    }
    
    public User getCurrentUser() {
        return authService.getCurrentUser();
    }
    
    public boolean isLoggedIn() {
        return authService.isLoggedIn();
    }
    
    public boolean isAdmin() {
        return authService.isAdmin();
    }
    
    public boolean isKasir() {
        return authService.isKasir();
    }
}
=======
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    private Stage stage;
    private static boolean databaseInitialized = false;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        try {
            // Inisialisasi database pada login pertama
            if (!databaseInitialized) {
                try {
                    DatabaseConnection.initializeDatabase();
                    databaseInitialized = true;
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Database Error", 
                            "Tidak dapat terhubung ke database: " + e.getMessage());
                    return;
                }
            }
            
            // Proses login
            User user = AuthService.login(username, password);
            
            // Simpan user ke session
            SessionManager.setCurrentUser(user);
            
            // Tampilkan pesan sukses
            showAlert(Alert.AlertType.INFORMATION, "Login Berhasil", 
                    "Selamat datang " + user.getFullName() + " (" + user.getRole() + ")");
            
            // Buka halaman sesuai role
            if (user.isAdmin()) {
                openAdminDashboard();
            } else if (user.isKasir()) {
                openKasirDashboard();
            }
            
            // Tutup login window
            if (stage != null) {
                stage.close();
            }
            
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.WARNING, "Validasi Error", e.getMessage());
        } catch (AuthenticationException e) {
            showAlert(Alert.AlertType.ERROR, "Login Gagal", e.getMessage());
            passwordField.clear();
        }
    }
    
    @FXML
    private void handleCancel() {
        System.exit(0);
    }
    
    private void openAdminDashboard() {
        // TODO: Buka admin dashboard sesuai dengan desain Anda
        System.out.println("Membuka Admin Dashboard untuk: " + SessionManager.getCurrentUser().getFullName());
    }
    
    private void openKasirDashboard() {
        // TODO: Buka kasir dashboard sesuai dengan desain Anda
        System.out.println("Membuka Kasir Dashboard untuk: " + SessionManager.getCurrentUser().getFullName());
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
