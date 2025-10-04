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

a. Paradigma Prosedural (Python):

def hello():
    print("Hello World, I am Wisnu-240320565")

hello()

b. Paradigma OOP (Python):

class Hello:
    def __init__(self, name, nim):
        self.name = name
        self.nim = nim

    def say_hello(self):
        print(f"Hello World, I am {self.name}-{self.nim}")

program = Hello("Wisnu", "240320565")
program.say_hello()

c. Paradigma Fungsional (Python):

hello = lambda name, nim: print(f"Hello World, I am {name}-{nim}")
hello("Wisnu", "240320565")

---

## Hasil Eksekusi

a. Paradigma Prosedural (Python):
<img width="448" height="73" alt="Screenshot 2025-10-04 211453" src="https://github.com/user-attachments/assets/a648c407-635e-423e-9ee9-c44965383643" />
b. paradigma OOP (Python) :
<img width="428" height="76" alt="Screenshot 2025-10-04 212230" src="https://github.com/user-attachments/assets/0159c453-ca13-4399-a9a8-664eada77f2d" />
c. paradigma fungsional (Python) :
<img width="429" height="79" alt="Screenshot 2025-10-04 212447" src="https://github.com/user-attachments/assets/73d7aa9f-0ffe-4b45-825a-778fd7671f0c" />

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
   



   …  )
