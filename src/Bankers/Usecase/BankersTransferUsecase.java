package Bankers.Usecase;

import Bankers.BankersRepository;
import Bankers.Model.Rekening;

import java.util.ArrayList;

public class BankersTransferUsecase {

    public void transferSaldo(String noRekAsal, String noRektujuan, double jumlahTransfer) throws Exception{
        Rekening rekeningAsal = BankersRepository.getRekeningByNoRekening(noRekAsal);
        Rekening rekeningTujuan = BankersRepository.getRekeningByNoRekening(noRektujuan);

        assert rekeningAsal != null;
        assert rekeningTujuan != null;

        rekeningAsal.setSaldo(rekeningAsal.getSaldo() - jumlahTransfer);
        rekeningTujuan.setSaldo(rekeningTujuan.getSaldo() + jumlahTransfer);
    }
    public void transferSaldo(String noRekAsal, ArrayList<String> listNoRekTujuan, double jumlahTransfer) {
        Rekening rekeningAsal = BankersRepository.getRekeningByNoRekening(noRekAsal);
        for (String noRekTujuan : listNoRekTujuan) {
            Rekening rekeningTujuan = BankersRepository.getRekeningByNoRekening(noRekTujuan);
            if (rekeningTujuan == null) {
                throw new RuntimeException("Rekening Tujuan tidak ditemukan");
            }
            rekeningAsal.setSaldo(rekeningAsal.getSaldo() - jumlahTransfer);
            rekeningTujuan.setSaldo(rekeningTujuan.getSaldo() + jumlahTransfer);
        }
    }
}
