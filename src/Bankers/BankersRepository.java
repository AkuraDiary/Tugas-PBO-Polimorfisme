package Bankers;

import Bankers.Model.Rekening;
import Bankers.Model.RekeningPerseorangan;
import Bankers.Model.RekeningPerusahaan;

import java.util.ArrayList;

public class BankersRepository {
    public static ArrayList<Rekening> listRekening = new ArrayList<>();

    public static Rekening getRekeningByNoRekening(String noRekening) {
        for (Rekening rekening : listRekening) {
            if (rekening.getNoRekening().equals(noRekening)) {
                return rekening;
            }
        }
        return null;
    }

    public static void initRepository() {
        // fill the listRekening with some data
        // variation of RekeningPerseorangan & RekeningPerusahaan
        Rekening rekPerusahaan = new RekeningPerusahaan(
                "PT. Iwak Vertical",
                "0987654321",
                "Ayam Goyeng",
                "1209123",
                "1234567890",
                100_000_000
        );

        Rekening rekPerseorangan = new RekeningPerseorangan(
                "Budi",
                "1234567890",
                "0987654321",
                1_000_000
        );

        rekPerseorangan.setPin("123456");
        rekPerusahaan.setPin("654321");

        listRekening.add(
                rekPerusahaan
        );
        listRekening.add(
                rekPerseorangan
        );
    }
}
