# Laporan Praktikum Minggu 6
Topik: UML SOLID
## Identitas
- Nama  : Rafi Kurniawan
- NIM   : 240202878
- Kelas : 3IKRB

---

## Tujuan
Tujuan praktikum minggu ke-6 adalah:

1. Mahasiswa mampu memahami konsep dasar permodelan UML.

2. Mahasiswa mampu membuat diagram Activity, Class, Sequence, dan Use Case secara benar.

3. Mahasiswa mampu menerapkan prinsip SOLID ke dalam desain perangkat lunak.

4. Mahasiswa mampu menghubungkan desain UML dengan implementasi kode program.

---

## Dasar Teori
1. UML (Unified Modeling Language) adalah bahasa standar untuk memodelkan sistem perangkat lunak melalui diagram visual seperti Activity, Class, Sequence, dan Use Case.

2. Activity Diagram menggambarkan alur proses dari awal sampai akhir.

3. Class Diagram menjelaskan struktur kelas, atribut, method, dan relasi antarkelas.

4. Sequence Diagram memperlihatkan interaksi objek secara berurutan dalam waktu.

5. SOLID adalah lima prinsip desain OOP yang membuat sistem lebih modular, mudah diperluas, dan mudah diuji.

---

## Langkah Praktikum
1. Membuat folder proyek berisi struktur src di VS Code.

2. Menginstall Mermaid CLI atau menggunakan ekstensi Markdown untuk merender diagram UML.

3. Membuat masing-masing file:
   - activity.mmd
   - class.mmd
   - sequence.mmd
   - usecase.mmd

4. Menuliskan kode UML Mermaid untuk setiap diagram.

5. Render diagram menjadi gambar .png

---

## Kode Program

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

## Hasil Eksekusi
![Screenshot hasil](screenshots/activity.png)

![Screenshot hasil](screenshots/class.png)

![Screenshot hasil](screenshots/sequence.png)

![Screenshot hasil](screenshots/usecase0.png)

![Screenshot hasil](screenshots/usecase1.png)

---

## Analisis
1. Kode memanfaatkan interface PaymentMethod sehingga setiap jenis pembayaran (tunai, e-wallet, kartu) dapat diimplementasikan tanpa mengubah class utama (Open/Closed Principle).
2.  Pendekatan minggu ini lebih terstruktur karena harus membuat diagram UML lengkap sebelum coding. Hal ini membuat implementasi lebih mudah dipahami, terencana, dan konsisten dengan prinsip SOLID.
3. Kendala yang dihadapi:
   - Instalasi Mermaid CLI mengeluarkan warning deprecated, namun tidak mengganggu proses.
   - Diagram perlu diperbaiki agar sesuai standar UML (misal menambahkan decision node & swimlane).
4. Cara mengatasi:
   - Membaca ulang dokumentasi Mermaid, memperbaiki struktur diagram.
   - Menggunakan Markdown Mermaid Preview agar mudah melihat hasilnya secara langsung.

---

## Kesimpulan
Dengan menerapkan UML dan prinsip SOLID, desain sistem menjadi lebih rapi, mudah dipahami, dan dapat dikembangkan tanpa merusak struktur yang sudah ada. Diagram UML membantu memvisualisasikan alur, hubungan antarkelas, interaksi objek, dan kebutuhan fungsional dengan lebih jelas.

---

## Quiz
1. Jelaskan perbedaan **aggregation** dan **composition** serta berikan contoh penerapannya pada desain Anda.
   **Jawaban:** …  
   - Aggregation adalah hubungan “bagian dari” tetapi objek masih bisa berdiri sendiri.
     Contoh: Kasir memiliki Produk—produk tetap ada walau kasir berhenti bekerja.
   - Composition adalah hubungan “bagian dari” yang sangat kuat. Jika objek induk dihapus, objek anak ikut hilang.
     Contoh: Transaksi memiliki DaftarItem. Jika transaksi dihapus, daftar item juga hilang.

1. Bagaimana prinsip **Open/Closed** dapat memastikan sistem mudah dikembangkan?
   **Jawaban:** …  
   Prinsip OCP menyatakan bahwa class harus terbuka untuk ekstensi tetapi tertutup untuk modifikasi. Artinya, kita dapat menambahkan fitur baru tanpa mengubah kode lama.Contoh pada sistem: menambah metode pembayaran baru QRISPayment tanpa mengubah class PaymentProcessor.

2. Mengapa **Dependency Inversion Principle (DIP)** meningkatkan **testability**? Berikan contoh penerapannya. 
   **Jawaban:** …  
   DIP mengharuskan class bergantung pada abstraksi, bukan implementasi konkret.
   Karena itu, kita bisa mengganti implementasi dengan mock/fake saat testing.

   Contoh:
   PaymentProcessor bergantung pada interface PaymentMethod, sehingga pada unit test kita dapat membuat:

   class FakePaymentMethod implements PaymentMethod {
       public boolean pay(double amount) { return true; }
   }

   Sehingga program lebih mudah dites tanpa mengubah kode asli.