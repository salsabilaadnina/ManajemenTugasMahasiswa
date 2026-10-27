# Manajemen Tugas Mahasiswa

Aplikasi Android untuk membantu mahasiswa mengelola tugas perkuliahan dengan lebih teratur. Aplikasi ini dikembangkan menggunakan Kotlin dan Jetpack Compose, dengan Room Database untuk menyimpan data tugas secara lokal.

## Fitur Aplikasi

- **Beranda:** Menampilkan halaman utama aplikasi.
- **Manajemen Tugas:** Menambahkan dan melihat informasi tugas.
- **Detail Tugas:** Melihat informasi tugas secara lebih lengkap.
- **Edit dan Hapus Tugas:** Mengelola data tugas yang tersimpan.
- **Status Tugas:** Menandai tugas yang sudah selesai.
- **Statistik:** Menampilkan informasi terkait tugas.
- **Profil:** Menampilkan halaman profil mahasiswa.
- **Penyimpanan Lokal:** Menyimpan data menggunakan Room Database.

## Teknologi yang Digunakan

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- Room Database
- Git dan GitHub

## Struktur Proyek

```text
ManajemenTugasMahasiswa/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   ├── com/example/manajementugasmahasiswa/
│           │   │   └── MainActivity.kt
│           │   ├── data/
│           │   ├── repository/
│           │   ├── ui/
│           │   └── viewmodel/
│           ├── res/
│           └── AndroidManifest.xml
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Cara Menjalankan Aplikasi

1. Unduh atau clone repository ini.
2. Buka proyek menggunakan Android Studio.
3. Tunggu hingga proses Gradle Sync selesai.
4. Hubungkan perangkat Android atau jalankan emulator.
5. Klik tombol **Run** untuk menjalankan aplikasi.

## Penyimpanan Data

Aplikasi menggunakan Room Database untuk menyimpan data tugas secara lokal pada perangkat. Data tugas memiliki informasi seperti judul, deskripsi, tenggat waktu, prioritas, kategori, dan status penyelesaian.

## Pengembang

- **Nama:** Salsabila Adnina Hadi
- **NIM:** 2411022
- **Kelas:** IFB5A
- **Program Studi:** Informatika
- **Mata Kuliah:** Pemrograman Aplikasi Bergerak

## Repository

[ManajemenTugasMahasiswa di GitHub](https://github.com/salsabilaadnina/ManajemenTugasMahasiswa)
