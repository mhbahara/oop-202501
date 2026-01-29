-- Seed Data untuk Agri-POS
-- Week 15 - Proyek Kelompok

-- Insert default users
-- Password: admin123 dan kasir123 (gunakan plaintext untuk demo, di production harus hash)
INSERT INTO users (username, password, full_name, role) VALUES
('admin', 'admin123', 'Administrator', 'ADMIN'),
('kasir1', 'kasir123', 'Kasir Pertama', 'KASIR'),
('kasir2', 'kasir123', 'Kasir Kedua', 'KASIR');

-- Insert sample products
INSERT INTO products (kode, nama, kategori, harga, stok) VALUES
('P001', 'Beras Premium 5kg', 'Beras', 75000.00, 50),
('P002', 'Pupuk Organik 10kg', 'Pupuk', 45000.00, 100),
('P003', 'Bibit Jagung Hibrida', 'Bibit', 25000.00, 200),
('P004', 'Pestisida Nabati 500ml', 'Pestisida', 35000.00, 75),
('P005', 'Gran Maja', 'Pupuk', 60000.00, 55),
('P006', 'Traktor Mini', 'Alat', 5000000.00, 10),
('P007', 'Benih Padi Unggul 1kg', 'Bibit', 15000.00, 150),
('P008', 'Pupuk NPK 25kg', 'Pupuk', 125000.00, 80),
('P009', 'Cangkul Baja', 'Alat', 85000.00, 30),
('P010', 'Semprotan Hama 5L', 'Alat', 150000.00, 25);

-- Insert sample transactions
INSERT INTO transactions (transaction_code, user_id, subtotal, payment_method, payment_amount, change_amount, status) VALUES
('TRX-20250120-001', 2, 150000.00, 'TUNAI', 200000.00, 50000.00, 'COMPLETED'),
('TRX-20250120-002', 2, 75000.00, 'E-WALLET', 75000.00, 0.00, 'COMPLETED'),
('TRX-20250119-001', 3, 500000.00, 'TUNAI', 500000.00, 0.00, 'COMPLETED');

-- Insert sample transaction details
INSERT INTO transaction_details (transaction_id, product_id, product_kode, product_nama, quantity, price, subtotal) VALUES
(1, 1, 'P001', 'Beras Premium 5kg', 2, 75000.00, 150000.00),
(2, 1, 'P001', 'Beras Premium 5kg', 1, 75000.00, 75000.00),
(3, 6, 'P006', 'Traktor Mini', 1, 5000000.00, 5000000.00);

SELECT 'Database seeded successfully!' as message;