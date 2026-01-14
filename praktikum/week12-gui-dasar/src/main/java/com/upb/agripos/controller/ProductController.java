package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.scene.control.Alert;

public class ProductController {

    private final ProductService productService;
    private final ProductFormView view;

    public ProductController(ProductService productService, ProductFormView view) {
        this.productService = productService;
        this.view = view;

        loadProducts();
        initEvent();
    }

    private void loadProducts() {
        view.getListView().getItems().clear();
        for (Product p : productService.getAll()) {
            view.getListView().getItems().add(
                    p.getCode() + " - " + p.getName() +
                    " (Stok: " + p.getStock() + ")"
            );
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

                productService.insert(p);
                view.clearForm();
                loadProducts();

            } catch (Exception ex) {
                showAlert("Input tidak valid!");
            }
        });
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
