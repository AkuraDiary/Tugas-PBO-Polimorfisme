package Bankers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Utilities {
    public static String generateNoRekening() {
        // generate random card number
        Random random = new Random();
        StringBuilder noRekening = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            noRekening.append(random.nextInt(10));
        }
        return noRekening.toString();
    }
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
}
