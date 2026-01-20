package com.upb.agripos.view;

import com.upb.agripos.controller.CartController;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.controller.TransactionController;
import com.upb.agripos.model.*;
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

public class CashierView extends BorderPane {
    private ProductController productController;
    private CartController cartController;
    private TransactionController transactionController;
    private TableView<Product> productTable;
    private TableView<CartItem> cartTable;
    private ComboBox<String> paymentMethodCombo;
    private TextField paymentField, quantityField;
    private Label totalLabel, itemCountLabel;
    private ObservableList<Product> productList;
    private ObservableList<CartItem> cartList;
    private NumberFormat currencyFormat;
    
    public CashierView() {
        this.productController = new ProductController();
        this.cartController = new CartController();
        this.transactionController = new TransactionController();
        this.productList = FXCollections.observableArrayList();
        this.cartList = FXCollections.observableArrayList();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        
        initializeUI();
        loadProducts();
    }
    
    private void initializeUI() {
        setPadding(new Insets(20));
        
        // Left - Products
        VBox productBox = createProductBox();
        
        // Right - Cart & Checkout
        VBox cartBox = createCartBox();
        
        HBox mainLayout = new HBox(20, productBox, cartBox);
        HBox.setHgrow(productBox, Priority.ALWAYS);
        HBox.setHgrow(cartBox, Priority.ALWAYS);
        
        setCenter(mainLayout);
    }
    
    private VBox createProductBox() {
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
        
        productTable = new TableView<>();
        productTable.setItems(productList);
        
        TableColumn<Product, String> kodeCol = new TableColumn<>("Kode");
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));
        
        TableColumn<Product, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        namaCol.setPrefWidth(200);
        
        TableColumn<Product, Double> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));
        hargaCol.setCellFactory(col -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty || price == null ? null : currencyFormat.format(price));
            }
        });
        
        TableColumn<Product, Integer> stokCol = new TableColumn<>("Stok");
        stokCol.setCellValueFactory(new PropertyValueFactory<>("stok"));
        
        productTable.getColumns().addAll(kodeCol, namaCol, hargaCol, stokCol);
        
        // Add to cart controls
        HBox addBox = new HBox(10);
        addBox.setPadding(new Insets(10, 0, 0, 0));
        
        quantityField = new TextField("1");
        quantityField.setPromptText("Qty");
        quantityField.setPrefWidth(60);
        
        Button addToCartButton = new Button("Tambah ke Keranjang");
        addToCartButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        addToCartButton.setOnAction(e -> handleAddToCart());
        
        addBox.getChildren().addAll(new Label("Quantity:"), quantityField, addToCartButton);
        
        box.getChildren().addAll(headerBox, productTable, addBox);
        VBox.setVgrow(productTable, Priority.ALWAYS);
        
        return box;
    }
    
    private VBox createCartBox() {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setPrefWidth(500);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 10; " +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);");
        
        Label titleLabel = new Label("Keranjang Belanja");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setStyle("-fx-text-fill: #2e7d32;");
        
        Button clearCartButton = new Button("🗑 Kosongkan");
        clearCartButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold;");
        clearCartButton.setOnAction(e -> handleClearCart());
        
        HBox headerBox = new HBox(20, titleLabel, new Region(), clearCartButton);
        HBox.setHgrow(headerBox.getChildren().get(1), Priority.ALWAYS);
        
        cartTable = new TableView<>();
        cartTable.setItems(cartList);
        cartTable.setPrefHeight(250);
        
        TableColumn<CartItem, String> namaCol = new TableColumn<>("Produk");
        namaCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProduct().getNama()));
        namaCol.setPrefWidth(180);
        
        TableColumn<CartItem, Integer> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        qtyCol.setPrefWidth(60);
        
        TableColumn<CartItem, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getProduct().getHarga()).asObject());
        priceCol.setCellFactory(col -> new TableCell<CartItem, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty || price == null ? null : currencyFormat.format(price));
            }
        });
        
        TableColumn<CartItem, Double> subtotalCol = new TableColumn<>("Subtotal");
        subtotalCol.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getSubtotal()).asObject());
        subtotalCol.setCellFactory(col -> new TableCell<CartItem, Double>() {
            @Override
            protected void updateItem(Double subtotal, boolean empty) {
                super.updateItem(subtotal, empty);
                setText(empty || subtotal == null ? null : currencyFormat.format(subtotal));
            }
        });
        
        TableColumn<CartItem, Void> actionCol = new TableColumn<>("Aksi");
        actionCol.setCellFactory(col -> new TableCell<CartItem, Void>() {
            private final Button deleteBtn = new Button("Hapus");
            {
                deleteBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
                deleteBtn.setOnAction(e -> {
                    CartItem item = getTableView().getItems().get(getIndex());
                    handleRemoveFromCart(item);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
        
        cartTable.getColumns().addAll(namaCol, qtyCol, priceCol, subtotalCol, actionCol);
        
        // Summary
        VBox summaryBox = new VBox(10);
        summaryBox.setPadding(new Insets(15));
        summaryBox.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 5;");
        
        itemCountLabel = new Label("Total Item: 0");
        itemCountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        totalLabel = new Label("Total: Rp 0");
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        totalLabel.setStyle("-fx-text-fill: #2e7d32;");
        
        summaryBox.getChildren().addAll(itemCountLabel, totalLabel);
        
        // Payment
        VBox paymentBox = new VBox(10);
        paymentBox.setPadding(new Insets(10, 0, 0, 0));
        
        paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll("TUNAI", "E-WALLET");
        paymentMethodCombo.setValue("TUNAI");
        paymentMethodCombo.setPrefWidth(Double.MAX_VALUE);
        
        paymentField = new TextField();
        paymentField.setPromptText("Jumlah Bayar");
        paymentField.setPrefHeight(35);
        
        Button checkoutButton = new Button("✓ Checkout");
        checkoutButton.setPrefWidth(Double.MAX_VALUE);
        checkoutButton.setPrefHeight(50);
        checkoutButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; " +
                              "-fx-font-size: 16px; -fx-font-weight: bold;");
        checkoutButton.setOnAction(e -> handleCheckout());
        
        paymentBox.getChildren().addAll(
            new Label("Metode Pembayaran:"),
            paymentMethodCombo,
            new Label("Jumlah Bayar:"),
            paymentField,
            checkoutButton
        );
        
        box.getChildren().addAll(headerBox, cartTable, summaryBox, paymentBox);
        
        return box;
    }
    
    private void handleAddToCart() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih produk terlebih dahulu!");
            return;
        }
        
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            cartController.addToCart(selectedProduct.getKode(), quantity);
            
            refreshCart();
            quantityField.setText("1");
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Quantity harus berupa angka!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    private void handleRemoveFromCart(CartItem item) {
        cartController.removeFromCart(item.getProduct().getKode());
        refreshCart();
    }
    
    private void handleClearCart() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Konfirmasi");
        confirm.setContentText("Kosongkan keranjang?");
        
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cartController.clearCart();
                refreshCart();
            }
        });
    }
    
    private void handleCheckout() {
        if (cartController.isCartEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Keranjang kosong!");
            return;
        }
        
        try {
            String paymentMethod = paymentMethodCombo.getValue();
            double paymentAmount = Double.parseDouble(paymentField.getText());
            
            Transaction transaction = transactionController.checkout(
                cartController.getCartItems(),
                paymentMethod,
                paymentAmount
            );
            
            Receipt receipt = transactionController.generateReceipt(transaction);
            showReceiptDialog(receipt);
            
            cartController.clearCart();
            refreshCart();
            loadProducts();
            paymentField.clear();
            
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Jumlah bayar harus berupa angka!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    private void showReceiptDialog(Receipt receipt) {
        String receiptText = transactionController.getReceiptText(receipt);
        
        TextArea textArea = new TextArea(receiptText);
        textArea.setEditable(false);
        textArea.setFont(Font.font("Monospaced", 12));
        textArea.setPrefHeight(500);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Struk Pembayaran");
        alert.setHeaderText("Transaksi Berhasil!");
        alert.getDialogPane().setContent(textArea);
        alert.setResizable(true);
        alert.showAndWait();
    }
    
    private void refreshCart() {
        cartList.clear();
        cartList.addAll(cartController.getCartItems());
        
        itemCountLabel.setText("Total Item: " + cartController.getTotalItems());
        totalLabel.setText("Total: " + currencyFormat.format(cartController.getTotalAmount()));
    }
    
    private void loadProducts() {
        try {
            productList.clear();
            productList.addAll(productController.getAllProducts());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal memuat produk: " + e.getMessage());
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