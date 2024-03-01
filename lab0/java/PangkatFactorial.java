import java.util.Scanner;

public class PangkatFactorial {
	public static void main (String[] args) {
		int n;
		int m;
		int hasilFact = 1;
		int hasilPangkat = 1;
		Scanner in = new Scanner (System.in);
		
		System.out.print("masukkan bilangan utama (n): ");
		n = in.nextInt();
		System.out.print("masukan pemangkatan (m): ");
		m = in.nextInt();
		
		// Factorial
		int tempn = n;
		while (tempn >= 1) {
			hasilFact *= tempn;
			tempn -= 1;
		}
		
		// Pangkat
		int i;
		for (i=0; i < m; ++i) {
			hasilPangkat *= n;
		}
		
		System.out.println("n factorial = " + hasilFact);
		System.out.println("n pangkat m = " + hasilPangkat);
		
	}
}