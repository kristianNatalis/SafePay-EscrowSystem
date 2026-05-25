import java.util.ArrayList;
import java.util.Scanner;

public class SafePay {

    static ArrayList<Buyer> daftarBuyer = new ArrayList<>();
    static ArrayList<Seller> daftarSeller = new ArrayList<>();
    static ArrayList<Admin> daftarAdmin = new ArrayList<>();
    static ArrayList<Transaction> daftarTransaksi = new ArrayList<>();
    static ArrayList<Barang> daftarBarang = new ArrayList<>();

    public static void main(String[] args) {
        Scanner moon = new Scanner(System.in);
        initData();
        tampilHeader();

        int pilih;
        do {
            System.out.println("\n  -------------------------------");
            System.out.println("  -         MENU UTAMA SAFEPAY         -");
            System.out.println("  --------------------------------");
            System.out.println("  -  1. Login Buyer                    -");
            System.out.println("  -  2. Login Seller                   -");
            System.out.println("  -  3. Login Admin                    -");
            System.out.println("  -  0. Keluar                         -");
            System.out.println("  -------------------------------");
            System.out.print("  Pilih : ");
            pilih = moon.nextInt();
            moon.nextLine();

            switch (pilih) {
                case 1: loginBuyer(moon); break;
                case 2: loginSeller(moon); break;
                case 3: loginAdmin(moon); break;
                case 0: System.out.println("\n  Terima kasih.\n"); break;
                default: System.out.println("  Pilihan salah.");
            }
        } while (pilih != 0);

        moon.close();
    }

    static void initData() {
        daftarBuyer.add(new Buyer("budi", "budi123", "Budi Santoso"));
        daftarSeller.add(new Seller("toko_elektronik", "toko123", "Toko Elektronik"));
        daftarAdmin.add(new Admin("admin", "admin123", "Super Admin"));

        daftarBarang.add(new Barang("Laptop ASUS", 7500000, 5, "toko_elektronik"));
        daftarBarang.add(new Barang("Mouse Logitech", 250000, 10, "toko_elektronik"));
        daftarBarang.add(new Barang("Keyboard Mechanical", 450000, 8, "toko_elektronik"));
    }

    static void tampilHeader() {
        System.out.println();
        System.out.println("  -----------------------------------");
        System.out.println("  -                                          -");
        System.out.println("  -        S A F E P A Y                     -");
        System.out.println("  -    Sistem Rekber / Middleman Online      -");
        System.out.println("  -    Transaksi Aman, Terpercaya            -");
        System.out.println("  -                                          -");
        System.out.println("  -----------------------------------");
        System.out.println();
        System.out.println("  Lihat semua akun di menu Admin.");
    }

    static void loginBuyer(Scanner moon) {
        System.out.println("\n  --- LOGIN BUYER ---");
        System.out.print("  Username : ");
        String user = moon.nextLine();
        System.out.print("  Password : ");
        String pass = moon.nextLine();

        for (Buyer b : daftarBuyer) {
            if (b.login(user, pass)) {
                System.out.println("  Selamat datang, " + b.getNama() + "!");
                b.tampilMenu(daftarTransaksi, daftarBarang, moon);
                return;
            }
        }
        System.out.println("  Salah.");
    }

    static void loginSeller(Scanner moon) {
        System.out.println("\n  --- LOGIN SELLER ---");
        System.out.print("  Username : ");
        String user = moon.nextLine();
        System.out.print("  Password : ");
        String pass = moon.nextLine();

        for (Seller s : daftarSeller) {
            if (s.login(user, pass)) {
                System.out.println("  Selamat datang, " + s.getNama() + "!");
                s.tampilMenu(daftarTransaksi, daftarBarang, moon);
                return;
            }
        }
        System.out.println("  Salah.");
    }

    static void loginAdmin(Scanner moon) {
        System.out.println("\n  --- LOGIN ADMIN ---");
        System.out.print("  Username : ");
        String user = moon.nextLine();
        System.out.print("  Password : ");
        String pass = moon.nextLine();

        for (Admin a : daftarAdmin) {
            if (a.login(user, pass)) {
                System.out.println("  Selamat datang, " + a.getNama() + "!");
                a.tampilMenu(daftarTransaksi, daftarBarang, moon);
                return;
            }
        }
        System.out.println("  Salah.");
    }
}
