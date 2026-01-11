-- Setup Database PostgreSQL untuk Agri-POS
-- Jalankan script ini di PostgreSQL sebelum menjalankan aplikasi

-- Buat database (jalankan sebagai user postgres)
CREATE DATABASE agripos_db;

-- Connect ke database agripos_db
\c agripos_db;

-- Buat tabel products
CREATE TABLE products (
    code VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(12, 2) NOT NULL CHECK (price > 0),
    stock INTEGER NOT NULL CHECK (stock >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data (opsional)
INSERT INTO products (code, name, price, stock) VALUES
('P001', 'Beras Premium 5kg', 75000, 50),
('P002', 'Pupuk Organik 10kg', 45000, 100),
('P003', 'Bibit Jagung Hibrida', 25000, 200);

-- Tampilkan data
SELECT * FROM products;

-- Buat index untuk performa (opsional)
CREATE INDEX idx_product_name ON products(name);

-- Grant privileges (sesuaikan dengan user Anda)
-- GRANT ALL PRIVILEGES ON TABLE products TO your_username;