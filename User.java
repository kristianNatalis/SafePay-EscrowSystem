import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
    private String username;
    private String password;
    private String nama;

    public User(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNama() { return nama; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setNama(String nama) { this.nama = nama; }

    public abstract void tampilMenu(ArrayList<Transaction> transaksi,
                                     ArrayList<Barang> barang,
                                     Scanner moon);

    public boolean login(String pass) {
        return this.password.equals(pass);
    }

    public boolean login(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }
}
