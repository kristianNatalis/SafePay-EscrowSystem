public class Transaction implements Payment {
    private static int counter = 1;

    private String id;
    private Barang barang;
    private int jumlah;
    private double total;
    private Status status;
    private String resi;
    private String buyer;
    private String seller;

    public Transaction(Barang barang, int jumlah, String buyer, String seller) {
        this.id = "TRX" + String.format("%03d", counter++);
        this.barang = barang;
        this.jumlah = jumlah;
        this.total = barang.getHarga() * jumlah;
        this.status = new Status();
        this.resi = "-";
        this.buyer = buyer;
        this.seller = seller;
    }

    public String getId() { return id; }
    public Barang getBarang() { return barang; }
    public int getJumlah() { return jumlah; }
    public double getTotal() { return total; }
    public Status getStatus() { return status; }
    public String getResi() { return resi; }
    public String getBuyer() { return buyer; }
    public String getSeller() { return seller; }

    public void setStatus(Status status) { this.status = status; }
    public void setResi(String resi) { this.resi = resi; }

    @Override
    public void bayar() {
        if (this.status.equals(Status.MENUNGGU)) {
            this.status.setNilai(Status.DIBAYAR);
            System.out.println("  Pembayaran berhasil! Dana ditahan oleh SafePay.");
        } else {
            System.out.println("  Transaksi sudah dibayar atau sudah selesai.");
        }
    }

    public void tampil() {
        System.out.println("  =================================");
        System.out.println("  ID       : " + id);
        System.out.println("  Barang   : " + barang.getNama());
        System.out.println("  Jumlah   : " + jumlah);
        System.out.println("  Total    : Rp " + String.format("%,.0f", total));
        System.out.println("  Status   : " + status);
        System.out.println("  Resi     : " + resi);
        System.out.println("  Buyer    : " + buyer);
        System.out.println("  Seller   : " + seller);
        System.out.println("  =================================");
    }
}
