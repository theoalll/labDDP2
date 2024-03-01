import java.util.Scanner;

public class Nilai {
	public static void main (String[] args) {
		String nama;
		int nilaiAsli;
		int durasi;
		double nilaiAkhir;
		Scanner in = new Scanner(System.in);
		
		System.out.print("Masukkan Nama Mahasiswa: ");
		nama = in.nextLine();
		System.out.print("Masukkan Nilai Asli: ");
		nilaiAsli = in.nextInt();
		System.out.print("Masukkan Durasi: ");
		durasi = in.nextInt();
		
		if (durasi < 60) {
			nilaiAkhir = 1.2*nilaiAsli;
		}
		else if (60 <= durasi & durasi <= 70) {
			nilaiAkhir = nilaiAsli;
		}
		else if (70 < durasi & durasi < 90) {
			nilaiAkhir = 0.75*nilaiAsli;
		}
		else if (90 <= durasi & durasi <=100) {
			nilaiAkhir = 0.5*nilaiAsli;
		}
		else {
			nilaiAkhir = 0.2*nilaiAsli;
		}
		
		System.out.println(nama + " mendapatkan nilai akhir " + nilaiAkhir);
		
	}
}