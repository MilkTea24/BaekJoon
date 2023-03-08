package B2K.B2798;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] cards;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        int result = Combinations(0, new ArrayList<Integer>(), m);
        System.out.println(result);
    }

    static int Combinations(int maxValue, ArrayList<Integer> selected, int blackJack) {
        if (selected.size() == 3) {
            int sum = 0;
            for (int i : selected) {
                sum += i;
            }
            if (sum <= blackJack && maxValue < sum) {
                maxValue = sum;
            }
        }
        else {
            for (int i = 0; i < cards.length; i++) {
                if (!selected.contains(cards[i])) {
                    selected.add(cards[i]);
                    maxValue = Combinations(maxValue, selected, blackJack);
                    selected.remove((Integer)cards[i]);
                }
            }
        }
        return maxValue;
    }
}
