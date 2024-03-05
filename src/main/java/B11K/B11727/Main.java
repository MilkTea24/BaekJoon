package B11K.B11727;

import java.util.Scanner;

public class Main {
    static int[] memo = new int[1000];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        memo[0] = 1;
        memo[1] = 3;
        for (int i = 2; i < input; i++) {
            memo[i] = (memo[i-1] + memo[i-2] * 2) % 10007;
        }
        System.out.println(memo[input-1]);
    }
}
