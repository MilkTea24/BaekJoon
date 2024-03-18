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

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                System.out.println("i: " + i + ", j: " + j + " 값: " + memo[i][j][0]);

                //n이 1 증가해서 j번째 여행을 넣을 수 있다면
                //(i - 1, j) -> (i, j)
                if (memo[i - 1][j][1] < trips.get(j-1).start && trips.get(j-1).end <= i) {
                    if (memo[i - 1][j][0] != i - 1) {
                        memo[i][j][0] = Math.max(memo[i - 1][j][0], (i - memo[i - 1][j][1]) - (trips.get(j - 1).end - trips.get(j - 1).start + 1));
                    }
                    else memo[i][j][0] = (i - memo[i - 1][j][1]) - (trips.get(j - 1).end - trips.get(j - 1).start + 1);
                    memo[i][j][1] = trips.get(j-1).end;
                    System.out.println("갱신: " + memo[i][j][0]);
                }
                //넣을 수 없다면 간격 1 증가
                else {
                    memo[i][j][0] = Math.max(memo[i - 1][j][0], i - memo[i - 1][j][1]);
                }

                //m이 1 증가해서 다음 여행을 넣을 수 있다면
                //(i, j - 1) -> (i, j)
                if (memo[i][j - 1][1] < trips.get(j-1).start && trips.get(j-1).end <= i) {
                    //앞서 택한 계산한 결과보다 더 작은 값을 구할 수 있다면
                    if (memo[i][j][0] > (i - memo[i][j - 1][1]) - (trips.get(j-1).end - trips.get(j-1).start + 1)) {
                        memo[i][j][0] = (i - memo[i][j - 1][1]) - (trips.get(j-1).end - trips.get(j-1).start + 1);
                        memo[i][j][1] = trips.get(j-1).end;
                        System.out.println("두번째 갱신: " + memo[i][j][0]);
                    }
                } else {
                    memo[i][j][0] = Math.min(memo[i][j][0], memo[i][j - 1][0]);
                }
                System.out.println("최종값: " + memo[i][j][0]);
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
