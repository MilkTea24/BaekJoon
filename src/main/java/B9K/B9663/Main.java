package B9K.B9663;

import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        System.out.println(placeQueen(0, new int[n]));
    }

    private static int placeQueen(int b, int[] isPlaced) {
        if (b == n) return 1;

        int result = 0;

        label: for (int i = 0; i < n; i++) {
            for (int j = 0; j < b; j++) {
                int eA = isPlaced[j];
                if (eA == i) continue label;
                if (j - eA == b - i) continue label;
                if (Math.abs(j - b) == Math.abs(eA - i)) continue label;
            }
            isPlaced[b] = i;
            result += placeQueen(b+1, isPlaced);
        }

        return result;
    }
}
