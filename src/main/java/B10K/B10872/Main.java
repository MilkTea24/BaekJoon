package B10K.B10872;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        long result = 1L;
        for (int i = 0; i < N; i++) {
            result *= (i + 1);
        }

        System.out.print(result);
    }
}
