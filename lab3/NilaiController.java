public class NilaiController {
    private String kodeMatkul;
    private int nilai;

    public NilaiController(String kodeMatkul){
        this.kodeMatkul = kodeMatkul;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }
    public int getNilai() {
        return nilai;
    }
    public void setNilai(int nilai) {
        this.nilai = nilai;
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab3