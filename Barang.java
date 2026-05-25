public class Barang {
    private String nama;
    private double harga;
    private int stok;
    private String seller;

    public Barang(String nama, double harga, int stok, String seller) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.seller = seller;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
    public String getSeller() { return seller; }

    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }

    public void kurangiStok(int jumlah) {
        if (jumlah <= stok) {
            stok -= jumlah;
        }
    }

    public void tambahStok(int jumlah) {
        stok += jumlah;
    }

    public void tampil() {
        System.out.println("  Nama   : " + nama);
        System.out.println("  Harga  : Rp " + String.format("%,.0f", harga));
        System.out.println("  Stok   : " + stok);
        System.out.println("  Seller : " + seller);
    }
}
