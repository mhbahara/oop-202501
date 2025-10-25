# Pendahuluan

## Kata Pengantar
Puji syukur ke hadirat Allah SWT, buku panduan praktikum *Pemrograman Berorientasi Objek (OOP)* berbasis proyek ini dapat disusun dengan baik. Panduan ini dirancang untuk mendukung pembelajaran berbasis **Outcome-Based Education (OBE)** dengan pendekatan *project-based learning*.

Kasus yang dipilih adalah **Agri-POS (Point of Sales Toko Pertanian)**, sebuah sistem kasir sederhana untuk mengelola produk pertanian, transaksi penjualan, dan laporan penjualan. Domain agrobisnis dipilih karena dekat dengan kehidupan sehari-hari masyarakat dan relevan dengan kebutuhan digitalisasi pertanian di Indonesia.

Buku panduan ini diharapkan dapat:
1. Membantu mahasiswa memahami prinsip-prinsip OOP secara bertahap.  
2. Memberikan pengalaman praktik nyata dari coding sederhana hingga aplikasi utuh.  
3. Menjadi portofolio proyek yang siap ditunjukkan ke industri.  

Terima kasih kepada semua pihak yang telah membantu dalam penyusunan buku ini. Semoga buku panduan ini dapat bermanfaat bagi mahasiswa, dosen, dan praktisi pendidikan.  

Kebumen, September 2025  
**Penyusun**  
Helmi Bahar Alim, S.Kom., M.Kom.  

---

## Petunjuk Umum Praktikum

### Tata Tertib Praktikum
- Mahasiswa wajib mengerjakan minimal 75% dari total praktikum.  
- Setiap mahasiswa wajib memiliki akun GitHub/GitLab dan mengelola repositori pribadi untuk praktikum.  
- Tugas praktikum dikumpulkan dalam bentuk commit dan push ke repositori dengan format folder `praktikum/weekX/`.  
- Plagiarisme (menyalin tanpa pemahaman) dilarang keras, pelanggaran dikenai sanksi.  

### Sistem Penilaian (mengacu pada RPS)
Praktikum ini sendiri mewakili 66% dari total penilaian (Tugas, Praktikum, dan partisipatif)
- Penilaian Proyek (Praktikum): 34%  
- Tugas (Quiz, Laporan): 27%  
- Penilaian Partisipatif: 5%  
- Ujian Tengah Semester (UTS): 14%  
- Ujian Akhir Semester (UAS): 20%  


### Alur Praktikum
- Setiap minggu mahasiswa membangun komponen baru dari sistem Agri-POS.  
- Hasil akhir berupa aplikasi kasir pertanian lengkap (produk, transaksi, basis data, dan antarmuka grafis).  
- Proses bertumbuh: Class → Inheritance → Polymorphism → DAO → GUI → Integrasi.  

### Etika dan Disiplin
- Diskusi diperbolehkan, namun implementasi kode harus dikerjakan secara mandiri.  
- Gunakan bahasa yang sopan dalam forum dan praktikum.  
- Pastikan repositori tetap rapi dan terdokumentasi.  

---

## Panduan Setup Lingkungan

### 1. Install JDK
- Gunakan Java JDK 17 (LTS).  
- Cek instalasi:  
```bash
java -version
```

### 2. Install IDE
- Pilih salah satu: IntelliJ IDEA, Eclipse, atau NetBeans.  
- Disarankan: IntelliJ IDEA Community Edition.  

### 3. Install PostgreSQL dan pgAdmin
- Unduh dari [https://www.postgresql.org/download/](https://www.postgresql.org/download/)  
- Buat basis data: `agripos`  
- Simpan user/password standar (misalnya: user `postgres`, password `1234`).  

### 4. Install JavaFX
- Unduh JavaFX SDK dari [https://openjfx.io](https://openjfx.io).  
- Tambahkan library ke project IDE.  

### 5. Setup Git dan Repositori
- Install Git dari [https://git-scm.com/](https://git-scm.com/).  
- Buat repositori Git dengan nama `oop-20251-<nim>`.  
- Struktur awal repositori:  
```
oop-20251-<nim>/
 ├─ README.md
 ├─ docs/
 ├─ sql/
 └─ src/
```

### 6. Uji Instalasi
- Buat file `HelloWorld.java`:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello Agri-POS!");
    }
}
```
- Jalankan dan pastikan output `Hello Agri-POS!` muncul.  
