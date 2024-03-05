package B11K.B11053;

import java.io.*;
import java.util.*;

public class Main {
    static int[] memo;
    static int N;
    static int[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        memo = new int[N];
        memo[0] = 1;
        int maxResult = 0;

        for (int i = 0; i < N; i++) {
            memo[i] = 1;
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) memo[i] = Math.max(memo[j] + 1, memo[i]);
            }
            maxResult = Math.max(memo[i], maxResult);
        }

        System.out.println(maxResult);
    }
}
