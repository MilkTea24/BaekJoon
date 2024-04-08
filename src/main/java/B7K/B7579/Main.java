package B7K.B7579;

import java.io.*;
import java.util.*;

/*
10시 39분 시작

실행중이지 않더라도 앱을 종료하지 않음
하지만 메모리가 꽉차면 종료할 앱을 골라야 함
앱을 메모리 공간, 다시 실행하고자 할 경우 추가 비용
비용의 합을 최소화하여 M 바이트를 확보
일단 M 바이트만 확보하면 됨 -> 비용은 최소화
앱은 최대 100개 백트래킹? dp
dp[n](1~n 최소비용) =
    If (1~n의 확보 바이트 메모리합 >= M) dp[n] = dp[n-1]
    If (1~n의 확보 바이트 메모리합 < M) dp[n] = Math.min(1 ~ n-1 dp 최소값 중 메모리 확보 가능한 것) + cost[n]

배낭 문제
dp[n][m] = Math.min(dp[n-1][m] <- 비활성화 앱으로 선택하지 않을 경우, dp[n-1][m-현재 앱 메모리] + 현재 비용 <- 비활성화 앱으로 선택할 경우)
----->dp[n][m]에 비용을 저장해서 최소 비용을 찾는 것이 아닌 dp[n][c]에 메모리를 저장해서 최대 메모리를 찾음(c는 비용)
dp를 순회하면서 최대 메모리가 M 넘는 것중 c가 최소가 되는 cost를 찾으면 된다
 */
public class Main {
    static List<App> appList = new ArrayList<>();
    static int N, M;

    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        dp = new int[N + 1][10001];

        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            appList.add(new App(line[i]));
        }

        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            appList.get(i).cost = line[i];
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 10001; j++) {
                if (j >= appList.get(i-1).cost) dp[i][j] = Math.max(dp[i-1][j] /*비활성화로 선택안함*/, dp[i-1][j - appList.get(i-1).cost] + appList.get(i-1).mem);
                else dp[i][j] = dp[i-1][j];
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < 10001; j++) {
                if (dp[i][j] >= M) minCost = Math.min(j, minCost);
            }
        }

        System.out.println(minCost);
    }

    static class App {
        int mem;
        int cost;

        App(int m) {
            mem = m;
        }
    }
}
