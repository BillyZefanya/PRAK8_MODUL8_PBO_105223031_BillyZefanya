// Abstract Class
abstract class LayananPengiriman {
    protected String noResi;
    protected double beratBarang;
    protected double jarakTempuh;

    // Constructor
    public LayananPengiriman(String noResi,
                              double beratBarang,
                              double jarakTempuh) {

        this.noResi = noResi;
        this.beratBarang = beratBarang;
        this.jarakTempuh = jarakTempuh;
    }

    // Concrete Method
    public void cetakResi() {
        System.out.println("No Resi       : " + noResi);
        System.out.println("Berat Barang  : " + beratBarang + " kg");
        System.out.println("Jarak Tempuh  : " + jarakTempuh + " km");
    }

    // Abstract Method
    public abstract double hitungOngkosKirim();
}

// Interface LacakKargo
interface LacakKargo {
    void updateStatus(String status);
    String cekLokasiTerakhir();
}

// Interface Asuransi
interface Asuransi {
    double hitungPremi(double nilaiBarang);

    default void cetakPolis() {
        System.out.println(
            "Polis Asuransi aktif: Menanggung kehilangan "
          + "dan kerusakan fisik sebesar 100% dari nilai barang."
        );
    }
}

// Class PengirimanDarat
class PengirimanDarat extends LayananPengiriman
        implements LacakKargo {

    private String jenisTruk;
    private String statusSaatIni;

    public PengirimanDarat(String noResi,
                            double beratBarang,
                            double jarakTempuh,
                            String jenisTruk) {

        super(noResi, beratBarang, jarakTempuh);

        this.jenisTruk = jenisTruk;
        this.statusSaatIni = "Menunggu Kurir";
    }

    @Override
    public double hitungOngkosKirim() {

        double ongkir =
            (beratBarang * 5000) +
            (jarakTempuh * 2000);

        if (jenisTruk.equalsIgnoreCase("Tronton")) {
            ongkir += 150000;
        }

        return ongkir;
    }

    @Override
    public void updateStatus(String status) {
        statusSaatIni = status;
    }

    @Override
    public String cekLokasiTerakhir() {
        return statusSaatIni;
    }
}

// Class PengirimanUdara
class PengirimanUdara extends LayananPengiriman
        implements LacakKargo, Asuransi {

    private String nomorPenerbangan;
    private String statusSaatIni;
    private double nilaiBarang;

    public PengirimanUdara(String noResi,
                            double beratBarang,
                            double jarakTempuh,
                            String nomorPenerbangan,
                            double nilaiBarang) {

        super(noResi, beratBarang, jarakTempuh);

        this.nomorPenerbangan = nomorPenerbangan;
        this.nilaiBarang = nilaiBarang;

        this.statusSaatIni =
            "Menunggu Jadwal Penerbangan";
    }

    @Override
    public double hitungOngkosKirim() {

        return (beratBarang * 25000) +
               (jarakTempuh * 5000);
    }

    @Override
    public double hitungPremi(double nilaiBarang) {
        return nilaiBarang * 0.03;
    }

    @Override
    public void updateStatus(String status) {
        statusSaatIni = status;
    }

    @Override
    public String cekLokasiTerakhir() {
        return statusSaatIni;
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        PengirimanDarat darat =
            new PengirimanDarat(
                "DRT-001",
                50,
                100,
                "Tronton"
            );

        PengirimanUdara udara =
            new PengirimanUdara(
                "UDR-999",
                10,
                800,
                "GA-123",
                5000000
            );

        // Update Status
        darat.updateStatus(
            "Sedang di jalan tol Cipali"
        );

        udara.updateStatus(
            "Transit di Bandara Soekarno-Hatta"
        );

        // Array Polymorphism
        LayananPengiriman[] daftar = {
            darat,
            udara
        };

        // Perulangan
        for (LayananPengiriman lp : daftar) {

            System.out.println(
                "====================================="
            );

            lp.cetakResi();

            // Casting polymorphism
            if (lp instanceof LacakKargo) {

                LacakKargo lk =
                    (LacakKargo) lp;

                System.out.println(
                    "Status Lokasi : "
                    + lk.cekLokasiTerakhir()
                );
            }

            double ongkir =
                lp.hitungOngkosKirim();

            System.out.println(
                "Ongkos Kirim  : Rp " + ongkir
            );

            // Cek asuransi
            if (lp instanceof Asuransi) {

                Asuransi a =
                    (Asuransi) lp;

                a.cetakPolis();

                double premi =
                    a.hitungPremi(5000000);

                System.out.println(
                    "Premi Asuransi : Rp "
                    + premi
                );

                double total =
                    ongkir + premi;

                System.out.println(
                    "Total Tagihan  : Rp "
                    + total
                );

            } else {

                System.out.println(
                    "Total Tagihan  : Rp "
                    + ongkir
                );
            }
        }

        System.out.println(
            "====================================="
        );
    }
}