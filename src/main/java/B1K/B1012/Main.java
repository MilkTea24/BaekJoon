package B1K.B1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] farm;
    static int N, M, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        try {
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < T; i++) {
                oneCase();
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void oneCase() throws Exception {
        int[] constant = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        M = constant[0];    N = constant[1];    K = constant[2];
        farm = new int[N][M];

        for (int i = 0; i < K; i++) {
            String[] line = br.readLine().split(" ");
            int b = Integer.parseInt(line[0]);
            int a = Integer.parseInt(line[1]);

            farm[a][b] = 1;
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (farm[i][j] == 1) {
                    count++;
                    DFS(i, j);
                }
            }
        }

        sb.append(count).append('\n');
    }

    static void DFS(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M) return;
        if (farm[i][j] != 1) return;
        farm[i][j] = -1;
        DFS(i-1, j);
        DFS(i+1, j);
        DFS(i, j-1);
        DFS(i, j+1);
    }
}
