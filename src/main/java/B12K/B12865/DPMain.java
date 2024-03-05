package B12K.B12865;

import java.util.*;
import java.io.*;

public class DPMain {
    static int[][] items;
    static int[][] memo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = line[0];    int K = line[1];

        items = new int[N+1][2];
        memo = new int[N+1][K+1];

        for (int i = 0; i < N; i++) {
            items[i+1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //case 1은 배열 초기화 때 자동으로 0으로 배치
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (items[i][0] > j) memo[i][j] = memo[i-1][j]; //case 2
                else {
                    //case 3
                    memo[i][j] = Math.max(memo[i-1][j], memo[i-1][j - items[i][0]] + items[i][1]);
                }
            }
        }

        System.out.print(memo[N][K]);
    }
}
