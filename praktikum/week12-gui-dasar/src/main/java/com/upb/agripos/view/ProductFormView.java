package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * ProductFormView - GUI JavaFX untuk Form Produk
 * Event-Driven Programming
 */
public class ProductFormView extends BorderPane {
    private ProductController controller;
    
    // UI Components
    private TextField txtCode;
    private TextField txtName;
    private TextField txtPrice;
    private TextField txtStock;
    private Button btnAdd;
    private Button btnRefresh;
    private ListView<String> listView;
    private Label lblStatus;

    public ProductFormView(ProductController controller) {
        this.controller = controller;
        initComponents();
        setupLayout();
        setupEventHandlers();
        updateListView();
    }

    /**
     * Inisialisasi komponen UI
     */
    private void initComponents() {
        // TextFields
        txtCode = new TextField();
        txtCode.setPromptText("Masukkan kode produk");
        
        txtName = new TextField();
        txtName.setPromptText("Masukkan nama produk");
        
        txtPrice = new TextField();
        txtPrice.setPromptText("Masukkan harga");
        
        txtStock = new TextField();
        txtStock.setPromptText("Masukkan stok");

        // Buttons
        btnAdd = new Button("Tambah Produk");
        btnAdd.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAdd.setPrefWidth(150);
        
        btnRefresh = new Button("Refresh");
        btnRefresh.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnRefresh.setPrefWidth(100);

        // ListView
        listView = new ListView<>();
        listView.setPrefHeight(300);

        // Status Label
        lblStatus = new Label("Ready");
        lblStatus.setTextFill(Color.GRAY);
    }

    /**
     * Setup layout GUI
     */
    private void setupLayout() {
        // Header
        Label title = new Label("Agri-POS - Form Input Produk");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKGREEN);
        
        VBox header = new VBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #E8F5E9;");

        // Form Input
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20));

        formGrid.add(new Label("Kode Produk:"), 0, 0);
        formGrid.add(txtCode, 1, 0);
        
        formGrid.add(new Label("Nama Produk:"), 0, 1);
        formGrid.add(txtName, 1, 1);
        
        formGrid.add(new Label("Harga:"), 0, 2);
        formGrid.add(txtPrice, 1, 2);
        
        formGrid.add(new Label("Stok:"), 0, 3);
        formGrid.add(txtStock, 1, 3);

        HBox buttonBox = new HBox(10, btnAdd, btnRefresh);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox formSection = new VBox(10, formGrid, buttonBox);
        formSection.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-border-radius: 5;");
        formSection.setPadding(new Insets(10));

        // List Section
        Label lblList = new Label("Daftar Produk:");
        lblList.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        VBox listSection = new VBox(10, lblList, listView);
        listSection.setPadding(new Insets(10));

        // Content Area
        VBox content = new VBox(15, formSection, listSection);
        content.setPadding(new Insets(20));

        // Footer
        HBox footer = new HBox(lblStatus);
        footer.setAlignment(Pos.CENTER_LEFT);
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #F5F5F5;");

        // Set to BorderPane
        this.setTop(header);
        this.setCenter(content);
        this.setBottom(footer);
    }

    /**
     * Setup event handlers
     * Event-Driven Programming
     */
    private void setupEventHandlers() {
        // Event handler tombol Tambah
        // Mengikuti Activity Diagram dan Sequence Diagram Bab 6
        btnAdd.setOnAction(event -> {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            String price = txtPrice.getText().trim();
            String stock = txtStock.getText().trim();

            // Validasi input kosong
            if (code.isEmpty() || name.isEmpty() || price.isEmpty() || stock.isEmpty()) {
                showStatus("Error: Semua field harus diisi!", Color.RED);
                return;
            }

            // Panggil controller untuk tambah produk
            // View → Controller → Service → DAO → DB
            String result = controller.addProduct(code, name, price, stock);

            if (result.contains("berhasil")) {
                showStatus(result, Color.GREEN);
                clearForm();
                updateListView();
            } else {
                showStatus(result, Color.RED);
            }
        });

        // Event handler tombol Refresh
        btnRefresh.setOnAction(event -> {
            controller.refreshProducts();
            updateListView();
            showStatus("Data di-refresh", Color.BLUE);
        });
    }

    /**
     * Update ListView dengan data dari controller
     */
    private void updateListView() {
        listView.getItems().clear();
        for (Product p : controller.getProductList()) {
            listView.getItems().add(p.toString());
        }
    }

    /**
     * Clear form input
     */
    private void clearForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
        txtCode.requestFocus();
    }

    /**
     * Tampilkan status message
     */
    private void showStatus(String message, Color color) {
        lblStatus.setText(message);
        lblStatus.setTextFill(color);
    }
}