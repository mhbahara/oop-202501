# Laporan Praktikum Minggu 7
Topik: [Bab 7 – Collections dan Implementasi Keranjang Belanja]

## Identitas
- Nama  : [Bunga Maura Aulya]
- NIM   : [240320562]
- Kelas : [3DSRA]

---

## Tujuan
Mahasiswa mampu:

1. Menjelaskan konsep collection dalam Java (List, Map, Set).
2. Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3. Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4. Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5. Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Dasar Teori
### 1. Collections Framework

Java Collections Framework menyediakan struktur data untuk mengelola objek secara dinamis dan efisien.

Struktur utama:

- List (implementasi: ArrayList) — Terurut, dapat menyimpan elemen duplikat.
- Map (implementasi: HashMap) — Menyimpan pasangan key–value, akses cepat berdasarkan key.
- Set (implementasi: HashSet) — Tidak menerima duplikat dan tidak mempertahankan urutan.

---

### 2. Studi Kasus: Keranjang Belanja Agri-POS

Keranjang belanja harus dapat:

- Menambahkan produk
- Menghapus produk
- Menampilkan isi keranjang
- Menghitung total nilai transaksi
- Menangani jumlah (quantity) menggunakan Map

Kasus ini mencerminkan penggunaan struktur data dalam aplikasi nyata seperti POS.

---

## Langkah Praktikum
1. Membuat Class Product
2. Implementasi Keranjang dengan ArrayList
3. Main Program (WAJIB DIISI)
4. Implementasi Alternatif Menggunakan Map (Dengan Quantity)
5. Commit message: week7-collections

---

## Kode Program
Kode utama yang dibuat:

```java
package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Bunga Maura Aulya-240320562 (Week7-collections)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);
       
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();
        cart.removeProduct(p1);
        cart.printCart();
    }
}

```

---

## Hasil Eksekusi
Screenshot hasil eksekusi program:  
![Screenshot hasil](/praktikum/week7-koleksi-keranjang/screenshots/hasil%20Collections.png)

---

## Analisis
- Jelaskan bagaimana kode berjalan. 
   Program dimulai dari kelas MainCart yang memiliki method main() sebagai titik awal eksekusi. Pada awal program, sistem menampilkan identitas praktikan sebagai penanda eksekusi program. Selanjutnya, dibuat dua buah objek Product yang merepresentasikan produk dengan atribut kode produk, nama produk, dan harga. Objek-objek ini kemudian dimasukkan ke dalam objek ShoppingCart yang berfungsi sebagai keranjang belanja.
   Kelas ShoppingCart menggunakan struktur Collection (List atau Map) untuk menyimpan kumpulan objek Product. Method addProduct() digunakan untuk menambahkan produk ke dalam keranjang, sedangkan printCart() menampilkan daftar produk yang ada di dalam keranjang. Setelah itu, salah satu produk dihapus menggunakan method removeProduct(). Dengan demikian, program menunjukkan proses manipulasi data koleksi berupa penambahan, penampilan, dan penghapusan objek secara dinamis selama runtime.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
   Pendekatan pada praktikum minggu ini berbeda dengan minggu sebelumnya karena telah menggunakan Java Collections Framework sebagai media penyimpanan data. Pada minggu sebelumnya, data objek umumnya dikelola secara statis atau melalui satuan objek tunggal tanpa pengelolaan kumpulan data yang terstruktur.
   Pada minggu ini, penggunaan Collection seperti ArrayList atau Map memungkinkan program untuk menyimpan banyak objek Product dalam satu struktur data, sehingga lebih fleksibel dan mudah dikembangkan. Pendekatan ini mencerminkan implementasi yang lebih realistis terhadap sistem nyata, seperti keranjang belanja, di mana jumlah data tidak tetap dan dapat berubah sewaktu-waktu.

- Kendala yang dihadapi dan cara mengatasinya.  
   Kendala utama yang dihadapi dalam pengerjaan praktikum ini adalah munculnya error cannot find symbol, yang disebabkan oleh ketidaksesuaian antara deklarasi package, struktur folder, dan penggunaan class antar file. Hal ini membuat compiler Java tidak dapat mengenali class Product dan ShoppingCart saat dipanggil di kelas MainCart.
   Kendala tersebut diatasi dengan menyamakan deklarasi package pada seluruh file Java serta memastikan struktur direktori sesuai dengan package yang digunakan. Selain itu, dilakukan pengecekan ulang terhadap penulisan nama class dan constructor agar sesuai dengan aturan Java. Setelah perbaikan tersebut dilakukan, program dapat dikompilasi dan dijalankan dengan baik tanpa error.
---

## Kesimpulan
Berdasarkan praktikum yang telah dilakukan, penggunaan Java Collections Framework memungkinkan pengelolaan data menjadi lebih terstruktur, fleksibel, dan mudah dikembangkan. Dengan memanfaatkan struktur data seperti List pada implementasi keranjang belanja, program mampu menyimpan, menambah, menampilkan, serta menghapus objek secara dinamis. Pendekatan ini memberikan gambaran penerapan konsep Object-Oriented Programming dalam pengelolaan kumpulan data yang lebih efisien dan mendekati kebutuhan sistem nyata.

---

## Quiz
1. Jelaskan perbedaan mendasar antara List, Map, dan Set.
   
   **Jawaban:** List adalah struktur data yang menyimpan elemen secara berurutan dan mengizinkan adanya duplikasi data. Set merupakan struktur data yang tidak mengizinkan duplikasi elemen sehingga setiap data yang disimpan bersifat unik. Sementara itu, Map menyimpan data dalam bentuk pasangan kunci dan nilai (key–value), di mana setiap kunci harus unik dan digunakan untuk mengakses nilai tertentu. 

2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
   
   **Jawaban:** ArrayList cocok digunakan untuk keranjang belanja sederhana karena mampu menyimpan data secara dinamis dan mempertahankan urutan data sesuai dengan proses penambahan. Selain itu, ArrayList memudahkan proses penambahan dan penghapusan item tanpa perlu menentukan ukuran data di awal, sehingga sesuai dengan kebutuhan keranjang belanja yang jumlah itemnya dapat berubah. 

3. Bagaimana struktur Set mencegah duplikasi data?
   
   **Jawaban:** Struktur Set mencegah duplikasi data dengan melakukan pengecekan terhadap elemen yang akan dimasukkan. Jika elemen tersebut sudah ada di dalam Set, maka elemen baru tidak akan ditambahkan. Mekanisme ini umumnya bergantung pada implementasi method equals() dan hashCode() pada objek yang disimpan.

4. Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.  
   
   **Jawaban:** Map sebaiknya digunakan ketika data perlu diakses menggunakan kunci tertentu, bukan berdasarkan indeks. Contohnya, dalam sistem keranjang belanja, Map dapat digunakan untuk menyimpan data produk dengan kode produk sebagai kunci dan objek produk sebagai nilai. Dengan pendekatan ini, pencarian produk berdasarkan kode menjadi lebih cepat dan efisien dibandingkan menggunakan List.
 
