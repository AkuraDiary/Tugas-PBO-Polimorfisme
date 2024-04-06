package Bankers.Usecase;

import Bankers.Model.RekeningPerseorangan;
import Bankers.Model.RekeningPerusahaan;

import java.io.IOException;

import static Bankers.Utilities.generateNoRekening;
import static Bankers.Utilities.input;

public class BankersRegisterUsecase {
    public RekeningPerseorangan registerPerseorangan() throws IOException {
        System.out.println();
        System.out.println("Register Perseorangan");

        System.out.print("Masukkan nama: ");
        String nama = input.readLine();

        System.out.print("Masukkan no Identitas (KTP / SIM): ");
        String noKTP = input.readLine();

        System.out.print("Masukkan saldo awal (minimal 50000) : ");
        double saldo = Double.parseDouble(input.readLine());

        if (saldo < 50000) {
            throw new IOException("Saldo minimal 50000");
        }
        String noRekening = generateNoRekening();
        return new RekeningPerseorangan(
                nama,
                noKTP,
                noRekening,
                saldo
        );

    }

    public RekeningPerusahaan registerPerusahaan() throws IOException{

        System.out.println();
        System.out.println("Register Perusahaan");

        System.out.print("Masukkan nama perusahaan: ");
        String namaPerusahaan = input.readLine();

        System.out.print("No Izin Perusahaan: ");
        String noIzin = input.readLine();

        System.out.print("Masukkan nama perwakilan rekening: ");
        String namaPerwakilan = input.readLine();

        System.out.println("Masukkan no Identitas Perwaklian (KTP / SIM): ");
        String noIdentitas = input.readLine();

        System.out.println("Masukkan saldo awal (minimal 500000) : ");
        double saldo = Double.parseDouble(input.readLine());

        if (saldo < 50000) {
            throw new IOException("Saldo minimal 50000");
        }

        String noRekening = generateNoRekening();
        return new RekeningPerusahaan(
                namaPerusahaan,
                noIzin,
                namaPerwakilan,
                noIdentitas,
                noRekening,
                saldo
        );
    }

}
