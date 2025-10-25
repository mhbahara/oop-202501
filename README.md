

# Praktikum OOP – Agri-POS (20251)

Repositori ini adalah **template praktikum** untuk mata kuliah *Pemrograman Berorientasi Objek (OOP)* semester gasal 2025/2026, dengan studi kasus **Agri-POS (Point of Sales Toko Pertanian)**.

Mahasiswa akan membangun aplikasi kasir pertanian secara bertahap, mulai dari konsep dasar OOP hingga aplikasi terintegrasi dengan basis data dan GUI.

---

## Instruksi Praktikum
1. **Fork** repositori ini ke akun Git masing-masing.
2. **Rename** hasil fork menjadi:
   ```
   oop-20251-<nim>
   ```
   Contoh: `oop-20251-2310112345`
3. **Clone** ke komputer lokal:
   ```bash
   git clone https://github.com/<username>/oop-20251-<nim>.git
   ```
4. **Tambahkan remote upstream** agar bisa sync update dari dosen:
   ```bash
   git remote add upstream https://github.com/mhbahara/oop-202501.git
   ```
5. **Sync fork secara berkala** untuk mendapatkan update materi terbaru :
   ```bash
   git fetch upstream
   git merge upstream/main
   git push origin main
   ```
6. Kerjakan tugas mingguan sesuai instruksi di folder [docs/](docs/).
7. Simpan kode program di dalam folder [src/](src/) dan [praktikum/](praktikum/) dengan struktur yang rapi.
8. Setiap minggu lakukan **commit & push** dengan format pesan sesuai minggu, contoh:
   - `week1-setup-hello-pos`
   - `week2-class-object`
   - `week3-inheritance`
   - dst.
9. Setiap tugas wajib menyertakan:
   - **Kode program**
   - **Screenshot hasil eksekusi**
   - **Commit log** sesuai instruksi
   - **Laporan singkat** (PDF/Markdown) berisi penjelasan kode dan hasil percobaan

---

## Struktur Folder
```
oop-202501/
 ├─ [README.md](README.md)                # Panduan umum repositori
 ├─ [docs/](docs/)                        # Panduan praktikum mingguan (Markdown)
 │   ├─ [00_pendahuluan.md](docs/00_pendahuluan.md)
 │   ├─ [01_bab1_paradigma_setup.md](docs/01_bab1_paradigma_setup.md)
 │   ├─ [02_bab2_class_object.md](docs/02_bab2_class_object.md)
 │   ├─ [03_bab3_inheritance.md](docs/03_bab3_inheritance.md)
 │   ├─ [04_bab4_polymorphism.md](docs/04_bab4_polymorphism.md)
 │   ├─ [05_bab5_abstraction_interface.md](docs/05_bab5_abstraction_interface.md)
 │   ├─ [06_bab6_uml_solid.md](docs/06_bab6_uml_solid.md)
 │   ├─ [07_bab7_koleksi_keranjang.md](docs/07_bab7_koleksi_keranjang.md)
 │   ├─ [08_bab8_uts.md](docs/08_bab8_uts.md)
 │   ├─ [09_bab9_exception.md](docs/09_bab9_exception.md)
 │   ├─ [10_bab10_pattern_testing.md](docs/10_bab10_pattern_testing.md)
 │   ├─ [11_bab11_dao_database.md](docs/11_bab11_dao_database.md)
 │   ├─ [12_bab12_gui_dasar.md](docs/12_bab12_gui_dasar.md)
 │   ├─ [13_bab13_gui_lanjutan.md](docs/13_bab13_gui_lanjutan.md)
 │   ├─ [14_bab14_integrasi_individu.md](docs/14_bab14_integrasi_individu.md)
 │   ├─ [15_bab15_proyek_kelompok.md](docs/15_bab15_proyek_kelompok.md)
 │   ├─ [16_bab16_uas.md](docs/16_bab16_uas.md)
 │   └─ [lampiran.md](docs/lampiran.md)
 ├─ [praktikum/](praktikum/)               # Folder tugas mingguan
 │   ├─ [week1-setup-hello-pos/](praktikum/week1-setup-hello-pos/)
 │   │   ├─ [laporan.md](praktikum/week1-setup-hello-pos/laporan.md)
 │   │   ├─ [screenshots/](praktikum/week1-setup-hello-pos/screenshots/)
 │   │   └─ [src/](praktikum/week1-setup-hello-pos/src/)
 │   ├─ [week2-class-object/](praktikum/week2-class-object/)
 │   ├─ [week3-inheritance/](praktikum/week3-inheritance/)
 │   ├─ [week4-polymorphism/](praktikum/week4-polymorphism/)
 │   ├─ [week5-abstraction-interface/](praktikum/week5-abstraction-interface/)
 │   ├─ [week6-uml-solid/](praktikum/week6-uml-solid/)
 │   ├─ [week7-koleksi-keranjang/](praktikum/week7-koleksi-keranjang/)
 │   ├─ [week8-uts/](praktikum/week8-uts/)
 │   ├─ [week9-exception-handling/](praktikum/week9-exception-handling/)
 │   ├─ [week10-pattern-testing/](praktikum/week10-pattern-testing/)
 │   ├─ [week11-dao-database/](praktikum/week11-dao-database/)
 │   ├─ [week12-gui-dasar/](praktikum/week12-gui-dasar/)
 │   ├─ [week13-gui-lanjutan/](praktikum/week13-gui-lanjutan/)
 │   ├─ [week14-integrasi-individu/](praktikum/week14-integrasi-individu/)
 │   ├─ [week15-proyek-kelompok/](praktikum/week15-proyek-kelompok/)
 │   └─ [week16-uas/](praktikum/week16-uas/)
 ├─ [sql/](sql/)                     # Skema dan seed database PostgreSQL
 │   ├─ [schema.sql](sql/schema.sql)
 │   └─ [seed.sql](sql/seed.sql)
 └─ [src/](src/)                     # Source code Java Tugas Akhir/ integrasi
   ├─ [main/java/com/upb/agripos/](src/main/java/com/upb/agripos/)
   └─ [test/java/com/upb/agripos/](src/test/java/com/upb/agripos/)
```

---

### Sistem Penilaian
Praktikum ini sendiri mewakili 66% dari total penilaian (Tugas, Praktikum, dan partisipatif)
- Penilaian Proyek (Praktikum): 34%  
- Tugas (Quiz, Laporan): 27%  
- Penilaian Partisipatif: 5%  
- Ujian Tengah Semester (UTS): 14%  
- Ujian Akhir Semester (UAS): 20%  

---

## Referensi Utama
- Liang, Y. D. *Introduction to Java Programming and Data Structures*.  
- Horstmann, C. S. *Core Java Volume I – Fundamentals*.  
- Schildt, H. *Java: The Complete Reference*.  
- PostgreSQL Official Documentation.  
- OpenJFX Documentation.  

---

## Lisensi
Proyek ini menggunakan lisensi MIT. Silakan gunakan dan modifikasi dengan tetap mencantumkan atribusi.
