import java.util.Map;

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

    @Override
    public void lihatListFilm() {
        // Implementasi melihat list film
        for (Map.Entry<String, Film> film : Film.getFilms().entrySet()) {
            if (film.getValue().getStock() <= 0) {
                continue;
            }

            System.out.printf("%s - %s - Harga: %.0f - Stok: %d\n", film.getValue().getName(), film.getValue().getDescription(), film.getValue().getPrice(), film.getValue().getStock());
        }
    }

    public void tambahFilm() {
        // Implementasi menambahkan film
        Main.scanner.nextLine();

        System.out.print("Nama Film: ");
        String name = Main.scanner.nextLine();

        System.out.print("Deskripsi Film: ");
        String description = Main.scanner.nextLine();

        System.out.print("Harga Tiket: ");
        double price = Main.scanner.nextDouble();

        System.out.print("Stok Tiket: ");
        int stock = Main.scanner.nextInt();

        Film.addFilm(name, description, price, stock);

    }
}
