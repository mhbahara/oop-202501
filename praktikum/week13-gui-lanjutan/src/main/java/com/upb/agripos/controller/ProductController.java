package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;
import javafx.scene.control.Alert;

public class ProductController {

    private final ProductService service;
    private final ProductTableView view;

    public ProductController(ProductService service, ProductTableView view) {
        this.service = service;
        this.view = view;

        loadData();
        initEvent();
    }

    private void loadData() {
        try {
            view.getTable().getItems().setAll(service.findAll());
        } catch (Exception e) {
            showAlert("Gagal memuat data!");
        }
    }

    private void initEvent() {

        view.getBtnAdd().setOnAction(e -> {
            try {
                Product p = new Product(
                        view.getCode(),
                        view.getName(),
                        view.getPrice(),
                        view.getStock()
                );
                service.insert(p);
                view.clearForm();
                loadData();
            } catch (Exception ex) {
                showAlert("Input tidak valid!");
            }
        });

        view.getBtnDelete().setOnAction(e -> {
            Product selected = view.getTable().getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    service.delete(selected.getCode());
                    loadData();
                } catch (Exception ex) {
                    showAlert("Gagal menghapus produk!");
                }
            }
        });
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
