package B15K.B15829;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
           Scanner scanner = new Scanner(System.in);
           int L = Integer.parseInt(scanner.nextLine());

           String inputString = scanner.nextLine();


           System.out.println(hashing(inputString));
    }

    static int hashing(String str) {
        char[] stringToChar = str.toCharArray();
        int[] stringToInt = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            stringToInt[i] = stringToChar[i] - 'a' + 1;
        }

            long result = 0;
            long r = 1;
            for (int i = 0; i < stringToInt.length; i++) {
                result += (stringToInt[i] * r % 1234567891);
                r = (r * 31) % 1234567891;
            }

        return (int)(result % 1234567891);
    }
}
