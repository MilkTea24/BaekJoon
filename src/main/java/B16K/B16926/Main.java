package B16K.B16926;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];    T = line[2];

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        for (int i = 0; i < T; i++) {
            rotate(N, M);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    public static void rotate(int n, int m) {
        if (n <= 1 || m <= 1) return;

        int remain = moveDown(n, m); //remain에는 13
        remain = moveRight(n, m, remain); //remain에는 16
        remain = moveUp(n, m, remain); //remain에는 2
        moveLeft(n, m, remain);

        rotate(n - 2, m - 2);
    }

    public static int moveDown(int n, int m) {
        int startX = (N-n)/2;
        int endX = N-1 - (N-n)/2;
        int y = (M-m)/2;

        int result = map[endX][y];
        for (int i = endX; i >= startX + 1; i--) {
            map[i][y] = map[i-1][y];
        }

        return result;
    }

    public static int moveRight(int n, int m, int remain) {
        int x = N-1 - (N-n)/2;
        int startY = (M-m)/2;
        int endY = M-1 - (M-m)/2;

        int result = map[x][endY];
        for (int i = endY; i >= startY + 2; i--) {
            map[x][i] = map[x][i-1];
        }
        map[x][startY + 1] = remain;

        return result;
    }

    public static int moveUp(int n, int m, int remain) {
        int y = M-1 - (M-m)/2;
        int startX = (N-n)/2;
        int endX = N-1 - (N-n)/2;

        int result = map[startX][y];
        for (int i = startX; i < endX - 1; i++) {
            map[i][y] = map[i+1][y];
        }
        map[endX - 1][y] = remain;
        return result;
    }

    public static void moveLeft(int n, int m, int remain) {
        int x = (N-n)/2;
        int startY = (M-m)/2;
        int endY = M-1 - (M-m)/2;

        for (int i = startY; i < endY - 1; i++) {
            map[x][i] = map[x][i+1];
        }
        map[x][endY - 1] = remain;
    }


}
