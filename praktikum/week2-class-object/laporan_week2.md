# Laporan Praktikum Minggu 2
Topik: "Class dan Object (Produk Pertanian)"

## Identitas
-  Nama : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]


---

## Tujuan
- Mahasiswa mampu menjelaskan konsep class, object, atribut, dan method dalam OOP.
- Mahasiswa mampu menerapkan access modifier dan enkapsulasi dalam pembuatan class.
- Mahasiswa mampu mengimplementasikan class Produk pertanian dengan atribut dan method yang sesuai.
- Mahasiswa mampu mendemonstrasikan instansiasi object serta menampilkan data produk pertanian di console.
- Mahasiswa mampu menyusun laporan praktikum dengan bukti kode, hasil eksekusi, dan analisis sederhana.

---

## Dasar Teori
Class adalah blueprint atau cetak biru dari sebuah objek. Objek merupakan instansiasi dari class yang berisi atribut (data) dan method (perilaku). Dalam OOP, enkapsulasi dilakukan dengan menyembunyikan data menggunakan access modifier (public, private, protected) serta menyediakan akses melalui getter dan setter.

Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek yang memiliki atribut nama, harga, dan stok. Dengan menggunakan class, setiap produk dapat dibuat, dikelola, dan dimanipulasi secara lebih terstruktur.

---

## Langkah Praktikum
1. Membuat Class Produk

- Buat file Produk.java pada package model.
- Tambahkan atribut: kode, nama, harga, dan stok.
- Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.

2. Membuat Class CreditBy

- Buat file CreditBy.java pada package util.
- Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: credit by: <NIM> - <Nama>.

3. Membuat Objek Produk dan Menampilkan Credit

- Buat file MainProduk.java.
- Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
- Tampilkan informasi produk melalui method getter.
- Panggil CreditBy.print("<NIM>", "<Nama>") di akhir main untuk menampilkan identitas.

4. Commit dan Push

    Commit dengan pesan: week2-class-object.

---

## Kode Program
```java
// produk
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
```
```java
// credit By
public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);
    }
```
```java
// main produk
p1.kurangiStok(25);
        p2.kurangiStok(5);
        p3.tambahStok(10);

        System.out.println("\n=== Setelah Update Stok ===");
        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());
```

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil eksekusi week2](screenshots\week2OOP.png)
)
---

## Analisis
**1. Jelaskan bagaimana kode berjalan.**

Program ini dibuat untuk menampilkan data produk pertanian menggunakan pendekatan berorientasi objek (OOP).
Pertama, program mengimpor dua class lain yaitu Produk dari folder model dan CreditBy dari folder util.
Class Produk berisi data dan fungsi yang menggambarkan satu produk pertanian, seperti kode, nama, harga, dan stok.
Sementara CreditBy digunakan untuk menampilkan identitas pembuat program (nama dan NIM).
Di dalam method main, program membuat tiga objek produk dengan cara memanggil konstruktor new Produk(...).
Masing-masing produk punya informasi yang berbeda — misalnya Benih Padi IR64, Pupuk Urea 50kg, dan Cangkul Baja.
Setelah itu, program menampilkan semua data produk ke layar menggunakan System.out.println.
Selanjutnya, program melakukan perubahan stok:
p1.kurangiStok(25) → mengurangi stok produk pertama sebanyak 25,
p2.kurangiStok(5) → mengurangi stok produk kedua sebanyak 5,
p3.tambahStok(10) → menambah stok produk ketiga sebanyak 10.
Kemudian program mencetak ulang data produk setelah stok diperbarui agar terlihat perbedaannya.
Terakhir, method CreditBy.print("Bunga Maura Aulya", "240320562") dipanggil untuk menampilkan identitas pembuat program sebagai penutup output.

**2. Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.**

Jadi, perbedaan minggu ini dengan minggu sebelumnya itu cukup jelas, minggu lalu masih belajar pengenalan tiga paradigma pemrograman yaitu prosedural, fungsional, dan OOP. Melalui contoh sederhana seperti program “Hello World” dan daftar produk. Tujuannya untuk membrikan pemahaman perbedaan gaya penulisan antar paradigma. Nah, di minggu ini lanjut ke penerapan OOP yang sesungguhnya.
Sudah mulai membuat class terpisah, seperti Produk dan CreditBy, lalu bikin object dari class itu di program utama (MainProduk). Selain itu, juga belajar pakai konstruktor, getter-setter, dan method seperti tambahStok() dan kurangiStok() untuk mengubah data produk. Jadi, kalau minggu lalu OOP-nya masih sekadar teori dan contoh kecil, minggu ini udah benar-benar mempraktikkan OOP untuk mengelola data nyata. Programnya jadi lebih rapi, mudah diatur, dan kelihatan bagaimana konsep “objek” bekerja di dunia nyata.

**3. Kendala yang dihadapi dan cara mengatasinya.**

Saat mengerjakan program minggu ini, saya sempat mengalami kendala karena kesalahan pada penulisan dan penempatan package. Program tidak bisa dijalankan karena Java tidak menemukan class yang sesuai dengan lokasi package yang dideklarasikan. Misalnya, file CreditBy.java sudah ditulis dengan package util;, tetapi ternyata file tersebut tidak berada di folder util sesuai strukturnya. Akibatnya muncul error saat program dijalankan. Untuk mengatasinya, saya memastikan setiap file berada di folder yang sesuai dengan deklarasi package-nya, seperti file Produk.java di folder model dan CreditBy.java di folder util. Selain itu, program juga dijalankan dari root folder project agar Java dapat membaca semua package dengan benar. Setelah memperbaiki hal itu, program akhirnya dapat dijalankan dengan lancar tanpa error.

---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini. 

Dengan menggunakan class dan object pada praktikum minggu ini, program menjadi lebih terstruktur, mudah dipahami, dan efisien dalam pengelolaan data. Setiap objek memiliki atribut dan perilaku sendiri, sehingga perubahan pada satu bagian tidak memengaruhi bagian lainnya. Selain itu, konsep OOP ini membuat program lebih fleksibel dan mudah dikembangkan untuk kebutuhan yang lebih kompleks di masa mendatang.

)

---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
    
    **Jawaban:** Atribut sebaiknya dideklarasikan sebagai private dalam class karena tujuannya untuk melindungi data (data enkapsulasi). Dengan menjadikannya private, atribut hanya bisa diakses dan diubah melalui method khusus (getter dan setter). Hal ini mencegah kode lain secara langsung mengubah data tanpa kendali, yang bisa menyebabkan kesalahan atau inkonsistensi data. Selain itu, cara ini membuat program lebih aman, rapi, dan mudah dipelihara, karena pengembang bisa mengatur bagaimana data diakses dan dimodifikasi tanpa harus mengubah bagian lain dari program.

2. Apa fungsi getter dan setter dalam enkapsulasi?

    **Jawaban:** Fungsi getter dan setter dalam enkapsulasi adalah untuk mengatur akses ke atribut yang bersifat private di dalam class. Getter berfungsi untuk mengambil atau menampilkan nilai dari atribut private. Setter berfungsi untuk mengubah atau menetapkan nilai baru pada atribut tersebut. Dengan cara ini, data di dalam class tetap terlindungi, tapi masih bisa diakses dan diubah secara terkendali. Jadi, getter dan setter membantu menjaga keamanan data sambil tetap memberikan fleksibilitas dalam penggunaannya.

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?

    **Jawaban:** Class Produk bisa mendukung pengembangan aplikasi POS (Point of Sale) yang lebih kompleks karena menjadi pondasi utama untuk mengelola data produk di dalam sistem.Dengan class ini, setiap produk memiliki atribut dan perilaku sendiri, seperti kode, nama, harga, dan stok, serta method untuk menambah atau mengurangi stok. Hal ini membuat pengelolaan data menjadi lebih terstruktur dan mudah dikembangkan Misalnya, nanti saat aplikasi POS dikembangkan lebih lanjut, class Produk bisa dengan mudah diperluas untuk menambahkan fitur seperti kategori produk, diskon, pajak, atau integrasi dengan database dan transaksi penjualan. Jadi, class ini membantu sistem POS menjadi modular, mudah dirawat, dan siap dikembangkan lebih lanjut tanpa harus mengubah struktur dasar program.