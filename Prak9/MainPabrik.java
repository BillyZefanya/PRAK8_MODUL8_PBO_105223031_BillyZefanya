import java.util.*;

// Class Ban digunakan untuk menyimpan data ban
class Ban {

    // Atribut ban
    private String merkBan;
    private int ukuranRing;

    // Constructor ban
    public Ban(String merkBan, int ukuranRing) {

        this.merkBan = merkBan;
        this.ukuranRing = ukuranRing;
    }

    // Method untuk menampilkan data ban
    public void tampilkanBan() {

        System.out.println("Merk Ban    : " + merkBan);
        System.out.println("Ukuran Ring : " + ukuranRing);
    }
}

// Class Mesin digunakan untuk menyimpan data mesin
class Mesin {

    // Atribut mesin
    private String nomorSeriMesin;
    private int kapasitasCC;

    // Constructor mesin
    public Mesin(String nomorSeriMesin, int kapasitasCC) {

        this.nomorSeriMesin = nomorSeriMesin;
        this.kapasitasCC = kapasitasCC;
    }

    // Method untuk menampilkan data mesin
    public void tampilkanMesin() {

        System.out.println("Nomor Seri Mesin : " + nomorSeriMesin);
        System.out.println("Kapasitas CC     : " + kapasitasCC);
    }
}

// Class Mobil digunakan untuk menyimpan data mobil
class Mobil {

    // Atribut mobil
    private String merkMobil;
    private String warnaMobil;

    // Mesin dibuat langsung di dalam mobil
    // Hal ini menunjukkan composition
    private Mesin mesinMobil;

    // Ban berasal dari luar lalu dipasang ke mobil
    // Hal ini menunjukkan aggregation
    private Ban[] daftarBan = new Ban[4];

    // Constructor mobil
    public Mobil(
            String merkMobil,
            String warnaMobil,
            String nomorSeriMesin,
            int kapasitasCC
    ) {

        this.merkMobil = merkMobil;
        this.warnaMobil = warnaMobil;

        // Membuat mesin langsung di constructor mobil
        mesinMobil =
                new Mesin(nomorSeriMesin, kapasitasCC);
    }

    // Method memasang ban
    public void pasangSetBan(Ban[] setBan) {

        for (int index = 0; index < setBan.length; index++) {

            daftarBan[index] = setBan[index];
        }
    }

    // Method menampilkan spesifikasi mobil
    public void tampilkanSpesifikasi() {

        System.out.println("\nSpesifikasi Mobil");

        System.out.println("Merk Mobil : " + merkMobil);
        System.out.println("Warna      : " + warnaMobil);

        System.out.println("\nData Mesin");

        mesinMobil.tampilkanMesin();

        System.out.println("\nData Ban");

        for (int index = 0; index < daftarBan.length; index++) {

            if (daftarBan[index] != null) {

                System.out.println("\nBan Ke-" + (index + 1));

                daftarBan[index].tampilkanBan();
            }
        }
    }
}

// Class Montir digunakan untuk menyimpan data montir
class Montir {

    // Atribut montir
    private String idMontir;
    private String namaMontir;

    // Constructor montir
    public Montir(String idMontir, String namaMontir) {

        this.idMontir = idMontir;
        this.namaMontir = namaMontir;
    }

    // Method ini menunjukkan association
    // Montir hanya memeriksa mobil
    public void lakukanQualityControl(Mobil mobil) {

        System.out.println("\nProses quality control");

        System.out.println(
                "Montir "
                        + namaMontir
                        + " sedang memeriksa mobil"
        );
    }
}

// Class utama untuk menjalankan program
public class MainPabrik {

    public static void main(String[] args) {

        // Membuat 4 ban independen
        Ban ban1 =
                new Ban("Bridgestone", 17);

        Ban ban2 =
                new Ban("Bridgestone", 17);

        Ban ban3 =
                new Ban("Bridgestone", 17);

        Ban ban4 =
                new Ban("Bridgestone", 17);

        // Membuat array ban
        Ban[] setBanMobil = {
                ban1,
                ban2,
                ban3,
                ban4
        };

        // Membuat mobil
        Mobil mobil1 =
                new Mobil(
                        "Toyota",
                        "Hitam",
                        "MSN-001",
                        1500
                );

        // Memasang ban ke mobil
        mobil1.pasangSetBan(setBanMobil);

        // Menampilkan spesifikasi mobil
        mobil1.tampilkanSpesifikasi();

        // Membuat montir
        Montir montir1 =
                new Montir("MTR-01", "Joko");

        // Montir melakukan quality control
        montir1.lakukanQualityControl(mobil1);

        // Mobil dihancurkan
        mobil1 = null;

        System.out.println("\nMobil sudah dihancurkan");

        // Ban masih ada karena aggregation
        ban1.tampilkanBan();

        /*
         Penjelasan:
         - Mesin ikut hilang karena composition
         - Ban tetap ada karena aggregation
         - Montir tetap ada karena association
        */
    }
}