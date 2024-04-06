package TokoBarang;

class Barang{
    private String kodeBarang;
    private String namaBarang;
    private int harga=0;
    private int jumlah=0;
    Barang(String kodeBarang, String namaBarang, int harga, int jumlah){
        this.kodeBarang=kodeBarang;
        this.namaBarang=namaBarang;
        this.harga=harga;
        this.jumlah=jumlah;
    }
    Barang(String kodeBarang, String namaBarang, int harga){
        this.kodeBarang=kodeBarang;
        this.namaBarang=namaBarang;
        this.harga=harga;
    }

    public String getKodeBarang(){
        return this.kodeBarang;
    }
    public String getNamaBarang(){
        return this.namaBarang;
    }
    public int getHarga(){
        return this.harga;
    }
    public int getJumlah(){
        return this.jumlah;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void infoBarang(){
        System.out.println();
        System.out.println("Kode 	: "+kodeBarang);
        System.out.println("Nama 	: "+namaBarang);
        System.out.println("Harga 	: "+harga);
        System.out.println("Jumlah  : "+jumlah);
    }
}
