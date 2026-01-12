package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;
import java.util.Optional;

/**
 * TableView GUI untuk menampilkan dan mengelola produk
 * Week 13 - GUI Lanjutan JavaFX dengan Lambda Expression
 */
public class ProductTableView {
    private ProductController controller;
    private TableView<Product> tableView;
    private ObservableList<Product> productData;
    private Label infoLabel; // Tambahkan sebagai instance variable

    public ProductTableView(ProductController controller) {
        this.controller = controller;
        this.productData = FXCollections.observableArrayList();
    }

    /**
     * Membuat scene utama dengan TableView
     */
    public Scene createScene() {
        // Root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Header
        VBox header = createHeader();
        root.setTop(header);

        // TableView
        tableView = createTableView();
        root.setCenter(tableView);

        // Button panel
        HBox buttonPanel = createButtonPanel();
        root.setBottom(buttonPanel);

        // Load data awal (UC-02 Lihat Daftar Produk)
        loadData();

        return new Scene(root, 800, 600);
    }

    /**
     * Membuat header aplikasi
     */
    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setPadding(new Insets(0, 0, 15, 0));

        Label title = new Label("AGRI-POS - Manajemen Produk");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label subtitle = new Label("Week 13 - TableView dengan Lambda Expression");
        subtitle.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        header.getChildren().addAll(title, subtitle);
        return header;
    }

    /**
     * Membuat TableView dengan kolom-kolom sesuai spesifikasi
     */
    @SuppressWarnings("unchecked")
    private TableView<Product> createTableView() {
        TableView<Product> table = new TableView<>();
        table.setItems(productData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Kolom Kode
        TableColumn<Product, String> codeCol = new TableColumn<>("Kode");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);

        // Kolom Nama
        TableColumn<Product, String> nameCol = new TableColumn<>("Nama Produk");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(300);

        // Kolom Harga
        TableColumn<Product, Double> priceCol = new TableColumn<>("Harga");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setPrefWidth(150);
        
        // Format harga dengan currency
        priceCol.setCellFactory(col -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(String.format("Rp %,.2f", price));
                }
            }
        });

        // Kolom Stok
        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stok");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockCol.setPrefWidth(100);

        table.getColumns().addAll(codeCol, nameCol, priceCol, stockCol);

        return table;
    }

    /**
     * Membuat panel tombol dengan Lambda Expression
     */
    private HBox createButtonPanel() {
        HBox buttonPanel = new HBox(15);
        buttonPanel.setPadding(new Insets(15, 0, 0, 0));
        buttonPanel.setAlignment(Pos.CENTER_LEFT);

        // Tombol Tambah Produk (Lambda Expression)
        Button btnAdd = new Button("➕ Tambah Produk");
        btnAdd.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; " +
                       "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnAdd.setOnAction(e -> showAddProductDialog());

        // Tombol Hapus Produk (Lambda Expression)
        // Sesuai UC-03 Hapus Produk dan SD-02
        Button btnDelete = new Button("🗑️ Hapus Produk");
        btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                          "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnDelete.setOnAction(e -> {
            Product selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Konfirmasi penghapusan
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Konfirmasi Hapus");
                confirm.setHeaderText("Hapus Produk");
                confirm.setContentText("Apakah Anda yakin ingin menghapus produk: " + 
                                      selected.getName() + "?");
                
                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Panggil controller untuk hapus (sesuai sequence diagram)
                    if (controller.delete(selected.getCode())) {
                        loadData(); // Reload data dari database
                    }
                }
            } else {
                showWarning("Tidak Ada Pilihan", "Silakan pilih produk yang ingin dihapus");
            }
        });

        // Tombol Refresh (Lambda Expression)
        Button btnRefresh = new Button("🔄 Refresh");
        btnRefresh.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; " +
                           "-fx-font-size: 14px; -fx-padding: 10 20;");
        btnRefresh.setOnAction(e -> loadData());

        // Label info
        infoLabel = new Label("Total: 0 produk");
        infoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonPanel.getChildren().addAll(btnAdd, btnDelete, btnRefresh, spacer, infoLabel);

        return buttonPanel;
    }

    /**
     * Load data dari database via Controller
     * Sesuai UC-02 Lihat Daftar Produk
     * Alur: View → Controller → Service → DAO → DB
     */
    private void loadData() {
        List<Product> products = controller.load();
        if (products != null) {
            productData.clear();
            productData.addAll(products);
            // Update info label
            infoLabel.setText("Total: " + productData.size() + " produk");
        }
    }

    /**
     * Dialog untuk menambah produk baru
     */
    private void showAddProductDialog() {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle("Tambah Produk Baru");
        dialog.setHeaderText("Masukkan data produk");

        // Set tombol
        ButtonType addButtonType = new ButtonType("Tambah", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Form input
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField codeField = new TextField();
        codeField.setPromptText("Kode produk");
        
        TextField nameField = new TextField();
        nameField.setPromptText("Nama produk");
        
        TextField priceField = new TextField();
        priceField.setPromptText("Harga");
        
        TextField stockField = new TextField();
        stockField.setPromptText("Stok");

        grid.add(new Label("Kode:"), 0, 0);
        grid.add(codeField, 1, 0);
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Harga:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Stok:"), 0, 3);
        grid.add(stockField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Convert result dengan Lambda Expression
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                // Panggil controller untuk tambah produk
                if (controller.addProduct(
                    codeField.getText(),
                    nameField.getText(),
                    priceField.getText(),
                    stockField.getText()
                )) {
                    loadData(); // Reload data
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * Tampilkan pesan warning
     */
    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Mendapatkan stage dari scene
     */
    public void show(Stage stage) {
        stage.setScene(createScene());
        stage.setTitle("Agri-POS - Manajemen Produk");
        stage.show();
    }
}