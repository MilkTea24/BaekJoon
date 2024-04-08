package B15K.B15919;

import java.io.*;
import java.util.*;

/*
24/03/19 07:30
여행 중이 아닌 기간을 가능한 짧게 하는 것으로 목표
목록 내 기간을 가거나 또는 가지 않거나
겹치는 경우는 없어야 한다. 경계도 겹치면 안됨 ~ 7월 23일, 7월 23일 ~ X
여행 중인 기간이 여러 개 있을 텐데 이 최대값이 제일 작은 여행 계획을 세워야 한다.
M = 1000 -> 조합으로는 풀 수 없음
그리디
간격이 최소가 되어야하니 제일 가까운 것을 선택?
24 29 -> O
20 23 -> O
12 15 -> O
10 11 -> O
3 10 -> X

배낭 문제? 여행기간이 n(1 <= n <= N)에서 여행 일정 m(1 <= m <= M)까지의 간격 최소
dp[n][m] = Math.min(dp[n][m-1], dp[n-1][m])

 */
public class Main {
    static int[][][] memo;
    static List<Trip> trips = new ArrayList<>();
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        memo = new int[N + 1][M + 1][2]; //memo[n][m][0]은 n과 m일 때 최댓값의 최소

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                memo[i][j][0] = i;
            }
        }

        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
            trips.add(new Trip(input[0], input[1]));
        }

        Collections.sort(trips);

        //1. j번째 여행을 넣을 수 없으면 memo[i][j] = memo[i][j-1]
        //2. j번째 여행을 넣을 수 있으면 memo[i][j] = Math.min((여행을 넣지 않는 선택)(memo[i-1][j] + 1), (여행을 넣었을 때)(앞뒤 간격 구하기))
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {

                if (memo[i - 1][j][1] >= trips.get(j-1).start || trips.get(j-1).end > i){
                    memo[i][j][0] = memo[i][j-1][0];
                }
                else {
                    memo[i][j][0] = Math.min(Math.max(memo[i-1][j][0], i - memo[i-1][j][1]),
                            Math.max(trips.get(j-1).start - memo[i-1][j][1] - 1,
                                    i - trips.get(j-1).end));
                    if (Math.max(memo[i-1][j][0], i - memo[i-1][j][1]) > Math.max(trips.get(j-1).start - memo[i-1][j][1] - 1,
                            i - trips.get(j-1).end)) {
                        memo[i][j][1] = trips.get(j-1).end;
                    }
                }
            }
        }

        for (int i = 0; i < N + 1; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < M + 1; j++) {
                System.out.print(memo[i][j][0] + " ");
            }
            System.out.println();
        }

        System.out.print(memo[N][M][0]);
    }

    static class Trip implements Comparable<Trip> {
        int start;
        int end;

        Trip(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Trip t) {
            if (end != t.end) return end - t.end;
            else return start - t.start;

        }
    }
}
