public class PaymentMethod {

    private String namaMetode;

    // constructor #1
    public PaymentMethod(String nm) {
        namaMetode = nm;
    }

    // constructor #2
    public PaymentMethod() {
        namaMetode = "";
    }

    // setter
    public void setNamaMetode(String namaMetode) {
        this.namaMetode = namaMetode;
    }

    // getter
    public String getNamaMetode() {
        return namaMetode;
    }
}