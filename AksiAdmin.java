import java.util.Scanner;

public class AksiAdmin extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
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

    // method tambah film
    public void tambahFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film: ");
        String nama = scanner.nextLine();
        System.out.print("Deskripsi Film: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double harga = scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Film.addFilm(nama, deskripsi, harga, stok);
    }
}
