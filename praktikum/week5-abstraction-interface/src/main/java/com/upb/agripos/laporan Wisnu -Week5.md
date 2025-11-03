# Laporan Praktikum Minggu 5 
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : [Wisnu Wibowo Saputro]
- NIM   : [240320565]
- Kelas : [3DSRSA]

---

## Tujuan

Setelah mempelajari materi ini, mahasiswa diharapkan mampu untuk:
1. Menjelaskan perbedaan antara abstract class dan interface.
2. Mendesain abstract class dengan method abstrak sesuai kebutuhan kasus.
3. Membuat interface dan mengimplementasikannya pada class konkret.
4. Menerapkan konsep multiple inheritance melalui interface.
5. Mendokumentasikan kode dengan komentar dan struktur proyek yang baik.
---

## Dasar Teori

1. Abstraksi (Abstraction) adalah konsep dalam pemrograman berorientasi objek yang bertujuan menyederhanakan kompleksitas sistem dengan hanya menampilkan fitur penting dan menyembunyikan detail implementasi yang tidak perlu.

2. Abstract Class merupakan kelas dasar yang tidak dapat diinstansiasi secara langsung dan dapat memiliki method abstrak (tanpa isi) maupun konkrit (dengan isi). Abstract class digunakan ketika terdapat perilaku umum yang ingin dibagikan kepada beberapa subclass.

3. Interface berfungsi sebagai kontrak yang menentukan serangkaian method yang harus diimplementasikan oleh kelas yang menggunakannya. Interface mendukung multiple inheritance, sehingga satu kelas dapat mengimplementasikan beberapa interface sekaligus.

4. Perbedaan utama antara abstract class dan interface terletak pada fungsinya: abstract class digunakan untuk pewarisan perilaku dasar (inheritance), sedangkan interface digunakan untuk menentukan kemampuan tambahan (capability) antar kelas yang berbeda hierarki.

5. Dalam konteks sistem AgriPOS, abstraksi digunakan untuk memodelkan berbagai jenis pembayaran (Cash, EWallet, TransferBank) menggunakan abstract class Pembayaran sebagai dasar, dan interface seperti Validatable serta Receiptable sebagai kontrak tambahan untuk validasi dan pencetakan struk.
---

## Langkah Praktikum

1. Persiapan dan Setup Proyek
   - Membuat folder proyek dengan struktur sesuai repositori:
      oop-20251-[NIM]/
      └─ praktikum/week5-abstraction-interface/
   - Membuka proyek di IDE (IntelliJ IDEA atau VS Code).
   - Membuat package utama com.upb.agripos serta subpackage:
      com.upb.agripos.model.pembayaran
      com.upb.agripos.model.kontrak
      com.upb.agripos.util

2. Pembuatan Abstract Class Pembayaran
   - Membuat file Pembayaran.java di dalam folder model/pembayaran/.
   - Menambahkan dua method abstrak (biaya() dan prosesPembayaran()) serta satu method konkrit (totalBayar()).
   - Class ini berfungsi sebagai template dasar bagi seluruh jenis pembayaran.

3. Pembuatan Interface Validatable dan Receiptable
   - File Validatable.java dan Receiptable.java dibuat di package model/kontrak/.
   - Interface Validatable memiliki method boolean validasi().
   - Interface Receiptable memiliki method String cetakStruk().
   - Interface ini memungkinkan multiple inheritance untuk mendefinisikan kemampuan tambahan di tiap kelas.

4. Implementasi Subclass Konkret
   - Cash.java → turunan Pembayaran dan implementasi Receiptable.
     Tidak memiliki biaya tambahan (biaya() = 0).
     Pembayaran berhasil jika uang tunai ≥ total.
   - EWallet.java → turunan Pembayaran, implementasi Validatable & Receiptable.
     Biaya transaksi 1,5% dari total.
     Proses pembayaran membutuhkan validasi OTP 6 digit.
   - TransferBank.java → turunan Pembayaran, implementasi Validatable & Receiptable.
     Biaya tetap sebesar Rp3.500.
     Pembayaran berhasil jika PIN berisi 4 digit.
   - Semua class mencetak struk dengan format rapi seperti struk toko.

5. Pembuatan Utility Class CreditBy
   - Dibuat di com.upb.agripos.util.
   - Menampilkan identitas pembuat (NIM dan Nama Mahasiswa) di akhir output program.

6. Membuat Class Utama MainAbstraction.java
   - File dibuat di package com.upb.agripos.
   - Menggunakan konsep polimorfisme untuk menampung berbagai jenis pembayaran dengan tipe referensi Pembayaran.
   - Menampilkan hasil cetak struk dari masing-masing metode pembayaran dan menutup dengan CreditBy.print().

7. Menjalankan Program
   - Jalankan file MainAbstraction.java.
   - Pastikan output menampilkan struk rapi dari setiap metode pembayaran dan identitas pembuat.

8. Commit dan Push ke Repository GitHub
   - Setelah semua file selesai dan program berjalan dengan benar, lakukan commit dan push dengan pesan:
   git add .
   git commit -m "week5-abstraction-interface"
   git push
---

## Kode Program

Kode Utama yang Dibuat
Berikut beberapa potongan kode utama yang diimplementasikan pada praktikum Abstraction (Abstract Class & Interface):
1. Abstract Class – Pembayaran.java`
      package com.upb.agripos.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // method abstrak untuk biaya tambahan
    public abstract boolean prosesPembayaran();   // method abstrak untuk proses pembayaran

    public double totalBayar() {
        return total + biaya(); // method konkrit
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
2. Interface – Validatable.java
      package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // kontrak untuk validasi (misal OTP/PIN)
}
3. Interface – Receiptable.java
      package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk(); // kontrak untuk mencetak struk pembayaran
}
4. Kelas Turunan – Cash.java
      package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar();
    }

    @Override
    public String cetakStruk() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return String.format(
            "========================================\n" +
            "              STRUK PEMBAYARAN\n" +
            "========================================\n" +
            "Invoice No  : %s\n" +
            "Metode      : CASH\n" +
            "Total Bayar : Rp%s\n" +
            "Tunai       : Rp%s\n" +
            "Kembali     : Rp%s\n" +
            "----------------------------------------\n" +
            "Terima kasih atas pembayaran Anda.\n" +
            "========================================\n",
            invoiceNo, df.format(totalBayar()), df.format(tunai),
            df.format(Math.max(0, tunai - totalBayar()))
        );
    }
}
5. Kelas Turunan – EWallet.java
      package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String status = prosesPembayaran() ? "BERHASIL" : "GAGAL";

        return String.format(
            "========================================\n" +
            "              STRUK PEMBAYARAN\n" +
            "========================================\n" +
            "Invoice No  : %s\n" +
            "Metode      : E-WALLET\n" +
            "Akun        : %s\n" +
            "Total+Fee   : Rp%s\n" +
            "Status      : %s\n" +
            "----------------------------------------\n" +
            "Terima kasih telah melakukan pembayaran.\n" +
            "========================================\n",
            invoiceNo, akun, df.format(totalBayar()), status
        );
    }
}
6. Kelas Turunan – TransferBank.java
      package com.upb.agripos.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;
import java.text.DecimalFormat;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String rekening;
    private String pin;

    public TransferBank(String invoiceNo, double total, String rekening, String pin) {
        super(invoiceNo, total);
        this.rekening = rekening;
        this.pin = pin;
    }

    @Override
    public double biaya() {
        return 3500.0; // biaya tetap
    }

    @Override
    public boolean validasi() {
        return pin != null && pin.length() == 4;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        String status = prosesPembayaran() ? "BERHASIL" : "GAGAL";

        return String.format(
            "========================================\n" +
            "              STRUK PEMBAYARAN\n" +
            "========================================\n" +
            "Invoice No  : %s\n" +
            "Metode      : TRANSFER BANK\n" +
            "Rekening    : %s\n" +
            "Total+Fee   : Rp%s\n" +
            "Status      : %s\n" +
            "----------------------------------------\n" +
            "Terima kasih telah melakukan pembayaran.\n" +
            "========================================\n",
            invoiceNo, rekening, df.format(totalBayar()), status
        );
    }
}
7. Class Utama – MainAbstraction.java
      package com.upb.agripos;

import com.upb.agripos.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        // Polimorfisme: Pembayaran digunakan sebagai tipe referensi
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");
        Pembayaran tb = new TransferBank("INV-003", 200000, "9876543210", "1234");

        System.out.println("=== DEMONSTRASI STRUK PEMBAYARAN ===\n");
        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) tb).cetakStruk());

        // Credit
        CreditBy.print("240320565", "Wisnu Wibowo Saputro");
    }
}

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](screenshots/hasil.png)
)
---

## Analisis
(
1. Cara Kerja Kode
   Program ini dibuat untuk menampilkan sistem pembayaran dengan beberapa metode: Cash, E-Wallet, dan Transfer Bank.Semua jenis pembayaran punya cara kerja berbeda, tapi dibuat dari satu kerangka yang sama yaitu class abstrak Pembayaran.
   Langkah jalannya program:
   - Pembayaran jadi dasar yang berisi data umum seperti nomor invoice dan total bayar.
   - Tiap jenis pembayaran punya class sendiri:
         - Cash: pakai uang tunai, hitung kembalian.
         - EWallet: pakai akun dan OTP 6 digit.
         - TransferBank: pakai nomor rekening dan PIN 4 digit.
   - Semua class punya kemampuan mencetak struk lewat interface Receiptable, dan yang butuh validasi (OTP/PIN) pakai interface Validatable.
   - Di MainAbstraction, ketiga pembayaran dibuat lalu dicetak struknya.
   - Hasilnya muncul di terminal seperti struk toko, lengkap dengan total, metode, status, dan ucapan terima kasih. 
   
2. Perbedaan Minggu Ini dan Minggu Sebelumnya

   a. Minggu lalu kita belajar pewarisan (inheritance), jadi fokusnya adalah kelas anak mewarisi sifat dari kelas induk.
   b. Minggu ini kita belajar abstraksi dan interface, di mana kita cuma bikin “kerangka” dan biarkan kelas turunannya yang mengisi detail.
   c. Bedanya, abstraksi membuat kode lebih teratur, sedangkan interface memastikan semua jenis pembayaran punya kemampuan yang sama (misalnya mencetak struk).
   d. Jadi, minggu ini lebih fokus ke struktur dan keseragaman daripada pewarisan saja.

3. Kendala dan Cara Mengatasinya
   a. struk awalnya terlihat berantakan karena saya menggunakan println biasa untuk menampilkan hasil. Solusinya saya ubah menjadi String.format agar tulisan di struk bisa sejajar dan tampak lebih rapi seperti struk di toko.
   b. saya sempat mengalami error import class, penyebabnya karena nama package dan folder tidak sama. Untuk memperbaikinya, saya menyesuaikan nama folder dengan nama package yang ada di dalam file Java agar bisa dikenali oleh program.
   c. saya tidak bisa memanggil method cetakStruk(), ternyata karena method itu bukan bagian dari class Pembayaran, melainkan dari interface Receiptable. Solusinya, saya tambahkan casting (Receiptable) sebelum memanggil method tersebut di MainAbstraction.
   d. saya juga sempat mengalami masalah saat validasi OTP dan PIN gagal, karena jumlah digit yang dimasukkan kurang dari 6 untuk OTP dan 4 untuk PIN. Setelah itu, saya tambahkan pengecekan panjang karakter di bagian validasi supaya sistem hanya menerima OTP dan PIN yang benar.
)
---

## Kesimpulan

   Dari praktikum Bab 5 tentang Abstraction (Abstract Class & Interface), saya belajar bagaimana cara membuat struktur program yang lebih rapi dan mudah dikembangkan.
   Dengan menggunakan abstract class, saya bisa membuat satu kelas dasar (Pembayaran) yang berisi aturan umum untuk semua jenis pembayaran.
   Sedangkan dengan interface, saya bisa menambahkan kemampuan tambahan seperti validasi (Validatable) dan cetak struk (Receiptable) tanpa harus mengubah struktur utama kelas.

   Selain itu, saya juga memahami cara kerja multiple inheritance melalui interface, di mana satu kelas bisa mengimplementasikan lebih dari satu interface sekaligus, seperti pada EWallet dan TransferBank.
   Hasil akhirnya, program dapat mencetak struk pembayaran yang rapi dan menampilkan status transaksi dengan jelas.

   Melalui latihan ini, saya semakin paham perbedaan antara abstract class dan interface, serta bagaimana keduanya saling melengkapi untuk membuat kode yang efisien, fleksibel, dan mudah dipelihara.

---

## Quiz

1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.

   Jawaban:
      Abstract class digunakan sebagai kerangka dasar untuk kelas lain yang memiliki sifat atau perilaku umum. Di dalamnya bisa ada atribut (variabel) dan method yang sudah punya isi maupun yang masih abstrak (belum punya isi).
      Sementara interface digunakan untuk mendefinisikan kemampuan tambahan yang harus dimiliki oleh suatu kelas, tanpa memperhatikan dari mana kelas itu diwariskan. Interface hanya berisi deklarasi method (tanpa isi), jadi setiap kelas yang mengimplementasikannya wajib menuliskan isi method tersebut.

   Singkatnya:
      Abstract class = cetakan dasar (ada logika dan atribut bersama).
      Interface = kontrak kemampuan yang bisa diterapkan lintas kelas.

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?

   Jawaban:
      Karena interface tidak membawa data atau state, hanya berisi deklarasi method, sehingga tidak menimbulkan konflik pewarisan seperti jika dua kelas induk punya variabel atau method dengan nama yang sama.
      Dengan kata lain, Java tidak mengizinkan multiple inheritance antar kelas (karena bisa membingungkan compiler), tapi memungkinkan multiple inheritance antar interface karena lebih aman dan tidak menimbulkan ambiguitas.

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.

   Jawaban:
      Pada proyek Agri-POS, Pembayaran paling tepat dijadikan abstract class, karena semua jenis pembayaran memiliki atribut dan perilaku dasar yang sama seperti invoiceNo, total, biaya(), dan prosesPembayaran().
      Sedangkan Validatable dan Receiptable lebih tepat dijadikan interface, karena sifatnya hanya sebagai kemampuan tambahan (validasi dan cetak struk) yang tidak dimiliki semua jenis pembayaran, dan bisa diterapkan pada kelas mana pun tanpa harus menjadi turunan dari Pembayaran.
