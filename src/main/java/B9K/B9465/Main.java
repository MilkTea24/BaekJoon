package B9K.B9465;

import java.io.*;
import java.util.*;

//n = 1 두 개 사이 최대값 = 50
//n = 2
public class Main {
    static int[][] memo = new int[2][3];
    static int[][] input;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cases; i++) {
            sb.append(oneCase()).append('\n');
        }

        System.out.print(sb);
    }

    private static int oneCase() throws Exception {
        int n = Integer.parseInt(br.readLine());

        input = new int[2][n];

        for (int i = 0; i < 2; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //1열 초기화
        memo[0][0] = input[0][0];
        memo[1][0] = input[1][0];
        if (n == 1) return Integer.max(input[0][0], input[1][0]);


        //2열 초기화
        memo[0][1] = input[1][0] + input[0][1];
        memo[1][1] = input[0][0] + input[1][1];
        if (n == 2) return Integer.max(memo[0][1], memo[1][1]);

        for (int i = 2; i < n; i++) {
            memo[0][2] = Integer.max(memo[1][1], memo[1][0]) + input[0][i];
            memo[1][2] = Integer.max(memo[0][1], memo[0][0]) + input[1][i];

            //이동
            memo[0][0] = memo[0][1];
            memo[0][1] = memo[0][2];

            memo[1][0] = memo[1][1];
            memo[1][1] = memo[1][2];
        }

        return Integer.max(memo[0][2], memo[1][2]);
    }
}
