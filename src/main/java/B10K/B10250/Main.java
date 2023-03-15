package B10K.B10250;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int cases = scanner.nextInt();

        for (int i = 0; i < cases; i++) {
            int H = scanner.nextInt();
            int W = scanner.nextInt();
            int N = scanner.nextInt();
            System.out.println(assignRoom(H, W, N));
        }
    }

    static String assignRoom(int H, int W, int N) {
        int intXX = N / H;
        intXX = N % H == 0 ? intXX : intXX + 1;
        String XX = intXX < 10 ? "0" + intXX : "" + intXX;
        int intYY = N % H;
        String YY = intYY == 0 ? "" + H : "" + intYY;
        return YY + XX;
    }
}
