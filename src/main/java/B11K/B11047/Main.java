package B11K.B11047;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        int ind = n-1;
        int diff = k;
        int result = 0;
        while (diff != 0) {
            if (coins[ind] <= diff) {
                result += diff / coins[ind];
                diff -= (diff / coins[ind]) * coins[ind];
            }
            ind--;
        }

        System.out.println(result);
    }
}
