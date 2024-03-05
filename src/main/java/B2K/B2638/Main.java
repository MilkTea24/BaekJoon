package B2K.B2638;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] board;

    static boolean[][] isVisited;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0]; M = line[1];

        board = new int[N][M];

        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        int times = 0;

        DFS(0, 0);

        //치즈 1, 녹을 예정인 치즈 2, 내부 공기 0, 외부 공기 3
        while (true) {
            boolean isAllCheeseMelted = true;

            //녹은 치즈 2에서 3으로 바꾸기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 2) board[i][j] = 3;
                    if (board[i][j] == 1) isAllCheeseMelted = false;
                }
            }


            if (isAllCheeseMelted) break;

            isVisited = new boolean[N][M];

            //치즈 외부 공간 찾아내기
            DFS(0, 0);

            //녹을 치즈 찾아내기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 1) findMeltingCheese(i, j);
                }
            }

            times++;
        }

        System.out.println(times);
    }

    private static void DFS(int a, int b) {
        if (a < 0 || a >= N || b < 0 || b >= M) return;

        if (board[a][b] == 1) return;

        if (isVisited[a][b]) return;

        board[a][b] = 3;

        isVisited[a][b] = true;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            DFS(newI, newJ);
        }
    }

    private static void findMeltingCheese(int a, int b) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;

            if (board[newI][newJ] == 3) count++;
        }

        if (count >= 2) board[a][b] = 2;
    }
}
