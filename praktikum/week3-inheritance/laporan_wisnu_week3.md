# Laporan Praktikum Minggu 3
Topik: Penerapan Konsep Inheritance dalam Sistem Produk Pertanian (Benih, Pupuk, dan Alat Pertanian)

## Identitas
- Nama  : [WISNU WIBOWO SAPUTRO]
- NIM   : [240320565]
- Kelas : [3DSRA]

---

## Tujuan

1. Memahami konsep pewarisan (inheritance) dalam pemrograman berorientasi objek (OOP).

2. Menerapkan inheritance untuk mengelompokkan objek-objek yang memiliki atribut dan method serupa.

3. Mengimplementasikan method turunan (override) dan pemanggilan method dari superclass.

---

## Dasar Teori

Topik praktikum ini membahas konsep Inheritance (pewarisan kelas) dalam Pemrograman Berorientasi Objek (Object-Oriented Programming / OOP).
Inheritance merupakan mekanisme yang memungkinkan sebuah kelas baru (disebut subclass atau child class) untuk mewarisi atribut dan method dari kelas lain (disebut superclass atau parent class).
Dengan cara ini, kode program menjadi lebih efisien, tidak berulang, dan mudah diperluas.
Dalam kasus praktikum ini:

1. Kelas Produk berperan sebagai superclass yang berisi atribut umum seperti kode, nama, harga, dan stok.

2. Kelas Benih, Pupuk, dan AlatPertanian merupakan subclass yang mewarisi atribut dan method dari Produk, namun menambahkan atribut spesifik seperti varietas, jenisPupuk, atau material.

Pewarisan ini juga memungkinkan penggunaan method seperti tambahStok() dan kurangiStok() secara langsung oleh setiap subclass tanpa perlu mendefinisikannya ulang.

---

## Langkah Praktikum

1. Membuat Superclass Produk
Gunakan class Produk dari Bab 2 sebagai superclass.
2. Membuat Subclass
Benih.java → atribut tambahan: varietas.
Pupuk.java → atribut tambahan: jenis pupuk (Urea, NPK, dll).
AlatPertanian.java → atribut tambahan: material (baja, kayu, plastik).
3. Membuat Main Class
Instansiasi minimal satu objek dari tiap subclass.
Tampilkan data produk dengan memanfaatkan inheritance.
4. Menambahkan CreditBy
Panggil class CreditBy untuk menampilkan identitas mahasiswa.
5. Commit dan Push
Commit dengan pesan: week3-inheritance.


---

## Kode Program

package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        // Header
        System.out.println("=====================================");
        System.out.println("   PRAKTIKUM INHERITANCE - WEEK 3   ");
        System.out.println("=====================================");

        // Instansiasi objek dari tiap subclass
        Benih benih = new Benih("B001", "Benih Padi", 50000, 100, "IR64");
        Pupuk pupuk = new Pupuk("P001", "Pupuk Organik", 75000, 50, "NPK");
        AlatPertanian alat = new AlatPertanian("A001", "Cangkul", 100000, 20, "Baja");

        // Tampilkan data produk dalam tabel
        System.out.println("\n--- DATA PRODUK ---");
        System.out.printf("%-10s %-15s %-10s %-8s %-15s\n", "Kode", "Nama", "Harga", "Stok", "Spesifik");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                benih.getKode(), benih.getNama(), benih.getHarga(), benih.getStok(), benih.getVarietas());
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                pupuk.getKode(), pupuk.getNama(), pupuk.getHarga(), pupuk.getStok(), pupuk.getJenisPupuk());
        System.out.printf("%-10s %-15s %-10.0f %-8d %-15s\n",
                alat.getKode(), alat.getNama(), alat.getHarga(), alat.getStok(), alat.getMaterial());

        // Penggunaan method inheritance
        System.out.println("\n--- PENGGUNAAN METHOD INHERITANCE ---");
        benih.tambahStok(10);
        pupuk.kurangiStok(5);

        // Credit
        System.out.println("\n--- CREDIT ---");
        CreditBy.displayCredit("Wisnu Wibowo Saputro", "240320565", "3DSRA");

        // Footer
        System.out.println("=====================================");
        System.out.println("   SELESAI - TERIMA KASIH!   ");
        System.out.println("=====================================");
    }
}

---

## Hasil Eksekusi

https://github.com/yusrilhidayah1933-commits/oop-202501-240320565/blob/main/praktikum/week3-inheritance/screenshots/Screenshot%202025-10-25%20194451.png
---

## Analisis
(
- Jelaskan bagaimana kode berjalan. 
Ketika program dijalankan, langkah-langkah eksekusi kode adalah sebagai berikut:
   1.	Program dimulai dari method main() di kelas MainInheritance.
   Header praktikum ditampilkan terlebih dahulu sebagai identitas.
   2.	Objek-objek dari subclass dibuat:
   Benih benih = new Benih(...);
   Pupuk pupuk = new Pupuk(...);
   AlatPertanian alat = new AlatPertanian(...);
   Ketiga objek tersebut merupakan turunan dari kelas Produk, sehingga otomatis memiliki atribut dan method dari superclass-nya.
   3.	Data produk ditampilkan dalam format tabel.
   Program menggunakan System.out.printf() untuk mencetak setiap objek dengan format kolom yang rapi.
   4.	Method inheritance dijalankan.
   o	benih.tambahStok(10) memanggil method dari superclass Produk untuk menambah stok benih.
   o	pupuk.kurangiStok(5) memanggil method dari superclass untuk mengurangi stok pupuk.
   Keduanya menunjukkan bahwa method dari superclass dapat digunakan langsung oleh subclass tanpa penulisan ulang.
   5.	Menampilkan identitas pembuat program.
   Method statis CreditBy.displayCredit() menampilkan nama, NIM, dan kelas mahasiswa.
   6.	Program selesai dengan pesan footer.
   Menandakan seluruh proses eksekusi sudah berjalan sesuai konsep inheritance.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
   Pendekatan pada minggu ini berfokus pada penerapan konsep inheritance (pewarisan), di mana kelas-kelas turunan seperti Benih, Pupuk, dan AlatPertanian mewarisi atribut serta method dari kelas induk Produk. Berbeda dengan minggu sebelumnya yang masih menggunakan pendekatan dasar kelas dan objek secara terpisah, kali ini program menjadi lebih efisien karena tidak perlu menulis ulang atribut dan method yang sama di setiap kelas. Pendekatan ini juga membuat struktur kode lebih terorganisir, mudah dikembangkan, dan menunjukkan bagaimana hubungan antar kelas dapat dimanfaatkan untuk memperluas fungsionalitas program tanpa mengulang kode.

- Kendala yang dihadapi dan cara mengatasinya?
   tidak ada kendala
)
---

## Kesimpulan

Dari praktikum minggu ke-3 ini dapat disimpulkan bahwa penerapan konsep inheritance (pewarisan) dalam pemrograman berorientasi objek sangat penting untuk menciptakan struktur kode yang efisien, terorganisir, dan mudah dikelola. Dengan inheritance, kelas turunan seperti Benih, Pupuk, dan AlatPertanian dapat mewarisi atribut dan method dari kelas induk Produk, sehingga menghindari pengulangan kode dan memudahkan pengembangan sistem. Praktikum ini juga menunjukkan bagaimana pewarisan dapat digunakan untuk memperluas fungsionalitas program, sekaligus memperkuat pemahaman terhadap hubungan antar kelas dalam konsep OOP.

---

## Quiz

- Jelaskan bagaimana kode berjalan. 
Ketika program dijalankan, langkah-langkah eksekusi kode adalah sebagai berikut:
   1.	Program dimulai dari method main() di kelas MainInheritance.
   Header praktikum ditampilkan terlebih dahulu sebagai identitas.
   2.	Objek-objek dari subclass dibuat:
   Benih benih = new Benih(...);
   Pupuk pupuk = new Pupuk(...);
   AlatPertanian alat = new AlatPertanian(...);
   Ketiga objek tersebut merupakan turunan dari kelas Produk, sehingga otomatis memiliki atribut dan method dari superclass-nya.
   3.	Data produk ditampilkan dalam format tabel.
   Program menggunakan System.out.printf() untuk mencetak setiap objek dengan format kolom yang rapi.
   4.	Method inheritance dijalankan.
   o	benih.tambahStok(10) memanggil method dari superclass Produk untuk menambah stok benih.
   o	pupuk.kurangiStok(5) memanggil method dari superclass untuk mengurangi stok pupuk.
   Keduanya menunjukkan bahwa method dari superclass dapat digunakan langsung oleh subclass tanpa penulisan ulang.
   5.	Menampilkan identitas pembuat program.
   Method statis CreditBy.displayCredit() menampilkan nama, NIM, dan kelas mahasiswa.
   6.	Program selesai dengan pesan footer.
   Menandakan seluruh proses eksekusi sudah berjalan sesuai konsep inheritance.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
   Pendekatan pada minggu ini berfokus pada penerapan konsep inheritance (pewarisan), di mana kelas-kelas turunan seperti Benih, Pupuk, dan AlatPertanian mewarisi atribut serta method dari kelas induk Produk. Berbeda dengan minggu sebelumnya yang masih menggunakan pendekatan dasar kelas dan objek secara terpisah, kali ini program menjadi lebih efisien karena tidak perlu menulis ulang atribut dan method yang sama di setiap kelas. Pendekatan ini juga membuat struktur kode lebih terorganisir, mudah dikembangkan, dan menunjukkan bagaimana hubungan antar kelas dapat dimanfaatkan untuk memperluas fungsionalitas program tanpa mengulang kode.

- Kendala yang dihadapi dan cara mengatasinya?
   tidak ada kendala