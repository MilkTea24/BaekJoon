package B10K.B11050;

import java.util.Scanner;

public class Main {
    static int[][] binomial;
    public static void main(String[] args) {
        binomial = new int[11][11];
        binomial[1][0] = 1;
        binomial[1][1] = 1;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(calculateBinomial(n, k));
    }

    static int calculateBinomial(int n, int k) {
        if (n < 0 || k < 0 || n < k) return 0;
        else if (binomial[n][k] != 0) return binomial[n][k];
        else {
            binomial[n][k] = calculateBinomial(n-1,k) + calculateBinomial(n-1, k - 1);
            return binomial[n][k];
        }
    }
}
