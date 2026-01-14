# Laporan Praktikum Minggu 12
Topik: GUI Dasar JavaFX (Event-Driven Programming)

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]

---

## Tujuan
1. Menjelaskan konsep event-driven programming.
2. Membangun antarmuka grafis sederhana menggunakan JavaFX.
3. Membuat form input data produk.
4. Menampilkan daftar produk pada GUI.
5. Mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Dasar Teori
1. Event-Driven Programming adalah paradigma pemrograman di mana alur program ditentukan oleh event atau aksi pengguna, seperti klik tombol atau input teks.
2. JavaFX merupakan framework GUI Java yang digunakan untuk membangun antarmuka grafis berbasis komponen (Stage, Scene, Node).
3. MVC (Model–View–Controller) memisahkan logika aplikasi menjadi tiga bagian: Model (data), View (tampilan), dan Controller (pengendali event).
4. Service Layer berfungsi sebagai penghubung antara Controller dan DAO agar logika bisnis tidak bercampur dengan logika tampilan.
5. DAO (Data Access Object) digunakan untuk mengakses dan mengelola data sehingga Controller tidak berinteraksi langsung dengan database (penerapan DIP – SOLID).

---

## Langkah Praktikum
1. Menyiapkan environment Java dan JavaFX serta membuka folder week12-gui-dasar pada VS Code.
2. Membuat struktur proyek berbasis MVC (model, view, controller, service, dao).
3. Mengimplementasikan ProductFormView menggunakan JavaFX sebagai antarmuka input dan tampilan data produk.
4. Membuat ProductController untuk menangani event tombol dan menghubungkan View dengan Service.
5. Menggunakan ProductService sebagai perantara antara Controller dan DAO sesuai prinsip MVC.
6. Menjalankan aplikasi JavaFX dan menguji fitur tambah serta tampil daftar produk.
7. Melakukan commit
---

## Kode Program
(Tuliskan kode utama yang dibuat, 

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        ProductFormView view = new ProductFormView();
        ProductService service = new ProductService();
        new ProductController(service, view);

        Scene scene = new Scene(view, 450, 500);
        stage.setTitle("Agri-POS - Week 12 (GUI Dasar)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week12-gui-dasar/screenshots/gui_form_produk.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  

    Aplikasi berjalan dengan pendekatan event-driven. Ketika pengguna mengisi form dan menekan tombol Tambah Produk, event handler pada ProductController akan membuat objek Product, lalu memanggil ProductService untuk menyimpan data. Setelah data tersimpan, tampilan ListView diperbarui untuk menampilkan daftar produk terbaru.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  

    Pada pertemuan sebelumnya, interaksi pengguna dilakukan melalui console (CLI), sedangkan pada praktikum ini interaksi dilakukan melalui GUI berbasis JavaFX. Selain itu, alur program tidak lagi berjalan secara sekuensial, tetapi dipicu oleh event seperti klik tombol.

- Kendala yang dihadapi dan cara mengatasinya. 

    Kendala yang dihadapi antara lain konfigurasi JavaFX agar dapat dijalankan di VS Code serta kesalahan akses komponen View dari Controller. Kendala tersebut diatasi dengan menambahkan konfigurasi vmArgs JavaFX pada launch.json dan menerapkan getter method pada View agar tetap sesuai dengan prinsip enkapsulasi.

)
---

## Kesimpulan
Dengan menggunakan JavaFX dan pendekatan MVC, aplikasi menjadi lebih interaktif, terstruktur, dan mudah dikembangkan. Pemisahan antara View, Controller, dan Service membuat logika program lebih rapi serta memudahkan integrasi antara antarmuka pengguna dan proses backend.

