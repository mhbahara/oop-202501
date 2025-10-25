# Laporan Praktikum Minggu 2
Class dan Object (Produk Pertanian)

## Identitas
- Nama  : [Wisnu wibowo saputro]
- NIM   : [240320565]
- Kelas : [3DSRA]

---

## Tujuan
Mahasiswa mampu menjelaskan konsep class, object, atribut, dan method dalam OOP.
Mahasiswa mampu menerapkan access modifier dan enkapsulasi dalam pembuatan class.
Mahasiswa mampu mengimplementasikan class Produk pertanian dengan atribut dan method yang sesuai.
Mahasiswa mampu mendemonstrasikan instansiasi object serta menampilkan data produk pertanian di console.
Mahasiswa mampu menyusun laporan praktikum dengan bukti kode, hasil eksekusi, dan analisis sederhana.

---

## Dasar Teori

1. Class dan Objek: Class adalah blueprint atau cetak biru dari sebuah objek, sedangkan objek merupakan instansiasi dari class yang berisi atribut (data) dan method (perilaku).  
2. Enkapsulasi dan Access Modifier: Enkapsulasi dilakukan dengan menyembunyikan data menggunakan access modifier seperti public, private, dan protected, kemudian menyediakan akses melalui getter dan setter.
3. Representasi Produk Pertanian: Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek dengan atribut nama, harga, dan stok.
4. Manajemen Objek Terstruktur: Dengan menggunakan class, setiap produk dapat dibuat, dikelola, dan dimanipulasi secara lebih terstruktur, memudahkan pengelolaan data dalam sistem.

---

## Langkah Praktikum

1. Membuat Class Produk
   a. Buat file Produk.java pada package model.
   b. Tambahkan atribut: kode, nama, harga, dan stok.
   c. Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.
2. Membuat Class CreditBy
   a. Buat file CreditBy.java pada package util.
   b. Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: credit by: <240320565> - <Wisnu Wibowo Saputro>.
3. Membuat Objek Produk dan Menampilkan Credit
   a. Buat file MainProduk.java.
   b. Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
   c. Tampilkan informasi produk melalui method getter.
   d. Panggil CreditBy.print("<240320565>", "<Wisnu Wibowo Saputro>") di akhir main untuk menampilkan identitas.
4. Commit dan Push

Commit dengan pesan: week2-class-object.

---

## Kode Program
  
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

  // Method untuk menambah stok
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
            System.out.println(jumlah + " stok ditambahkan. Stok sekarang: " + stok);
        } else {
            System.out.println("Jumlah harus lebih dari 0");
        }
    }

    // Method untuk mengurangi stok
    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
            System.out.println(jumlah + " stok dikurangi. Stok sekarang: " + stok);
        } else {
            System.out.println("Jumlah tidak valid atau stok tidak cukup");
        }
    }
}
)
---

## Hasil Eksekusi
( https://github.com/yusrilhidayah1933-commits/oop-202501-240320565/blob/main/praktikum/week2-class-object/screenshots/Screenshot%202025-10-17%20160406.png
)
---

## Analisis
(
1. Bagaimana kode berjalan: 
Program membuat objek Produk, menampilkan informasi produk, dan bisa menambah atau mengurangi stok menggunakan method khusus.
2. Perbedaan minggu ini dengan minggu sebelumnya: 
Minggu lalu hanya membuat “Hello World” sederhana, minggu ini belajar OOP penuh dengan class, objek, atribut, dan method, jadi program lebih terstruktur.
3. Kendala dan cara mengatasinya: Sulit memahami getter/setter dan menambahkan method stok, diatasi dengan mencoba langsung di kode dan mengecek hasilnya di console. 
)
---

## Kesimpulan
Dengan menggunakan class, object, dan enkapsulasi, program menjadi lebih terstruktur, aman, dan mudah dikembangkan, serta memudahkan pengelolaan data seperti stok produk dalam aplikasi POS.
---

## Quiz

1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
Jawaban: Atribut sebaiknya dideklarasikan sebagai private untuk melindungi data dari akses langsung dari luar class, sehingga mencegah perubahan atau manipulasi yang tidak diinginkan dan memastikan integritas data.
2. Apa fungsi getter dan setter dalam enkapsulasi?
Jawaban: Getter dan setter berfungsi sebagai metode pengakses dan pengubah nilai atribut private secara terkontrol. Getter digunakan untuk membaca nilai atribut, sedangkan setter digunakan untuk memodifikasi nilai atribut dengan validasi jika diperlukan.
3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
Jawaban: Class Produk memungkinkan setiap produk direpresentasikan sebagai objek dengan atribut dan method yang jelas, sehingga mempermudah pengelolaan inventaris, perhitungan harga, dan integrasi dengan fitur lain dalam aplikasi POS, seperti transaksi, laporan, dan manajemen stok, tanpa mengubah struktur dasar program.
