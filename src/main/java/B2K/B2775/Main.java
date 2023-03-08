package B2K.B2775;

import java.util.Scanner;

public class Main {
    static int[][] apartment = new int[15][15];
    public static void main(String[] args) {
        for (int j = 1; j <= 14; j++) {
            apartment[0][j] = j;
        }
        for (int i = 1; i <= 14; i++) {
            apartment[i][1] = apartment[i-1][1];
            for (int j = 2; j <= 14; j++) {
                apartment[i][j] = apartment[i][j-1] + apartment[i-1][j];
            }
        }

        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();

        for (int i = 0; i < cases; i++) {
            int k = scanner.nextInt();
            int n = scanner.nextInt();
            System.out.println(apartment[k][n]);
        }
    }
}
