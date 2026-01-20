package com.upb.agripos.view;

<<<<<<< HEAD
import com.upb.agripos.controller.LoginController;
import com.upb.agripos.model.User;
import com.upb.agripos.util.SessionManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
=======
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
import javafx.stage.Stage;

public class MainView {
    private LoginController loginController;
    private TabPane tabPane;
    private User currentUser;
    
<<<<<<< HEAD
    public MainView() {
        this.loginController = new LoginController();
        this.currentUser = SessionManager.getInstance().getCurrentUser();
    }
    
    public void show(Stage stage) {
        BorderPane root = new BorderPane();
        
        // Header
        HBox header = createHeader(stage);
        root.setTop(header);
        
        // Tab pane
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Add tabs based on role
        if (currentUser.isKasir()) {
            // Kasir only sees transaction tab
            Tab cashierTab = new Tab("Transaksi Penjualan");
            cashierTab.setContent(new CashierView());
            tabPane.getTabs().add(cashierTab);
            
        } else if (currentUser.isAdmin()) {
            // Admin sees all tabs
            Tab productTab = new Tab("Manajemen Produk");
            productTab.setContent(new ProductManagementView());
            
            Tab cashierTab = new Tab("Transaksi Penjualan");
            cashierTab.setContent(new CashierView());
            
            Tab reportTab = new Tab("Laporan Penjualan");
            reportTab.setContent(new ReportView());
            
            tabPane.getTabs().addAll(productTab, cashierTab, reportTab);
        }
        
        root.setCenter(tabPane);
        
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("AGRI-POS - " + currentUser.getRole());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    private HBox createHeader(Stage stage) {
        HBox header = new HBox(20);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: #2e7d32;");
        
        // Title
        Label titleLabel = new Label("AGRI-POS");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        
        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // User info
        Label userLabel = new Label(currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        userLabel.setFont(Font.font("Arial", 14));
        userLabel.setStyle("-fx-text-fill: white;");
        
        // Logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #c62828; -fx-text-fill: white; " +
                             "-fx-font-weight: bold; -fx-background-radius: 5;");
        logoutButton.setOnAction(e -> handleLogout(stage));
        
        header.getChildren().addAll(titleLabel, spacer, userLabel, logoutButton);
        return header;
    }
    
    private void handleLogout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText("Apakah Anda yakin ingin logout?");
        alert.setContentText("Anda akan kembali ke halaman login.");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                loginController.logout();
                
                // Show login view
                LoginView loginView = new LoginView();
                loginView.show(stage);
            }
        });
    }
}
=======
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
>>>>>>> c6a2d84756afe6ec286e66b35e0c193c781e75d5
