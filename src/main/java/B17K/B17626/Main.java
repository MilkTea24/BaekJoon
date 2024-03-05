package B17K.B17626;

import java.util.Scanner;

public class Main {
    static int[] squares;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int rootN = (int)Math.sqrt(n);

        if (rootN * rootN == n) {
            System.out.println(1);
            return;
        }

        squares = new int[rootN + 1];
        for (int i = 0; i < rootN + 1; i++) {
            squares[i] = i * i;
        }

        System.out.println(searchNumbers(n, rootN, 0, 0));
    }

    static int searchNumbers(int n, int rootN, int partialSum, int depth) {
        int result = Integer.MAX_VALUE;

        if (partialSum == n) return depth;
        if (depth == 3) return depth + 1;


        for (int i = 0; i <= rootN; i++) {
            result = Math.min(result, searchNumbers(n, rootN, partialSum + squares[i], depth + 1));
        }

        return result;

    }
}
