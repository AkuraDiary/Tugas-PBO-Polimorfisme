package Bankers.Model;

import Bankers.Model.Rekening;

public class RekeningPerseorangan extends Rekening {

    private String namaPemilik;
    private String noIdentitas;
    public RekeningPerseorangan(String namaPemilik, String noIdentitas, String noRekening, double saldo) {
        super(noRekening, saldo);
        this.namaPemilik = namaPemilik;
        this.noIdentitas = noIdentitas;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    @Override
    public void informasiRekening() {
        super.informasiRekening();
        System.out.println("Nama Pemilik: " + namaPemilik);
        System.out.println("No Identitas: " + noIdentitas);
    }
}
