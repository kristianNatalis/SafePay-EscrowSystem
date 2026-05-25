public class Status {
    public static final String MENUNGGU = "MENUNGGU_PEMBAYARAN";
    public static final String DIBAYAR  = "SUDAH_DIBAYAR";
    public static final String DIKIRIM  = "DIKIRIM";
    public static final String SELESAI  = "SELESAI";

    private String nilai;

    public Status() {
        this.nilai = MENUNGGU;
    }

    public Status(String nilai) {
        this.nilai = nilai;
    }

    public String getNilai() { return nilai; }

    public void setNilai(String nilai) {
        if (nilai.equals(MENUNGGU) || nilai.equals(DIBAYAR) || nilai.equals(DIKIRIM) || nilai.equals(SELESAI)) {
            this.nilai = nilai;
        } else {
            System.out.println("  Status tidak valid!");
        }
    }

    public boolean equals(String statusLain) {
        return this.nilai.equals(statusLain);
    }

    @Override
    public String toString() { return nilai; }
}
