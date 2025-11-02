# Laporan Praktikum Minggu 3 (sesuaikan minggu ke berapa?)
Topik: Inheritance

## Identitas
- Nama  : [LIA LUSIANTI]
- NIM   : [240202869]
- Kelas : [3IKRB]

---

## Tujuan
- Mahasiswa mampu menjelaskan konsep inheritance (pewarisan class) dalam OOP.
- Mahasiswa mampu membuat superclass dan subclass untuk produk pertanian.
- Mahasiswa mampu mendemonstrasikan hierarki class melalui contoh kode.
- Mahasiswa mampu menggunakan super untuk memanggil konstruktor dan method parent class.
- Mahasiswa mampu membuat laporan praktikum yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.


---

## Dasar Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.

1. Superclass: class induk yang mendefinisikan atribut umum.
2. Subclass: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.

super digunakan untuk memanggil konstruktor atau method superclass.
Dalam konteks Agri-POS, kita dapat membuat class Produk sebagai superclass, kemudian Benih, Pupuk, dan AlatPertanian sebagai subclass. Hal ini membuat kode lebih reusable dan terstruktur.
---

## Langkah Praktikum
1. Membuat Superclass Produk

- Gunakan class Produk dari Bab 2 sebagai superclass.

2. Membuat Subclass

- Benih.java → atribut tambahan: varietas.
- Pupuk.java → atribut tambahan: jenis pupuk (Urea, NPK, dll).
- AlatPertanian.java → atribut tambahan: material (baja, kayu, plastik).

3. Membuat Main Class

- Instansiasi minimal satu objek dari tiap subclass.
- Tampilkan data produk dengan memanfaatkan inheritance.

4. Menambahkan CreditBy

- Panggil class CreditBy untuk menampilkan identitas mahasiswa.

5. Commit dan Push

- Commit dengan pesan: week3-inheritance.

---

## Kode Program

```java
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }
}
```
```java
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
}
```
```java
package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}
```
```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        System.out.println("Benih: " + b.getNama() + " Varietas: " + b.getVarietas());
        System.out.println("Pupuk: " + p.getNama() + " Jenis: " + p.getJenis());
        System.out.println("Alat Pertanian: " + a.getNama() + " Material: " + a.getMaterial());

        CreditBy.print("<NIM>", "<Nama Mahasiswa>");
    }
}
```
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + 240202869 + "   - Lia Lusianti");
    }
}
```
---

## Hasil Eksekusi
 
![Hasil week3](./screenshots/Hasil_Week3.png)
---

## Analisis

- Kode berjalan dengan memanfaatkan konsep inheritance, di mana class Benih, Pupuk, dan AlatPertanian mewarisi atribut serta method dari class Produk. Saat program dijalankan, setiap subclass membuat objek dengan memanggil constructor super() untuk menginisialisasi atribut umum seperti kode, nama, harga, dan stok. Kemudian, atribut tambahan dari masing-masing subclass ditampilkan bersama data produk utama.
- Pada minggu sebelumnya, program hanya menggunakan class tunggal (Produk) tanpa hubungan antarclass. Minggu ini diperkenalkan konsep pewarisan (inheritance) yang memungkinkan penggunaan kembali kode dari superclass ke beberapa subclass. Pendekatan ini membuat struktur program lebih modular dan mudah dikembangkan untuk berbagai jenis produk.
- Kendala: Error pada package dan struktur folder yang tidak sesuai dengan deklarasi package com.upb.agripos.model.
Solusi: Memperbaiki struktur folder agar sesuai dengan path package.

Kendala: Lupa menambahkan super() di constructor subclass.
Solusi: Menambahkan pemanggilan super() untuk menginisialisasi atribut dari superclass.

Kendala: Class belum dikenali saat dijalankan.
Solusi: Mengecek kembali import dan nama file agar sesuai dengan nama class.
---

## Kesimpulan
*Dari hasil praktikum ini dapat disimpulkan bahwa penerapan konsep inheritance mempermudah pengembangan program dengan cara mewariskan atribut dan perilaku dari superclass ke subclass. Pendekatan ini membuat kode lebih efisien, terstruktur, dan mudah diperluas. Dalam konteks aplikasi POS pertanian, inheritance memungkinkan pengelolaan berbagai jenis produk dengan cara yang konsisten namun tetap fleksibel untuk ditambah fitur spesifik sesuai kebutuhan masing-masing produk.*
---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?  
   **Jawaban:** Keuntungan menggunakan inheritance adalah kode menjadi lebih efisien dan mudah dikelola karena atribut dan method yang sama tidak perlu ditulis berulang pada setiap class. Dengan pewarisan, subclass dapat mewarisi perilaku dan atribut dari superclass, sehingga memudahkan pengembangan dan pemeliharaan program. Selain itu, perubahan pada superclass otomatis berdampak pada seluruh subclass yang terkait.  

2. Bagaimana cara subclass memanggil konstruktor superclass?  
   **Jawaban:** Subclass memanggil konstruktor superclass menggunakan keyword super() di dalam konstruktor miliknya. Pemanggilan ini harus dilakukan pada baris pertama konstruktor subclass untuk menginisialisasi atribut yang dimiliki superclass. 

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass. 
   **Jawaban:** Contoh lain yang bisa dijadikan subclass adalah ObatTanaman, yang merupakan turunan dari class Produk. Subclass ini bisa memiliki atribut tambahan seperti jenis hama yang ditangani atau dosis penggunaan. 
