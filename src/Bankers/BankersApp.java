package Bankers;

import Bankers.Model.Rekening;
import Bankers.Usecase.BankersRegisterUsecase;
import Bankers.Usecase.BankersTransferUsecase;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.ArrayList;

import static Bankers.BankersRepository.listRekening;
import static Bankers.Utilities.input;

public class BankersApp {
    private Rekening currentRekening = null;
    private final BankersRegisterUsecase bankersRegisterUsecase = new BankersRegisterUsecase();
    private final BankersTransferUsecase bankersTransferUsecase = new BankersTransferUsecase();

    public void run() {
        BankersRepository.initRepository();
        while (true) {
//                1 register
//                2 login
//                    2.1 transfer (ke 1 rekening 2 beberapa rekening)
//                    2.2 tarik saldo
//                    2.3 setor
//                0 exit
            System.out.println();
            System.out.println("Bankers App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Pilih menu: ");
            try {
                int pilihan = Integer.parseInt(input.readLine());
                switch (pilihan) {
                    case 1:
                        registerMenu();
                        break;
                    case 2:
                        loginMenu();
                        break;
                    case 69:
                        System.out.println("Nice");
                        listRekening.forEach(Rekening::informasiRekening);
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    private void loginMenu() {
        //                2 login
        //                    2.1 transfer (ke 1 rekening 2 beberapa rekening)
        //                    2.2 tarik saldo
        //                    2.3 setor
        //                0 logout

        while (true) {
            System.out.println();
            System.out.println("Login");
            System.out.print("Masukkan no rekening: ");
            try {
                String noRekening = input.readLine();
                Rekening rekening = BankersRepository.getRekeningByNoRekening(noRekening);
                if (rekening == null) {
                    System.out.println("Rekening tidak ditemukan");
                    return;
                }

                currentRekening = rekening;

                validatePin();

                System.out.println("Login berhasil");
                while (true) {
                    System.out.println();
                    System.out.println("1. Transfer");
                    System.out.println("2. Tarik Saldo");
                    System.out.println("3. Setor");
                    System.out.println("4. Info");
                    System.out.println("0. Logout");
                    System.out.print("Pilih menu: ");
                    int pilihan = Integer.parseInt(input.readLine());
                    switch (pilihan) {
                        case 1:
                            transferMenu();
                            break;
                        case 2:
                            tarikSaldoMenu();
                            break;
                        case 3:
                            setorMenu();
                            break;
                        case 4:
                            currentRekening.informasiRekening();
                            break;
                        case 0:
                            currentRekening = null;
                            return;
                        default:
                            System.out.println("Pilihan tidak valid");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    private void validatePin() throws IOException {
        System.out.print("Masukkan pin: ");
        String pin = input.readLine();
        if (!currentRekening.getPin().equals(pin)) {
            throw new IOException("Pin salah");
        }
    }
    private void setorMenu() throws IOException{
        // input pin, if match lanjut, else return
        System.out.println("Setor");
        validatePin();

        System.out.print("Masukkan jumlah yang akan disetor: ");
        double jumlah = Double.parseDouble(input.readLine());
        if (jumlah <= 0) {
            throw new IOException("Jumlah setoran tidak valid");
        }
        currentRekening.setSaldo(currentRekening.getSaldo() + jumlah);
        System.out.println("Setor berhasil");

    }

    private void tarikSaldoMenu() throws Exception{
        // input pin, if match lanjut, else return
        System.out.println("Setor");
        validatePin();

        System.out.print("Masukkan jumlah yang akan Ditarik: ");
        double jumlah = Double.parseDouble(input.readLine());

        if (jumlah <= 0) {
            throw new IOException("Jumlah penarikan tidak valid");
        }
        // if jumlah is not the multiple of 50000 or 100000
        if (jumlah % 50000 != 0 && jumlah % 100000 != 0) {
            throw new OperationNotSupportedException("Jumlah penarikan harus kelipatan 50000 atau 100000");
        }

        // if saldo rekening is not enough (must be at least 50000 inside the rekening)
        if(currentRekening.getSaldo() - jumlah <= 50000) {
            throw new OperationNotSupportedException("Saldo tidak cukup");
        }

        //else
        currentRekening.setSaldo(currentRekening.getSaldo() - jumlah);
        System.out.println("Penarikan berhasil");
    }

    private void transferMenu() throws Exception{
        // input pin, if match lanjut, else return

        System.out.println("Transfer");
        validatePin();

        // input no rekening tujuan, min 1 max 3
        // then input jumlah transfer (if rekening more than 1, then the amount of money for each rekening is same)
        // validate saldo inside rekening
        int counter = 0;
        ArrayList<String> noRekeningTujuan = new ArrayList<>();
        while (counter < 3){
            System.out.print("Masukkan no rekening tujuan: ");
            String rekTujuan = input.readLine();
            Rekening rekeningTujuan = BankersRepository.getRekeningByNoRekening(rekTujuan);
            if (rekeningTujuan == null) {
                System.out.println("Rekening tidak ditemukan");
                return;
            }
            noRekeningTujuan.add(rekTujuan);
            // input lagi ?
            System.out.print("Apakah ingin menambahkan rekening tujuan lagi? (y/n atau kosongi): ");
            String jawaban = input.readLine();
            counter++;
            if (jawaban.equalsIgnoreCase("n") || jawaban.isBlank()) {
                break;
            }

        }
        System.out.print("Masukkan jumlah yang akan ditransfer: ");
        double jumlah = Double.parseDouble(input.readLine());
        if (jumlah <= 0) {
            throw new IOException("Jumlah transfer tidak valid");
        }
        if (currentRekening.getSaldo() - jumlah*counter <= 50000) {
            throw new OperationNotSupportedException("Saldo tidak cukup");
        }
        if (noRekeningTujuan.isEmpty()) {
            throw new OperationNotSupportedException("Minimal 1 rekening tujuan");
        }

        if(noRekeningTujuan.size() == 1){
            bankersTransferUsecase.transferSaldo(currentRekening.getNoRekening(),noRekeningTujuan.get(0), jumlah);
        } else {
            bankersTransferUsecase.transferSaldo(currentRekening.getNoRekening(), noRekeningTujuan, jumlah);
        }

        System.out.println("Transfer berhasil");

    }
    private void registerMenu() {
        // pilih register perseorangan atau perusahaan
        // masukkan data
        while (true) {
            System.out.println();
            System.out.println("Register");
            System.out.println("1. Register Perseorangan");
            System.out.println("2. Register Perusahaan");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            try {
                int pilihan = Integer.parseInt(input.readLine());
                Rekening rekening = null;
                switch (pilihan) {
                    case 1:
                        rekening = bankersRegisterUsecase.registerPerseorangan();
                        break;
                    case 2:
                        rekening = bankersRegisterUsecase.registerPerusahaan();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Pilihan tidak valid");
                        break;
                }

                assert rekening != null;
                System.out.println();
                System.out.println("Rekening berhasil dibuat");
                rekening.informasiRekening();
                System.out.println();
                System.out.println("Pembuatan PIN");
                // insert pin
                System.out.print("Masukkan pin: ");
                String pin = input.readLine();
                rekening.setPin(pin);

                listRekening.add(rekening);
                System.out.println("Rekening berhasil disimpan");
                System.out.println("Silahkan login untuk mengakses rekening");

                break;

            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }

    }
}
