package Bankers.Model;

import Bankers.Model.Rekening;

public class RekeningPerusahaan extends Rekening {
    String namaPerusahaan, noIzinUsaha, namaPerwakilan, noIdentitas;
    public RekeningPerusahaan(String namaPerusahaan, String noIzinUsaha, String namaPerwakilan, String noIdentitas, String noRekening, double saldo) {
        super(noRekening, saldo);
        this.namaPerusahaan = namaPerusahaan;
        this.noIzinUsaha = noIzinUsaha;
        this.namaPerwakilan = namaPerwakilan;
        this.noIdentitas = noIdentitas;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getNoIzinUsaha() {
        return noIzinUsaha;
    }

    public void setNoIzinUsaha(String noIzinUsaha) {
        this.noIzinUsaha = noIzinUsaha;
    }

    public String getNamaPerwakilan() {
        return namaPerwakilan;
    }

    public void setNamaPerwakilan(String namaPerwakilan) {
        this.namaPerwakilan = namaPerwakilan;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    @Override
    public void informasiRekening() {
        super.informasiRekening();
        System.out.println("Nama Perusahaan: " + namaPerusahaan);
        System.out.println("No Izin Usaha: " + noIzinUsaha);
        System.out.println("Nama Perwakilan: " + namaPerwakilan);
        System.out.println("No Identitas: " + noIdentitas);
    }
}
