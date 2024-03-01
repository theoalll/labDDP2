public class Siswa {
    private String npm;
    private NilaiController[] listNilai = new NilaiController[100];

    public Siswa(String npm){
        this.npm = npm;
    }

// Mengambil matkul (menempatkan object MataKuliah di index array pertama yang kosong)
    public String ambilMatkul(MataKuliah mataKuliah) {
        String output = "";
        output = String.format("Siswa dengan NPM %s berhasil mengambil matkul dengan kode %s", npm, mataKuliah.getKodeMatkul());
        int i = 0;
        for (NilaiController elem: listNilai) {
            if (elem == null) break; // mengambil index array pertama yang kosong
            else i++;
        }
        listNilai[i] = new NilaiController(mataKuliah.getKodeMatkul()); // menempatkan object MataKuliah di index array pertama yang kosong
        return output;

    }

// Menampilkan nilai semua mata kuliah yang diambil siswa
    public String tampilkanNilai() {
        String output = "";
        for (NilaiController elem: listNilai){
            if (elem == null) break;
            else output += String.format("Kode matkul %s memiliki nilai %d", elem.getKodeMatkul(), elem.getNilai());
        }
        return output;
    }

    public NilaiController[] getListNilai() {
        return listNilai;
    }

    public String getNpm() {
        return npm;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab3