package B1K.B1676;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] numberOfTwo = new int[501];
        int[] numberOfFive = new int[501];
        numberOfTwo[0] = 0; numberOfFive[0] = 0;
        for (int i = 1; i <= 500; i++) {
            numberOfTwo[i] = numberOfTwo[i-1] + numberOfN(i, 2);
            numberOfFive[i] = numberOfFive[i-1] + numberOfN(i, 5);
        }
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        System.out.println(Integer.min(numberOfTwo[input], numberOfFive[input]));
    }

    static int numberOfN(int input, int N) {
        int count = 0;
        while (input % N == 0) {
            input = input / N;
            count++;
        }
        return count;
    }
}
