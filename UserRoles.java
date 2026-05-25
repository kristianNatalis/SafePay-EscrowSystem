import java.util.ArrayList;
import java.util.Scanner;

class Buyer extends User {
    public Buyer(String username, String password, String nama) {
        super(username, password, nama);
    }

    @Override
    public void tampilMenu(ArrayList<Transaction> transaksi,
                           ArrayList<Barang> barang,
                           Scanner moon) {
        int pilih;
        do {
            System.out.println("\n  ===============================");
            System.out.println("  =       MENU BUYER - " + getNama());
            System.out.println("  ===============================");
            System.out.println("  =  1. Lihat Barang            =");
            System.out.println("  =  2. Beli Barang             =");
            System.out.println("  =  3. Lihat Transaksi         =");
            System.out.println("  =  4. Konfirmasi Diterima     =");
            System.out.println("  =  0. Logout                  =");
            System.out.println("  ===============================");
            System.out.print("  Pilih : ");
            pilih = moon.nextInt();
            moon.nextLine();

            switch (pilih) {
                case 1: lihatBarang(barang); break;
                case 2: beli(transaksi, barang, moon); break;
                case 3: lihatTransaksi(transaksi); break;
                case 4: konfirmasi(transaksi, moon); break;
                case 0: System.out.println("  Logout berhasil."); break;
                default: System.out.println("  Pilihan salah.");
            }
        } while (pilih != 0);
    }

    private void lihatBarang(ArrayList<Barang> barang) {
        System.out.println("\n  --- BARANG TERSEDIA ---");
        boolean ada = false;
        for (Barang b : barang) {
            if (b.getStok() > 0) {
                b.tampil();
                System.out.println();
                ada = true;
            }
        }
        if (!ada) System.out.println("  Kosong.");
    }

    private void beli(ArrayList<Transaction> transaksi,
                      ArrayList<Barang> barang,
                      Scanner moon) {
        lihatBarang(barang);
        System.out.print("\n  Nama barang : ");
        String nama = moon.nextLine();
        System.out.print("  Jumlah      : ");
        int jumlah = moon.nextInt();
        moon.nextLine();

        for (Barang b : barang) {
            if (b.getNama().equalsIgnoreCase(nama) && b.getStok() >= jumlah) {
                b.kurangiStok(jumlah);
                Transaction t = new Transaction(b, jumlah, getUsername(), b.getSeller());
                transaksi.add(t);
                System.out.println("\n  Transaksi dibuat!");
                System.out.println("  ID    : " + t.getId());
                System.out.println("  Total : Rp " + String.format("%,.0f", t.getTotal()));
                System.out.println("\n  Bayar sekarang? (y/n)");
                System.out.print("  > ");
                String jawab = moon.nextLine();
                if (jawab.equalsIgnoreCase("y")) {
                    t.bayar();
                }
                return;
            }
        }
        System.out.println("  Barang tidak ada atau stok kurang.");
    }

    private void lihatTransaksi(ArrayList<Transaction> transaksi) {
        System.out.println("\n  --- TRANSAKSI SAYA ---");
        boolean ada = false;
        for (Transaction t : transaksi) {
            if (t.getBuyer().equals(getUsername())) {
                t.tampil();
                ada = true;
            }
        }
        if (!ada) System.out.println("  Belum ada.");
    }

    private void konfirmasi(ArrayList<Transaction> transaksi, Scanner moon) {
        lihatTransaksi(transaksi);
        System.out.print("\n  ID transaksi : ");
        String id = moon.nextLine();
        boolean ketemu = false;
        for (Transaction t : transaksi) {
            if (t.getId().equals(id) && t.getBuyer().equals(getUsername())) {
                if (t.getStatus().equals(Status.DIKIRIM)) {
                    t.getStatus().setNilai(Status.SELESAI);
                    System.out.println("  Barang diterima! Status: SELESAI");
                } else {
                    System.out.println("  Belum dikirim.");
                }
                ketemu = true;
                break;
            }
        }
        if (!ketemu) System.out.println("  ID tidak ditemukan.");
    }
}

class Seller extends User {
    public Seller(String username, String password, String nama) {
        super(username, password, nama);
    }

    @Override
    public void tampilMenu(ArrayList<Transaction> transaksi,
                           ArrayList<Barang> barang,
                           Scanner moon) {
        int pilih;
        do {
            System.out.println("\n  ===============================");
            System.out.println("  =       MENU SELLER - " + getNama());
            System.out.println("  ===============================");
            System.out.println("  =  1. Lihat Pesanan           =");
            System.out.println("  =  2. Input Resi              =");
            System.out.println("  =  3. Update Status Kirim     =");
            System.out.println("  =  4. Tambah Stok             =");
            System.out.println("  =  5. Tambah Barang Baru      =");
            System.out.println("  =  0. Logout                  =");
            System.out.println("  ===============================");
            System.out.print("  Pilih : ");
            pilih = moon.nextInt();
            moon.nextLine();

            switch (pilih) {
                case 1: lihatPesanan(transaksi); break;
                case 2: inputResi(transaksi, moon); break;
                case 3: updateKirim(transaksi, moon); break;
                case 4: tambahStok(barang, moon); break;
                case 5: tambahBarang(barang, moon); break;
                case 0: System.out.println("  Logout berhasil."); break;
                default: System.out.println("  Pilihan salah.");
            }
        } while (pilih != 0);
    }

    private void lihatPesanan(ArrayList<Transaction> transaksi) {
        System.out.println("\n  --- PESANAN MASUK ---");
        boolean ada = false;
        for (Transaction t : transaksi) {
            if (t.getSeller().equals(getUsername())) {
                t.tampil();
                ada = true;
            }
        }
        if (!ada) System.out.println("  Kosong.");
    }

    private void inputResi(ArrayList<Transaction> transaksi, Scanner moon) {
        lihatPesanan(transaksi);
        System.out.print("\n  ID transaksi : ");
        String id = moon.nextLine();
        System.out.print("  Nomor resi   : ");
        String resi = moon.nextLine();
        boolean ketemu = false;
        for (Transaction t : transaksi) {
            if (t.getId().equals(id) && t.getSeller().equals(getUsername())) {
                t.setResi(resi);
                System.out.println("  Resi tersimpan: " + resi);
                ketemu = true;
                break;
            }
        }
        if (!ketemu) System.out.println("  ID tidak ditemukan.");
    }

    private void updateKirim(ArrayList<Transaction> transaksi, Scanner moon) {
        lihatPesanan(transaksi);
        System.out.print("\n  ID transaksi : ");
        String id = moon.nextLine();
        boolean ketemu = false;
        for (Transaction t : transaksi) {
            if (t.getId().equals(id) && t.getSeller().equals(getUsername())) {
                if (t.getStatus().equals(Status.DIBAYAR)) {
                    t.getStatus().setNilai(Status.DIKIRIM);
                    System.out.println("  Status jadi DIKIRIM.");
                } else {
                    System.out.println("  Belum dibayar atau sudah selesai.");
                }
                ketemu = true;
                break;
            }
        }
        if (!ketemu) System.out.println("  ID tidak ditemukan.");
    }

    private void tambahStok(ArrayList<Barang> barang, Scanner moon) {
        System.out.println("\n  --- BARANG SAYA ---");
        for (Barang b : barang) {
            if (b.getSeller().equals(getUsername())) {
                b.tampil();
                System.out.println();
            }
        }
        System.out.print("  Nama barang  : ");
        String nama = moon.nextLine();
        System.out.print("  Tambah stok  : ");
        int jumlah = moon.nextInt();
        moon.nextLine();
        for (Barang b : barang) {
            if (b.getNama().equalsIgnoreCase(nama) && b.getSeller().equals(getUsername())) {
                b.tambahStok(jumlah);
                System.out.println("  Stok sekarang: " + b.getStok());
                return;
            }
        }
        System.out.println("  Barang tidak ditemukan.");
    }

    private void tambahBarang(ArrayList<Barang> barang, Scanner moon) {
        System.out.println("\n  --- TAMBAH BARANG BARU ---");
        System.out.print("  Nama barang  : ");
        String nama = moon.nextLine();
        System.out.print("  Harga        : ");
        double harga = moon.nextDouble();
        moon.nextLine();
        System.out.print("  Stok awal    : ");
        int stok = moon.nextInt();
        moon.nextLine();

        barang.add(new Barang(nama, harga, stok, getUsername()));
        System.out.println("  Barang baru ditambahkan!");
    }
}

class Admin extends User {
    public Admin(String username, String password, String nama) {
        super(username, password, nama);
    }

    @Override
    public void tampilMenu(ArrayList<Transaction> transaksi,
                           ArrayList<Barang> barang,
                           Scanner moon) {
        int pilih;
        do {
            System.out.println("\n  ===============================");
            System.out.println("  =       MENU ADMIN - " + getNama());
            System.out.println("  ===============================");
            System.out.println("  =  1. Lihat Semua Transaksi   =");
            System.out.println("  =  2. Verifikasi Pembayaran   =");
            System.out.println("  =  3. Lepas Dana              =");
            System.out.println("  =  4. Lihat Semua Akun        =");
            System.out.println("  =  5. Tambah Akun Buyer       =");
            System.out.println("  =  6. Tambah Akun Seller      =");
            System.out.println("  =  0. Logout                  =");
            System.out.println("  ===============================");
            System.out.print("  Pilih : ");
            pilih = moon.nextInt();
            moon.nextLine();

            switch (pilih) {
                case 1: lihatSemua(transaksi); break;
                case 2: verifikasi(transaksi, moon); break;
                case 3: lepasDana(transaksi, moon); break;
                case 4: lihatAkun(); break;
                case 5: tambahBuyer(moon); break;
                case 6: tambahSeller(moon); break;
                case 0: System.out.println("  Logout berhasil."); break;
                default: System.out.println("  Pilihan salah.");
            }
        } while (pilih != 0);
    }

    private void lihatSemua(ArrayList<Transaction> transaksi) {
        System.out.println("\n  --- SEMUA TRANSAKSI ---");
        if (transaksi.isEmpty()) {
            System.out.println("  Kosong.");
            return;
        }
        for (Transaction t : transaksi) {
            t.tampil();
        }
    }

    private void verifikasi(ArrayList<Transaction> transaksi, Scanner moon) {
        System.out.println("\n  --- VERIFIKASI PEMBAYARAN ---");
        System.out.println("  Yang menunggu pembayaran:");
        boolean ada = false;
        for (Transaction t : transaksi) {
            if (t.getStatus().equals(Status.MENUNGGU)) {
                t.tampil();
                ada = true;
            }
        }
        if (!ada) { System.out.println("  Tidak ada."); return; }

        System.out.print("\n  ID transaksi : ");
        String id = moon.nextLine();
        for (Transaction t : transaksi) {
            if (t.getId().equals(id) && t.getStatus().equals(Status.MENUNGGU)) {
                t.bayar();
                System.out.println("  Verifikasi berhasil.");
                return;
            }
        }
        System.out.println("  ID tidak ditemukan.");
    }

    private void lepasDana(ArrayList<Transaction> transaksi, Scanner moon) {
        System.out.println("\n  --- LEPAS DANA ---");
        System.out.println("  Yang sudah SELESAI:");
        boolean ada = false;
        for (Transaction t : transaksi) {
            if (t.getStatus().equals(Status.SELESAI)) {
                t.tampil();
                ada = true;
            }
        }
        if (!ada) { System.out.println("  Tidak ada."); return; }

        System.out.print("\n  ID transaksi : ");
        String id = moon.nextLine();
        for (Transaction t : transaksi) {
            if (t.getId().equals(id) && t.getStatus().equals(Status.SELESAI)) {
                System.out.println("  Dana Rp " + String.format("%,.0f", t.getTotal()) + " dikirim ke " + t.getSeller());
                System.out.println("  Transaksi " + id + " selesai.");
                return;
            }
        }
        System.out.println("  ID tidak ditemukan.");
    }

    private void lihatAkun() {
        System.out.println("\n  --- DAFTAR AKUN ---");
        System.out.println("\n  [BUYER]");
        for (Buyer b : SafePay.daftarBuyer) {
            System.out.println("  User : " + b.getUsername() + " | Pass : " + b.getPassword() + " | Nama : " + b.getNama());
        }
        System.out.println("\n  [SELLER]");
        for (Seller s : SafePay.daftarSeller) {
            System.out.println("  User : " + s.getUsername() + " | Pass : " + s.getPassword() + " | Nama : " + s.getNama());
        }
        System.out.println("\n  [ADMIN]");
        for (Admin a : SafePay.daftarAdmin) {
            System.out.println("  User : " + a.getUsername() + " | Pass : " + a.getPassword() + " | Nama : " + a.getNama());
        }
    }

    private void tambahBuyer(Scanner moon) {
        System.out.println("\n  --- TAMBAH BUYER ---");
        System.out.print("  Username : ");
        String user = moon.nextLine();
        System.out.print("  Password : ");
        String pass = moon.nextLine();
        System.out.print("  Nama     : ");
        String nama = moon.nextLine();
        SafePay.daftarBuyer.add(new Buyer(user, pass, nama));
        System.out.println("  Buyer ditambahkan!");
    }

    private void tambahSeller(Scanner moon) {
        System.out.println("\n  --- TAMBAH SELLER ---");
        System.out.print("  Username : ");
        String user = moon.nextLine();
        System.out.print("  Password : ");
        String pass = moon.nextLine();
        System.out.print("  Nama     : ");
        String nama = moon.nextLine();
        SafePay.daftarSeller.add(new Seller(user, pass, nama));
        System.out.println("  Seller ditambahkan!");
    }
}
