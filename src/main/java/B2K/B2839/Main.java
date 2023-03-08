package B2K.B2839;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int maxFivePack = n / 5;
        while (maxFivePack >= 0) {
            int remainKg = n - 5 * maxFivePack;
            if (remainKg % 3 == 0) {
                int result = maxFivePack + (remainKg / 3);
                System.out.println(result);
                System.exit(0);
            }
            maxFivePack--;
        }
        System.out.println(-1);
    }
}
