public class MataKuliah {
    private String kodeMatkul;
    private Siswa[] listSiswa;
    private int kapasitas;
    private int jumlahSiswa;

    public MataKuliah(String kodeMatkul, int kapasitas){
        this.kodeMatkul = kodeMatkul;
        this.kapasitas = kapasitas;
        this.listSiswa = new Siswa[100];
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public Siswa[] getListSiswa() {
        return listSiswa;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setJumlahSiswa(int jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    }

    public int getJumlahSiswa() {
        return jumlahSiswa;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab3