# Laporan Praktikum Minggu 10
Topik: Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]

---

## Tujuan
Setelah mengikuti praktikum ini, mahasiswa mampu:

1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Dasar Teori
### 1. Design Pattern

Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak. Fokus minggu ini:
- Singleton Pattern
- MVC (Model–View–Controller)

### 2. Singleton Pattern

Tujuan: Menjamin suatu class hanya memiliki satu instance dan menyediakan titik akses global.

Karakteristik:
- Constructor `private`
- Atribut `static instance`
- Method `static getInstance()`

Contoh Implementasi:
```java
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

Penerapan pada Agri-POS: koneksi database atau service global yang tidak boleh lebih dari satu instance.

### 3. MVC (Model–View–Controller)

Memisahkan tanggung jawab aplikasi:

| Komponen | Tanggung Jawab |
|---------|------------------|
| Model   | Data dan logika bisnis |
| View    | Tampilan/output |
| Controller | Penghubung Model dan View |

Contoh Struktur MVC Sederhana:
- Model → `Product`
- View → `ConsoleView`
- Controller → `ProductController`


---

## Langkah Praktikum
1. Implementasikan Singleton untuk `DatabaseConnection`.
2. Buat struktur MVC sederhana untuk fitur Product.
3. Buat minimal 1 unit test JUnit.
4. Jalankan unit test dan dokumentasikan hasilnya.

Commit message:
```
week10-pattern-testing: [fitur] [deskripsi singkat]
```

---

## Kode Program
PRODUCT TEST 

```java
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}
```

APP MVC

```
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}

```


---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week10-pattern-testing/screenshots/hasil%20week%2010.png)

![Screenshot hasil](/praktikum/week10-pattern-testing/screenshots/hasil%20pengujian.png)


---

## Analisis

- Jelaskan bagaimana kode berjalan.

    Pada praktikum minggu ke-10, program dijalankan dengan menerapkan design pattern Singleton, arsitektur Model–View–Controller (MVC), serta unit testing menggunakan JUnit. Singleton Pattern digunakan pada class DatabaseConnection untuk memastikan bahwa hanya ada satu instance koneksi yang digunakan selama aplikasi berjalan. Hal ini dilakukan dengan membuat constructor bersifat private dan menyediakan method getInstance() sebagai satu-satunya cara untuk mengakses instance tersebut. Pada penerapan MVC, class Product berperan sebagai Model yang menyimpan data produk, ConsoleView sebagai View yang bertugas menampilkan output ke layar, dan ProductController sebagai Controller yang menghubungkan Model dan View serta mengatur alur logika program. Pada class AppMVC, objek model, view, dan controller diinisialisasi, kemudian controller memanggil method untuk menampilkan informasi produk. Selain itu, unit testing dilakukan menggunakan JUnit melalui class ProductTest untuk memastikan bahwa method pada class Product berjalan sesuai dengan yang diharapkan, dan hasil pengujian menunjukkan bahwa test berhasil dijalankan tanpa error.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya. 

    Pendekatan yang digunakan pada minggu ini berbeda dengan minggu sebelumnya karena fokus tidak hanya pada pembuatan fitur, tetapi juga pada penerapan pola desain dan pengujian kode. Pada minggu-minggu sebelumnya, implementasi program cenderung langsung dan logika aplikasi sering tercampur dengan tampilan. Sementara itu, pada minggu ke-10, kode disusun lebih terstruktur dengan pemisahan tanggung jawab melalui MVC, penggunaan Singleton untuk pengelolaan instance global, serta penambahan unit testing untuk memastikan kualitas dan keandalan kode. Pendekatan ini membuat program lebih mudah dipelihara, dikembangkan, dan diuji.

- Kendala yang dihadapi dan cara mengatasinya.
    
    Selama praktikum, terdapat beberapa kendala yang dihadapi, seperti Maven dan JUnit yang awalnya tidak terdeteksi karena dependensi belum dikonfigurasi dengan benar pada pom.xml, perbedaan parameter constructor antara class Product dan ProductTest, serta kesalahan penulisan package yang tidak sesuai dengan struktur folder. Kendala-kendala tersebut diatasi dengan menambahkan dependensi JUnit yang sesuai, menyesuaikan pemanggilan constructor pada unit test, dan menyamakan deklarasi package dengan struktur direktori proyek. Setelah perbaikan dilakukan, program berhasil dikompilasi dan unit test berjalan dengan status BUILD SUCCESS.


---

## Kesimpulan
Dengan menerapkan design pattern Singleton, arsitektur Model–View–Controller (MVC), dan unit testing menggunakan JUnit pada praktikum minggu ini, program menjadi lebih terstruktur, modular, dan mudah dipelihara. Penggunaan Singleton memastikan pengelolaan instance global berjalan dengan aman, MVC membantu memisahkan logika bisnis, tampilan, dan pengendali aplikasi, sedangkan unit testing meningkatkan keandalan kode dengan memastikan setiap fungsi berjalan sesuai harapan. Pendekatan ini membantu meningkatkan kualitas perangkat lunak serta mempermudah proses pengembangan dan pengujian di tahap selanjutnya.


---

## Quiz
1. Mengapa constructor pada Singleton harus bersifat private?

    **Jawaban:** Constructor pada Singleton harus bersifat private agar objek dari class tersebut tidak dapat dibuat secara bebas dari luar class. Dengan demikian, pembuatan instance hanya dapat dilakukan melalui method getInstance(), sehingga jumlah instance tetap satu dan tujuan Singleton dapat tercapai.

2. Jelaskan manfaat pemisahan Model, View, dan Controller.

    **Jawaban:** Pemisahan Model, View, dan Controller membuat kode lebih terstruktur dengan membagi tanggung jawab masing-masing komponen. Model menangani data dan logika bisnis, View mengatur tampilan, dan Controller mengelola alur aplikasi. Hal ini memudahkan pemeliharaan, pengembangan, serta pengujian aplikasi.

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?

    **Jawaban:** Unit testing berperan untuk memastikan setiap bagian kecil dari program berjalan sesuai dengan yang diharapkan. Dengan adanya unit testing, kesalahan dapat dideteksi lebih awal, mengurangi bug, serta meningkatkan kepercayaan terhadap kualitas dan kestabilan perangkat lunak.

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?
   **Jawaban:** Jika Singleton tidak diimplementasikan dengan benar, dapat terjadi lebih dari satu instance dalam aplikasi. Hal ini berisiko menimbulkan inkonsistensi data, pemborosan sumber daya, serta perilaku program yang tidak terduga, terutama pada komponen yang seharusnya bersifat global seperti koneksi database.

