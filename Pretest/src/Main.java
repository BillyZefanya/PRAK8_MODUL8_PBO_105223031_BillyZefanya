abstract class Karyawan {
    protected  String nama;

    public Karyawan (String nama) {
    this.nama = nama;
    }

    abstract double HitungGaji();

}

class Programmer extends Karyawan {
    private double gajiPokok;
   
    public Programmer (String nama, double gajiPokok){
    super (nama);
    this.gajiPokok = gajiPokok;
   }
   
   @Override
   double HitungGaji() {
    return gajiPokok;
    }

    public String getNama() {
        return nama;
    }

}
public class Main {
    public static void main(String[] args) throws Exception {
        Programmer programmer1 = new Programmer("Billy", 5000.0);
        System.out.println("Nama: " + programmer1.getNama());
        System.out.println("Gaji: " + programmer1.HitungGaji());
    }
}
