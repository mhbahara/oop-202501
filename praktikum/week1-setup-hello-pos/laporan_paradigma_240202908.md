# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Paradigma

## Identitas
- Nama  : Inayah Fijanatin
- NIM   : 240202908
- Kelas : 3IKKA

---

## Tujuan
(Tuliskan tujuan praktikum minggu ini.  
Contoh: *Mahasiswa memahami konsep class dan object serta dapat membuat class Produk dengan enkapsulasi.*)

---

## Dasar Teori
(Tuliskan ringkasan teori singkat (3–5 poin) yang mendasari praktikum.  
Contoh:  
1. Class adalah blueprint dari objek.  
2. Object adalah instansiasi dari class.  
3. Enkapsulasi digunakan untuk menyembunyikan data.)

---

## Langkah Praktikum
(Tuliskan Langkah-langkah dalam prakrikum, contoh:
1. Langkah-langkah yang dilakukan (setup, coding, run).  
2. File/kode yang dibuat.  
3. Commit message yang digunakan.)

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
// Contoh
Produk p1 = new Produk("BNH-001", "Benih Padi", 25000, 100);
System.out.println(p1.getNama());
```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
- Kendala yang dihadapi dan cara mengatasinya.  
)
---

## Kesimpulan
(Tuliskan kesimpulan dari praktikum minggu ini.  
Contoh: *Dengan menggunakan class dan object, program menjadi lebih terstruktur dan mudah dikembangkan.*)

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
