# Laporan Praktikum Minggu 15

## Proyek Kelompok – Aplikasi AgriPOS (Point of Sale)

---

## Topik

**Desain Sistem, Implementasi Terintegrasi, Testing, dan Dokumentasi Aplikasi AgriPOS**

---

## Identitas

| No | Nama Anggota | NIM        | Peran                  |
| -- | ------------ | ---------- | ---------------------- |
| 1  | (Isi Nama)   | (Isi NIM)  | Service & Testing      |
| 2  | (Isi Nama)   | (Isi NIM)  | Controller & Exception |
| 3  | (Isi Nama)   | (Isi NIM)  | Model & Payment        |
| 4  | Rafi Kurniawan   | (Isi NIM)  | DAO, Database & Util   |
| 5  | (Opsional)   | (Opsional) | View (JavaFX)          |

---

## Tujuan

Tujuan praktikum Minggu 15 ini adalah:

1. Mahasiswa mampu bekerja secara kolaboratif dalam pengembangan aplikasi.
2. Mahasiswa mampu menerapkan konsep **Object Oriented Programming (OOP)** secara terintegrasi.
3. Mahasiswa mampu menerapkan **arsitektur berlapis** (View–Controller–Service–DAO).
4. Mahasiswa mampu mendesain sistem menggunakan **UML (Use Case, Class, Sequence Diagram)**.
5. Mahasiswa mampu melakukan **testing dasar** dan menyusun dokumentasi proyek.

---

## Dasar Teori

Dasar teori yang digunakan pada praktikum ini antara lain:

1. **Object Oriented Programming (OOP)** memodelkan sistem dalam bentuk class dan object.
2. **Layered Architecture** memisahkan tanggung jawab setiap bagian sistem.
3. **SOLID Principle**, khususnya Dependency Inversion Principle (DIP), untuk meningkatkan maintainability.
4. **UML (Unified Modeling Language)** sebagai alat bantu perancangan sistem.
5. **DAO Pattern** untuk pemisahan logika akses data.
6. **Strategy Pattern** untuk metode pembayaran yang extensible.

---

## UML Diagram

### 1. Activity Diagram
```mermaid
flowchart TD
    A([Start]) --> B[Scan Produk]
    B --> C[Hitung Total]

    C --> D{Pilih Metode Pembayaran?}

    %% TUNAI
    D -->|Tunai| E[Input Nominal Pembayaran]
    E --> F{Nominal Cukup?}
    F -->|Tidak| G[Tolak & Minta Ulang]
    G --> E
    F -->|Ya| H[Proses Pembayaran Tunai]

    %% E-WALLET
    D -->|E-Wallet| I[Permintaan ke Payment Gateway]
    I --> J{Saldo Cukup?}
    J -->|Tidak| K[Gagal & Kembali]
    J -->|Ya| L[Otorisasi Berhasil]

    %% LANJUT
    H --> M[Update Stok Produk]
    L --> M

    M --> N[Simpan Transaksi]
    N --> O[Cetak Struk]
    O --> P([End])
```

---

### 2. Class Diagram
```mermaid
classDiagram

    class Product {
        -String id
        -String name
        -double price
        -int stock
        +updateStock(qty)
        +getInfo()
    }

    class CartItem {
        -Product product
        -int qty
        +getSubtotal()
    }

    class Cart {
        -List~CartItem~ items
        +addItem(product, qty)
        +removeItem(product)
        +getTotal()
        +clear()
    }

    class Payment {
        <<abstract>>
        -double amount
        +processPayment()
    }

    class PaymentCash {
        -double nominal
        +processPayment()
    }

    class PaymentEWallet {
        -String walletId
        -boolean authorize()
        +processPayment()
    }

    class Transaction {
        -String id
        -Date date
        -double total
        -List~CartItem~ items
        +save()
    }

    class Receipt {
        -String text
        +generate(transaction)
    }

    class Cashier {
        -String id
        -String name
        +checkout()
        +printReceipt()
    }

    Product "1" <-- "*" CartItem : contains
    Cart "1" --> "*" CartItem : aggregates
    Payment <|-- PaymentCash
    Payment <|-- PaymentEWallet
    Transaction --> "*" CartItem : stores
    Receipt --> Transaction : generates
    Cashier --> Cart : uses
    Cashier --> Payment : selects
    Cashier --> Receipt : prints
```

---

### 3. Sequence Diagram
```mermaid
sequenceDiagram
    actor Kasir
    participant System
    participant Cart
    participant Payment
    participant Gateway as PaymentGateway
    participant Transaction
    participant Receipt

    Kasir->>System: Scan Produk
    System->>Cart: addItem(product)
    Cart-->>System: item added

    Kasir->>System: Checkout()
    System->>Cart: getTotal()
    Cart-->>System: total price

    Kasir->>System: Pilih Metode Pembayaran

    alt Tunai
        System->>Payment: processPayment()
        Payment-->>System: success
    else E-Wallet
        System->>Gateway: requestAuthorization()
        Gateway-->>System: authorized
    end

    System->>Transaction: create & save()
    Transaction-->>System: saved

    System->>Receipt: generate(transaction)
    Receipt-->>System: struk selesai

    System->>Kasir: Beri Struk
```

---

## 4. Use Case Diagram
```mermaid
---
title: Use Case Diagram AgriPOS
---

flowchart LR
    actorAdmin([Admin])
    actorKasir([Kasir])

    subgraph AgriPOS
        UC1((Kelola Produk))
        UC2((Kelola Pengguna))
        UC3((Lihat Laporan))
        UC4((Checkout))
        UC5((Scan Produk))
        UC6((Kelola Keranjang))
        UC7((Pembayaran Tunai))
        UC8((Pembayaran E-Wallet))
        UC9((Cetak Struk))
        UC10((Update Stok))
        UC11((Simpan Transaksi))
    end

    %% RELASI AKTOR
    actorAdmin --> UC1
    actorAdmin --> UC2
    actorAdmin --> UC3

    actorKasir --> UC4
    actorKasir --> UC5
    actorKasir --> UC6
    actorKasir --> UC9

    %% INCLUDE RELATION
    UC4 -. include .-> UC5
    UC4 -. include .-> UC6
    UC4 -. include .-> UC7
    UC4 -. include .-> UC8
    UC4 -. include .-> UC10
    UC4 -. include .-> UC11
    UC4 -. include .-> UC9
```

---

## Langkah Praktikum

Langkah-langkah yang dilakukan pada praktikum Minggu 15 adalah:

1. Membentuk kelompok dan menentukan pembagian tugas.
2. Menyusun desain sistem menggunakan UML.
3. Menyiapkan struktur folder proyek sesuai ketentuan.
4. Mengimplementasikan layer Service, DAO, dan Controller.
5. Menghubungkan aplikasi dengan PostgreSQL menggunakan JDBC.
6. Menjalankan unit test pada Service layer.
7. Mendokumentasikan hasil implementasi dan pengujian.

**Contoh commit message:**

```
week15-proyek-kelompok: integrasi transaksi dan pembayaran
```

---

## Kode Program

Pada praktikum Minggu 15, fokus utama adalah **integrasi sistem**, sehingga tidak seluruh kode ditampilkan.

Contoh potongan kode utama:

```java
transactionService.checkout(cart, paymentMethod);
receiptService.generate(transaction);
```

Kode lengkap tersedia pada folder `src/main/java/com/upb/agripos/`.

---

## Hasil Eksekusi

Hasil eksekusi menunjukkan bahwa:

* Login berhasil sesuai role (Admin/Kasir).
* Produk dapat ditambahkan ke keranjang.
* Total belanja dihitung dengan benar.
* Pembayaran tunai dan e-wallet berhasil diproses.
* Struk transaksi berhasil ditampilkan.

📸 *Screenshot hasil eksekusi terlampir (transaksi, struk, dan hasil unit test).*

---

## Analisis

### Analisis Program

Program berjalan dengan alur View → Controller → Service → DAO → Database. Pemisahan layer membuat sistem lebih rapi dan mudah dikembangkan.

### Perbedaan dengan Minggu Sebelumnya

* Minggu sebelumnya berfokus pada implementasi individu.
* Minggu ini berfokus pada **integrasi sistem secara menyeluruh**, desain, dan dokumentasi.

### Kendala dan Solusi

* **Kendala**: Error koneksi database → **Solusi**: Singleton DatabaseConnection.
* **Kendala**: Integrasi antar layer → **Solusi**: Penggunaan interface Service.
* **Kendala**: Konsistensi UML dan kode → **Solusi**: Review UML bersama.

---

## Kesimpulan

Dari praktikum Minggu 15 dapat disimpulkan bahwa pengembangan aplikasi secara berkelompok membutuhkan desain yang matang, arsitektur yang rapi, serta dokumentasi dan testing yang baik. Dengan menerapkan OOP, UML, dan arsitektur berlapis, sistem AgriPOS dapat berjalan dengan baik dan mudah dikembangkan di masa depan.

---

**END OF DOCUMENT**
