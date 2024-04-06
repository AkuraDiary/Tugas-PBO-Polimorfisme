package Bankers.Model;

public class Rekening{
    private String noRekening;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private String pin;
    private double saldo;

    public Rekening(String noRekening, double saldo){
        this.noRekening=noRekening;
        this.saldo=saldo;
    }

    public Rekening(String noRekening) {
        this.noRekening = noRekening;
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public void informasiRekening(){
        System.out.println();
        System.out.println("Informasi Rekening");
        System.out.println("No Rekening: "+noRekening);
        System.out.println("Saldo: "+saldo);
    }
}
