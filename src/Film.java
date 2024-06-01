import java.util.HashMap;
import java.util.Map;

public class Film {
    private static final Map<String, Film> films = new HashMap<>();
    private final String name;
    private final String description;
    private final double price;
    private int stock;

    public Film(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public static void addFilm(String name, String description, double price, int stock) {
        if (!films.containsKey(name)) {
            Film newFilm = new Film(name, description, price, stock);
            films.put(name, newFilm);
            System.out.println("Film " + name + " berhasil ditambahkan.");
        } else {
            System.out.println("Film " + name + " sudah ada.");
        }
    }

    public static Map<String, Film> getFilms() {
        return films;
    }

    public static Film isAvailable(String name) {

        for (Map.Entry<String, Film> film : Film.getFilms().entrySet()) {
            if (film.getValue().getName().equalsIgnoreCase(name)) {
                return film.getValue();
            }
        }

        return null;
    }

}
