package B11K.B11404;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[][] busInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        busInfo = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
               if (i != j) busInfo[i][j] = 10000000;
            }
        }

        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;
            int weight = line[2];

            busInfo[start][end] = Math.min(busInfo[start][end], weight);
        }

        for (int m = 0; m < N; m++) {
            for (int s = 0; s < N; s++) {
                for (int e = 0; e < N; e++) {
                    busInfo[s][e] = Math.min(busInfo[s][e], busInfo[s][m] + busInfo[m][e]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(busInfo[i][j] == 10000000 ? 0 : busInfo[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);

    }
}
