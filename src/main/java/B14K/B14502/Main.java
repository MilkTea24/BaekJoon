package B14K.B14502;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;

    static int[][] input;
    static int[][] oneCase;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];

        input = new int[N][M];

        for (int i = 0; i < N; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        long result = 0;
        String resultInd = " ";

        for (int i = 0; i < N * M; i++) {
            int firstPosX = i % M;
            int firstPosY = i / M;

            if (input[firstPosY][firstPosX] != 0) continue;

            for (int j = i + 1; j < N * M; j++) {
                int secondPosX = j % M;
                int secondPosY = j / M;

                if (input[secondPosY][secondPosX] != 0) continue;

                for (int k = j + 1; k < N * M; k++) {
                    int thirdPosX = k % M;
                    int thirdPosY = k / M;

                    if (input[thirdPosY][thirdPosX] != 0) continue;

                    oneCase = new int[N][M];
                    copyInputToOneCase();

                    oneCase[firstPosY][firstPosX] = 1;
                    oneCase[secondPosY][secondPosX] = 1;
                    oneCase[thirdPosY][thirdPosX] = 1;

                    DFSAll();

                    long count = Arrays.stream(oneCase)
                            .flatMapToInt(Arrays::stream)
                            .filter(e -> e == 0)
                            .count();

                    result = Math.max(result, count);
                }
            }
        }

        System.out.print(result);
    }

    private static void copyInputToOneCase() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                oneCase[i][j] = input[i][j];
            }
        }
    }

    private static void DFSAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (oneCase[i][j] == 2) DFS(i, j);
            }
        }
    }

    private static void DFS(int a, int b) {
        if (a < 0 || a >= N || b < 0 || b >= M) return;
        if (oneCase[a][b] != 0 && oneCase[a][b] != 2) return;

        oneCase[a][b] = 3;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            DFS(newI, newJ);
        }
    }
}
