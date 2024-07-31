package B2K.B2738;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N, M;
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        int[][] arrayA = new int[N][M];
        int[][] arrayB = new int[N][M];
         for (int i = 0; i < N; i++) {
             arrayA[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
         }

        for (int i = 0; i < N; i++) {
            arrayB[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arrayA[i][j] + arrayB[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
