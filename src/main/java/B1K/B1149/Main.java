package B1K.B1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] memo;
    static int[][] others = {{1, 2}, {0, 2}, {0, 1}};
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        memo = new int[n][3];



        for (int i = 0; i < n; i++) {
            int[] RGB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (i == 0) {
                memo[0][0] = RGB[0];
                memo[0][1] = RGB[1];
                memo[0][2] = RGB[2];
                continue;
            }

            for (int j = 0; j < 3; j++) {
                int minSum = RGB[j] + Math.min(memo[i - 1][others[j][0]], memo[i - 1][others[j][1]]);
                memo[i][j] = minSum;
            }
        }

        System.out.println(Math.min(memo[n - 1][0], Math.min(memo[n - 1][1], memo[n - 1][2])));
    }
}
