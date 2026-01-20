package com.upb.agripos.model;

public class TransactionDetail {
    private Integer id;
    private Integer transactionId;
    private Integer productId;
    private String productKode;
    private String productNama;
    private Integer quantity;
    private Double price;
    private Double subtotal;

    public TransactionDetail() {}

    public TransactionDetail(Product product, Integer quantity) {
        this.productId = product.getId();
        this.productKode = product.getKode();
        this.productNama = product.getNama();
        this.quantity = quantity;
        this.price = product.getHarga();
        this.subtotal = price * quantity;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { 
        this.transactionId = transactionId; 
    }
    
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    
    public String getProductKode() { return productKode; }
    public void setProductKode(String productKode) { this.productKode = productKode; }
    
    public String getProductNama() { return productNama; }
    public void setProductNama(String productNama) { this.productNama = productNama; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}