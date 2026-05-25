# 🛡️ SafePay: Sistem Rekber & Middleman Online

[![Language](https://img.shields.io/badge/language-Java-orange.svg)](https://www.oracle.com/java/)
[![OOP Paradigm](https://img.shields.io/badge/paradigm-Object--Oriented-blue.svg)](https://en.wikipedia.org/wiki/Object-oriented_programming)
[![Architecture](https://img.shields.io/badge/architecture-Modular%20%7C%20Escrow-green.svg)](https://github.com/)

## Tentang Proyek

**SafePay** adalah sebuah aplikasi berbasis konsol (*command-line interface*) yang dirancang khusus untuk mensimulasikan mekanisme rekening bersama (Rekber) atau penyedia jasa perantara (*middleman*) dalam transaksi perdagangan elektronik digital. Proyek ini lahir dari kebutuhan untuk menghadirkan solusi keamanan transaksi antara pembeli (*buyer*) dan penjual (*seller*) guna meminimalisir risiko penipuan daring. Dalam ekosistem ini, SafePay bertindak sebagai pihak ketiga tepercaya yang bertugas menahan dana pembayaran dari pembeli sebelum status pengiriman barang dikonfirmasi selesai, sehingga menciptakan lingkungan transaksi yang aman, transparan, dan dapat diandalkan bagi kedua belah pihak.

Arsitektur aplikasi ini dibangun menggunakan bahasa pemrograman **Java** dengan menerapkan konsep berorientasi objek (OOP) secara menyeluruh. Dengan membagi fungsionalitas sistem ke dalam modul-modul kelas yang spesifik, aplikasi ini mampu mengelola status siklus hidup transaksi secara *real-time*, mulai dari tahap tunggu pembayaran, proses verifikasi oleh administrator, pelacakan nomor resi pengiriman barang oleh penjual, hingga tahap pelepasan dana akhir kepada penjual setelah pembeli melakukan konfirmasi penerimaan objek transaksi.

---

## Implementasi Paradigma Object-Oriented Programming (OOP)

Seluruh komponen logika dalam program SafePay dirancang menggunakan pilar-pilar utama pemrograman berorientasi objek demi memastikan keterbacaan kode yang optimal dan kemudahan pemeliharaan jangka panjang. Inti dari manajemen pengguna berpusat pada kelas abstrak bernama `User`. Kelas ini mengemas data dasar pengguna seperti identitas akun dan kata sandi, sekaligus menetapkan sebuah metode abstrak `tampilkanMenu()`. Melalui mekanisme **Polimorfisme**, kelas turunan seperti `Buyer`, `Seller`, dan `Admin` dapat mengimplementasikan ulang metode tersebut untuk menghasilkan dasbor antarmuka operasional yang sepenuhnya unik berdasarkan hak akses peran masing-masing di dalam sistem.

Selain pewarisan sifat, proyek ini juga menerapkan **Abstraksi** melalui antarmuka (*interface*) bernama `Payment` yang mengabstraksikan fungsi `bayar()`. Kontrak antarmuka ini direalisasikan di dalam kelas `Transaction` untuk menangani perubahan status finansial objek belanja secara aman. Keamanan alur kerja aplikasi diperkuat lebih lanjut oleh kelas `Status` yang menerapkan enkapsulasi ketat menggunakan sekumpulan konstanta string, memastikan bahwa perubahan kondisi transaksi hanya dapat bergerak secara logis sesuai dengan aturan bisnis sistem escrow tanpa adanya manipulasi data dari luar kelas.

---

## Alur Kerja Multi-Role di Dalam Sistem

Ketika program utama dijalankan melalui kelas entitas `SafePay`, pengguna dapat memilih untuk masuk ke dalam tiga gerbang otoritas yang memiliki alur bisnis yang saling terintegrasi:

### 1. Dasbor Pembeli (Buyer Dashboard)
Seorang pembeli dapat menjelajahi katalog inventaris barang tersedia yang dijual oleh para pedagang di dalam sistem. Ketika pembeli memutuskan untuk melakukan pembelian, sistem akan menguji ketersediaan stok barang, memperbarui jumlah inventaris secara otomatis, dan menerbitkan nota transaksi baru dengan pengenal unik. Pembeli kemudian memiliki opsi untuk langsung melakukan pembayaran di mana dana tersebut akan langsung dialihkan ke dalam status tertahan oleh sistem perantara SafePay. Setelah barang fisik sampai di tujuan, pembeli memegang otoritas untuk memberikan konfirmasi akhir agar transaksi dapat dinyatakan selesai.

### 2. Dasbor Penjual (Seller Dashboard)
Pihak penjual memiliki ruang kerja khusus untuk memantau setiap pesanan masuk yang mengarah ke toko mereka. Setelah sistem mengonfirmasi bahwa dana pembeli telah masuk ke rekening penampungan SafePay, penjual berkewajiban untuk memproses pesanan tersebut dengan memasukkan nomor resi pengiriman yang valid serta memperbarui status logistik menjadi terkirim. Selain memproses transaksi berjalan, dasbor ini juga menyediakan fitur manajemen inventaris mandiri, yang memungkinkan penjual untuk menambah kuantitas stok barang lama maupun mendaftarkan komoditas barang baru ke dalam sistem pasar.

### 3. Dasbor Administrator (Admin Dashboard)
Administrator bertindak sebagai pengawas tertinggi dan fasilitator eksekusi finansial dalam ekosistem SafePay. Admin memiliki hak akses penuh untuk memantau seluruh transaksi global yang terjadi di dalam platform dan melihat data detail akun pengguna. Fungsi krusial admin meliputi verifikasi pembayaran masuk yang dilakukan oleh pembeli untuk mengubah status transaksi, serta melakukan proses pelepasan dana (*release funds*) secara manual untuk mentransfer uang penampungan ke saldo penjual ketika pembeli telah mengonfirmasi bahwa barang telah diterima dengan baik.

---

## Panduan Instalasi dan Pengoperasian

Aplikasi ini berjalan sepenuhnya di atas ekosistem terminal sehingga sangat ringan dan tidak memerlukan instalasi pustaka pihak ketiga. Untuk menguji fungsionalitas program di komputer lokal, Anda hanya perlu memastikan bahwa Java Development Kit (JDK) versi modern telah terpasang dan dikonfigurasi di dalam variabel lingkungan sistem operasi Anda. 

Proses pengoperasian dapat diawali dengan mengunduh seluruh berkas kode sumber yang berada di dalam paket kerja, membuka jendela terminal di direktori tersebut, kemudian melakukan kompilasi semua berkas `.java`. Setelah berkas *bytecode* terbentuk, jalankan kelas utama program. Pengguna dapat langsung berinteraksi menggunakan masukan teks dinamis berbasis kelas `Scanner` untuk mensimulasikan skenario transaksi perdagangan daring dari awal hingga akhir secara interaktif.
