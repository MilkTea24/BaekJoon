package B1K.B1629;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        long c = scanner.nextLong();

        System.out.println(divideAndConquer(a, b, c));
    }

    //1001 -> 501, 500 -> 251, 250, 250, 250 -> 126, 125, 125, 125, 125, 125, 125, 125 -> ...
    static long divideAndConquer(long a, long b, long c) {
        if (b == 1) return a % c;

        long result;
        long divideResult = divideAndConquer(a, b/2 , c);
        if (b % 2 == 0) result = (divideResult * divideResult) % c;
        else result = (divideResult * divideResult % c) * a % c;

        return result;
    }
}
