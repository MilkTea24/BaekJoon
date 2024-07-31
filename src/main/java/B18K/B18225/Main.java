package B18K.B18225;

import java.util.*;

/*
12시 35분 시작
 */
public class Main {
    static int A, B, x, y, p, q;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        A = scanner.nextInt();
        B = scanner.nextInt();
        x = scanner.nextInt();
        y = scanner.nextInt();
        p = scanner.nextInt();
        q = scanner.nextInt();

        /*
         (x + pZ) % A == 0 && (y + qZ) % B == 0
         */
        int[] results = findMinimumSumSolution(q * A, -1 * p * B, q * x - p * y);
        if (results == null) System.out.println(-1);
        else System.out.println(results[0] + results[1] - 1);
    }

    public static int[] extendedGCD(int a, int b) {
        if (b == 0) return new int[]{a, 1, 0};
        int[] vals = extendedGCD(b, a % b);
        int g = vals[0];
        int x = vals[2];
        int y = vals[1] - (a / b) * vals[2];
        return new int[]{g, x, y};
    }

    public static int[] findMinimumSumSolution(int A, int B, int C) {
        if (C == 0) {
            int gcd = gcd(A, B);
            return new int[]{Math.abs(B / gcd), Math.abs(A / gcd)};
        }

        int[] egcd = extendedGCD(A, B);
        int g = egcd[0], x0 = egcd[1], y0 = egcd[2];

        // C가 gcd로 나누어 떨어지지 않으면 해가 존재하지 않음
        if (C % g != 0) return null;

        // C에 맞게 해 조정
        int scale = C / g;
        x0 *= scale;
        y0 *= scale;

        // k를 조정하여 n + m의 최소값 찾기
        int A_g = A / g, B_g = B / g;
        int k = (int) Math.ceil(-x0 / (double) B_g);
        int n = x0 + k * B_g;
        int m = y0 - k * A_g;
        int minimumSum = Math.abs(n + m);
        for (int k_adj = k - 1; k_adj <= k + 1; k_adj++) {
            int n_adj = x0 + k_adj * B_g;
            int m_adj = y0 - k_adj * A_g;
            int sum = Math.abs(n_adj + m_adj);
            if (sum < minimumSum) {
                minimumSum = sum;
                n = n_adj;
                m = m_adj;
            }
        }

        return new int[]{n, m};
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
