# Laporan Praktikum Minggu 9
Topik: [Bab 9 – Exception Handling, Custom Exception, dan Penerapan Design Pattern]

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]

---

## Tujuan
1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
5. (Opsional) Menerapkan design pattern sederhana (Singleton/MVC) dan unit testing dasar.

---

## Dasar Teori
### 1. Error vs Exception

- Error → kondisi fatal, tidak dapat ditangani (contoh: OutOfMemoryError).
- Exception → kondisi tidak normal yang dapat ditangani oleh program.

### 2. Struktur try–catch–finally

```java
try {
    // kode yang berpotensi menimbulkan kesalahan
} catch (Exception e) {
    // penanganan
} finally {
    // blok yang selalu dijalankan
}
```

### 3. Membuat Custom Exception

```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
```

---

## Studi Kasus Agri-POS: Keranjang Belanja

Keranjang belanja harus memvalidasi:

- Jumlah pembelian > 0
- Produk ada dalam keranjang
- Stok mencukupi

Kesalahan–kesalahan tersebut ditangani menggunakan custom exception.

---

## Langkah Praktikum
1. Membuat Custom Exception
2. Model Product dengan Stok
3. Implementasi ShoppingCart dengan Exception Handling
4. Main Program untuk Menguji Exception Handling
5. Commit (message yang digunakan: week9-exception)

---

## Kode Program
Tuliskan kode utama yang dibuat:

```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Bunga Maura Aulya]-[240320562] (Week9-exception)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}

```

---

## Hasil Eksekusi
Screenshot hasil eksekusi program:  
![Screenshot hasil](/praktikum/week9-exception-handling/screenshots/hasilWeek9-exception.png)

---

## Analisis
- Jelaskan bagaimana kode berjalan. 
    
    Program dijalankan melalui kelas MainExceptionDemo yang berfungsi sebagai kelas utama. Pada awal eksekusi, program menampilkan identitas praktikan sebagai penanda awal program. Selanjutnya, dibuat sebuah objek ShoppingCart sebagai keranjang belanja dan sebuah objek Product yang merepresentasikan produk dengan atribut kode, nama, harga, dan stok.    
    Program kemudian menguji beberapa skenario kesalahan menggunakan blok try–catch. Pada skenario pertama, method addProduct() dipanggil dengan jumlah negatif sehingga memicu InvalidQuantityException. Exception tersebut ditangkap dan pesan kesalahan ditampilkan ke layar. Pada skenario kedua, method removeProduct() dipanggil ketika produk belum ada di dalam keranjang, sehingga memicu ProductNotFoundException. Selanjutnya, pada skenario terakhir, produk ditambahkan dengan jumlah melebihi stok dan proses checkout() dijalankan, yang menyebabkan exception terkait stok tidak mencukupi. Dengan alur ini, program menunjukkan bagaimana custom exception digunakan untuk memvalidasi kondisi keranjang belanja secara aman dan terkontrol.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya. 

    Pendekatan pada praktikum minggu ini berfokus pada Exception Handling dengan menggunakan custom exception untuk menangani kondisi kesalahan secara eksplisit. Pada minggu sebelumnya, validasi data masih mengandalkan alur logika biasa seperti pengecekan kondisi (if–else) tanpa mekanisme penanganan kesalahan yang terstruktur.    
    Dengan penggunaan custom exception, kesalahan tidak hanya terdeteksi tetapi juga dipisahkan dari alur bisnis utama program. Hal ini membuat kode menjadi lebih rapi, mudah dipelihara, dan lebih jelas dalam mendefinisikan jenis kesalahan yang mungkin terjadi. Pendekatan ini juga meningkatkan keandalan program karena kesalahan dapat ditangani tanpa menghentikan keseluruhan proses aplikasi.

- Kendala yang dihadapi dan cara mengatasinya.  
    Kendala yang dihadapi dalam praktikum ini adalah menentukan kondisi validasi yang tepat dan memastikan exception dilempar pada method yang sesuai, seperti tambahProduk, hapusProduk, dan checkout. Selain itu, muncul kesalahan ketika exception belum didefinisikan atau belum ditangkap dengan benar sehingga menyebabkan program berhenti secara tiba-tiba.
    Kendala tersebut diatasi dengan membuat custom exception secara terpisah, menyesuaikan pesan kesalahan agar lebih informatif, serta menerapkan blok try–catch pada kelas utama. Dengan cara ini, setiap kesalahan dapat ditangani dengan baik dan program tetap berjalan sesuai dengan alur yang diharapkan.

---

## Kesimpulan
Berdasarkan praktikum yang telah dilakukan, penerapan custom exception pada sistem keranjang belanja memungkinkan program melakukan validasi kesalahan secara terstruktur dan terkontrol. Dengan memanfaatkan mekanisme try–catch, kesalahan seperti jumlah produk tidak valid, produk tidak ditemukan, dan stok tidak mencukupi dapat ditangani dengan baik tanpa menghentikan jalannya program. Pendekatan ini membuat kode menjadi lebih aman, mudah dipelihara, serta meningkatkan kejelasan alur logika dalam pengembangan aplikasi berbasis Object-Oriented Programming.


---

## Quiz
1. Jelaskan perbedaan error dan exception.

   **Jawaban:** Error merupakan kesalahan serius yang umumnya terjadi di luar kendali program dan sulit atau tidak dapat ditangani, seperti kehabisan memori. Exception adalah kesalahan yang terjadi saat program berjalan dan masih dapat ditangani menggunakan mekanisme try–catch.

2. Apa fungsi finally dalam blok try–catch–finally?

   **Jawaban:** Blok finally digunakan untuk mengeksekusi kode yang harus selalu dijalankan, baik terjadi exception maupun tidak, seperti menutup resource atau membersihkan data sementara.
   
3. Mengapa custom exception diperlukan?

   **Jawaban:** Custom exception diperlukan untuk menangani kesalahan yang spesifik sesuai kebutuhan aplikasi, sehingga pesan kesalahan lebih jelas, logika program lebih terstruktur, dan proses debugging menjadi lebih mudah.
   
4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.

   **Jawaban:** Contoh kasusnya adalah ketika jumlah barang yang dibeli melebihi stok yang tersedia. Dalam kondisi ini, custom exception digunakan untuk memberikan peringatan bahwa stok tidak mencukupi tanpa menghentikan proses aplikasi.
   
