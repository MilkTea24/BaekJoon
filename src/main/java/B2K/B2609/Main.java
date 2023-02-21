package B2K.B2609;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();  int b = scanner.nextInt();
        int gcd = GCD(a, b);
        int lcm = LCM(a, b);

        System.out.println(gcd);
        System.out.println(lcm);
    }
    static int GCD(int a, int b) {
        int smallNum = (a > b) ? b : a;
        int result = 1;
        for (int i = 1; i <= smallNum; i++) {
            if (a % i == 0 && b % i == 0) {
                result = i;
            }
        }
        return result;
    }

    static int LCM(int a, int b) {
        int multipleA = a;
        int multipleB = b;

        while (true) {
            while (multipleA > multipleB) {
                multipleB += b;
            }
            while (multipleB > multipleA) {
                multipleA += a;
            }
            if (multipleA == multipleB) return multipleA;
        }
    }
}
