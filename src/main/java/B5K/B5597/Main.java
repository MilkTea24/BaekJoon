package B5K.B5597;

import java.util.*;

public class Main {
    static boolean[] arr = new boolean[30];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 28; i++) {
            int n = scanner.nextInt() - 1;
            arr[n] = true;
        }

        for (int i = 0; i < 30; i++) {
            if (!arr[i]) System.out.println(i + 1);
        }
    }
}
