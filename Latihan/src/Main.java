// Abstract Class
abstract class Pembayaran {
    protected String namaPembayar;
    protected double nominal;

    // Constructor
    public Pembayaran(String namaPembayar, double nominal) {
        this.namaPembayar = namaPembayar;
        this.nominal = nominal;
    }

    // Concrete Method
    public void tampilkanDetail() {
        System.out.println("Nama Pembayar : " + namaPembayar);
        System.out.println("Nominal       : Rp " + nominal);
    }

    // Abstract Method
    public abstract void prosesPembayaran();
}

// Interface
interface Keamanan {
    boolean autentikasi();
}

// Class KartuKredit
class KartuKredit extends Pembayaran implements Keamanan {
    private String nomorKartu;

    public KartuKredit(String namaPembayar, double nominal, String nomorKartu) {
        super(namaPembayar, nominal);
        this.nomorKartu = nomorKartu;
    }

    @Override
    public boolean autentikasi() {
        System.out.println("Autentikasi PIN berhasil.");
        return true;
    }

    @Override
    public void prosesPembayaran() {
        double admin = nominal * 0.02;
        double total = nominal + admin;

        System.out.println("Metode Pembayaran : Kartu Kredit");
        System.out.println("Biaya Admin       : Rp " + admin);
        System.out.println("Total Tagihan     : Rp " + total);
    }
}

// Class EWallet
class EWallet extends Pembayaran implements Keamanan {
    private String nomorHP;

    public EWallet(String namaPembayar, double nominal, String nomorHP) {
        super(namaPembayar, nominal);
        this.nomorHP = nomorHP;
    }

    @Override
    public boolean autentikasi() {
        System.out.println("Autentikasi E-Wallet berhasil.");
        return true;
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Metode Pembayaran : E-Wallet");
        System.out.println("Biaya Admin       : Rp 0");
        System.out.println("Total Tagihan     : Rp " + nominal);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        Pembayaran[] daftarPembayaran = new Pembayaran[2];

        daftarPembayaran[0] =
            new KartuKredit("Billy", 500000, "1234-5678-9999");

        daftarPembayaran[1] =
            new EWallet("Zefanya", 250000, "08123456789");

        for (Pembayaran p : daftarPembayaran) {

            p.tampilkanDetail();

            if (p instanceof Keamanan) {

                Keamanan k = (Keamanan) p;

                if (k.autentikasi()) {
                    p.prosesPembayaran();
                }
            }
        }
    }
}