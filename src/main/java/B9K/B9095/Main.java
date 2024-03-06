package B9K.B9095;

import java.io.*;

public class Main {
    static int[] memo = new int[11];
    public static void main(String[] args) {
        memo[0] = 1;
        memo[1] = 2; //1+1, 2
        memo[2] = 4; //1+1+1, 2+1, 1+2, 3
        for (int i = 3; i < 10; i++) {
            memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                int input = Integer.parseInt(br.readLine());
                System.out.println(memo[input-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//n을 1, 2, 3의 합으로 나타내는 방법의 수
