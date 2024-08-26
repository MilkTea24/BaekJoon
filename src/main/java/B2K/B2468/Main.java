package B2K.B2468;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int maxHeight = 0;

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            maxHeight = Arrays.stream(map[i]).max().orElse(maxHeight);
        }

        int maxGroupNum = 0;
        for (int i = 0; i <= maxHeight; i++) {
            maxGroupNum = Math.max(maxGroupNum, DFSStart(i));
        }

        System.out.println(maxGroupNum);
    }

    static int DFSStart(int n) {
        //System.out.println("stage: " + n);

        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        int maxGroupNum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[i][j] > n) {
                    DFS(i, j, newMap, n);
                    maxGroupNum = maxGroupNum + 1;
                }
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(newMap[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("stage result: " + maxGroupNum);

         */

        return maxGroupNum;
    }

    static void DFS(int a, int b, int[][] map, int n) {
        if (a < 0 || a >= N || b < 0 || b>= N) return;
        if (map[a][b] <= n) return;

        map[a][b] = 0;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            DFS(newI, newJ, map, n);
        }
    }
}
