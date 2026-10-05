public abstract class Produk {

    // variables - private
    private String nama;
    private float harga;
    private int jumlah;

    // constructor #1
    public Produk(String n, float h, int j) {
        nama = n;
        harga = h;
        jumlah = j;
    }

    // constructor #2
    public Produk() {
        nama = "";
        harga = 0;
        jumlah = 0;
    }

    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public float getHarga() {
        return harga;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    // menghitung total harga produk
    public float hitungTotal() {
        return harga * jumlah;
    }

    // diskon dasar Produk (abstract method)
    public abstract float hitungDiskon();
}