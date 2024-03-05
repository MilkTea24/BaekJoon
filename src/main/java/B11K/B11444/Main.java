package B11K.B11444;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final long DIV = 1000000007;
    private static Map<Long, Long> memo = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        long result = fib(n);
        System.out.println(result);
    }

    private static long fib(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);

        long result;

        if (n % 2 == 0) {
            long a1 = fib(n/2);
            long a2 = fib(n/2 - 1);

            result = a1 * (a1 + 2 * a2 % DIV) % DIV;
        }
        else {
            long a1 = fib(n/2);
            long a2 = fib(n/2 + 1);

            result = (a1 * a1 % DIV + a2 * a2 % DIV) % DIV;
        }

        memo.put(n, result);
        return result;
    }
}
