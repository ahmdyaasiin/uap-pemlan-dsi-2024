import java.util.Map;

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

    public void lihatSaldo() {
        // Implementasi lihat Saldo
        System.out.printf("Saldo anda: %.0f\n", Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        // Implementasi pemesanan film
        Main.scanner.nextLine();

        System.out.print("Nama Film yang ingin dipesan: ");
        String name = Main.scanner.nextLine();

        Film recentFilm = Film.isAvailable(name);
        if(recentFilm == null) {
            System.out.print("Film yang dicari tidak ditemukan.\n");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int quantity = Main.scanner.nextInt();

        if(recentFilm.getStock() < quantity) {
            System.out.print("Stok tiket tidak mencukupi.\n");
            return;
        }

        recentFilm.setStock(recentFilm.getStock()-quantity);

        System.out.printf("Harga satuan tiket %.0f\n", recentFilm.getPrice());
        System.out.printf("Total harga %.0f\n", recentFilm.getPrice() * quantity);

        if (Akun.getCurrentUser().getSaldo() < recentFilm.getPrice() * quantity) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki 100000.");
        } else {
            Akun.getCurrentUser().addPesanan(recentFilm, quantity);
            Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo()-(recentFilm.getPrice() * quantity));
            System.out.println("Tiket berhasil dipesan.");
        }

    }

    public void lihatPesanan() {
        // Implementasi melihat pesanan
        Map<String, Pesanan> pesanans = Akun.getCurrentUser().getPesanan();
        if (pesanans.isEmpty()) {
            System.out.print("Kamu belum pernah melakukan pemesanan.\n");
        } else {
            for (Map.Entry<String, Pesanan> pesanan : pesanans.entrySet()) {
                System.out.printf("Film: %s - Jumlah: %d - Total Harga: %.0f\n", pesanan.getValue().getFilm().getName(), pesanan.getValue().getKuantitas(), pesanan.getValue().getFilm().getPrice()*pesanan.getValue().getKuantitas());
            }
        }

    }
}
