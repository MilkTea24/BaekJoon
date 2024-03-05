package B11K.B11726;

import java.util.Scanner;

public class Main {
    static int[] answer = new int[1000];
    public static void main(String[] args) {
        answer[0] = 1;
        answer[1] = 2;

        for (int i = 2; i < 1000; i++) {
            answer[i] = (answer[i-1] + answer[i-2]) % 10007;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println(answer[scanner.nextInt() - 1]);
    }
}
