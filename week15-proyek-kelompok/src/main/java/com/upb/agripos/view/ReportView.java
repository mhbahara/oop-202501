package com.upb.agripos.view;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import com.upb.agripos.controller.TransactionController;
import com.upb.agripos.model.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReportView extends BorderPane {

    private final TransactionController transactionController;
    private TableView<Transaction> transactionTable;
    private DatePicker startDatePicker, endDatePicker;

    // 🔧 FIX: HARUS VBox (bukan Label)
    private VBox totalTransLabel;
    private VBox totalRevenueLabel;
    private VBox cashLabel;
    private VBox ewalletLabel;

    private final ObservableList<Transaction> transactionList;
    private final NumberFormat currencyFormat;

    public ReportView() {
        this.transactionController = new TransactionController();
        this.transactionList = FXCollections.observableArrayList();
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        initializeUI();
        loadTodayReport();
    }

    private void initializeUI() {
        setPadding(new Insets(20));

        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 10;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);
                """);

        // ===== HEADER =====
        Label titleLabel = new Label("Laporan Penjualan");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: #2e7d32;");

        // ===== FILTER =====
        HBox filterBox = new HBox(15);
        filterBox.setAlignment(Pos.CENTER_LEFT);

        startDatePicker = new DatePicker(LocalDate.now());
        endDatePicker = new DatePicker(LocalDate.now());

        Button loadButton = new Button("Tampilkan");
        loadButton.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold;");
        loadButton.setOnAction(e -> loadReport());

        Button todayButton = new Button("Hari Ini");
        todayButton.setStyle("-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-weight: bold;");
        todayButton.setOnAction(e -> loadTodayReport());

        filterBox.getChildren().addAll(
                new Label("Dari:"), startDatePicker,
                new Label("Sampai:"), endDatePicker,
                loadButton, todayButton
        );

        // ===== SUMMARY =====
        GridPane summaryGrid = new GridPane();
        summaryGrid.setHgap(15);
        summaryGrid.setVgap(15);

        totalTransLabel = createSummaryCard("Total Transaksi", "0", "#1976d2");
        totalRevenueLabel = createSummaryCard("Total Pendapatan", "Rp 0", "#2e7d32");
        cashLabel = createSummaryCard("Tunai", "Rp 0", "#ff6f00");
        ewalletLabel = createSummaryCard("E-Wallet", "Rp 0", "#9c27b0");

        summaryGrid.add(totalTransLabel, 0, 0);
        summaryGrid.add(totalRevenueLabel, 1, 0);
        summaryGrid.add(cashLabel, 2, 0);
        summaryGrid.add(ewalletLabel, 3, 0);

        // ===== TABLE =====
        transactionTable = new TableView<>();
        transactionTable.setItems(transactionList);

        TableColumn<Transaction, String> codeCol = new TableColumn<>("Kode Transaksi");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("transactionCode"));
        codeCol.setPrefWidth(200);

        TableColumn<Transaction, String> dateCol = new TableColumn<>("Tanggal");
        dateCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                } else {
                    Transaction trx = getTableRow().getItem();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    setText(trx.getTransactionDate().format(formatter));
                }
            }
        });

        TableColumn<Transaction, Double> subtotalCol = new TableColumn<>("Subtotal");
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        subtotalCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                setText(empty || price == null ? null : currencyFormat.format(price));
            }
        });

        TableColumn<Transaction, String> methodCol = new TableColumn<>("Metode");
        methodCol.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        TableColumn<Transaction, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        transactionTable.getColumns().addAll(
                codeCol, dateCol, subtotalCol, methodCol, statusCol
        );

        VBox.setVgrow(transactionTable, Priority.ALWAYS);
        mainBox.getChildren().addAll(titleLabel, filterBox, summaryGrid, transactionTable);
        setCenter(mainBox);
    }

    private VBox createSummaryCard(String title, String value, String color) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(15));
        card.setPrefWidth(250);
        card.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 8;");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 12));
        titleLabel.setStyle("-fx-text-fill: white;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        valueLabel.setStyle("-fx-text-fill: white;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    private void loadTodayReport() {
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
        loadReport();
    }

    private void loadReport() {
        try {
            Map<String, Object> report =
                    transactionController.getSalesReportByDateRange(
                            startDatePicker.getValue(),
                            endDatePicker.getValue()
                    );

            updateSummary(report);
            transactionList.setAll((java.util.List<Transaction>) report.get("transactions"));

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal memuat laporan: " + e.getMessage());
        }
    }

    private void updateSummary(Map<String, Object> report) {
        ((Label) totalTransLabel.getChildren().get(1))
                .setText(String.valueOf(report.get("totalTransactions")));

        ((Label) totalRevenueLabel.getChildren().get(1))
                .setText(currencyFormat.format((double) report.get("totalRevenue")));

        ((Label) cashLabel.getChildren().get(1))
                .setText(currencyFormat.format((double) report.get("cashRevenue")));

        ((Label) ewalletLabel.getChildren().get(1))
                .setText(currencyFormat.format((double) report.get("ewalletRevenue")));
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
