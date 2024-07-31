package B16K.B16946;

import java.io.*;
import java.util.*;

/*
6시 45분 시작
빈 공간을 저장
그 벽에서 상하좌우 공간을 더하기
 */
public class Main {
    static int N, M;
    static int[][] input;
    static int[][] dp;
    static int[][] groupNum;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];

        input = new int[N][M];
        dp = new int[N][M];
        groupNum = new int[N][M];

        for (int i = 0; i < N; i++) {
            input[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int groupNumIndex = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (input[i][j] == 0) {
                    int count = countDFS(i, j, 0);
                    fillDFS(i, j, count, groupNumIndex);
                    groupNumIndex += 1;
                }
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(groupNum[i][j] + " ");
            }
            System.out.println();
        }
         */


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (input[i][j] == 1) {
                    sb.append(addBlanks(i, j));
                }
                else sb.append(0);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int countDFS(int a, int b, int count) {
        if (a < 0 || a >= N || b < 0 || b >= M) return 0;

        if (input[a][b] != 0) return 0;

        int result = 1;
        input[a][b] = -1;

        for (int i = 0; i < 4; i++) {
            int newA = a + direction[i][0];
            int newB = b + direction[i][1];

            result += countDFS(newA, newB, count + 1);
        }

        return result;
    }

    static void fillDFS(int a, int b, int count, int groupNumIndex) {
        if (a < 0 || a >= N || b < 0 || b >= M) return;

        if (input[a][b] != -1) return;
        if (dp[a][b] != 0) return;

        dp[a][b] = count;
        groupNum[a][b] = groupNumIndex;

        for (int i = 0; i < 4; i++) {
            int newA = a + direction[i][0];
            int newB = b + direction[i][1];

            fillDFS(newA, newB, count, groupNumIndex);
        }
    }

    static int addBlanks(int a, int b) {
        int result = 1;

        Set<Integer> groupNumSet = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int newA = a + direction[i][0];
            int newB = b + direction[i][1];

            if (newA < 0 || newA >= N || newB < 0 || newB >= M) continue;

            if (groupNumSet.contains(groupNum[newA][newB])) continue;

            result += dp[newA][newB];
            groupNumSet.add(groupNum[newA][newB]);
        }

        return result % 10;
    }


}
