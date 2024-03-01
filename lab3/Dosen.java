public class Dosen {
    private String idDosen;
    private MataKuliah mataKuliah;

    public Dosen(String idDosen, MataKuliah mataKuliah){
        this.idDosen = idDosen;
        this.mataKuliah = mataKuliah;
    }

// Memberikan nilai kepada siswa
    public String beriNilai(String npm, int nilai) {
        String output = "";
        output = String.format("%s berhasil memberikan nilai kepada siswa dengan NPM %s", idDosen, npm);
        return output;
    }

    public String getIdDosen() {
        return idDosen;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab3