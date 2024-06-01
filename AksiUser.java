import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    // method lihat film
    @Override
    public void lihatListFilm() {
        System.out.println("Daftar Film:");
        for (Film film : Film.getFilms().values()) {
            System.out.println("Nama: " + film.getName() + " - Deskripsi: " + film.getDescription() +
                    " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    // method lihat saldo
    public void lihatSaldo() {
        System.out.println("Saldo Anda: " + Akun.getCurrentUser().getSaldo());
    }

    // method pesan film
    public void pesanFilm() {

        Scanner scanner = new Scanner(System.in);

        //Input Nama Film
        System.out.print("Nama film: ");
        String namaFilm = scanner.nextLine();

        //Input Jumlah Tiket
        System.out.print("Jumlah tiket: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        Film film = Film.getFilms().get(namaFilm);
        // jika film tidak ada di-list
        if (film == null) {
            System.out.println("Film tidak ditemukan.");
            return;
        }

        if (film.getStock() < jumlah) {
        // jika stok tiket film abis
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        // mengkalikan harga film dengan jumlah yang akan dibeli
        double totalHarga = film.getPrice() * jumlah;
        // jika saldo user lagi tanggal tua
        if (Akun.getCurrentUser().getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi.");
            return;
        }

        // mengurangi jumlah stok film dengan yang jumlah yang telah dibeli user
        film.setStock(film.getStock() - jumlah);
        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);

        // menambahkan film dan jumlah yang dipesan user ke histori (agar bisa dilihat di "lihat pesanan")
        Akun.getCurrentUser().addPesanan(film, jumlah);
        System.out.println("Tiket berhasil dipesan. Total harga: " + totalHarga);
    }

    // method lihat pesanan
    public void lihatPesanan() {
        Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();
        // Jika user belum pernah beli (tidak punya riwayat)
        if (pesanan.isEmpty()) {
            System.out.println("Anda belum pernah memesan tiket.");
        } else {
            // Jika user pernah beli
            System.out.println("Daftar Pesanan:");
            for (Pesanan p : pesanan.values()) {
                String namaFilm = p.getFilm().getName();
                int jumlah = p.getKuantitas();
                double hargaPerTiket = p.getFilm().getPrice();
                double totalHarga = jumlah * hargaPerTiket;
                System.out.println("Film: " + namaFilm + ", Jumlah: " + jumlah + ", Total Harga: " + totalHarga);
            }
        }
    }
    
}
