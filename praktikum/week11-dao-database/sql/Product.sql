-- =============================================
-- Database Setup Script
-- Project: AgriPOS Week 11 - DAO Database
-- Author: Rafi Kurniawan (240202878)
-- Date: January 11, 2026
-- =============================================

-- =============================================
-- 1. Database Creation (Optional - run in postgres database)
-- =============================================
-- Uncomment below if you want to create database via script
-- DROP DATABASE IF EXISTS agripos;
-- CREATE DATABASE agripos
--     WITH 
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     CONNECTION LIMIT = -1;

-- Connect to agripos database
-- \c agripos

-- =============================================
-- 2. Drop Existing Table (for clean setup)
-- =============================================
DROP TABLE IF EXISTS products CASCADE;

-- =============================================
-- 3. Create Products Table
-- =============================================
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL CHECK (price >= 0),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add comments to table and columns
COMMENT ON TABLE products IS 'Stores product information for AgriPOS system';
COMMENT ON COLUMN products.code IS 'Unique product code (PK)';
COMMENT ON COLUMN products.name IS 'Product name';
COMMENT ON COLUMN products.price IS 'Product price in IDR';
COMMENT ON COLUMN products.stock IS 'Current stock quantity';
COMMENT ON COLUMN products.created_at IS 'Record creation timestamp';
COMMENT ON COLUMN products.updated_at IS 'Record last update timestamp';

-- =============================================
-- 4. Create Indexes for Better Performance
-- =============================================
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_price ON products(price);
CREATE INDEX idx_products_stock ON products(stock);

-- =============================================
-- 5. Insert Sample Data
-- =============================================
INSERT INTO products (code, name, price, stock) VALUES
('P001', 'Pupuk Organik', 25000, 100),
('P002', 'Pestisida Alami', 35000, 50),
('P003', 'Bibit Jagung Hibrida', 15000, 200),
('P004', 'Bibit Padi Unggul', 12000, 150),
('P005', 'Pupuk NPK 16-16-16', 45000, 80),
('P006', 'Insektisida Nabati', 28000, 60),
('P007', 'Bibit Tomat', 8000, 300),
('P008', 'Bibit Cabai Rawit', 10000, 250),
('P009', 'Pupuk Kandang', 20000, 120),
('P010', 'Fungisida Organik', 32000, 70);

-- =============================================
-- 6. Verification Queries
-- =============================================

-- Check table structure
SELECT 
    column_name, 
    data_type, 
    character_maximum_length,
    is_nullable,
    column_default
FROM information_schema.columns
WHERE table_name = 'products'
ORDER BY ordinal_position;

-- Display all products
SELECT * FROM products ORDER BY code;

-- Count total records
SELECT COUNT(*) as total_products FROM products;

-- Display summary statistics
SELECT 
    COUNT(*) as total_products,
    SUM(stock) as total_stock,
    AVG(price) as average_price,
    MIN(price) as min_price,
    MAX(price) as max_price,
    SUM(price * stock) as total_inventory_value
FROM products;

-- =============================================
-- 7. Useful Test Queries
-- =============================================

-- Find expensive products (price > 25000)
-- SELECT * FROM products WHERE price > 25000 ORDER BY price DESC;

-- Find products with low stock (stock < 100)
-- SELECT * FROM products WHERE stock < 100 ORDER BY stock ASC;

-- Find products by name pattern
-- SELECT * FROM products WHERE name LIKE '%Pupuk%';

-- Get products sorted by stock (ascending)
-- SELECT * FROM products ORDER BY stock ASC;

-- Get products sorted by price (descending)
-- SELECT * FROM products ORDER BY price DESC;

-- Total value per product
-- SELECT code, name, price, stock, (price * stock) as total_value 
-- FROM products ORDER BY total_value DESC;

-- =============================================
-- 8. Function: Update timestamp trigger
-- =============================================
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger
CREATE TRIGGER update_products_updated_at 
    BEFORE UPDATE ON products
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- =============================================
-- 9. Grant Permissions (Optional)
-- =============================================
-- GRANT ALL PRIVILEGES ON TABLE products TO postgres;
-- GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE products TO your_app_user;

-- =============================================
-- 10. Cleanup Scripts (Use with caution!)
-- =============================================

-- Remove all data but keep table structure:
-- TRUNCATE TABLE products RESTART IDENTITY;

-- Remove table completely:
-- DROP TABLE IF EXISTS products CASCADE;

-- Remove database (DANGEROUS!):
-- DROP DATABASE IF EXISTS agripos;

-- =============================================
-- Setup Complete!
-- =============================================
-- Database: agripos
-- Table: products
-- Sample Records: 10
-- Indexes: 3
-- Triggers: 1
-- =============================================

-- Display success message
DO $$
BEGIN
    RAISE NOTICE '========================================';
    RAISE NOTICE 'Database Setup Completed Successfully!';
    RAISE NOTICE '========================================';
    RAISE NOTICE 'Database: agripos';
    RAISE NOTICE 'Table: products';
    RAISE NOTICE 'Sample Data: 10 records inserted';
    RAISE NOTICE '========================================';
END $$;