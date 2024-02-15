package B1K.B1932;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //초기화
            if (i == 0) {
                memo[0][0] = line[0];
                continue;
            }

            for (int j = 0; j <= i; j++) {
                //왼쪽 끝일경우
                if (j == 0) memo[i][j] = memo[i-1][j] + line[j];
                //오른쪽 끝일경우
                else if (j == i) memo[i][j] = memo[i-1][j-1] + line[j];
                //그 외 모든 경우
                else {
                    memo[i][j] = Integer.max(memo[i-1][j-1] + line[j], memo[i-1][j] + line[j]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Integer.max(result, memo[n-1][i]);
        }

        System.out.println(result);
    }
}
