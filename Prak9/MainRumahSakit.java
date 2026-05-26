import java.util.*;

// Class Pasien digunakan untuk menyimpan data pasien
class Pasien {

    // Atribut pasien
    private String namaPasien;
    private int umurPasien;

    // Constructor untuk mengisi data pasien
    public Pasien(String namaPasien, int umurPasien) {

        this.namaPasien = namaPasien;
        this.umurPasien = umurPasien;
    }

    // Getter nama pasien
    public String getNamaPasien() {

        return namaPasien;
    }

    // Getter umur pasien
    public int getUmurPasien() {

        return umurPasien;
    }
}

// Class Dokter digunakan untuk menyimpan data dokter
class Dokter {

    // Atribut dokter
    private String namaDokter;
    private String spesialisDokter;

    // Constructor dokter
    public Dokter(String namaDokter, String spesialisDokter) {

        this.namaDokter = namaDokter;
        this.spesialisDokter = spesialisDokter;
    }

    // Method untuk menampilkan data dokter
    public void tampilkanDataDokter() {

        System.out.println("Nama Dokter      : " + namaDokter);
        System.out.println("Spesialis Dokter : " + spesialisDokter);
    }

    // Method ini menunjukkan association
    // Dokter hanya berinteraksi dengan pasien
    public void periksaPasien(Pasien pasien) {

        System.out.println("\nProses pemeriksaan pasien");

        System.out.println(
                "Dokter "
                        + namaDokter
                        + " spesialis "
                        + spesialisDokter
                        + " sedang memeriksa pasien "
                        + pasien.getNamaPasien()
                        + " umur "
                        + pasien.getUmurPasien()
                        + " tahun"
        );
    }
}

// Class Ruangan digunakan untuk menyimpan data ruangan
class Ruangan {

    // Atribut ruangan
    private String nomorRuangan;
    private int kapasitasPasien;

    // Constructor ruangan
    public Ruangan(String nomorRuangan, int kapasitasPasien) {

        this.nomorRuangan = nomorRuangan;
        this.kapasitasPasien = kapasitasPasien;
    }

    // Method untuk menampilkan data ruangan
    public void tampilkanDataRuangan() {

        System.out.println("Nomor Ruangan    : " + nomorRuangan);
        System.out.println("Kapasitas Pasien : " + kapasitasPasien);
    }
}

// Class RumahSakit digunakan untuk menyimpan data rumah sakit
class RumahSakit {

    // Nama rumah sakit
    private String namaRumahSakit;

    // Ruangan dibuat langsung di dalam rumah sakit
    // Hal ini menunjukkan composition
    private Ruangan[] daftarRuangan = new Ruangan[2];

    // Dokter berasal dari luar lalu dimasukkan ke rumah sakit
    // Hal ini menunjukkan aggregation
    private Dokter[] daftarDokter = new Dokter[2];

    // Constructor rumah sakit
    public RumahSakit(String namaRumahSakit) {

        this.namaRumahSakit = namaRumahSakit;

        // Membuat ruangan langsung di constructor
        daftarRuangan[0] = new Ruangan("R-01", 10);
        daftarRuangan[1] = new Ruangan("R-02", 20);
    }

    // Method menambah dokter
    public void tambahDokter(Dokter dokter, int posisiArray) {

        daftarDokter[posisiArray] = dokter;
    }

    // Method menampilkan ruangan
    public void tampilkanRuangan() {

        System.out.println("\nData Ruangan Rumah Sakit");

        for (int index = 0; index < daftarRuangan.length; index++) {

            daftarRuangan[index].tampilkanDataRuangan();

            System.out.println();
        }
    }

    // Method menampilkan dokter
    public void tampilkanDokter() {

        System.out.println("\nData Dokter Rumah Sakit");

        for (int index = 0; index < daftarDokter.length; index++) {

            if (daftarDokter[index] != null) {

                daftarDokter[index].tampilkanDataDokter();

                System.out.println();
            }
        }
    }
}

// Class utama untuk menjalankan program
public class MainRumahSakit {

    public static void main(String[] args) {

        // Membuat objek dokter
        Dokter dokter1 =
                new Dokter("Andi", "Jantung");

        Dokter dokter2 =
                new Dokter("Bambang", "Saraf");

        // Membuat objek pasien
        Pasien pasien1 =
                new Pasien("Budi", 20);

        Pasien pasien2 =
                new Pasien("Caca", 22);

        // Dokter memeriksa pasien
        dokter1.periksaPasien(pasien1);

        // Membuat rumah sakit
        RumahSakit rumahSakit =
                new RumahSakit("RS Sehat Selalu");

        // Menambahkan dokter ke rumah sakit
        rumahSakit.tambahDokter(dokter1, 0);
        rumahSakit.tambahDokter(dokter2, 1);

        // Menampilkan data ruangan
        rumahSakit.tampilkanRuangan();

        // Menampilkan data dokter
        rumahSakit.tampilkanDokter();

        // Menghapus rumah sakit
        rumahSakit = null;

        System.out.println("\nObjek rumah sakit sudah dihapus");

        // Dokter masih ada karena aggregation
        dokter1.tampilkanDataDokter();

        /*
         Penjelasan:
         - Ruangan ikut hilang karena composition
         - Dokter tetap ada karena aggregation
         - Pasien tetap ada karena association
        */
    }
}