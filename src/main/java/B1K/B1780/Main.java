package B1K.B1780;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] paper;
    static int[][] section = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            paper = new int[N][N];
            for (int i = 0; i < N; i++) {
                paper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[] answers = new int[3];
            dividePaper(N, 0, 0,  answers);

            System.out.print(answers[0] + "\n" + answers[1] + "\n" + answers[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void dividePaper(int N, int x, int y, int[] answers) {
        if (N == 1) {
            answers[paper[x][y] + 1]++;
            return;
        }

        boolean isAllSameNumber = true;
        int startNum = paper[x][y];
        Label : for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                 if (paper[i][j] != startNum) {
                     isAllSameNumber = false;
                     break Label;
                 }
            }
        }

        if (isAllSameNumber) {
            answers[startNum + 1]++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            int divideN = N / 3;
            int startX = x + section[i][0] * divideN;
            int startY = y + section[i][1] * divideN;
            dividePaper(divideN, startX, startY, answers);
        }
    }
}
