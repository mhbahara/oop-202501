package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.model.Product;
import com.upb.agripos.model.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;

/**
 * JavaFX View - GUI utama aplikasi
 * Bab 12-13: TableView, Event Handling
 */
public class PosView {
    private final PosController controller;
    private final Stage primaryStage;
    
    // Components
    private TableView<Product> productTable;
    private ObservableList<Product> productData;
    private TextField txtCode, txtName, txtPrice, txtStock, txtQuantity;
    private Label lblCartTotal, lblCartItems;
    private ListView<String> cartListView;

    public PosView(PosController controller, Stage primaryStage) {
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.productData = FXCollections.observableArrayList();
    }

    public void show() {
        primaryStage.setTitle("Agri-POS - Kasir Pertanian");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header
        root.setTop(createHeader());
        
        // Main content
        HBox mainContent = new HBox(15);
        
        // Left: Product Management + Table
        VBox leftPanel = new VBox(15);
        leftPanel.getChildren().addAll(
            createProductManagementSection(),
            createProductTableSection()
        );
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        
        // Right: Cart Section
        VBox rightPanel = createCartSection();
        
        mainContent.getChildren().addAll(leftPanel, rightPanel);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadProductData();
    }

    /**
     * Membuat header aplikasi (konsisten dengan Week 13)
     */
    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setPadding(new Insets(0, 0, 15, 0));

        Label title = new Label("AGRI-POS - Kasir Pertanian");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label subtitle = new Label("Bab 14 - Integrasi Individu (OOP + Database + GUI)");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        header.getChildren().addAll(title, subtitle);
        return header;
    }

    private VBox createProductManagementSection() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label title = new Label("📦 Kelola Produk");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        txtCode = new TextField();
        txtCode.setPromptText("Kode produk");
        txtName = new TextField();
        txtName.setPromptText("Nama produk");
        txtPrice = new TextField();
        txtPrice.setPromptText("Harga");
        txtStock = new TextField();
        txtStock.setPromptText("Stok");

        grid.add(new Label("Kode:"), 0, 0);
        grid.add(txtCode, 1, 0);
        grid.add(new Label("Nama:"), 2, 0);
        grid.add(txtName, 3, 0);
        grid.add(new Label("Harga:"), 0, 1);
        grid.add(txtPrice, 1, 1);
        grid.add(new Label("Stok:"), 2, 1);
        grid.add(txtStock, 3, 1);

        Button btnAdd = new Button("➕ Tambah Produk");
        btnAdd.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; " +
                       "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnAdd.setOnAction(e -> handleAddProduct());
        
        Button btnDelete = new Button("🗑️ Hapus Produk");
        btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                          "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnDelete.setOnAction(e -> handleDeleteProduct());

        Button btnRefresh = new Button("🔄 Refresh");
        btnRefresh.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; " +
                           "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnRefresh.setOnAction(e -> loadProductData());

        HBox buttonBox = new HBox(10, btnAdd, btnDelete, btnRefresh);
        
        vbox.getChildren().addAll(title, grid, buttonBox);
        return vbox;
    }

    private VBox createProductTableSection() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-radius: 5;");
        
        Label title = new Label("📋 Daftar Produk");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        productTable = new TableView<>();
        productTable.setItems(productData);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCode.setPrefWidth(100);

        TableColumn<Product, String> colName = new TableColumn<>("Nama Produk");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(250);

        TableColumn<Product, BigDecimal> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setPrefWidth(150);
        
        // Format harga dengan currency (konsisten dengan Week 13)
        colPrice.setCellFactory(col -> new TableCell<Product, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("Rp %,.2f", price.doubleValue()));
                }
            }
        });

        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colStock.setPrefWidth(100);

        productTable.getColumns().addAll(colCode, colName, colPrice, colStock);

        // Info label
        Label infoLabel = new Label();
        infoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        productData.addListener((javafx.collections.ListChangeListener<Product>) c -> {
            infoLabel.setText("Total: " + productData.size() + " produk");
        });

        vbox.getChildren().addAll(title, productTable, infoLabel);
        VBox.setVgrow(productTable, Priority.ALWAYS);
        return vbox;
    }

    private VBox createCartSection() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setPrefWidth(350);
        vbox.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-radius: 5;");

        Label title = new Label("🛒 Keranjang Belanja");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Add to cart controls
        GridPane addGrid = new GridPane();
        addGrid.setHgap(10);
        addGrid.setVgap(10);
        
        txtQuantity = new TextField("1");
        txtQuantity.setPrefWidth(80);
        txtQuantity.setPromptText("Qty");
        
        Button btnAddToCart = new Button("Tambah ke Keranjang");
        btnAddToCart.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; " +
                             "-fx-font-size: 12px; -fx-padding: 8 15;");
        btnAddToCart.setOnAction(e -> handleAddToCart());
        
        addGrid.add(new Label("Quantity:"), 0, 0);
        addGrid.add(txtQuantity, 1, 0);
        addGrid.add(btnAddToCart, 0, 1, 2, 1);

        // Cart display
        cartListView = new ListView<>();
        cartListView.setPrefHeight(300);
        cartListView.setStyle("-fx-border-color: #ddd;");

        // Cart summary
        lblCartItems = new Label("Total Item: 0");
        lblCartItems.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");
        
        lblCartTotal = new Label("Total: Rp 0");
        lblCartTotal.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        // Buttons
        Button btnCheckout = new Button("✅ Checkout");
        btnCheckout.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; " +
                            "-fx-font-size: 14px; -fx-padding: 10 20; -fx-font-weight: bold;");
        btnCheckout.setOnAction(e -> handleCheckout());
        btnCheckout.setPrefWidth(160);
        
        Button btnClearCart = new Button("🗑️ Kosongkan");
        btnClearCart.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                             "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnClearCart.setOnAction(e -> handleClearCart());
        btnClearCart.setPrefWidth(160);

        HBox buttonBox = new HBox(10, btnCheckout, btnClearCart);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(
            title, 
            new Separator(),
            addGrid, 
            new Separator(), 
            new Label("Item dalam Keranjang:"),
            cartListView, 
            new Separator(),
            lblCartItems, 
            lblCartTotal, 
            buttonBox
        );
        
        VBox.setVgrow(cartListView, Priority.ALWAYS);
        return vbox;
    }

    // Event Handlers
    
    private void handleAddProduct() {
        try {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            double price = Double.parseDouble(txtPrice.getText());
            int stock = Integer.parseInt(txtStock.getText());

            controller.addProduct(code, name, price, stock);
            
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Produk berhasil ditambahkan!");
            clearProductForm();
            loadProductData();
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Format harga/stok tidak valid!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleDeleteProduct() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Tidak Ada Pilihan", 
                     "Silakan pilih produk yang ingin dihapus");
            return;
        }

        // Konfirmasi penghapusan (konsisten dengan Week 13)
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmasi Hapus");
        confirm.setHeaderText("Hapus Produk");
        confirm.setContentText("Apakah Anda yakin ingin menghapus produk: " + 
                              selected.getName() + "?");
        
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    controller.deleteProduct(selected.getCode());
                    showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Produk berhasil dihapus!");
                    loadProductData();
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                }
            }
        });
    }

    private void handleAddToCart() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih produk terlebih dahulu!");
            return;
        }

        try {
            int quantity = Integer.parseInt(txtQuantity.getText());
            controller.addToCart(selected, quantity);
            
            updateCartDisplay();
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", 
                "Produk ditambahkan ke keranjang!");
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Jumlah tidak valid!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void handleClearCart() {
        controller.clearCart();
        updateCartDisplay();
        showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Keranjang dikosongkan!");
    }

    private void handleCheckout() {
        if (controller.getCartService().getCart().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Keranjang masih kosong!");
            return;
        }

        // Tampilkan konfirmasi checkout
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Konfirmasi Checkout");
        confirmAlert.setHeaderText("Total Pembayaran: " + 
            String.format("Rp %,.0f", controller.getCartTotal().doubleValue()));
        confirmAlert.setContentText("Apakah Anda yakin ingin melanjutkan checkout?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Proses checkout
                performCheckout();
            }
        });
    }

    private void performCheckout() {
        try {
            // Kurangi stok untuk setiap item dalam keranjang
            for (CartItem item : controller.getCartService().getCart().getItems()) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                
                // Update stok produk
                int newStock = product.getStock() - quantity;
                controller.updateProductStock(product.getCode(), newStock);
            }
            
            // Tampilkan ringkasan transaksi
            StringBuilder summary = new StringBuilder();
            summary.append("=== TRANSAKSI BERHASIL ===\n\n");
            
            for (CartItem item : controller.getCartService().getCart().getItems()) {
                summary.append(String.format("%s\n  %d x Rp %,.0f = Rp %,.0f\n\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice().doubleValue(),
                    item.getSubtotal().doubleValue()));
            }
            
            summary.append("------------------------\n");
            summary.append(String.format("TOTAL: Rp %,.0f\n", 
                controller.getCartTotal().doubleValue()));
            summary.append("\nStok produk telah diperbarui.");
            summary.append("\nTerima kasih!");

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Checkout Berhasil");
            successAlert.setHeaderText("Transaksi telah selesai");
            successAlert.setContentText(summary.toString());
            successAlert.showAndWait();

            // Clear cart setelah checkout
            controller.clearCart();
            updateCartDisplay();
            
            // Refresh product table untuk menampilkan stok yang sudah diperbarui
            loadProductData();
            
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Checkout gagal: " + e.getMessage());
        }
    }

    // Helper Methods
    
    private void loadProductData() {
        try {
            productData.clear();
            productData.addAll(controller.loadProducts());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal memuat data: " + e.getMessage());
        }
    }

    private void updateCartDisplay() {
        ObservableList<String> items = FXCollections.observableArrayList();
        
        for (CartItem item : controller.getCartService().getCart().getItems()) {
            items.add(item.toString());
        }
        
        cartListView.setItems(items);
        lblCartItems.setText("Total Item: " + controller.getCartItemCount());
        lblCartTotal.setText(String.format("Total: Rp %,.0f", 
            controller.getCartTotal().doubleValue()));
    }

    private void clearProductForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}