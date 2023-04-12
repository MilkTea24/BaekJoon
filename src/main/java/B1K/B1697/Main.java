package B1K.B1697;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int[] timeFromSuBin = new int[100001];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int suBin = scanner.nextInt();
        int sister = scanner.nextInt();

        for (int i = 0; i <= 100000; i++) {
            timeFromSuBin[i] = -1;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(suBin);
        timeFromSuBin[suBin] = 0;

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();

            if (current * 2 <= 100000 && timeFromSuBin[current * 2] == -1) {
                queue.addLast(current * 2);
                timeFromSuBin[current * 2] = timeFromSuBin[current] + 1;
            }

            if (current - 1 >= 0 && timeFromSuBin[current - 1] == -1) {
                queue.addLast(current - 1);
                timeFromSuBin[current - 1] = timeFromSuBin[current] + 1;
            }

            if (current + 1 <= 100000 && timeFromSuBin[current + 1] == -1) {
                queue.addLast(current + 1);
                timeFromSuBin[current + 1] = timeFromSuBin[current] + 1;
            }

            if (timeFromSuBin[sister] != -1) {
                break;
            }
        }
        System.out.println(timeFromSuBin[sister]);
    }
}
