import java.util.Scanner;

// Interface digunakan sebagai aturan dasar otorisasi
interface Otorisasi {

    // Method verifikasi pin
    boolean verifikasiPIN(int pinInput);
}

// Class BukuMutasi digunakan untuk mencatat transaksi
class BukuMutasi {

    // Method mencatat aktivitas transaksi
    public void catatAktivitas(String aktivitas) {

        System.out.println("Mutasi : " + aktivitas);
    }
}

// Abstract class rekening
abstract class Rekening implements Otorisasi {

    // Atribut rekening
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;
    private int pin;

    // Buku mutasi dibuat langsung di rekening
    // Hal ini menunjukkan composition
    private BukuMutasi bukuMutasi;

    // Constructor rekening
    public Rekening(
            String nomorRekening,
            String namaPemilik,
            double saldoAwal,
            int pin
    ) {

        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldoAwal;
        this.pin = pin;

        // Membuat buku mutasi langsung
        bukuMutasi = new BukuMutasi();
    }

    // Getter saldo
    public double getSaldo() {

        return saldo;
    }

    // Getter nama pemilik
    public String getNamaPemilik() {

        return namaPemilik;
    }

    // Method setor uang
    public void setor(double jumlahSetor) {

        saldo += jumlahSetor;

        bukuMutasi.catatAktivitas(
                "Setor uang Rp " + jumlahSetor
        );
    }

    // Method tarik dioverride
    public abstract void tarik(double jumlahTarik);

    // Method verifikasi pin
    @Override
    public boolean verifikasiPIN(int pinInput) {

        return pinInput == pin;
    }

    // Method protected mengurangi saldo
    protected void kurangiSaldo(double jumlah) {

        saldo -= jumlah;
    }

    // Method protected mencatat mutasi
    protected void catatMutasi(String isiMutasi) {

        bukuMutasi.catatAktivitas(isiMutasi);
    }

    // Method menampilkan rekening
    public void tampilkanDataRekening() {

        System.out.println("Nama Pemilik   : " + namaPemilik);
        System.out.println("Saldo          : Rp " + saldo);
    }
}

// Rekening reguler turunan dari rekening
class RekeningReguler extends Rekening {

    public RekeningReguler(
            String nomorRekening,
            String namaPemilik,
            double saldoAwal,
            int pin
    ) {

        super(
                nomorRekening,
                namaPemilik,
                saldoAwal,
                pin
        );
    }

    // Override method tarik
    @Override
    public void tarik(double jumlahTarik) {

        double biayaAdmin = 2500;

        double total =
                jumlahTarik + biayaAdmin;

        if (getSaldo() >= total) {

            kurangiSaldo(total);

            catatMutasi(
                    "Tarik uang Rp "
                            + jumlahTarik
            );

            System.out.println("Tarik berhasil");
        }

        else {

            System.out.println("Saldo tidak cukup");
        }
    }
}

// Rekening prioritas turunan dari rekening
class RekeningPrioritas extends Rekening {

    public RekeningPrioritas(
            String nomorRekening,
            String namaPemilik,
            double saldoAwal,
            int pin
    ) {

        super(
                nomorRekening,
                namaPemilik,
                saldoAwal,
                pin
        );
    }

    // Override method tarik
    @Override
    public void tarik(double jumlahTarik) {

        if (jumlahTarik < 500000) {

            System.out.println(
                    "Minimal tarik Rp 500000"
            );

            return;
        }

        if (getSaldo() >= jumlahTarik) {

            kurangiSaldo(jumlahTarik);

            catatMutasi(
                    "Tarik uang prioritas Rp "
                            + jumlahTarik
            );

            System.out.println("Tarik berhasil");
        }

        else {

            System.out.println("Saldo tidak cukup");
        }
    }
}

// Class customer service
class CustomerService {

    private String namaCustomerService;

    // Constructor customer service
    public CustomerService(
            String namaCustomerService
    ) {

        this.namaCustomerService =
                namaCustomerService;
    }

    // Association
    // Customer service hanya berinteraksi dengan nasabah
    public void layaniKeluhan(
            Nasabah nasabah,
            String keluhan
    ) {

        System.out.println(
                "Customer Service "
                        + namaCustomerService
                        + " melayani "
                        + nasabah.getNamaNasabah()
        );

        System.out.println(
                "Keluhan : " + keluhan
        );
    }
}

// Class nasabah
class Nasabah {

    private String namaNasabah;

    // Rekening berasal dari luar
    // Hal ini menunjukkan aggregation
    private Rekening[] daftarRekening =
            new Rekening[3];

    // Constructor nasabah
    public Nasabah(String namaNasabah) {

        this.namaNasabah = namaNasabah;
    }

    // Getter nama nasabah
    public String getNamaNasabah() {

        return namaNasabah;
    }

    // Method tambah rekening
    public void tambahRekening(
            Rekening rekening,
            int posisiArray
    ) {

        daftarRekening[posisiArray] =
                rekening;
    }

    // Getter rekening
    public Rekening getRekening(int index) {

        return daftarRekening[index];
    }

    // Method tampil rekening
    public void tampilkanSemuaRekening() {

        for (int index = 0;
             index < daftarRekening.length;
             index++) {

            if (daftarRekening[index] != null) {

                System.out.println(
                        "\nRekening Ke-"
                                + (index + 1)
                );

                daftarRekening[index]
                        .tampilkanDataRekening();
            }
        }
    }
}

// Class utama program
public class MainNeoBank {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Nasabah nasabah = null;

        CustomerService customerService =
                new CustomerService("Sinta");

        int pilihanMenu;

        do {

            System.out.println("\nMenu NeoBank");
            System.out.println("1. Registrasi Nasabah");
            System.out.println("2. Buka Rekening");
            System.out.println("3. Setor Uang");
            System.out.println("4. Tarik Uang");
            System.out.println("5. Tampilkan Rekening");
            System.out.println("6. Customer Service");
            System.out.println("7. Hapus Nasabah");
            System.out.println("0. Keluar");

            System.out.print("Pilih menu : ");
            pilihanMenu = input.nextInt();
            input.nextLine();

            switch (pilihanMenu) {

                case 1:

                    System.out.print(
                            "Nama nasabah : "
                    );

                    String namaNasabah =
                            input.nextLine();

                    nasabah =
                            new Nasabah(namaNasabah);

                    System.out.println(
                            "Nasabah berhasil dibuat"
                    );

                    break;

                case 2:

                    if (nasabah == null) {

                        System.out.println(
                                "Buat nasabah terlebih dahulu"
                        );

                        break;
                    }

                    System.out.print(
                            "Nomor rekening : "
                    );

                    String nomorRekening =
                            input.nextLine();

                    System.out.print(
                            "Saldo awal : "
                    );

                    double saldoAwal =
                            input.nextDouble();

                    System.out.print(
                            "PIN : "
                    );

                    int pin =
                            input.nextInt();

                    System.out.println(
                            "1. Reguler"
                    );

                    System.out.println(
                            "2. Prioritas"
                    );

                    System.out.print(
                            "Pilih jenis rekening : "
                    );

                    int jenisRekening =
                            input.nextInt();

                    Rekening rekeningBaru;

                    if (jenisRekening == 1) {

                        rekeningBaru =
                                new RekeningReguler(
                                        nomorRekening,
                                        nasabah.getNamaNasabah(),
                                        saldoAwal,
                                        pin
                                );
                    }

                    else {

                        rekeningBaru =
                                new RekeningPrioritas(
                                        nomorRekening,
                                        nasabah.getNamaNasabah(),
                                        saldoAwal,
                                        pin
                                );
                    }

                    System.out.print(
                            "Posisi array rekening 0-2 : "
                    );

                    int posisiArray =
                            input.nextInt();

                    nasabah.tambahRekening(
                            rekeningBaru,
                            posisiArray
                    );

                    System.out.println(
                            "Rekening berhasil dibuat"
                    );

                    break;

                case 3:

                    System.out.print(
                            "Index rekening : "
                    );

                    int indexSetor =
                            input.nextInt();

                    Rekening rekeningSetor =
                            nasabah.getRekening(indexSetor);

                    System.out.print(
                            "Jumlah setor : "
                    );

                    double jumlahSetor =
                            input.nextDouble();

                    rekeningSetor.setor(jumlahSetor);

                    break;

                case 4:

                    System.out.print(
                            "Index rekening : "
                    );

                    int indexTarik =
                            input.nextInt();

                    Rekening rekeningTarik =
                            nasabah.getRekening(indexTarik);

                    System.out.print(
                            "Jumlah tarik : "
                    );

                    double jumlahTarik =
                            input.nextDouble();

                    rekeningTarik.tarik(jumlahTarik);

                    break;

                case 5:

                    nasabah.tampilkanSemuaRekening();

                    break;

                case 6:

                    input.nextLine();

                    System.out.print(
                            "Masukkan keluhan : "
                    );

                    String keluhan =
                            input.nextLine();

                    customerService.layaniKeluhan(
                            nasabah,
                            keluhan
                    );

                    break;

                case 7:

                    nasabah = null;

                    System.out.println(
                            "Nasabah dihapus"
                    );

                    /*
                     Penjelasan:
                     - Rekening tetap ada karena aggregation
                     - Buku mutasi ikut hilang karena composition
                    */

                    break;
            }

        } while (pilihanMenu != 0);
    }
}