import java.io.*;
import java.util.StringTokenizer;

/**
 * DekDepeNG
 */
public class DekDepeNG {

    private static InputReader in = new InputReader(System.in);
    private static OutputStream outputStream = System.out;
    private static PrintWriter out = new PrintWriter(outputStream); 
    private static Dosen[] listDosen;
    private static Siswa[] listSiswa;
    private static MataKuliah[] listMataKuliah;

    public static void main(String[] args) {
// Menerima input untuk class Dosen dan MataKuliah
        int jumlahDosen = in.nextInt();
        listDosen = new Dosen[jumlahDosen];
        listMataKuliah = new MataKuliah[jumlahDosen];
// Inisiasi object Dosen dan MataKuliah dan simpan ke dalam array
        for (int i = 0; i < jumlahDosen; i++){
            String idDosen = in.next();
            String kodeMatkul = in.next();
            int kapasitas = in.nextInt();
            MataKuliah mataKuliah = new MataKuliah(kodeMatkul, kapasitas);
            Dosen dosen = new Dosen(idDosen, mataKuliah);
            listMataKuliah[i] = mataKuliah;
            listDosen[i] = dosen;
        }
// Menerima input untuk class Siswa        
        int jumlahSiswa = in.nextInt();
        listSiswa = new Siswa[jumlahSiswa];
        for (int i = 0; i < jumlahSiswa; i++){
            String npm = in.next();
// Inisiasi object Siswa dan simpan ke dalam array
            Siswa siswa = new Siswa(npm);
            listSiswa[i] = siswa;
        }
// Menerima input untuk perintah dan looping sebanyak jumlahPerintah
        int jumlahPerintah = in.nextInt();
        for(int i = 0; i < jumlahPerintah; i++){
            String perintah = in.next();
            switch (perintah) {
                case "BERINILAI": {
                    String idDosen = in.next();
                    String npm = in.next();
                    int nilai = in.nextInt();
                    // Memanggil fungsi untuk memberi nilai
                    beriNilai(idDosen, npm, nilai);
                    break;
                }
                case "CEKNILAI": {
                    String npm = in.next();
                    // Memanggil fungsi untuk mengecek nilai
                    cekNilai(npm);
                    break;
                }
                case "AMBILMATKUL": {
                    String npm = in.next();
                    String kodeMatkul = in.next();
                    // Memanggil fungsi untuk mengambil mata kuliah

                    for (Dosen elem: listDosen) { // Iterasi dosen
                        if (elem.getMataKuliah().getKodeMatkul().equals(kodeMatkul)){ // Validasi jika dosen mengajar matkul yang diambil siswa
                            if (elem.getMataKuliah().getKapasitas()-elem.getMataKuliah().getJumlahSiswa() >=1) { // validasi Jika kapasitas matkul masih tersedia
                                for (Siswa elem1: listSiswa) { // Iterasi siswa
                                    if (elem1.getNpm().equals(npm)){ // Validasi jika siswa ditemukan
                                        out.println(elem1.ambilMatkul(elem.getMataKuliah())); // Memanggil fungsi ambilMatkul
                                        break;
                                    }
                                }
                                // Menambah jumlah siswa yang mengambil matkul
                                elem.getMataKuliah().setJumlahSiswa(elem.getMataKuliah().getJumlahSiswa()+1);
                            }
                            else // Jika kapasitas matkul sudah penuh
                                out.printf("Siswa dengan NPM %s gagal mengambil matkul dengan kode %s\n", npm, kodeMatkul);

                        }
                        break;
                    }
                    break;
                }
            }
        }
        out.close();
    }

// Fungsi untuk memberi nilai kepada siswa 
    public static void beriNilai(String idDosen, String npm, int nilai) {
        for (Siswa elem: listSiswa) { // Iterasi siswa
            if (elem.getNpm().equals(npm)){ // Validasi jika siswa ditemukan
                for (Dosen elem1: listDosen) { // Iterasi dosen
                    if (elem1.getIdDosen().equals(idDosen)) { // Validasi jika dosen ditemukan
                        for(NilaiController elem2: elem.getListNilai()) { // Iterasi nilai
                            if (elem2.getKodeMatkul().equals(elem1.getMataKuliah().getKodeMatkul())){ // Validasi jika matkul ditemukan
                                out.println(elem1.beriNilai(npm, nilai));
                                elem2.setNilai(nilai); 
                                break;
                            }
                            else if (elem2.equals(elem.getListNilai()[(elem.getListNilai()).length-1])){ // Jika matkul tidak ditemukan (sudah mencapai object array terakhir)
                                out.printf("&s gagal memberikan nilai kepada siswa dengan NPM %s\n", idDosen, npm);
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }    
    }

// Fungsi untuk mengecek nilai siswa
    public static void cekNilai(String npm) {
        for (Siswa elem: listSiswa) { // Iterasi siswa
            if (elem.getNpm().equals(npm)) { // Validasi jika siswa ditemukan 
                if (elem.getListNilai()[0] == null){ // Jika siswa belum mengambil matkul (object array nilai PERTAMA masih kosong)
                    out.println("Siswa belum mengambil mata kuliah :v");
                    break;
                }
                else { // Jika siswa sudah mengambil matkul
                    out.println(elem.tampilkanNilai());          
                }
            }
        }
    }

    
    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab3