package TokoBarang;

import java.util.ArrayList;

class Superstore {
    private ArrayList<Barang> arrStockBarang = new ArrayList<>();
    public void tambahBarang(String kodeBarang, String namaBarang, int harga) {
        System.out.println();
        System.out.println("Menambahkan Daftar Barang Baru");
        Barang temp = new Barang(kodeBarang, namaBarang, harga);
        arrStockBarang.add(temp);
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .orElse(new Barang("Not Found", "Not Found", 0))
                .infoBarang();
        System.out.println("!!! Jumlah barang 0, lakukan penambahan stock sebelum melakukan penjualan");
    }

    public void tambahBarang(String kodeBarang, String namaBarang, int harga, int jumlah) {
        System.out.println();
        System.out.println("Menambahkan Daftar Barang Baru");
        Barang temp = new Barang(kodeBarang, namaBarang, harga, jumlah);
        arrStockBarang.add(temp);
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .orElse(new Barang("Not Found", "Not Found", 0))
                .infoBarang();
        System.out.println("Barang Ditambahkan");
    }

    public void infoStockAllBarang() {
        System.out.println();
        System.out.println("Informasi Semua Daftar Barang");
        if(arrStockBarang.isEmpty()){
            System.out.println("Belum ada barang dalam list data");
            return;
        }
        arrStockBarang.forEach(Barang::infoBarang);

    }

    public void updateBarang(String kodeBarang, int harga) {
        System.out.println();
        System.out.println("Update data barang dengan kode " + kodeBarang);
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Updating data " + barang.getNamaBarang() + " harga menjadi " + harga);
                    barang.setHarga(harga);
                    System.out.println("Data barang berhasil diupdate");
                    barang.infoBarang();
                });
    }

    public void updateBarang(String kodeBarang, int harga, int jumlah, boolean isTambah) {
        System.out.println();
        System.out.println("Update data barang dengan kode " + kodeBarang);
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Updating data " + barang.getNamaBarang() + " harga menjadi " + harga + " jumlah diupdate " + jumlah);
                    if (isTambah) {
                        barang.setJumlah(barang.getJumlah() + jumlah);
                    } else {
                        barang.setJumlah(jumlah);
                    }
                    barang.setHarga(harga);
                    System.out.println("Data barang berhasil diupdate");
                    barang.infoBarang();
                });
    }

    // TRANSAKSI REGION

    private ArrayList<Barang> arrKeranjang = new ArrayList<>();

    public void tambahBarangKeranjang(String kodeBarang, int jumlah) {
        System.out.println("Menambahkan Barang ke Keranjang");
        // get barang from arrStockBarang, add the barang to arrKeranjang
        // substract the stock in arrStockBarang

        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Menambahkan barang " + barang.getNamaBarang() + " ke keranjang");
                    if (barang.getJumlah() < jumlah) {
                        System.out.println("Jumlah barang tidak mencukupi");
                        return;
                    }
                    // check if barang already in arrKeranjang
                    if (arrKeranjang.stream().anyMatch(barang1 -> barang1.getKodeBarang().equals(kodeBarang))) {
                        System.out.println("Barang sudah ada di keranjang, lakukan update jumlah barang di keranjang");
                        updateBarangKeranjang(kodeBarang, barang.getHarga(), jumlah, true);
                        return;
                    }
                    Barang temp = new Barang(barang.getKodeBarang(), barang.getNamaBarang(), barang.getHarga(), jumlah);
                    arrKeranjang.add(temp);
                    // substract the stock in arrStockBarang
                    barang.setJumlah(barang.getJumlah() - jumlah);
                    System.out.println("Barang Ditambahkan Ke keranjang");
                    infoKeranjang();
                });
    }

    public void infoKeranjang() {
        System.out.println();
        System.out.println("Informasi Semua Daftar Barang dalam Keranjang");
        if(arrKeranjang.isEmpty()){
            System.out.println("Belum ada barang dalam keranjang");
            return;
        }
        arrKeranjang.forEach(Barang::infoBarang);

    }

    public void updateBarangKeranjang(String kodeBarang, int harga) {
        System.out.println("Update data barang dengan kode " + kodeBarang);
        arrKeranjang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Updating data " + barang.getNamaBarang());
                    barang.setHarga(harga);
                    System.out.println("Data barang berhasil diupdate");
                    barang.infoBarang();
                });
    }

    public void updateBarangKeranjang(String kodeBarang, int harga, int jumlah, boolean isTambah) {
        System.out.println("Update data barang dengan kode " + kodeBarang);
        arrKeranjang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Updating data " + barang.getNamaBarang());
                    if (isTambah) {
                        barang.setJumlah(barang.getJumlah() + jumlah);
                    } else {
                        barang.setJumlah(jumlah);
                    }
                    barang.setHarga(harga);
                    System.out.println("Data barang berhasil diupdate");
                    barang.infoBarang();
                });
    }

    public void doTransaksi(String kodeBarang, int jumlahBeli ){
        System.out.println("Melakukan transaksi");
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Transaksi barang " + barang.getNamaBarang());
                    if (barang.getJumlah() < jumlahBeli) {
                        System.out.println("Jumlah barang tidak mencukupi");
                        return;
                    }
                    int totalHarga = barang.getHarga() * jumlahBeli;
                    System.out.println("Total harga : " + totalHarga);
                    // substract the stock in arrStockBarang
                    barang.setJumlah(barang.getJumlah() - jumlahBeli);
                    System.out.println("Transaksi berhasil");
                    barang.infoBarang();
                });
    }

    public void doTransaksi(String kodeBarang, int jumlahBeli, int diskon, boolean isPercentage){
        System.out.println("Melakukan transaksi");
        arrStockBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .ifPresent(barang -> {
                    System.out.println("Transaksi barang " + barang.getNamaBarang());
                    if (barang.getJumlah() < jumlahBeli) {
                        System.out.println("Jumlah barang tidak mencukupi");
                        return;
                    }
                    int totalHarga = barang.getHarga() * jumlahBeli;
                    if (isPercentage) {
                        totalHarga = totalHarga - (totalHarga * diskon / 100);
                    } else {
                        totalHarga = totalHarga - diskon;
                    }
                    System.out.println("Total harga : " + totalHarga);
                    // substract the stock in arrStockBarang
                    barang.setJumlah(barang.getJumlah() - jumlahBeli);
                    System.out.println("Transaksi berhasil");
                    barang.infoBarang();
                });
    }

    public void doTransaksi(int diskon, boolean isPercentage){
        // do Transaksi di Keranjang
        System.out.println("Melakukan transaksi");
        if(arrKeranjang.isEmpty()){
            System.out.println("Keranjang masih kosong");
            return;
        }
        int totalHarga = 0;
        for (Barang barang : arrKeranjang) {
            totalHarga += barang.getHarga() * barang.getJumlah();
        }
        if (isPercentage) {
            totalHarga = totalHarga - (totalHarga * diskon / 100);
        } else {
            totalHarga = totalHarga - diskon;
        }
        System.out.println("Total harga : " + totalHarga);
        System.out.println("Transaksi berhasil");

        // clear keranjang
        arrKeranjang.clear();

    }

} //end of class Superstore
