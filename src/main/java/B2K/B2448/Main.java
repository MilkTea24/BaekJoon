package B2K.B2448;

import java.util.Scanner;

//k = 0 아래 별 5개(삼각형 하나) 5 * 3 (6 - 1) * (3 * 1)
//k = 1 아래 별 10개(삼각형 둘) 11 * 6 (6 * 2 - 1) * (3 * 2)
//k = 2 아래 23 * 12 (6 * 4 - 1) * (3 * 4)
//k = 3 47 * 24 (6 * 8 - 1) * (3 * 8)
public class Main {
    static StringBuilder[] print;
    static int N;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        print = new StringBuilder[N];

        String initialString = " ".repeat(N * 2 - 1);

        for (int i = 0; i < N; i++) {
            print[i] = new StringBuilder().append(initialString);
        }

        int startX = 2;
        int startY = N - 3;

        drawTriangle(N-1, 0, N * 2 - 1);

        for (int i = 0; i < N; i++) {
            System.out.println(print[i]);
        }
    }

    private static void drawTriangle(int startX, int startY, int len) {
            if (len == 5) {
                print[startX - 2].replace(startY + 2, startY + 3, "*");
                print[startX - 1].replace(startY+1, startY+4, "* *");
                print[startX].replace(startY, startY + 5, "*****");
            }

            else {
                drawTriangle(startX, startY, len / 2);
                drawTriangle(startX, startY + (len / 2) + 1,  len / 2);
                drawTriangle(startX - (len / 4) - 1, startY + (len / 4) + 1, len / 2);
            }
    }
}
