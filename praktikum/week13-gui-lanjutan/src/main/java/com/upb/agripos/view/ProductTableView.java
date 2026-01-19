package com.upb.agripos.view;

import com.upb.agripos.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ProductTableView extends VBox {

    private final TextField txtCode = new TextField();
    private final TextField txtName = new TextField();
    private final TextField txtPrice = new TextField();
    private final TextField txtStock = new TextField();

    private final Button btnAdd = new Button("Tambah Produk");
    private final Button btnDelete = new Button("Hapus Produk");

    private final TableView<Product> table = new TableView<>();

    public ProductTableView() {
        setPadding(new Insets(20));
        setSpacing(15);

        Label title = new Label("Manajemen Produk Agri-POS");
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Kode"), 0, 0);
        form.add(txtCode, 1, 0);
        form.add(new Label("Nama"), 0, 1);
        form.add(txtName, 1, 1);
        form.add(new Label("Harga"), 2, 0);
        form.add(txtPrice, 3, 0);
        form.add(new Label("Stok"), 2, 1);
        form.add(txtStock, 3, 1);

        HBox buttonBox = new HBox(10, btnAdd, btnDelete);

        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCode()));

        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));

        TableColumn<Product, Number> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPrice()));

        TableColumn<Product, Number> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getStock()));

        table.getColumns().addAll(colCode, colName, colPrice, colStock);
        table.setPrefHeight(300);

        getChildren().addAll(title, form, buttonBox, table);
    }

    // ===== Getter untuk Controller =====
    public String getCode() { return txtCode.getText(); }
    public String getName() { return txtName.getText(); }
    public double getPrice() { return Double.parseDouble(txtPrice.getText()); }
    public int getStock() { return Integer.parseInt(txtStock.getText()); }

    public Button getBtnAdd() { return btnAdd; }
    public Button getBtnDelete() { return btnDelete; }
    public TableView<Product> getTable() { return table; }

    public void clearForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }
}
