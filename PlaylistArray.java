/*
 * Tugas Kelompok 4 - Data Structures and Algorithm Analysis (COSC6025)
 *
 * Identitas Kelompok:
 * Nama : M. SAIFULLOH HUDA
 * NIM  : 2902771754
 * Nama : Mohamad Akmal Pramana
 * NIM  : 2902799802
 * Nama : Sepistiani Charmelita Noya
 * NIM  : 2902791264
 *
 */

import java.util.Scanner;

// Class Lagu digunakan untuk merepresentasikan data lagu.
class Lagu {
    String judul;
    String artis;
    double durasi;

    // Constructor untuk mengisi data lagu ketika objek dibuat.
    Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Method untuk menampilkan informasi lagu.
    void tampilkanInfo() {
        System.out.printf("%s - %s (%.2f menit)%n", judul, artis, durasi);
    }
}

public class PlaylistArray {
    // Kapasitas maksimum playlist sesuai instruksi tugas.
    static final int MAX_LAGU = 10;

    // Array statis untuk menyimpan objek Lagu.
    static Lagu[] playlist = new Lagu[MAX_LAGU];

    // Variabel untuk mencatat jumlah lagu yang sedang tersimpan.
    static int jumlahLagu = 0;

    // Scanner digunakan untuk menerima input dari pengguna.
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Data awal agar program langsung dapat diuji.
        tambahDataAwal();

        int pilihan;

        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tampilkanSemuaLagu();
                    break;
                case 2:
                    tambahLagu();
                    break;
                case 3:
                    hapusLagu();
                    break;
                case 4:
                    cariLagu();
                    break;
                case 5:
                    urutkanLaguBerdasarkanDurasi();
                    break;
                case 6:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu 1 sampai 6.");
            }
        } while (pilihan != 6);

        input.close();
    }

    // Menambahkan beberapa data awal agar operasi traversal, searching, deletion, dan sorting mudah diuji.
    static void tambahDataAwal() {
        playlist[jumlahLagu++] = new Lagu("Perfect", "Ed Sheeran", 4.23);
        playlist[jumlahLagu++] = new Lagu("Shivers", "Ed Sheeran", 3.50);
    }

    // Method untuk menampilkan menu utama program.
    static void tampilkanMenu() {
        System.out.println("\n=== MENU PLAYLIST MUSIK ===");
        System.out.println("1. Tampilkan semua lagu");
        System.out.println("2. Tambah lagu baru");
        System.out.println("3. Hapus lagu berdasarkan judul");
        System.out.println("4. Cari lagu berdasarkan judul");
        System.out.println("5. Urutkan berdasarkan durasi");
        System.out.println("6. Keluar");
    }

    // TRAVERSAL
    // Method ini menelusuri seluruh elemen array dari indeks 0 sampai jumlahLagu - 1.
    static void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.println("\nDaftar lagu saat ini:");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.print((i + 1) + ". ");
            playlist[i].tampilkanInfo();
        }
    }

    // INSERTION
    // Method ini menambahkan lagu baru pada indeks terakhir array yang masih kosong.
    static void tambahLagu() {
        if (jumlahLagu >= MAX_LAGU) {
            System.out.println("Playlist sudah penuh. Maksimal hanya dapat menyimpan 10 lagu.");
            return;
        }

        System.out.println("\n=== TAMBAH LAGU BARU ===");
        System.out.print("Masukkan judul lagu    : ");
        String judul = input.nextLine();

        System.out.print("Masukkan artis         : ");
        String artis = input.nextLine();

        double durasi = bacaDouble("Masukkan durasi (menit): ");

        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;

        System.out.println("Lagu berhasil ditambahkan!");
        tampilkanSemuaLagu();
    }

    // DELETION
    // Method ini menghapus lagu berdasarkan judul, lalu menggeser elemen setelahnya ke kiri.
    static void hapusLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong. Tidak ada lagu yang dapat dihapus.");
            return;
        }

        System.out.println("\n=== HAPUS LAGU ===");
        System.out.print("Masukkan judul lagu yang akan dihapus: ");
        String judul = input.nextLine();

        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].judul.equalsIgnoreCase(judul)) {
                // Menggeser elemen setelah lagu yang dihapus agar array tetap rapat.
                for (int j = i; j < jumlahLagu - 1; j++) {
                    playlist[j] = playlist[j + 1];
                }

                // Menghapus referensi terakhir karena datanya sudah digeser.
                playlist[jumlahLagu - 1] = null;
                jumlahLagu--;

                System.out.println("Lagu berhasil dihapus.");
                tampilkanSemuaLagu();
                return;
            }
        }

        System.out.println("Lagu dengan judul \"" + judul + "\" tidak ditemukan.");
    }

    // SEARCHING
    // Method ini mencari lagu berdasarkan judul menggunakan linear search.
    static void cariLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong. Tidak ada lagu yang dapat dicari.");
            return;
        }

        System.out.println("\n=== CARI LAGU ===");
        System.out.print("Masukkan judul lagu yang dicari: ");
        String judul = input.nextLine();

        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].judul.equalsIgnoreCase(judul)) {
                System.out.println("Lagu ditemukan pada indeks ke-" + i + ":");
                playlist[i].tampilkanInfo();
                return;
            }
        }

        System.out.println("Lagu dengan judul \"" + judul + "\" tidak ditemukan.");
    }

    // SORTING
    // Method ini mengurutkan lagu berdasarkan durasi secara ascending menggunakan Bubble Sort.
    static void urutkanLaguBerdasarkanDurasi() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong. Tidak ada lagu yang dapat diurutkan.");
            return;
        }

        System.out.println("\n=== SORTING BERDASARKAN DURASI ===");
        System.out.println("Playlist sebelum diurutkan:");
        tampilkanSemuaLagu();

        // Bubble Sort membandingkan dua elemen bersebelahan.
        for (int i = 0; i < jumlahLagu - 1; i++) {
            for (int j = 0; j < jumlahLagu - i - 1; j++) {
                if (playlist[j].durasi > playlist[j + 1].durasi) {
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }

        System.out.println("\nPlaylist sesudah diurutkan berdasarkan durasi:");
        tampilkanSemuaLagu();
    }

    // Method pembantu untuk membaca input integer agar program tidak error ketika pengguna salah input.
    static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                int nilai = Integer.parseInt(input.nextLine());
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
            }
        }
    }

    // Method pembantu untuk membaca input double agar durasi lagu dapat diinput dengan benar.
    static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                double nilai = Double.parseDouble(input.nextLine());
                if (nilai <= 0) {
                    System.out.println("Durasi harus lebih dari 0 menit.");
                } else {
                    return nilai;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input durasi harus berupa angka. Contoh: 4.10");
            }
        }
    }
}
