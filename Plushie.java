public class Plushie extends Produk {

    private String tekstur;

    // constructor #1
    public Plushie(String n, float h, int j, String t) {
        super(n, h, j);
        tekstur = t;
    }

    // constructor #2
    public Plushie() {
        super();
        tekstur = "";
    }

    // setter
    public void setTekstur(String tekstur) {
        this.tekstur = tekstur;
    }

    // getter
    public String getTekstur() {
        return tekstur;
    }

    // menghitung diskon Plushie
    @Override
    public float hitungDiskon() {
        return 0;
    }
}