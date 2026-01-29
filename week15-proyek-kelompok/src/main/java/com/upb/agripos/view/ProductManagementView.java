package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductManagementView extends BorderPane {
    private ProductController productController;
    private TableView<Product> productTable;
    private TextField kodeField, namaField, hargaField, stokField;
    private ComboBox<String> kategoriCombo;
    private Button addButton, updateButton, deleteButton, clearButton;
    private ObservableList<Product> productList;
    private NumberFormat currencyFormat;
    
    public ProductManagementView() {
        this.productController = new ProductController();
        this.productList = FXCollections.observableArrayList();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        
        initializeUI();
        loadProducts();
    }
    
    private void initializeUI() {
        setPadding(new Insets(20));
        
        // Left side - Form
        VBox formBox = createFormBox();
        
        // Right side - Table
        VBox tableBox = createTableBox();
        
        // Layout
        HBox mainLayout = new HBox(20, formBox, tableBox);
        HBox.setHgrow(tableBox, Priority.ALWAYS);
        
        setCenter(mainLayout);
    }
    
    private VBox createFormBox() {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setPrefWidth(350);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        
        Label titleLabel = new Label("Kelola Produk");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setStyle("-fx-text-fill: #2e7d32;");
        
        // Fields
        kodeField = new TextField();
        kodeField.setPromptText("Kode Produk");
        kodeField.setPrefHeight(35);
        
        namaField = new TextField();
        namaField.setPromptText("Nama Produk");
        namaField.setPrefHeight(35);
        
        kategoriCombo = new ComboBox<>();
        kategoriCombo.getItems().addAll("Beras", "Pupuk", "Bibit", "Pestisida", "Alat");
        kategoriCombo.setPromptText("Pilih Kategori");
        kategoriCombo.setPrefHeight(35);
        kategoriCombo.setPrefWidth(Double.MAX_VALUE);
        
        hargaField = new TextField();
        hargaField.setPromptText("Harga");
        hargaField.setPrefHeight(35);
        
        stokField = new TextField();
        stokField.setPromptText("Stok");
        stokField.setPrefHeight(35);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        
        addButton = new Button("+ Tambah");
        addButton.setPrefHeight(40);
        addButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        addButton.setOnAction(e -> handleAddProduct());
        
        updateButton = new Button("✎ Ubah");
        updateButton.setPrefHeight(40);
        updateButton.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold;");
        updateButton.setOnAction(e -> handleUpdateProduct());
        updateButton.setDisable(true);
        
        deleteButton = new Button("🗑 Hapus");
        deleteButton.setPrefHeight(40);
        deleteButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> handleDeleteProduct());
        deleteButton.setDisable(true);
        
        clearButton = new Button("Clear");
        clearButton.setPrefHeight(40);
        clearButton.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold;");
        clearButton.setOnAction(e -> clearForm());
        
        buttonBox.getChildren().addAll(addButton, updateButton, deleteButton, clearButton);
        
        box.getChildren().addAll(
            titleLabel,
            new Label("Kode:"), kodeField,
            new Label("Nama:"), namaField,
            new Label("Kategori:"), kategoriCombo,
            new Label("Harga:"), hargaField,
            new Label("Stok:"), stokField,
            new Separator(),
            buttonBox
        );
        
        return box;
    }
    
    private VBox createTableBox() {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        
        Label titleLabel = new Label("Daftar Produk");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setStyle("-fx-text-fill: #2e7d32;");
        
        Button refreshButton = new Button("⟳ Refresh");
        refreshButton.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold;");
        refreshButton.setOnAction(e -> loadProducts());
        
        HBox headerBox = new HBox(20, titleLabel, new Region(), refreshButton);
        HBox.setHgrow(headerBox.getChildren().get(1), Priority.ALWAYS);
        
        // Table
        productTable = new TableView<>();
        productTable.setItems(productList);
        productTable.setPrefHeight(500);
        
        TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));
        kodeCol.setPrefWidth(100);
        
        TableColumn<Product, String> namaCol = new TableColumn<>("Nama Produk");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        namaCol.setPrefWidth(250);
        
        TableColumn<Product, String> kategoriCol = new TableColumn<>("Kategori");
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        kategoriCol.setPrefWidth(120);
        
        TableColumn<Product, Double> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));
        hargaCol.setPrefWidth(150);
        hargaCol.setCellFactory(col -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        TableColumn<Product, Integer> stokCol = new TableColumn<>("Stok");
        stokCol.setCellValueFactory(new PropertyValueFactory<>("stok"));
        stokCol.setPrefWidth(80);
        
        productTable.getColumns().addAll(kodeCol, namaCol, kategoriCol, hargaCol, stokCol);
        
        // Selection listener
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillFormWithProduct(newSelection);
                addButton.setDisable(true);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });
        
        Label totalLabel = new Label("Total: 0 produk");
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        productList.addListener((javafx.collections.ListChangeListener.Change<? extends Product> c) -> {
            totalLabel.setText("Total: " + productList.size() + " produk");
        });
        
        box.getChildren().addAll(headerBox, productTable, totalLabel);
        VBox.setVgrow(productTable, Priority.ALWAYS);
        
        return box;
    }
    
    private void handleAddProduct() {
        try {
            String kode = kodeField.getText().trim();
            String nama = namaField.getText().trim();
            String kategori = kategoriCombo.getValue();
            double harga = Double.parseDouble(hargaField.getText().trim());
            int stok = Integer.parseInt(stokField.getText().trim());
            
            productController.addProduct(kode, nama, kategori, harga, stok);
            
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Produk berhasil ditambahkan!");
            clearForm();
            loadProducts();
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Harga dan Stok harus berupa angka!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    private void handleUpdateProduct() {
        try {
            String kode = kodeField.getText().trim();
            String nama = namaField.getText().trim();
            String kategori = kategoriCombo.getValue();
            double harga = Double.parseDouble(hargaField.getText().trim());
            int stok = Integer.parseInt(stokField.getText().trim());
            
            productController.updateProduct(kode, nama, kategori, harga, stok);
            
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Produk berhasil diubah!");
            clearForm();
            loadProducts();
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Harga dan Stok harus berupa angka!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    private void handleDeleteProduct() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmasi Hapus");
        confirm.setHeaderText("Hapus Produk");
        confirm.setContentText("Yakin ingin menghapus produk ini?");
        
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String kode = kodeField.getText().trim();
                    productController.deleteProduct(kode);
                    
                    showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Produk berhasil dihapus!");
                    clearForm();
                    loadProducts();
                    
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                }
            }
        });
    }
    
    private void clearForm() {
        kodeField.clear();
        namaField.clear();
        kategoriCombo.setValue(null);
        hargaField.clear();
        stokField.clear();
        
        kodeField.setEditable(true);
        addButton.setDisable(false);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        
        productTable.getSelectionModel().clearSelection();
    }
    
    private void fillFormWithProduct(Product product) {
        kodeField.setText(product.getKode());
        namaField.setText(product.getNama());
        kategoriCombo.setValue(product.getKategori());
        hargaField.setText(String.valueOf(product.getHarga()));
        stokField.setText(String.valueOf(product.getStok()));
        
        kodeField.setEditable(false);
    }
    
    private void loadProducts() {
        try {
            productList.clear();
            productList.addAll(productController.getAllProducts());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal memuat data produk: " + e.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
