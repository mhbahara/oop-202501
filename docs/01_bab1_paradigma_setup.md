# Bab 1 – Pengenalan Paradigma dan Setup Proyek

## Tujuan Pembelajaran
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).

---

## Ringkasan Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:  
- **Prosedural**: program dibangun sebagai rangkaian perintah (fungsi/prosedur).  
- **OOP (Object-Oriented Programming)**: program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).  
- **Fungsional**: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data.  

Dalam konteks Agri-POS, OOP membantu memodelkan entitas nyata seperti Produk, Transaksi, dan Pembayaran sebagai objek. Dengan demikian, sistem lebih mudah dikembangkan dan dipelihara.  

---

## Langkah Praktikum
1. **Setup Project**
   - Pastikan sudah menginstall **JDK** (Java Development Kit), **IDE** (misal: IntelliJ IDEA, VS Code, NetBeans), **Git**, **PostgreSQL**, dan **JavaFX** di komputer.
   - Buat folder project `oop-pos-<nim>`.
   - Inisialisasi repositori Git.
   - Buat struktur awal `src/main/java/com/upb/agripos/`.
   - Pastikan semua tools dapat berjalan (uji dengan membuat dan menjalankan program Java sederhana).

2. **Program Sederhana dalam 3 Paradigma**
   - Prosedural: program untuk menghitung total harga dua produk.
   - OOP: class `Produk` dengan atribut nama dan harga, buat minimal tiga objek, lalu hitung total.  
   - Fungsional: gunakan `Stream` atau lambda untuk menghitung total harga dari minimal tiga objek.  

3. **Commit dan Push**
   - Commit dengan pesan: `week1-setup-hello-pos`.  

---

## Struktur Repositori
```
oop-20251-<nim>/
 ├─ README.md                  # Tidak perlu diubah
 ├─ docs/                      # Tidak perlu diubah
 │   ├─ 00_pendahuluan.md
 │   ├─ 01_bab1_paradigma_setup.md
 │   └─ ...
 ├─ praktikum/
 │   ├─ week1-setup-hello-pos/ # Folder tugas minggu 1, WAJIB DIISI
 │   │   ├─ src/main/java/com/upb/agripos/
 │   │   │   ├─ HelloProcedural.java        # Diisi kode paradigma prosedural
 │   │   │   ├─ HelloOOP.java               # Diisi kode paradigma OOP
 │   │   │   └─ HelloFunctional.java        # Diisi kode paradigma fungsional
 │   │   ├─ screenshots/
 │   │   │   └─ hasil.png                   # Diisi screenshot hasil eksekusi
 │   │   └─ laporan_week1.md                # Diisi laporan singkat
 │   ├─ week2-class-object/
 │   └─ ...
 │   └─ week16-uas/
 ├─ sql/
 │   ├─ schema.sql
 │   └─ seed.sql
 └─ src/
    ├─ main/java/com/upb/agripos/
    └─ test/java/com/upb/agripos/
```

---

## Tugas dan Latihan
### Tugas dan Latihan

1. **Buat program "Hello World, I am [nama]-[nim]" dalam 3 paradigma:**
   - Paradigma prosedural
   - Paradigma OOP
   - Paradigma fungsional

2. **Jelaskan kelebihan dan kekurangan tiap pendekatan** pada laporan singkat.

3. **Upload ke Git** masing-masing.

**Ketentuan Pengumpulan:**
- Sertakan kode program pada folder `praktikum/week1-setup-hello-pos/src/main/java/com/upb/agripos/`.
- Sertakan screenshot hasil eksekusi dari ketiga versi program pada folder `praktikum/week1-setup-hello-pos/screenshots/`.
- Commit log sesuai instruksi.
- Laporan singkat (`laporan_week1.md`) pada folder `praktikum/week1-setup-hello-pos/`.
---

## Contoh Implementasi Program

### 1. Prosedural
```java
// HelloProcedural.java
public class HelloProcedural {
   public static void main(String[] args) {
      String nim = "2310112345";
      String nama = "Budi";
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
```

### 2. OOP
```java
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
      String nim = "2310112345";
      String namaMhs = "Budi";
      Produk[] daftar = {
         new Produk("Beras", 10000),
         new Produk("Pupuk", 15000),
         new Produk("Benih", 12000)
      };
      int total = 0;
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + namaMhs);
      System.out.println("Daftar Produk:");
      for (Produk p : daftar) {
         System.out.println("- " + p.nama + ": " + p.harga);
         total += p.harga;
      }
      System.out.println("Total harga semua produk: " + total);
   }
}
```

### 3. Fungsional
```java
// HelloFunctional.java
import java.util.*;
import java.util.stream.*;
public class HelloFunctional {
   public static void main(String[] args) {
      String nim = "2310112345";
      String nama = "Budi";
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
```

---

## Checklist Keberhasilan
- [ ] Lingkungan kerja sudah siap (JDK, IDE, Git, PostgreSQL, JavaFX).  
- [ ] Repositori Git sudah dibuat dan commit awal berhasil (`week1-setup-hello-pos`).  
- [ ] Program "Hello POS World" berjalan di tiga paradigma dan menampilkan NIM serta nama mahasiswa.  
- [ ] Versi OOP dan fungsional menggunakan minimal tiga objek/entri produk.  
- [ ] Tangkapan layar hasil eksekusi ketiga program telah disertakan.  
- [ ] Laporan singkat telah dilampirkan.  
- [ ] Perbedaan paradigma sudah dipahami dan dijelaskan pada laporan.  

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?
   **Jawaban:** …

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?
   **Jawaban:** …

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?
   **Jawaban:** …

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   **Jawaban:** …

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (*boilerplate code*)?
   **Jawaban:** …

---

## Referensi
- Liang, Y. D. *Introduction to Java Programming* (Bab 1).  
- Horstmann, C. S. *Core Java Volume I – Fundamentals*.  
- Oracle Docs: [Java SE Documentation](https://docs.oracle.com/javase/).  
