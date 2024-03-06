package B9K.B9461;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static long[] memo = new long[100];

    public static void main(String[] args) throws Exception {
        memo[2] = memo[1] = memo[0] = 1;
        memo[4] = memo[3] = 2;

        for (int i = 5; i < 100; i++) {
            memo[i] = memo[i-1] + memo[i-5];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int inputNum = Integer.parseInt(br.readLine());
            sb.append(memo[inputNum-1]).append("\n");
        }
        System.out.println(sb);
    }
}
