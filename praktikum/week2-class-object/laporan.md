
# Laporan Praktikum Minggu 2
Topik: "Class dan Object"

## Identitas
- Nama  : Rafi Kurniawan
- NIM   : 240202878
- Kelas : 3IKRB

---

## Tujuan

- Memahami konsep dasar OOP (class, objek, atribut, method).

- Dapat membuat program yang modular dan terstruktur dengan package.

- Mampu menerapkan enkapsulasi dan manipulasi data objek secara aman.

- Terbiasa dengan coding convention dan struktur proyek Java profesional.


---

## Dasar Teori
1. Class dan Object
Class adalah cetakan (blueprint) untuk membuat objek. Objek merupakan instance dari class yang memiliki atribut (data) dan method (perilaku).

2. Atribut dan Method
Atribut digunakan untuk menyimpan data dalam class, sedangkan method digunakan untuk mendefinisikan perilaku atau aksi dari objek.

3. Enkapsulasi (Encapsulation)
Merupakan konsep penyembunyian data (data hiding) dengan membuat atribut bersifat private dan diakses menggunakan getter dan setter agar data lebih aman dan terkontrol.

4. Instansiasi Objek
Proses pembuatan objek dari class menggunakan kata kunci new. Melalui instansiasi, kita dapat mengakses dan memanipulasi data serta method dari class.

5. Package dalam Java
Package digunakan untuk mengelompokkan class berdasarkan fungsinya agar struktur program lebih rapi, modular, dan mudah dikelola (misalnya: model, util, main).

---

## Langkah Praktikum

- Membuat Struktur Folder Proyek

- Membuat Class Produk (package model)

- Membuat Class CreditBy (package util)

- Membuat Class MainProduk (package main)

- Menjalankan Program

---

## Kode Program
```java
// Produk
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    // Constructor
    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // ✅ Latihan Mandiri
    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("❌ Stok tidak mencukupi untuk dikurangi!");
        }
    }
}

```java
// CreditBy
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("Credit by: " + nim + " - " + nama);
    }
}

```java
// MainProduk
package com.upb.agripos.main;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        // Membuat minimal 3 objek produk
        Produk p1 = new Produk("P001", "Benih Padi", 50000, 20);
        Produk p2 = new Produk("P002", "Pupuk Urea", 75000, 15);
        Produk p3 = new Produk("P003", "Cangkul Baja", 120000, 10);

        // Menampilkan daftar produk
        System.out.println("=== Daftar Produk ===");
        System.out.println(p1.getKode() + " - " + p1.getNama() + " | Rp" + p1.getHarga() + " | Stok: " + p1.getStok());
        System.out.println(p2.getKode() + " - " + p2.getNama() + " | Rp" + p2.getHarga() + " | Stok: " + p2.getStok());
        System.out.println(p3.getKode() + " - " + p3.getNama() + " | Rp" + p3.getHarga() + " | Stok: " + p3.getStok());

        // Menggunakan latihan mandiri
        System.out.println("\n=== Update Stok ===");
        p1.tambahStok(5);
        p2.kurangiStok(3);
        System.out.println(p1.getNama() + " stok baru: " + p1.getStok());
        System.out.println(p2.getNama() + " stok baru: " + p2.getStok());

        // Menampilkan identitas mahasiswa
        System.out.println();
        CreditBy.print("240202878", "Rafi Kurniawan");
    }
}

---

## Hasil Eksekusi
praktikum/week3-inheritance/screenshots/Hasil MainIheritance.png
---

## Analisis
Kode berjalan berdasarkan konsep pemrograman berorientasi objek (OOP).
  - Class Produk berfungsi sebagai blueprint (cetakan) dari objek produk yang memiliki atribut seperti kode, nama, harga, dan stok.
  - Class CreditBy digunakan untuk menampilkan identitas pembuat program (nama dan NIM).
  - Class MainProduk merupakan titik awal program yang memiliki method main().
    Di sini program:
    1. Membuat beberapa objek Produk.
    2. Menampilkan data produk ke layar.
    3. Menjalankan method tambahStok() dan kurangiStok() untuk mengubah stok.
    4. Menampilkan hasil akhir serta identitas pembuat program melalui CreditBy.credit().
Alur ini menunjukkan bagaimana objek saling berinteraksi dalam satu sistem.

---

## Kesimpulan
Praktikum ini berhasil menerapkan konsep Pemrograman Berorientasi Objek (OOP) dengan membuat dan menggunakan class Produk, CreditBy, serta MainProduk.
Penerapan enkapsulasi membuat data lebih aman dan terkontrol melalui penggunaan getter dan setter.
Struktur program menjadi lebih modular dan terorganisasi dengan pembagian package (model, util, main).
Penggunaan method seperti tambahStok() dan kurangiStok() membuktikan bagaimana perilaku objek dapat diatur melalui fungsi dalam class.

---

## Quiz

1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?  
   **Jawaban:** …  
   Atribut sebaiknya dibuat private karena alasan enkapsulasi (encapsulation), yaitu prinsip utama dalam Object-Oriented Programming (OOP) yang bertujuan untuk melindungi data.

2. Apa fungsi getter dan setter dalam enkapsulasi?
   **Jawaban:** …  
   Fungsi Getter
   - Untuk mengambil (membaca) nilai dari atribut private.
   - Biasanya digunakan jika kita ingin menampilkan data tanpa bisa mengubahnya secara langsung.

   Fungsi Setter
   - Untuk mengubah (menulis) nilai dari atribut private.
   - Dapat diberi logika validasi agar data yang dimasukkan tetap benar

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks? 
   **Jawaban:** …  
   Class Produk mendukung pengembangan aplikasi POS yang lebih kompleks karena menyediakan struktur data yang rapi, aman, mudah dikembangkan, dan bisa diintegrasikan dengan komponen lain dalam sistem.