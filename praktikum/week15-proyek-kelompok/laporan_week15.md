# Proyek Kelompok (Desain Sistem + Implementasi Terintegrasi + Testing + Dokumentasi) 

# 1. Identitas kelompok

| Nama | NIM | Peran |
|--- | --- | --- |
| As Syifa Dian Rinesti | 240320559 | Manajemen Produk |
| Azizzah Nurul Putri | 240320560 | Exception |
| Azzahra Ramadhani | 240320561 | Login dan Hak Akses |
| Bunga Maura Aulya | 240320562 | Metode Pembayaran |
| Difa Rizkiana Fauziyah | 240320564 | Transaksi Penjualan |
| Rossa Aqila Zahra | 240320568 | Struk dan Laporan |

---

## 2. Ringkasan Sistem

Agri-POS adalah aplikasi *Point of Sale* sederhana berbasis Java yang ditujukan untuk membantu proses penjualan produk pertanian. Sistem ini mendukung manajemen produk, transaksi penjualan, metode pembayaran yang extensible, laporan penjualan, serta pengelolaan hak akses pengguna (Admin dan Kasir).

**Scope sistem:**

* Manajemen produk (CRUD)
* Transaksi penjualan dan keranjang
* Pembayaran tunai dan e-wallet
* Struk transaksi dan laporan penjualan
* Login dan pembatasan hak akses

**Batasan sistem:**

* Sistem bersifat desktop (JavaFX)
* Database menggunakan PostgreSQL
* Tidak terhubung ke payment gateway nyata (mock)

---

## 3. Desain Sistem

### 3.1 Daftar Kebutuhan Sistem

#### Functional Requirements (FR)

* **FR-1 Manajemen Produk**: Admin dapat menambah, mengubah, menghapus, dan melihat daftar produk.
* **FR-2 Transaksi Penjualan**: Kasir dapat membuat transaksi, mengelola keranjang, dan menghitung total belanja.
* **FR-3 Metode Pembayaran**: Sistem mendukung pembayaran Tunai dan E-Wallet dengan pendekatan Strategy.
* **FR-4 Struk & Laporan**: Sistem menampilkan struk transaksi dan laporan penjualan.
* **FR-5 Login & Hak Akses**: Sistem menyediakan login dengan peran Admin dan Kasir.

#### Non-Functional Requirements

* Aplikasi mudah digunakan (UI sederhana)
* Arsitektur berlapis dan mudah dikembangkan
* Data persisten dan aman menggunakan DB

### 3.2 Arsitektur Sistem

Sistem menggunakan arsitektur berlapis sesuai prinsip SOLID dan DIP:

```
View (JavaFX)
   ↓
Controller
   ↓
Service (Business Logic)
   ↓
DAO (JDBC)
   ↓
PostgreSQL Database
```

Tidak ada akses database langsung dari layer View.

---

## 4. UML Diagram

### 4.1 Use Case Diagram

Aktor utama:

* **Admin**: login, kelola produk, lihat laporan
* **Kasir**: login, transaksi penjualan, checkout

(Use Case Diagram dilampirkan pada folder screenshots/docs)

### 4.2 Class Diagram

Class utama:

* `Product`, `User`, `Transaction`, `TransactionItem`
* `ProductService`, `TransactionService`, `AuthService`
* `ProductDAO`, `TransactionDAO`, `UserDAO`
* `PaymentMethod` (interface)

  * `CashPayment`
  * `EWalletPayment`

### 4.3 Sequence Diagram

* **SD-1 Tambah Produk**: Admin → ProductController → ProductService → ProductDAO → DB
* **SD-2 Checkout Transaksi**: Kasir → TransactionController → TransactionService → PaymentMethod → DAO → DB

---

## 5. Desain Database

### 5.1 ERD Sederhana

Tabel utama:

* `users (id, username, password, role)`
* `products (id, kode, nama, kategori, harga, stok)`
* `transactions (id, tanggal, total, payment_method)`
* `transaction_items (id, transaction_id, product_id, qty, subtotal)`

### 5.2 DDL (Contoh)

```sql
CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  kode VARCHAR(20),
  nama VARCHAR(100),
  kategori VARCHAR(50),
  harga NUMERIC,
  stok INT
);
```

### 5.3 Akses Data

Akses database dilakukan melalui DAO menggunakan JDBC dan `PreparedStatement`.

---

## 6. Test Plan & Test Case

### 6.1 Test Plan

Strategi pengujian:

* Manual testing untuk alur utama (UI)
* Unit testing (JUnit) untuk logika Service

### 6.2 Contoh Test Case Manual

| TC ID | Deskripsi           | Prekondisi          | Langkah                  | Expected Result          |
| ----- | ------------------- | ------------------- | ------------------------ | ------------------------ |
| TC-01 | Login Admin         | User admin tersedia | Login dengan akun admin  | Masuk ke dashboard admin |
| TC-02 | Tambah Produk       | Login admin         | Isi form produk → Simpan | Produk tampil di tabel   |
| TC-03 | Ubah Produk         | Produk ada          | Edit data produk         | Data terupdate           |
| TC-04 | Hapus Produk        | Produk ada          | Klik hapus               | Produk terhapus          |
| TC-05 | Tambah ke Keranjang | Login kasir         | Pilih produk + qty       | Item masuk keranjang     |
| TC-06 | Hitung Total        | Keranjang terisi    | Lihat total              | Total sesuai             |
| TC-07 | Checkout Tunai      | Keranjang terisi    | Pilih Tunai → Bayar      | Transaksi sukses         |
| TC-08 | Lihat Laporan       | Login admin         | Buka laporan             | Data laporan tampil      |

### 6.3 Unit Test (JUnit)

Unit test dilakukan pada `CartService` untuk memastikan perhitungan total berjalan benar.
(Screenshot hasil JUnit terlampir)

---

## 7. Traceability Matrix

| Artefak   | Referensi        | Implementasi                   | Bukti                |
| --------- | ---------------- | ------------------------------ | -------------------- |
| FR-1      | Manajemen Produk | ProductController/Service/DAO  | Screenshot CRUD      |
| FR-2      | Transaksi        | CartService/TransactionService | Screenshot keranjang |
| FR-3      | Pembayaran       | PaymentMethod (Strategy)       | Screenshot pilihan   |
| FR-4      | Struk & Laporan  | ReceiptService/ReportService   | Screenshot laporan   |
| FR-5      | Login & Role     | AuthService                    | Screenshot login     |
| Sequence  | SD-Checkout      | View→Controller→Service→DAO    | Diagram              |
| Test Case | TC-07            | Checkout Tunai                 | Screenshot           |

---

## 8. Pembagian Kerja & Kontribusi

* Anggota 1: Integrasi sistem & arsitektur
* Anggota 2: Service & DAO
* Anggota 3: UI JavaFX
* Anggota 4: Testing & dokumentasi

Bukti kontribusi terdapat pada commit Git masing-masing anggota.

---

## 9. Kendala & Solusi

1. **Masalah koneksi database** → Solusi: pengecekan konfigurasi JDBC dan environment.
2. **Duplikasi logika di controller** → Solusi: refactor ke Service.
3. **Pengujian sulit pada UI** → Solusi: fokus unit test pada Service.

---

## 10. Kesimpulan

Proyek Agri-POS berhasil diimplementasikan secara terintegrasi dengan arsitektur berlapis. Sistem memenuhi seluruh kebutuhan fungsional utama, mudah dikembangkan, serta terdokumentasi dengan baik sesuai ketentuan Bab 15.


