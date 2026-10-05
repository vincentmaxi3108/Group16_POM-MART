import java.util.ArrayList;

    public class Penjualan implements CetakStruk {

        // Atribut
        private String nomorStruk;
        private String namaToko;
        private String alamatToko;
        private String npwp;
        private String timestamp;
        private float subtotal;
        private float diskon;
        private float ppn;
        private float total;
        private int pointsEarned;
        private boolean shoppingBag;

        // Association
        private ArrayList<Produk> listProduk;
        private PaymentMethod paymentMethod;
        private Member member;

        // Constructor
        public Penjualan(String id, String t, String nt, String a, String n,
                        float s, float d, float p, float to, int pt,
                        boolean sb, PaymentMethod pm, Member m) {

            nomorStruk = id;
            timestamp = t;
            namaToko = nt;
            alamatToko = a;
            npwp = n;
            subtotal = s;
            diskon = d;
            ppn = p;
            total = to;
            pointsEarned = pt;
            shoppingBag = sb;
            paymentMethod = pm;
            member = m;

            listProduk = new ArrayList<>();
        }

        // Constructor kosong
        public Penjualan() {
            listProduk = new ArrayList<>();
        }

        // Setter dan Getter
        public void setNomorStruk(String id) {
            this.nomorStruk = id;
        }

        public String getNomorStruk() {
            return nomorStruk;
        }

        public void setNamaToko(String nt) {
            this.namaToko = nt;
        }

        public String getNamaToko() {
            return namaToko;
        }

        public void setAlamatToko(String a) {
            this.alamatToko = a;
        }

        public String getAlamatToko() {
            return alamatToko;
        }

        public void setNpwp(String n) {
            this.npwp = n;
        }

        public String getNpwp() {
            return npwp;
        }

        public void setTimestamp(String t) {
            this.timestamp = t;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setSubtotal(float s) {
            this.subtotal = s;
        }

        public float getSubtotal() {
            return subtotal;
        }

        public void setDiskon(float d) {
            this.diskon = d;
        }

        public float getDiskon() {
            return diskon;
        }

        public void setPpn(float p) {
            this.ppn = p;
        }

        public float getPpn() {
            return ppn;
        }

        public void setTotal(float to) {
            this.total = to;
        }

        public float getTotal() {
            return total;
        }

        public void setPointsEarned(int pt) {
            this.pointsEarned = pt;
        }

        public int getPointsEarned() {
            return pointsEarned;
        }

        public void setShoppingBag(boolean sb) {
            this.shoppingBag = sb;
        }

        public boolean getShoppingBag() {
            return shoppingBag;
        }

        public void setPaymentMethod(PaymentMethod pm) {
            this.paymentMethod = pm;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public void setMember(Member m) {
            this.member = m;
        }

        public Member getMember() {
            return member;
        }

        public ArrayList<Produk> getListProduk() {
            return listProduk;
        }

        // Menambahkan produk
        public void addProduk(Produk produk) {
            listProduk.add(produk);
        }

        // Menghitung subtotal dari semua produk
        public float hitungSubtotal() {

            subtotal = 0;

            for (Produk produk : listProduk) {
                subtotal = subtotal + produk.hitungTotal();
            }
            
            return subtotal;
        }

        // Menghitung total
        public float hitungTotalFinal() {

            total = subtotal - diskon + ppn;

            return total;
        }

@Override
public void cetakStruk() {

    System.out.println("============================================================");
    System.out.println("                    " + namaToko);
    System.out.println("                 Indonesia Jakarta Central Park");
    System.out.println("      " + alamatToko);
    System.out.println("                    NPWP: " + npwp);
    System.out.println("============================================================");

    System.out.println("No. Struk : " + nomorStruk);
    System.out.println("Tanggal   : " + timestamp);

    System.out.println("------------------------------------------------------------");
    System.out.printf("%-43s %3s %12s%n", "Produk", "Qty", "Harga");
    System.out.println("------------------------------------------------------------");

    for (Produk produk : listProduk) {

        System.out.printf(
            "%-43s %3d %12s%n",
            produk.getNama(),
            produk.getJumlah(),
            formatRupiah(produk.hitungTotal())
        );
    }

    // Menampilkan shopping bag
    if (shoppingBag) {

        if (member != null) {
            System.out.printf(
                "%-43s %3d %12s%n",
                "Shopping Bag S (FREE)",
                1,
                formatRupiah(0)
            );
        } else {
            System.out.printf(
                "%-43s %3d %12s%n",
                "Shopping Bag S",
                1,
                formatRupiah(2000)
            );
        }
    }

    System.out.println("------------------------------------------------------------");

    System.out.printf(
        "%-48s %12s%n",
        "Subtotal",
        formatRupiah(subtotal)
    );

    System.out.printf(
        "%-48s %12s%n",
        "Diskon",
        formatRupiah(diskon)
    );

    System.out.printf(
        "%-48s %12s%n",
        "PPN (12%)",
        formatRupiah(ppn)
    );

    System.out.println("------------------------------------------------------------");

    System.out.printf(
        "%-48s %12s%n",
        "TOTAL",
        formatRupiah(total)
    );

    System.out.println();

    System.out.println("Metode Pembayaran : " + paymentMethod.getNamaMetode());
    System.out.println("Points Earned     : " + pointsEarned);

    System.out.println("------------------------------------------------------------");
    System.out.println("                    Terima kasih!");
    System.out.println("============================================================");
}

    // Method untuk format rupiah
    private String formatRupiah(float amount) {
        return String.format("Rp%,.0f", amount).replace(',', '.');
    }
}