package com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductFormView extends VBox {

    private final TextField txtCode = new TextField();
    private final TextField txtName = new TextField();
    private final TextField txtPrice = new TextField();
    private final TextField txtStock = new TextField();
    private final Button btnAdd = new Button("Tambah Produk");
    private final ListView<String> listView = new ListView<>();

    public ProductFormView() {
        setSpacing(15);
        setPadding(new Insets(20));

        // ===== Judul =====
        Label title = new Label("Form Input Produk Agri-POS");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // ===== Form Grid =====
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);

        form.add(new Label("Kode Produk:"), 0, 0);
        form.add(txtCode, 1, 0);

        form.add(new Label("Nama Produk:"), 0, 1);
        form.add(txtName, 1, 1);

        form.add(new Label("Harga:"), 0, 2);
        form.add(txtPrice, 1, 2);

        form.add(new Label("Stok:"), 0, 3);
        form.add(txtStock, 1, 3);

        // ===== Daftar Produk =====
        Label listLabel = new Label("Daftar Produk:");
        listLabel.setStyle("-fx-font-weight: bold;");

        getChildren().addAll(
                title,
                form,
                btnAdd,
                listLabel,
                listView
        );

        listView.setPrefHeight(200);
    }

    // ===== GETTER (untuk Controller) =====
    public String getCode() { return txtCode.getText(); }
    public String getName() { return txtName.getText(); }
    public double getPrice() { return Double.parseDouble(txtPrice.getText()); }
    public int getStock() { return Integer.parseInt(txtStock.getText()); }

    public Button getBtnAdd() { return btnAdd; }
    public ListView<String> getListView() { return listView; }

    public void clearForm() {
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtStock.clear();
    }
}
