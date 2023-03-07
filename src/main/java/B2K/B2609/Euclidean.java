package B2K.B2609;

import java.util.Scanner;

public class Euclidean {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();

        int GCD = GCD(A, B);
        int LCM = A*B / GCD;

        System.out.println(GCD);
        System.out.println(LCM);
    }

    static int GCD(int A, int B) {
        if (A < B) return GCD(B, A);
        if (B == 0) return A;
        return GCD(B,A % B);
    }
}
