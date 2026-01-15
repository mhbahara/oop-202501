package com.upb.agripos.view;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.model.CartItem;
import com.upb.agripos.model.Product;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class PosView {

    private PosController controller;
    private VBox root;

    private TableView<Product> productsTable;
    private TableView<CartItem> cartTable;
    private Label totalLabel;

    private ObservableList<Product> productsList;
    private ObservableList<CartItem> cartList;

    public PosView(PosController controller, Stage stage) {
        this.controller = controller;
        this.productsList = FXCollections.observableArrayList(controller.getProductService().getAllProducts());
        this.cartList = FXCollections.observableArrayList();

        root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #f4f4f9; -fx-font-family: 'Arial';");

        Label title = new Label("AgriPOS - Sistem Kasir");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox tablesBox = new HBox(20);

        // PRODUCTS TABLE
        productsTable = new TableView<>(productsList);
        productsTable.setPrefWidth(450);

        TableColumn<Product, String> productCodeColumn = new TableColumn<>("Code");
        productCodeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCode()));

        TableColumn<Product, String> productNameColumn = new TableColumn<>("Nama");
        productNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));

        TableColumn<Product, String> productPriceColumn = new TableColumn<>("Harga");
        productPriceColumn.setCellValueFactory(c -> new SimpleStringProperty(String.format("%.2f", c.getValue().getPrice())));

        TableColumn<Product, String> productStockColumn = new TableColumn<>("Stok");
        productStockColumn.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getStock())));

        productsTable.getColumns().addAll(productCodeColumn, productNameColumn, productPriceColumn, productStockColumn);

        Button addToCartBtn = new Button("Tambah ke Keranjang");
        addToCartBtn.setOnAction(e -> addSelectedProductToCart());

        VBox productsBox = new VBox(10, new Label("Daftar Produk"), productsTable, addToCartBtn);

        // CART TABLE
        cartTable = new TableView<>(cartList);
        cartTable.setPrefWidth(450);

        TableColumn<CartItem, String> cartNameColumn = new TableColumn<>("Nama");
        cartNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProduct().getName()));

        TableColumn<CartItem, Integer> cartQtyColumn = new TableColumn<>("Qty");
        cartQtyColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getQuantity()).asObject());
        cartQtyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cartQtyColumn.setOnEditCommit(e -> {
            CartItem item = e.getRowValue();
            int newQty = e.getNewValue();
            if (newQty <= 0) cartList.remove(item);
            else item.setQuantity(newQty);
            updateCart();
        });

        TableColumn<CartItem, Double> cartSubtotalColumn = new TableColumn<>("Subtotal");
        cartSubtotalColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSubtotal()).asObject());

        cartTable.getColumns().addAll(cartNameColumn, cartQtyColumn, cartSubtotalColumn);
        cartTable.setEditable(true);

        totalLabel = new Label("Total: 0.00");
        totalLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setOnAction(e -> showReceipt());

        VBox cartBox = new VBox(10, new Label("Keranjang Belanja"), cartTable, totalLabel, checkoutBtn);

        tablesBox.getChildren().addAll(productsBox, cartBox);
        root.getChildren().addAll(title, tablesBox);
    }

    private void addSelectedProductToCart() {
        Product selected = productsTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        // Ganti getId() → getCode() dan getProducts() → getProduct()
        CartItem existing = cartList.stream()
                .filter(ci -> ci.getProduct().getCode().equals(selected.getCode()))
                .findFirst().orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            cartList.add(new CartItem(selected, 1));
        }
        updateCart();
    }

    private void updateCart() {
        cartTable.refresh();
        double total = cartList.stream().mapToDouble(CartItem::getSubtotal).sum();
        totalLabel.setText(String.format("Total: %.2f", total));
    }

    private void showReceipt() {
        StringBuilder sb = new StringBuilder("=== STRUK PEMBAYARAN ===\n\n");
        cartList.forEach(item -> sb.append(String.format("%s x%d = %.2f\n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getSubtotal())));
        sb.append(String.format("\nTOTAL: %.2f", cartList.stream().mapToDouble(CartItem::getSubtotal).sum()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Struk Pembayaran");
        alert.setHeaderText(null);
        alert.setContentText(sb.toString());
        alert.showAndWait();

        // Clear cart setelah checkout
        cartList.clear();
        updateCart();
    }

    public VBox getRoot() {
        return root;
    }
}
