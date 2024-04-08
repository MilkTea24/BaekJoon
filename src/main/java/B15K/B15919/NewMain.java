package B15K.B15919;

import java.io.*;
import java.util.*;

//dp[i] = 마지막 여행이 i에 끝났을 때 결과값
public class NewMain {
    static int N, M;
    static List<Trip> tripsSortedByStart = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            tripsSortedByStart.add(new Trip(line[0], line[1]));
        }


        Collections.sort(tripsSortedByStart, (t1, t2) -> {
            return t1.start - t2.start;
        });

        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (tripsSortedByStart.get(j).start <= i) continue;

                dp[tripsSortedByStart.get(j).end] = Math.min(Math.max(dp[i], tripsSortedByStart.get(j).start - i - 1), dp[tripsSortedByStart.get(j).end]);
            }
            dp[i] = Math.max(dp[i], N - i);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }

    static class Trip {
        int start;
        int end;

        Trip (int s, int e) {
            start = s;
            end = e;
        }
    }
}
