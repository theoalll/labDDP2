import java.util.Scanner;

public class kalkulatorIp {
    public static void main (String[] args) {
// Deklarasi dan inisialisasi variabel yang digunakan untuk mengolah data
        int jmlMatkul;
        double totalSksLulus = 0;
        double totalSKS = 0;
        double totalMutu = 0;
        double totalMutuLulus = 0;

// Mengambil input jumlah mata kuliah yang diambil
        Scanner in = new Scanner(System.in);
        System.out.print("Masukkan jumlah mata kuliah: ");
        jmlMatkul = in.nextInt();
        in.nextLine();
// Validasi input jumlah mata kuliah yang diambil
        while (jmlMatkul<0) {
            System.out.println("Jumlah mata kuliah yang diambil tidak dapat negatif, silahkan isi kembali");
            System.out.print("Masukkan jumlah mata kuliah: ");
            jmlMatkul = in.nextInt();
            in.nextLine();
        }

// Looping sebanyak jumlah mata kuliah yang diambil untuk mengambil input-input
        for (int i = 1; i<=jmlMatkul; i++){
            double bobot;
        // Meminta input pertama
            System.out.print("Masukkan nama mata kuliah ke-" + i + ": ");
            String namaMatkul = in.nextLine();
            System.out.print("Masukkan jumlah sks: ");
            int jmlSks = in.nextInt();
            in.nextLine();
        // Validasi input jumlah sks
            while (jmlSks<=0){
                System.out.println("Jumlah SKS mata kuliah yang diambil tidak dapat negatif atau 0, silahkan isi kembali");
                System.out.print("Masukkan jumlah sks: ");
                jmlSks = in.nextInt();
                in.nextLine();
            }
            // Jika jumlah sks mata kuliah yang diambil valid, jumlah sks total ditambahkan
            totalSKS += jmlSks;
            System.out.print("Masukkan nilai: ");
            double nilai = in.nextDouble();
            in.nextLine();
        // Validasi input nilai
            while (nilai<0 || nilai>100){
                System.out.println("Nilai yang didapat tidak valid! Silahkan ulangi!");
                System.out.print("Masukkan nilai: ");
                nilai = in.nextDouble();
                in.nextLine();
            }

// Meng-assign nilai huruf dan bobot dari nilai yang diinput
            if (nilai<40) {
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah E ");
                bobot=0.0;
            }
            else if (nilai<55){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah D ");
                bobot=1.0;
            }
            else if (nilai<60){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah C ");
                bobot=2.0;
            }
            else if (nilai<65){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah C+ ");
                bobot=2.3;
            }
            else if (nilai<70){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah C+ ");
                bobot=2.7;
            }
            else if (nilai<75){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah B- ");
                bobot=3.0;
            }
            else if (nilai<80){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah B+ ");
                bobot=3.3;
            }
            else if (nilai<85){
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah A- ");
                bobot=3.7;
            }
            else {
                System.out.print("Nilai huruf mata kuliah " + namaMatkul + " adalah A ");
                bobot=4.0;
            }

// Menghitung mutu dari nilai yang diinput
            if (nilai>=55){
                totalSksLulus += jmlSks;
                totalMutuLulus += jmlSks*bobot;
            }
            else{}
            totalMutu += jmlSks*bobot;
            System.out.printf("dengan mutu %.2f \n\n", jmlSks*bobot);
        }

// Menampilkan hasil perhitungan
        System.out.printf("Jumlah mutu: %.2f\n", totalMutu);
        System.out.println("Jumlah sks diambil: " + totalSKS);
        System.out.printf("IP Semester: %.2f\n", totalMutu/totalSKS);
        System.out.printf("Jumlah mutu lulus: %.2f\n", totalMutuLulus);
        System.out.println("Jumlah sks lulus: " + totalSksLulus);
    // Jika jumlah sks lulus lebih dari 0, maka akan ditampilkan IP kumulatif
        if (totalSksLulus>0)
            System.out.printf("IP Kumulatif: %.2f\n", totalMutuLulus/totalSksLulus);
        else
            System.out.printf("IP Kumulatif: 0.00");

        in.close();
    }
}

// DDP_D_2106165660_TheoAnandalemuel_Lab1