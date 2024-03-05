package B11K.B11660;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] input;
    static int[][] endMemo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" ");

        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        input = new int[N][N];
        endMemo = new int[N][N];

        for (int i = 0; i < N; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        endMemo[0][0] = input[0][0];

        for (int i = 1; i < N; i++) {
            endMemo[i][0] = endMemo[i-1][0] + input[i][0];
        }

        for (int i = 1; i < N; i++) {
            int linePartialSum = 0;
            for (int j = 0; j < N; j++) {
                linePartialSum += input[j][i];
                endMemo[j][i] = endMemo[j][i-1] + linePartialSum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = line[0] - 1;
            int y1 = line[1] - 1;
            int x2 = line[2] - 1;
            int y2 = line[3] - 1;


            int totalSquare = endMemo[x2][y2];
            int leftSquare = y1 == 0 ? 0 : endMemo[x2][y1 - 1];
            int upSquare = x1 == 0 ? 0 : endMemo[x1 - 1][y2];
            int duplicateSquare = (x1 == 0 || y1 == 0) ? 0 : endMemo[x1 - 1][y1 - 1];

            sb.append(totalSquare - leftSquare - upSquare + duplicateSquare).append('\n');
        }

        System.out.print(sb);
    }
}
