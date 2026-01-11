# Laporan Praktikum Minggu 11
Topik: Data Access Object (DAO) dan CRUD Database dengan JDBC

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]
---

## Tujuan
1. Menjelaskan konsep Data Access Object (DAO) dalam pengembangan aplikasi OOP.
2. Menghubungkan aplikasi Java dengan basis data menggunakan JDBC.
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi OOP sesuai prinsip desain yang baik.

---

## Dasar Teori
### 1. Konsep Data Access Object (DAO)

DAO adalah pola desain yang memisahkan logika akses data dari logika bisnis aplikasi. Dengan DAO, perubahan teknologi basis data tidak memengaruhi logika utama aplikasi.

Manfaat DAO:
- Kode lebih terstruktur dan mudah dipelihara
- Mengurangi tight coupling antara aplikasi dan database
- Mendukung pengujian dan pengembangan lanjutan

---

### 2. JDBC dan Koneksi Database

JDBC (Java Database Connectivity) digunakan untuk menghubungkan aplikasi Java dengan basis data relasional, dalam praktikum ini menggunakan PostgreSQL.

Komponen utama JDBC:
- DriverManager
- Connection
- PreparedStatement
- ResultSet


---

## Langkah Praktikum
1. Buat database & tabel
2	Rapikan struktur folder
3	Buat Product (Model)
4	Buat ProductDAO (Interface)
5	Buat ProductDAOImpl (JDBC)
6	Integrasi di MainDAOTest
7	Run & screenshot
8	Tulis laporan
9	Commit & push

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) {
        try {
            System.out.println("====================================");
            System.out.println("MENGHUBUNGKAN KE DATABASE...");
            System.out.println("====================================");

            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos",
                "postgres",
                "postgres"
            );

            System.out.println("KONEKSI DATABASE BERHASIL");
            System.out.println("Database : agripos");
            System.out.println("====================================\n");

            ProductDAO dao = new ProductDAOImpl(conn);

            // INSERT
            System.out.println("[INSERT]");
            dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
            System.out.println("Produk berhasil ditambahkan\n");

            // UPDATE
            System.out.println("[UPDATE]");
            dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));
            System.out.println("Produk berhasil diperbarui\n");

            // READ
            System.out.println("[READ]");
            Product p = dao.findByCode("P01");
            if (p != null) {
                System.out.println("Kode  : " + p.getCode());
                System.out.println("Nama  : " + p.getName());
                System.out.println("Harga : " + p.getPrice());
                System.out.println("Stok  : " + p.getStock());
            }

            // DELETE
            System.out.println("\n[DELETE]");
            dao.delete("P01");
            System.out.println("Produk berhasil dihapus\n");

            conn.close();

            System.out.println("====================================");
            System.out.println("PROGRAM SELESAI");
            System.out.println("====================================");

        } catch (Exception e) {
            System.out.println("KONEKSI DATABASE GAGAL");
            e.printStackTrace();
        }
    }
}
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week11-dao-database/screenshots/output%20dao%20database.png)

![Screenshot hasil](/praktikum/week11-dao-database/screenshots/hasil%20test.png)
)
---

## Analisis

- Jelaskan bagaimana kode berjalan. 

    Kode pada praktikum ini berjalan dengan menerapkan pola DAO (Data Access Object) untuk mengelola interaksi antara aplikasi Java dan database PostgreSQL. Program dimulai dari kelas MainDAOTest yang berfungsi sebagai penguji utama. Pada awal eksekusi, aplikasi mencoba membangun koneksi ke database menggunakan DriverManager dengan URL, username, dan password yang telah ditentukan. Setelah koneksi berhasil, objek ProductDAOImpl dibuat untuk mengakses data produk. Melalui objek DAO ini, program melakukan operasi CRUD (Create, Read, Update, Delete), yaitu menambahkan data produk ke database, memperbarui data produk, mengambil data berdasarkan kode produk, dan menghapus data tersebut. Seluruh proses ini dijalankan secara berurutan, dan koneksi database ditutup setelah semua operasi selesai.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.

    Pendekatan pada minggu ini berbeda dengan minggu sebelumnya karena pada praktikum ini aplikasi sudah terhubung langsung dengan database nyata menggunakan JDBC dan pola DAO. Pada minggu sebelumnya, pengolahan data masih bersifat sederhana dan umumnya menggunakan objek atau koleksi di dalam program tanpa pemisahan lapisan akses data. Dengan DAO, logika bisnis dan logika akses database dipisahkan ke dalam kelas yang berbeda, sehingga kode menjadi lebih terstruktur, mudah dipelihara, dan lebih siap dikembangkan untuk skala aplikasi yang lebih besar.
    
- Kendala yang dihadapi dan cara mengatasinya.  

    Kendala utama yang dihadapi adalah kegagalan koneksi ke database, seperti error No suitable driver found dan password authentication failed. Masalah ini terjadi karena dependensi driver PostgreSQL belum tersedia di classpath dan karena ketidaksesuaian username atau password database. Kendala tersebut diatasi dengan menambahkan dependency PostgreSQL JDBC pada file pom.xml, menjalankan proyek menggunakan Maven agar dependensi terunduh dengan benar, serta memastikan username dan password yang digunakan di kode Java sesuai dengan konfigurasi PostgreSQL. Setelah permasalahan tersebut diperbaiki, aplikasi berhasil terhubung ke database dan seluruh operasi DAO dapat dijalankan dengan baik.
---

## Kesimpulan
Dengan menerapkan pola DAO (Data Access Object) dan koneksi database menggunakan JDBC, pengelolaan data dalam aplikasi menjadi lebih terstruktur, terpisah dari logika bisnis, serta lebih mudah dipelihara dan dikembangkan. Praktikum ini menunjukkan bahwa penggunaan DAO membantu meningkatkan kerapian kode, memudahkan proses CRUD ke database, dan membuat aplikasi lebih siap untuk digunakan pada sistem berskala lebih besar.
