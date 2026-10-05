public class Figure extends Produk {

    private String bahan;

    // constructor #1
    public Figure(String n, float h, int j, String b) {
        super(n, h, j);
        bahan = b;
    }

    // constructor #2
    public Figure() {
        super();
        bahan = "";
    }

    // setter
    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    // getter
    public String getBahan() {
        return bahan;
    }

    // menghitung diskon Figure
    @Override
    public float hitungDiskon() {
        return 0;
    }
}