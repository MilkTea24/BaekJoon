package B1K.B1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DynamicProgramming {
    static int[] memo = new int[1000001];
    public static void main(String[] args) {
        memo[0] = -1;   memo[1] = 0;
        memo[2] = 1;    memo[3] = 1;

        for (int i = 4; i <= 1000000; i++) {
            memo[i] = Integer.MAX_VALUE;
            if (i % 2 == 0) {
                memo[i] = Integer.min(memo[i], memo[i/2] + 1);
            }
            if (i % 3 == 0) {
                memo[i] = Integer.min(memo[i], memo[i/3] + 1);
            }
            memo[i] = Integer.min(memo[i], memo[i-1] + 1);
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int input = Integer.parseInt(br.readLine());
            System.out.println(memo[input]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
