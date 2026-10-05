import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);

    // Menyimpan semua produk Figure & Plushie serta transaksi terakhir
    static ArrayList<Produk> listFigure = new ArrayList<>();
    static ArrayList<Produk> listPlushie = new ArrayList<>();
    static Penjualan penjualanTerakhir = null;

    // Nama file
    static String fileFigure = "data/figure.txt";
    static String filePlushie = "data/plushie.txt";
    static String filePenjualan = "data/penjualan.txt";

    public static void main(String[] args) {
        // Membuat folder data jika belum ada
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Membaca data produk dari file
        loadProduk();

        // Variabel untuk menyimpan pilihan menu
        int pilihan;

        // Perulangan menu utama
        do {
            System.out.println();
            System.out.println("======================================");
            System.out.println("          POM MART INDONESIA");
            System.out.println("======================================");
            System.out.println("1. Lihat Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Buat Penjualan");
            System.out.println("4. Cetak Struk");
            System.out.println("5. Keluar");
            System.out.println("======================================");

            System.out.print("Pilihan: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    lihatProduk();
                    break;
                case 2:
                    tambahProduk();
                    break;
                case 3:
                    buatPenjualan();
                    break;
                case 4:
                    cetakStruk();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println();
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 5);
        input.close();
    }

    // Load produk dari file
    public static void loadProduk() {
        // Membersihkan data sebelum membaca file
        listFigure.clear();
        listPlushie.clear();

        // Membaca file figure
        try {
            // Membuat object File dan mengecek apakah file tersedia
            File file = new File(fileFigure);
            if (file.exists()) {

                // Membuat Scanner untuk membaca file
                Scanner fileInput = new Scanner(file);

                // Membaca setiap baris
                while (fileInput.hasNextLine()) {

                    // Mengambil satu baris
                    String data = fileInput.nextLine();

                    // Menghindari baris kosong
                    if (data.trim().isEmpty()) {
                        continue;
                    }

                    // Memisahkan data berdasarkan |
                    String[] bagian = data.split("\\|");

                    // Mengecek jumlah data
                    if (bagian.length == 4) {

                        // Mengambil data nama, harga, jumlah, bahan
                        String nama = bagian[0];
                        float harga = Float.parseFloat(bagian[1]);
                        int jumlah = Integer.parseInt(bagian[2]);
                        String bahan = bagian[3];

                        // Membuat object Figure
                        Figure figure = new Figure(nama, harga, jumlah, bahan);

                        // Menambahkan object ke ArrayList
                        listFigure.add(figure);
                    }
                }
                fileInput.close();
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca figure.txt");
        }

        // Membaca File Plushie
        try {
            // Membuat object File dan mengecek apakah file tersedia
            File file = new File(filePlushie);
            if (file.exists()) {

                // Membuat Scanner untuk membaca file
                Scanner fileInput = new Scanner(file);

                // Membaca setiap baris
                while (fileInput.hasNextLine()) {

                    // Mengambil satu baris
                    String data = fileInput.nextLine();

                    // Menghindari baris kosong
                    if (data.trim().isEmpty()) {
                        continue;
                    }

                    // Memisahkan data berdasarkan |
                    String[] bagian = data.split("\\|");

                    // Mengecek jumlah data
                    if (bagian.length == 4) {

                        // Mengambil data nama, harga, jumlah, tekstur
                        String nama = bagian[0];
                        float harga = Float.parseFloat(bagian[1]);
                        int jumlah = Integer.parseInt(bagian[2]);
                        String tekstur = bagian[3];

                        // Membuat object Plushie
                        Plushie plushie = new Plushie(nama, harga, jumlah, tekstur);

                        // Menambahkan object ke ArrayList
                        listPlushie.add(plushie);
                    }
                }
                // Menutup Scanner file
                fileInput.close();
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca plushie.txt");
        }
    }

    // Lihat Produk
    public static void lihatProduk() {
        // Menampilkan judul
        System.out.println();
        System.out.println("============== DAFTAR PRODUK ==============");

        // Nomor produk
        int nomor = 1;

        //Menampilkan figure
        System.out.println();
        System.out.println("--- FIGURE ---");

        // Mengecek apakah Figure tersedia
        if (listFigure.isEmpty()) {
            System.out.println("Belum ada Figure.");
        } else {
            // Mengulang semua Figure
            for (Produk produk : listFigure) {
                // Mengubah object menjadi Figure
                Figure figure = (Figure) produk;
                // Menampilkan data Figure
                System.out.println(nomor + ". " + figure.getNama() );
                System.out.println("   Harga : Rp" + (int) figure.getHarga() );
                System.out.println( "   Stok  : " + figure.getJumlah() );
                System.out.println( "   Bahan : " + figure.getBahan() );
                nomor++;
            }
        }

        // Menampilkan Plushie
        System.out.println();
        System.out.println("--- PLUSHIE ---");

        // Mengecek apakah Plushie tersedia
        if (listPlushie.isEmpty()) {
            System.out.println("Belum ada Plushie.");
        } else {
            // Mengulang semua Plushie
            for (Produk produk : listPlushie) {
                // Mengubah object menjadi Plushie
                Plushie plushie = (Plushie) produk;

                // Menampilkan data Plushie
                System.out.println( nomor + ". " + plushie.getNama() );
                System.out.println( "   Harga  : Rp" + (int) plushie.getHarga() );
                System.out.println( "   Stok   : " + plushie.getJumlah() );
                System.out.println( "   Tekstur: " + plushie.getTekstur() );
                nomor++;
            }
        }
        System.out.println(
                "============================================"
        );
    }

    // Tambah Produk
    public static void tambahProduk() {
        // Menampilkan pilihan jenis produk
        System.out.println();
        System.out.println("=========== TAMBAH PRODUK ===========");
        System.out.println("1. Figure");
        System.out.println("2. Plushie");
        System.out.print("Pilih jenis produk: ");

        // Membaca pilihan
        int jenis = input.nextInt();
        input.nextLine();

        // Mengecek jenis produk
        if (jenis != 1 && jenis != 2) {
            System.out.println();
            System.out.println("Jenis produk tidak tersedia.");
            return;
        }

        // Meminta input
        System.out.print("Nama produk: ");
        String nama = input.nextLine();
        System.out.print("Harga: ");
        float harga = input.nextFloat();
        System.out.print("Jumlah stok: ");
        int jumlah = input.nextInt();
        input.nextLine();

        // Menambah Figure
        if (jenis == 1) {
            // Meminta bahan Figure
            System.out.print("Bahan: ");
            String bahan = input.nextLine();

            // Membuat object Figure
            Figure figure = new Figure( nama, harga, jumlah, bahan );

            // Menambahkan object ke ArrayList
            listFigure.add(figure);

            // Menyimpan data ke file
            simpanFigure();
            System.out.println();
            System.out.println( "Figure berhasil ditambahkan." );

        // Menambah Plushie
        } else {
            // Meminta tekstur Plushie
            System.out.print("Tekstur: ");
            String tekstur = input.nextLine();

            // Membuat object Plushie
            Plushie plushie = new Plushie( nama, harga, jumlah, tekstur );

            // Menambahkan object ke ArrayList
            listPlushie.add(plushie);

            // Menyimpan data ke file
            simpanPlushie();
            System.out.println();
            System.out.println( "Plushie berhasil ditambahkan." );
        }
    }

    // Buat Penjualan
    public static void buatPenjualan() {
        // Mengecek apakah ada produk
        if (listFigure.isEmpty() && listPlushie.isEmpty()) {
            System.out.println();
            System.out.println("Belum ada produk.");
            return;
        }

        // Membuat object Penjualan kosong
        Penjualan penjualan = new Penjualan();

        // Data Struk
        // Membuat nomor struk otomatis
        String nomorStruk = "ID09D" + LocalDateTime.now().format( DateTimeFormatter.ofPattern( "yyyyMMddHHmmss" ) );

        // Mengambil timestamp otomatis
        String timestamp = LocalDateTime.now().format( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) );

        // Mengisi data toko
        penjualan.setNomorStruk(nomorStruk);
        penjualan.setNamaToko( "PT POM MART Indonesia" );
        penjualan.setAlamatToko( "Jl. Letjen S. Parman No. Kav. 28, Tj. Duren, Jakbar" );
        penjualan.setNpwp("0997455399012000");
        penjualan.setTimestamp(timestamp);

        // Memilih Produk
        System.out.println();
        System.out.print( "======= TAMBAH PRODUK KE PENJUALAN =======" );

        // Variabel untuk perulangan
        String lagi = "ya";
        do {
            // Meminta jenis produk
            System.out.println();
            System.out.println("1. Figure");
            System.out.println("2. Plushie");
            System.out.print("Pilih jenis: ");

            int jenis = input.nextInt();
            input.nextLine();

            // Memilih Figure
            if (jenis == 1) {
                // Mengecek data Figure
                if (listFigure.isEmpty()) {
                    System.out.println( "Belum ada Figure." );
                    continue;
                }
                // Menampilkan Figure
                for (int i = 0; i < listFigure.size(); i++) {
                    Figure figure = (Figure) listFigure.get(i);
                    System.out.println( (i + 1) + ". " + figure.getNama() + " - Rp" +
                                        (int) figure.getHarga() + " - Stok: " + figure.getJumlah() );
                }
                // Memilih Figure
                System.out.print("Pilih Figure: ");
                int pilihan = input.nextInt();

                // Mengecek pilihan
                if (pilihan < 1 || pilihan > listFigure.size()) {
                    System.out.println( "Pilihan Figure tidak tersedia." );
                    input.nextLine();
                    continue;
                }

                // Mengambil Figure
                Figure produkAsli = (Figure) listFigure.get( pilihan - 1 );

                // Meminta jumlah pembelian
                System.out.print("Jumlah beli: ");
                int jumlahBeli = input.nextInt();
                input.nextLine();

                // Mengecek jumlah
                if (jumlahBeli <= 0) {
                    System.out.println( "Jumlah beli harus lebih dari 0." );
                    continue;
                }

                // Mengecek stok
                if (jumlahBeli > produkAsli.getJumlah()) {
                    System.out.println( "Stok tidak mencukupi." );
                    continue;
                }

                // Membuat object Figure untuk transaksi
                Figure produkTransaksi =
                        new Figure(produkAsli.getNama(),produkAsli.getHarga(),jumlahBeli,produkAsli.getBahan());

                // Memasukkan object ke Penjualan
                penjualan.addProduk(produkTransaksi);

                // Mengurangi stok
                produkAsli.setJumlah( produkAsli.getJumlah() - jumlahBeli );


            // Memilih plushie
            } else if (jenis == 2) {
                // Mengecek data Plushie
                if (listPlushie.isEmpty()) {
                    System.out.println( "Belum ada Plushie." );
                    continue;
                }

                // Menampilkan Plushie
                for (int i = 0; i < listPlushie.size(); i++) {
                    Plushie plushie = (Plushie) listPlushie.get(i);
                    System.out.println( (i + 1) + ". " + plushie.getNama() + " - Rp" +
                                        (int) plushie.getHarga() + " - Stok: " + plushie.getJumlah() );
                }
                // Memilih Plushie
                System.out.print("Pilih Plushie: ");
                int pilihan = input.nextInt();

                // Mengecek pilihan
                if (pilihan < 1 || pilihan > listPlushie.size()) {
                    System.out.println( "Pilihan Plushie tidak tersedia." );
                    input.nextLine();
                    continue;
                }

                // Mengambil Plushie
                Plushie produkAsli = (Plushie) listPlushie.get( pilihan - 1 );

                // Meminta jumlah pembelian
                System.out.print("Jumlah beli: ");
                int jumlahBeli = input.nextInt();
                input.nextLine();

                // Mengecek jumlah
                if (jumlahBeli <= 0) {
                    System.out.println( "Jumlah beli harus lebih dari 0." );
                    continue;
                }
                // Mengecek stok
                if (jumlahBeli > produkAsli.getJumlah()){
                    System.out.println( "Stok tidak mencukupi.");
                    continue;
                }

                // Membuat object Plushie untuk transaksi
                Plushie produkTransaksi =
                        new Plushie(produkAsli.getNama(),produkAsli.getHarga(),jumlahBeli,produkAsli.getTekstur());

                // Memasukkan object ke Penjualan
                penjualan.addProduk(produkTransaksi);

                // Mengurangi stok
                produkAsli.setJumlah( produkAsli.getJumlah() - jumlahBeli );

            // Jenis tidak tersedia
            } else {
                System.out.println( "Jenis produk tidak tersedia." );
                continue;
            }

            // Menanyakan apakah ingin menambah produk lagi
            System.out.print( "Tambah produk lain? (ya/tidak): " );
            lagi = input.nextLine();
        } while (lagi.equalsIgnoreCase("ya"));

        // Mengecek Transaksi
        if (penjualan.getListProduk().isEmpty()) {
            System.out.println( "Tidak ada produk dalam transaksi." );
            return;
        }

        // Menyimpan perubahan stok
        simpanFigure();
        simpanPlushie();

        // Mengecek apakah customer adalah member
        System.out.println();
        System.out.print( "Apakah customer member? (ya/tidak): " );
        String jawabanMember = input.nextLine();
        Member member;

        if (jawabanMember.equalsIgnoreCase("ya")) {
            // Membuat object Member
            member = new Member( "MEMBER001", 0 );
            // Member mendapatkan shopping bag gratis
            penjualan.setShoppingBag(true);
            // Tidak ada diskon shopping bag
            penjualan.setDiskon(0);
        } else {
            // Tidak ada member
            member = null;
            // Non-member membeli shopping bag
            penjualan.setShoppingBag(true);
            // Tidak ada diskon
            penjualan.setDiskon(0);
        }

        // Memasukkan object Member ke Penjualan
        penjualan.setMember(member);

        // Payment Method
        System.out.println();
        System.out.println("Metode pembayaran:");
        System.out.println("1. QRIS");
        System.out.println("2. Debit card");
        System.out.println("3. Credit Card");
        System.out.print("Pilihan: ");

        int pilihanPayment = input.nextInt();
        input.nextLine();
        PaymentMethod paymentMethod;

        if (pilihanPayment == 1) {
            paymentMethod = new PaymentMethod( "QRIS" );
        } else if (pilihanPayment == 2) {
            paymentMethod = new PaymentMethod( "Debit Card" );
        } else if (pilihanPayment == 3) {
            paymentMethod = new PaymentMethod( "Credit Card" );
        } else {
            System.out.println( "Pilihan tidak tersedia. " + "Payment menggunakan QRIS." );
            paymentMethod = new PaymentMethod( "QRIS" );
        }

        // Memasukkan PaymentMethod ke Penjualan
        penjualan.setPaymentMethod(paymentMethod);

        // Hitung Transaksi
        // Menghitung subtotal produk
        float subtotalProduk = penjualan.hitungSubtotal();

        // Menentukan subtotal
        // Non-member mendapat tambahan shopping bag Rp2.000
        if (member == null) {
            penjualan.setSubtotal( subtotalProduk + 2000 );
        } else {
            // Member mendapatkan shopping bag gratis
            penjualan.setSubtotal(subtotalProduk);
        }

        // Hitung PPN 12%, PPN sudah termasuk dalam subtotal
        float ppn = penjualan.getSubtotal() * 0.12f;
        penjualan.setPpn(ppn);

        // Total
        // Karena PPN sudah termasuk subtotal, PPN tidak ditambahkan lagi ke total
        penjualan.setTotal( penjualan.getSubtotal() - penjualan.getDiskon());

        // Points,  menghitung points berdasarkan total
        int points = (int) ( penjualan.getTotal() / 10000 );

        // Menyimpan points
        penjualan.setPointsEarned(points);

        // Menyimpan transaksi terakhir
        penjualanTerakhir = penjualan;

        // Menyimpan transaksi ke file
        simpanPenjualan(penjualan);

        // Hasil Transaksi
        System.out.println();
        System.out.println("======================================");
        System.out.println( "       TRANSAKSI BERHASIL" );
        System.out.println("======================================");
        System.out.println( "Nomor Struk : " + penjualan.getNomorStruk() );
        System.out.println( "Subtotal    : Rp" + (int) penjualan.getSubtotal() );
        System.out.println( "PPN 12%     : Rp" + (int) penjualan.getPpn() );

        if (member == null) {
            System.out.println( "Shopping Bag: Rp2000" );
        } else {
            System.out.println( "Shopping Bag: GRATIS" );
        }
        System.out.println( "Total       : Rp" + (int) penjualan.getTotal() );
        System.out.println( "Points      : " + penjualan.getPointsEarned() );
        System.out.println("======================================");
    }


    // Cetak Struk
    public static void cetakStruk() {
        // Mengecek apakah transaksi sudah dibuat
        if (penjualanTerakhir == null) { System.out.println();
            System.out.println( "Belum ada transaksi." );
            return;
        }
        // Memanggil method dari interface CetakStruk
        penjualanTerakhir.cetakStruk();
    }

    // Simpan Figure
    public static void simpanFigure() {
        try {
            // Membuat folder data
            File folder = new File("data");
            folder.mkdirs();

            // Membuka file Figure
            FileWriter writer = new FileWriter( fileFigure );

            // Menulis semua Figure
            for (Produk produk : listFigure) {
                Figure figure = (Figure) produk;
                writer.write( figure.getNama() + "|" + figure.getHarga() + "|"
                            + figure.getJumlah() + "|" + figure.getBahan() + "\n" );
            }
            // Menutup FileWriter
            writer.close();
        } catch (IOException e) {
            System.out.println( "Gagal menyimpan figure.txt" );
        }
    }

    // Simpan Plushie
    public static void simpanPlushie() {
        try {
            // Membuat folder data
            File folder = new File("data");
            folder.mkdirs();

            // Membuka file Plushie
            FileWriter writer = new FileWriter( filePlushie );

            // Menulis semua Plushie
            for (Produk produk : listPlushie) {
                Plushie plushie = (Plushie) produk;
                writer.write( plushie.getNama() + "|" + plushie.getHarga() + "|"
                            + plushie.getJumlah() + "|" + plushie.getTekstur() + "\n" );
            }
            // Menutup FileWriter
            writer.close();
        } catch (IOException e) {
            System.out.println( "Gagal menyimpan plushie.txt" );
        }
    }

    // Simpan Penjualan
    public static void simpanPenjualan(
            Penjualan penjualan) {
        try {
            // Membuat folder data
            File folder = new File("data");
            folder.mkdirs();

            // Membuka file penjualan, true berarti data ditambahkan bukan menimpa data lama
            FileWriter writer = new FileWriter( filePenjualan, true );

            // Menulis data transaksi
            writer.write( penjualan.getNomorStruk() + "|" + penjualan.getTimestamp() + "|" + penjualan.getSubtotal()
                        + "|" + penjualan.getDiskon() + "|" + penjualan.getPpn() + "|" + penjualan.getTotal() + "|"
                        + penjualan.getPointsEarned() + "\n" );

            // Menutup FileWriter
            writer.close();
            } catch (IOException e) {
                System.out.println("Gagal menyimpan penjualan.txt"
            );
        }
    }
}