# Laporan Praktikum Minggu 1 (Setup-Hello-pos)
Topik: Hello POS

## Identitas
- Nama  : Inayah Fijanatin
- NIM   : 240202908
- Kelas : 3IKKA

---

## Tujuan
1. Mahasiswa mampu memahami cara kerja program berbasis prosedur
2. Mahasiswa mampu melatih penggunaan array,loop, variabel sederhana tanpa objek
3. Mahasiswa mampu meunjukan bagaimana logika disusun secara linear
4. Mahasiswa mampu mengetahui bagaimana operasi pada data bisa dilakukan secara deklaratif dan lebih ringkas
5. Mahasiswa mampu mengetahui bagaimana struktur kode menjadi lebih terorganisir dan mudah dikembangkan.

---

## Dasar Teori
(Tuliskan ringkasan teori singkat (3–5 poin) yang mendasari praktikum.  
1. Paradigma Pemrograman adalah gaya atau pendekatan dalam menulis dan mengorganisasi kode program. Tiga paradigma umum adalah prosedural, fungsional, dan berorientasi objek (OOP).
2. Pemrograman Prosedural berfokus pada urutan langkah atau prosedur untuk menyelesaikan masalah. Kode disusun secara linear menggunakan variabel, array, dan perulangan.
3. Pemrograman Fungsional menekankan pada penggunaan fungsi murni dan ekspresi deklaratif, seperti stream dan lambda expression, untuk memproses data tanpa mengubah keadaan (state) secara langsung.
4. Pemrograman Berorientasi Objek (OOP) berfokus pada konsep class dan object. Class adalah cetak biru (blueprint), sedangkan object adalah instans nyata dari class yang memiliki atribut dan perilaku.
5. Konsep OOP seperti enkapsulasi, abstraksi, dan modularitas membantu membuat program lebih terstruktur, mudah dikelola, serta mendukung pengembangan berkelanjutan.

---

## Langkah Praktikum
1. Persiapan Lingkungan
   - Pastikan sudah menginstal Java Development Kit (JDK) dan Visual Studio Code.
   - Buat folder proyek, misalnya oop-202501-240820908, kemudian buat subfolder src/main/java/.
   - Buka folder proyek tersebut di VS Code.
2. Membuat Program Versi Prosedural
   - Buat file baru bernama HelloProcedural.java.
   - Tulis kode program menggunakan struktur prosedural, yaitu:
   - Deklarasi variabel nim, nama, produk[], dan harga[].
   - Gunakan loop for untuk menampilkan daftar produk dan menghitung total harga.
   - Jalankan program untuk memastikan output sesuai.
3. Membuat Program Versi Fungsional
   - Buat file baru bernama HelloFunctional.java.
   - Gunakan fitur Java Stream API dan lambda expression untuk menggantikan loop manual.
   - Gunakan List<String> untuk daftar produk dan List<Integer> untuk harga.
   - Gunakan IntStream.range() dan forEach() untuk iterasi data.
   - Hitung total harga dengan stream().mapToInt().sum().
   - Jalankan dan amati hasil yang sama seperti versi prosedural, namun dengan gaya kode lebih ringkas.
4. Membuat Program Versi OOP
   - Buat file baru bernama HelloOOP.java.
   - Buat class Produk dengan atribut nama dan harga, serta konstruktor untuk inisialisasi.
   - Di dalam main(), buat array berisi beberapa objek Produk.
   - Gunakan loop untuk menampilkan produk dan menghitung total harga semua produk.
   - Jalankan program dan pastikan output sama seperti dua versi sebelumnya.
5. Menganalisis Perbandingan
   - Bandingkan ketiga pendekatan berdasarkan:
   - Struktur kode
   - Kemudahan dibaca dan dikembangkan
   - Fleksibilitas untuk penambahan fitur
   - Catat kelebihan dan kekurangannya untuk laporan praktikum.
6. Menyusun Laporan Praktikum
   - Tuliskan bagian: Tujuan Praktikum, Dasar Teori, Langkah-Langkah Praktikum, Hasil & Pembahasan (sertakan tangkapan layar output, Kesimpulan

## Kode Program
**HelloProcedural.java**
// HelloProcedural.java
public class HelloProcedural {
    public static void main(String[] args) {
        String nim = "240202908";
        String nama = "Inayah Fijanatin";
        String[] produk = {"Beras", "Pupuk", "Benih"};
        int[] harga = {10000, 15000, 12000};
        int total = 0;

        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + nama);
        System.out.println("Daftar Produk:");
        for (int i = 0; i < produk.length; i++) {
            System.out.println("- " + produk[i] + ": " + harga[i]);
            total += harga[i];
        }
        System.out.println("Total harga semua produk: " + total);
    }
}

**HelloFunctional.java**
// HelloFunctional.java
import java.util.*;
import java.util.stream.*;

public class HelloFunctional {
    public static void main(String[] args) {
        String nim = "240202908";
        String nama = "Inayah Fijanatin";
        List<String> produk = Arrays.asList("Beras", "Pupuk", "Benih");
        List<Integer> harga = Arrays.asList(10000, 15000, 12000);

        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + nama);
        System.out.println("Daftar Produk:");

        IntStream.range(0, produk.size())
                 .forEach(i -> System.out.println("- " + produk.get(i) + ": " + harga.get(i)));

        int total = harga.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total harga semua produk: " + total);
    }
}

**HelloOOP.java**
// HelloOOP.java
class Produk {
    String nama;
    int harga;

    Produk(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }
}

public class HelloOOP {
    public static void main(String[] args) {
        String nim = "240202908";
        String namaMhs = "Inayah Fijanatin";

        Produk[] daftar = {
            new Produk("Beras", 10000),
            new Produk("Pupuk", 15000),
            new Produk("Benih", 12000)
        };

        System.out.println("Hello POS World");
        System.out.println("NIM: " + nim + ", Nama: " + namaMhs);
        System.out.println("Daftar Produk:");

        int total = 0;
        for (Produk p : daftar) {
            System.out.println("- " + p.nama + ": " + p.harga);
            total += p.harga;
        }

        System.out.println("Total harga semua produk: " + total);
    }
}




## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.
  Ketiga program menampilkan data produk dan menghitung total harga, namun dengan pendekatan berbeda.
  Prosedural: Semua logika ada di main(), berjalan berurutan.
  Fungsional: Menggunakan Stream dan lambda agar lebih ringkas dan deklaratif.
  OOP: Memakai class Produk untuk memisahkan data dan perilaku, lebih terstruktur.
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya...
  Perbedaan minggu ini adalah fokus pada praktikum perbandingan paradigma (prosedural, fungsional, OOP), sedangkan minggu
  lalu hanya pembahasan materi secara konsep saja
- Kendala yang dihadapi dan cara mengatasinya.
  hanya masih bingung untuk mengawali praktikum walaupun sudah disediakan langkah-langkahnya
)
---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini.  
Jadi, dari praktikum ini dapat disimpulkan bahwa satu masalah dapat diselesaikan dengan berbagai paradigma pemrograman. Prosedural mudah dipahami untuk program kecil.Fungsional membuat kode lebih ringkas dan efisien untuk pengolahan data.OOP memberikan struktur yang lebih rapi dan mudah dikembangkan. Ketiganya saling melengkapi, dan pemilihan paradigma tergantung pada kebutuhan serta kompleksitas aplikasi yang dibuat.

---

## Quiz
(1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban:** OOP tidak selalu lebih baik dari procedural karena keduanya itu punya kekurangan dan kelebihan masing masing tentunya, dan tergantung kebutuhan penggunanya. Ketika yang dibutuhkan dengan kondisi program sederhana, lebih kecil, dan langsung jalan maka yang diperlukan adalah prosdural. akan tetapi jika yang dibutuhkan adalah kondisi yang membutuhkan program besar, kompleks, butuh pengembangan jangka Panjang maka yang digunakan adalah OOP.  

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?  
   **Jawaban:** perlu diketahui terlebih dahulu bahwasanya functional tidak mengubah data asli, yang artinya functional tidak bergantung pada kondisi luar, tidak mengubah variable di luar dirinya, dan selalu meberikan hasil yang sama untuk inputan yang sama. Oleh karena itu functional programming lebih cocok  dibanding OOP atau procedural digunakan pada saat kondisi pemrosesan data dalam jumlah besar, kebutuhan paralelisme atau komputasi serentak, Perhitungan matematis atau ilmiah, Aplikasi dengan logika transformasi data kompleks.

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi? 
   **Jawaban:** Prosedural: Cocok untuk program kecil. Tapi kalau program makin besar, sulit dirawat (maintainability rendah) dan susah dikembangkan (scalability rendah), karena semua fungsi saling bergantung.

OOP (Object-Oriented):Lebih mudah dirawat dan dikembangkan. Setiap bagian program (kelas/objek) terpisah, jadi perubahan bisa dilakukan tanpa merusak bagian lain. Cocok untuk aplikasi besar dan tim besar.

Fungsional (Functional):Kode lebih bersih, mudah diuji, dan stabil karena tidak ada data yang diubah sembarangan. Sangat baik untuk sistem besar atau pemrosesan data paralel, tapi kurang cocok untuk aplikasi yang banyak perubahan state seperti UI.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?  
   **Jawaban:** Karena aplikasi POS (Point of Sale) punya banyak komponen berbeda seperti produk, pelanggan, kasir, dan transaksi yang saling berhubungan, maka OOP lebih cocok. Dengan OOP, setiap komponen bisa dibuat sebagai objek terpisah (misalnya Produk, Transaksi, Kasir) yang memiliki data dan fungsi sendiri, sehingga: Kode lebih terstruktur dan mudah dikembangkan, Fitur baru bisa ditambah tanpa merusak kode lama,Lebih mudah dirawat dan diuji dibanding pendekatan prosedural yang cepat jadi rumit saat program membesar.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?
   **Jawaban:** Paradigma fungsional membantu mengurangi kode berulang (boilerplate) karena menggunakan fungsi murni dan fungsi tingkat tinggi seperti map, filter, dan reduce. Dengan cara ini, penguna bisa menulis logika umum sekali saja, lalu memanggilnya berulang kali untuk berbagai data tanpa menyalin kode. Selain itu, karena tidak ada perubahan state, kode jadi lebih ringkas, konsisten, dan mudah dibaca.
