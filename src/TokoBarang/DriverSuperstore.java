package TokoBarang;

class DriverSuperStore{
    public static void main(String[] args){
        Superstore st = new Superstore();

        System.out.println("Menambahkan barang ke dalam stock");
        st.tambahBarang("AO1000","Susu Bendera",50000);
        st.tambahBarang("BO1000","Kertas HVS",28000,3);
        st.tambahBarang("CO1000","Kacang Kedelai",4700,5);
        st.tambahBarang("NO1000","Nopal",5000);
        st.infoStockAllBarang();

        System.out.println();
        System.out.println("Update Barang dalam stock");
        st.updateBarang("CO1000", 30000, 5, true);
        st.updateBarang("NO1000", 50000, 10, true);
        st.updateBarang("AO1000", 50000, 15, true);
        st.updateBarang("BO1000", 25000);
        st.infoStockAllBarang();

        // pembelian sekali
        System.out.println();
        System.out.println("Pembelian Sekali");
        st.doTransaksi("AO1000", 2);

        // pembelian keranjang
        System.out.println();
        System.out.println("Pembelian Keranjang");
        st.tambahBarangKeranjang("BO1000", 1);
        st.tambahBarangKeranjang("CO1000", 2);
        st.infoKeranjang();
        st.infoStockAllBarang();
        st.doTransaksi(0, false);

        System.out.println("Stock After Transaksi");
        st.infoStockAllBarang();

    }
}
