# Laporan Praktikum Minggu 14
Topik: Integrasi Individu (OOP + Database + GUI)

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]

---

## Tujuan
Setelah mengikuti praktikum ini, mahasiswa mampu:
1. Mengintegrasikan konsep OOP (Bab 1–5) ke dalam satu aplikasi yang utuh.
2. Mengimplementasikan rancangan UML + SOLID (Bab 6) menjadi kode nyata.
3. Mengintegrasikan Collections + Keranjang (Bab 7) ke alur aplikasi.
4. Menerapkan exception handling (Bab 9) untuk validasi dan error flow.
5. Menerapkan pattern + unit testing (Bab 10) pada bagian yang relevan.
6. Menghubungkan aplikasi dengan database via DAO + JDBC (Bab 11).
7. Menyajikan aplikasi berbasis JavaFX (Bab 12–13) yang terhubung ke backend.

---

## Dasar Teori
1. Object Oriented Programming (OOP) digunakan untuk membangun aplikasi berbasis class dan objek agar kode terstruktur, modular, dan mudah dikembangkan.
2. Arsitektur berlapis (MVC + Service + DAO) diterapkan untuk memisahkan tampilan, logika aplikasi, dan akses data sesuai prinsip SOLID.
3. DAO dan JDBC digunakan untuk menghubungkan aplikasi Java dengan database PostgreSQL dan menjalankan operasi CRUD.
4. Collections Framework dimanfaatkan untuk mengelola data keranjang belanja secara dinamis.
5. Exception handling, design pattern, dan unit testing digunakan untuk validasi, pengelolaan koneksi, dan pengujian logika non-UI.

---

## Langkah Praktikum
1. Melanjutkan proyek dari Bab 1–13 dan menyiapkan struktur folder sesuai ketentuan Bab 14.
2. Mengimplementasikan class model (Product, Cart, CartItem) berbasis OOP.
3. Menerapkan DAO dan Service untuk operasi CRUD produk menggunakan JDBC.
4. Mengimplementasikan fitur keranjang belanja menggunakan Collections.
5. Menerapkan validasi input dan exception handling.
6. Membuat antarmuka aplikasi menggunakan JavaFX dan menghubungkannya dengan Controller.
7. Menerapkan satu design pattern dan membuat satu unit test JUnit.
8. Menjalankan aplikasi dan mendokumentasikan hasil eksekusi.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        PosController controller = new PosController();
        PosView view = new PosView(controller, primaryStage);

        Scene scene = new Scene(view.getRoot(), 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AgriPOS - Bunga Maura Aulya");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```
)

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week14-integrasi-individu/screenshots/app_main.png)

![Screenshot hasil](/praktikum/week14-integrasi-individu/screenshots/junit_result.png)
)

---

## Analisis

- Jelaskan bagaimana kode berjalan.

    Aplikasi Agri-POS berjalan dengan alur berlapis, dimulai dari View (JavaFX) yang menerima input pengguna, kemudian diteruskan ke Controller untuk mengatur alur aplikasi. Selanjutnya, Service menangani logika bisnis seperti validasi dan pengelolaan keranjang, sedangkan DAO bertugas melakukan operasi CRUD ke database PostgreSQL melalui JDBC. Data yang diperoleh kemudian ditampilkan kembali pada antarmuka JavaFX.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  

    Pendekatan pada praktikum minggu ini berbeda dengan minggu sebelumnya karena seluruh konsep dari beberapa bab diintegrasikan menjadi satu aplikasi utuh. Pada minggu sebelumnya, implementasi masih terpisah dan fokus pada satu konsep tertentu, sedangkan pada Bab 14 diterapkan arsitektur lengkap dengan pemisahan layer, penggunaan database, GUI, serta pengujian unit.

- Kendala yang dihadapi dan cara mengatasinya.  

    Kendala yang dihadapi antara lain kesalahan koneksi database, pengaturan alur komunikasi antar layer, dan validasi input pada GUI. Kendala tersebut diatasi dengan pengecekan konfigurasi JDBC, penyesuaian pemanggilan metode sesuai arsitektur MVC + Service + DAO, serta penerapan exception handling untuk menangani kesalahan input dan runtime.

---

## Kesimpulan
Tuliskan kesimpulan dari praktikum minggu Praktikum Bab 14 berhasil mengintegrasikan konsep OOP, database, dan GUI ke dalam satu aplikasi yang utuh. Dengan menerapkan arsitektur berlapis, penggunaan Collections, exception handling, design pattern, dan unit testing, aplikasi menjadi lebih terstruktur, mudah dipelihara, dan siap dikembangkan lebih lanjut.

---
