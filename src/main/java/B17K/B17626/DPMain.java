package B17K.B17626;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DPMain {
    static int[] memo;

    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();


        memo = new int[n+1];

        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = minSquare(i) + 1;
        }
        System.out.println(memo[n]);
    }

    private static int minSquare(int n) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            minValue = Math.min(memo[n - i*i], minValue);
        }

        return minValue;
    }
}
