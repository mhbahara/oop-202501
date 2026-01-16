package com.upb.agripos.view;

import com.upb.agripos.util.SessionManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {
    
    public static void show(Stage primaryStage) throws Exception {
        // Inisialisasi komponen
        BorderPane root = new BorderPane();
        
        // Top Bar - Header
        VBox topBar = createTopBar();
        root.setTop(topBar);
        
        // Left Sidebar - Menu
        VBox sidebar = createSidebar();
        root.setLeft(sidebar);
        
        // Center - Content Area
        VBox content = createContent();
        root.setCenter(content);
        
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("AgriPOS - Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
    private static VBox createTopBar() {
        VBox topBar = new VBox();
        topBar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 10;");
        
        Label titleLabel = new Label("Admin Dashboard - " + 
            SessionManager.getCurrentUser().getFullName());
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
        
        Button logoutBtn = new Button("Logout");
        logoutBtn.setStyle("-fx-padding: 5 15;");
        logoutBtn.setOnAction(e -> handleLogout());
        
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.getChildren().addAll(titleLabel, logoutBtn);
        
        topBar.getChildren().add(hbox);
        return topBar;
    }
    
    private static VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setStyle("-fx-background-color: #34495e; -fx-padding: 10;");
        sidebar.setPrefWidth(200);
        sidebar.setSpacing(10);
        
        // Menu buttons
        Button dashboardBtn = new Button("Dashboard");
        Button userMgmtBtn = new Button("Manajemen User");
        Button productMgmtBtn = new Button("Manajemen Produk");
        Button reportBtn = new Button("Laporan");
        Button settingsBtn = new Button("Pengaturan");
        
        for (Button btn : new Button[]{dashboardBtn, userMgmtBtn, 
                                       productMgmtBtn, reportBtn, settingsBtn}) {
            btn.setPrefWidth(Double.MAX_VALUE);
            btn.setStyle("-fx-padding: 10; -fx-font-size: 12;");
        }
        
        userMgmtBtn.setOnAction(e -> System.out.println("Buka User Management"));
        productMgmtBtn.setOnAction(e -> System.out.println("Buka Product Management"));
        reportBtn.setOnAction(e -> System.out.println("Buka Laporan"));
        
        sidebar.getChildren().addAll(dashboardBtn, userMgmtBtn, productMgmtBtn, 
                                     reportBtn, settingsBtn);
        
        return sidebar;
    }
    
    private static VBox createContent() {
        VBox content = new VBox();
        content.setPadding(new Insets(20));
        content.setSpacing(15);
        
        Label welcome = new Label("Selamat datang, " + 
            SessionManager.getCurrentUser().getFullName() + "!");
        welcome.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        
        Label role = new Label("Role: Admin");
        role.setStyle("-fx-font-size: 12;");
        
        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(20);
        statsGrid.setVgap(20);
        
        // Contoh statistik
        VBox stat1 = createStatBox("Total Transaksi", "1,250");
        VBox stat2 = createStatBox("Total Pendapatan", "Rp 25.000.000");
        VBox stat3 = createStatBox("Pengguna Aktif", "5");
        
        statsGrid.add(stat1, 0, 0);
        statsGrid.add(stat2, 1, 0);
        statsGrid.add(stat3, 2, 0);
        
        content.getChildren().addAll(welcome, role, new Separator(), statsGrid);
        return content;
    }
    
    private static VBox createStatBox(String title, String value) {
        VBox box = new VBox();
        box.setPadding(new Insets(15));
        box.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5;");
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        
        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        
        box.getChildren().addAll(titleLabel, valueLabel);
        return box;
    }
    
    private static void handleLogout() {
        SessionManager.logout();
        System.out.println("Logout berhasil");
        // TODO: Kembali ke login screen
    }
}
