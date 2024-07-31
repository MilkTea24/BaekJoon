package B14K.B14628;

import java.io.*;
import java.util.*;

/*
11시 21분 시작
스킬을 한번 사용할 때마다 비용이 K 증가
최소 마나 포인트
 */
public class Main {
    static int N, M, K;
    static int[][] skill;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];    K = line[2];

        skill = new int[N][2];

        for (int i = 0; i < N; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            skill[i][0] = line[0];  skill[i][1] = line[1];
        }

        dp = new int[M + 1];
        int[][] picked = new int[M+1][N];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 0; i <= M; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            for (int j = 0; j < N; j++) {
                if (i + skill[j][1] > M) continue;

                if (dp[i + skill[j][1]] > dp[i] + skill[j][0] + (K * picked[i][j])) {
                    dp[i + skill[j][1]] = dp[i] + skill[j][0] + (K * picked[i][j]);
                    int[] newPicked = new int[N];
                    System.arraycopy(picked[i], 0, newPicked, 0, N);
                    newPicked[j] += 1;
                    picked[i + skill[j][1]] = newPicked;
                }
            }
        }

        System.out.println(dp[M]);
    }
}
