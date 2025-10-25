# Laporan Praktikum Minggu 1 
Topik: Pengenalan paradigma pemrograman: prosedural, OOP , functional , konsep dasar dan class

## Identitas
- Nama  : [Wisnu Wibowo Saputro]
- NIM   : [240320565]
- Kelas : [3DSRA]

---

## Tujuan
Setelah mempelajari materi ini, mahasisawa akan mengerti berbagai cara atau gaya dalam menulis program komputer, yaitu paradigma prosedural, berorientasi objek (OOP), dan fungsional. mahasisawa juga akan memahami konsep dasar seperti apa itu class dan bagaimana class digunakan untuk membuat program yang lebih terstruktur dan mudah dipahami. Dengan begitu, mahasiawa bisa memilih cara yang tepat dalam membuat program sesuai kebutuhan dan mampu membuat program sederhana menggunakan konsep-konsep tersebut.
---

## Dasar Teori
1. Paradigma Prosedural
Paradigma ini menekankan penulisan program sebagai urutan langkah atau prosedur yang dijalankan secara berurutan untuk menyelesaikan masalah.
2. Paradigma Berorientasi Objek (OOP)
Paradigma OOP menggunakan class dan objek untuk menggabungkan data dan fungsi dalam satu kesatuan, sehingga program menjadi lebih terstruktur dan mudah dikembangkan.
3. Paradigma Fungsional
Paradigma ini fokus pada penggunaan fungsi murni yang tidak mengubah data dan menghindari efek samping, sehingga program menjadi lebih mudah diuji dan diprediksi.

---

## Langkah Praktikum
1. Langkah-langkah yang dilakukan (setup, coding, run).  
2. File/kode yang dibuat.  
3. Commit message yang digunakan.

---

## Kode Program

a. Paradigma Prosedural :

package main.java;

public class HelloProcedural {
   public static void main(String[] args) {
      String nim = "240320565";
      String nama = "wisnu wibowo saputr0 ";
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

b. Paradigma OOP :

package main.java;
// Class Produk
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
        String nim = "240320565";
        String namaMhs = "Wisnu Wibowo Saputro";

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


c. Paradigma Fungsional :

package main.java;

import java.util.*;
import java.util.stream.*;
public class HelloFunctional {
   public static void main(String[] args) {
      String nim = "240320565";
      String nama = "Wisnu Wibowo Saputro";
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

---

## Hasil Eksekusi

a. Paradigma Prosedural :
https://github.com/yusrilhidayah1933-commits/oop-202501-240320565/blob/main/praktikum/week1-setup-hello-pos/screenshots/Screenshot%202025-10-13%20201845-HelloProcedural.png

b. paradigma OOP :
https://github.com/yusrilhidayah1933-commits/oop-202501-240320565/blob/main/praktikum/week1-setup-hello-pos/screenshots/Screenshot%202025-10-13%20211151-HelloOOP.png

c. paradigma fungsional :
https://github.com/yusrilhidayah1933-commits/oop-202501-240320565/blob/main/praktikum/week1-setup-hello-pos/screenshots/Screenshot%202025-10-13%20201734-HelloFunctional.png

---

## Analisis

a. Paradigma Prosedural
    Di sini cara berpikirnya langkah demi langkah. Kita bikin fungsi hello(), lalu di dalamnya ada perintah cetak. Terakhir, kita panggil fungsi itu.
    Paradigma prosedural intinya adalah instruksi dijalankan satu per satu secara urut.
b. Paradigma OOP
    Di sini kita pakai konsep kelas (class) dan objek (object).
    class Hello itu seperti cetakan.
    __init__ digunakan untuk menyimpan data (nama dan NIM).
    say_hello() adalah perilaku (method) yang dimiliki objek.
    program = Hello("Wisnu", "240320565") artinya kita bikin objek dari cetakan tadi.
    Terakhir, program.say_hello() memanggil fungsi yang ada di dalam objek.
    Paradigma OOP menekankan pada objek yang punya data dan fungsi sendiri.
c.Paradigma Fungsional
  Di sini kita nggak pakai class atau prosedur panjang.
  Kita langsung bikin fungsi anonim (lambda) yang bisa dipakai sekali jalan. Jadi pendekatannya adalah fokus ke fungsi murni tanpa menyimpan state atau bikin objek.
---

## Kesimpulan

paradigma fungsional tidak menggunakan class atau prosedur panjang, melainkan cukup dengan fungsi anonim (lambda) yang dapat dipakai langsung sehingga lebih ringkas dan sederhana. Paradigma ini cocok digunakan ketika membutuhkan kode singkat untuk operasi yang tidak kompleks, seperti perhitungan matematis atau pemanggilan cepat. Namun, untuk program yang lebih besar dan rumit, disarankan memakai paradigma prosedural atau OOP agar kode lebih terstruktur, mudah dipelihara, dan bisa dikembangkan lebih lanjut.
---

## Quiz
 1. Apakah OOP selalu lebih baik dari prosedural?   
   **Jawaban:OOP tidak selalu lebih baik dari prosedural. OOP bagus untuk aplikasi besar yang butuh struktur jelas, sedangkan prosedural lebih cepat dan sederhana untuk program kecil.** …  

2. Kapan functional programming lebih cocok digunakan
 dibanding OOP atau prosedural? 
   **Jawaban:Functional programming lebih cocok dipakai saat butuh parallel processing, pengolahan data besar, atau ketika ingin meminimalisir state (contoh: big data, AI/ML).** …  

3. Bagaimana paradigma (prosedural, OOP, fungsional) 
memengaruhi maintainability dan scalability aplikasi? 
   **Jawaban:Paradigma pemrograman sangat memengaruhi maintainability dan scalability aplikasi; paradigma prosedural bersifat sederhana dan cocok untuk program kecil, tetapi akan sulit dipelihara jika program semakin besar, sedangkan paradigma berorientasi objek (OOP) lebih mudah dikembangkan serta dipelihara sehingga sesuai untuk aplikasi yang kompleks, sementara paradigma fungsional cenderung menghasilkan kode dengan minim bug, mudah dijalankan secara paralel, dan sangat cocok digunakan untuk aplikasi berskala besar.**

4. MengapaOOP lebih cocok untuk mengembangkan
 aplikasi POS dibanding prosedural?
    **Jawaban:OOP lebih cocok untuk aplikasi POS karena bisa merepresentasikan objek nyata seperti produk, kasir, transaksi, dan laporan dengan lebih jelas.**

5. Bagaimana paradigma fungsional dapat membantu
 mengurangi kode berulang (boilerplate code)?
  **Jawaban:Paradigma fungsional mengurangi kode berulang dengan fungsi reusable, higher-order function, dan fitur seperti map/filter/reduce.**
