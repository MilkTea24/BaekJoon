package B2K.B2086;

import java.util.Scanner;

public class Main {
    static final long DIVIDE = 1000000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        long max = Matrix.pow(b + 2) - 1;
        long min = Matrix.pow(a + 1) - 1;
        if (max < min) System.out.println(DIVIDE + max - min);
        else System.out.println(max - min);
    }

    static class Matrix {
        static long[][] matrix = {{1, 1}, {1, 0}};

        static long pow(long n) {
            long[][] result = powMatrix(n);
            return result[0][1];
        }

        static long[][] powMatrix(long n) {
            if (n == 1) return matrix;
            long[][] half = powMatrix(n / 2);
            if (n % 2 == 0) {
                return multiple(half, half);
            }
            return multiple(multiple(half, half), matrix);
        }

        static long[][] multiple(long[][] a, long[][] b) {
            long[][] result = new long[2][2];
            result[0][0] = ((a[0][0] * b[0][0]) % DIVIDE + (a[0][1] * b[1][0]) % DIVIDE) % DIVIDE;
            result[0][1] = ((a[0][0] * b[0][1]) % DIVIDE + (a[0][1] * b[1][1]) % DIVIDE) % DIVIDE;
            result[1][0] = ((a[1][0] * b[0][0]) % DIVIDE + (a[1][1] * b[1][0]) % DIVIDE) % DIVIDE;
            result[1][1] = ((a[1][0] * b[0][1]) % DIVIDE + (a[1][1] * b[1][1]) % DIVIDE) % DIVIDE;

            return result;
        }


    }
}
