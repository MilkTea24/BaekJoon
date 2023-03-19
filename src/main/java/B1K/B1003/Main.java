package B1K.B1003;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] fibonacci;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            fibonacci = new int[41][2];
            fibonacci[0][0] = 1;
            fibonacci[0][1] = 0;
            fibonacci[1][0] = 0;
            fibonacci[1][1] = 1;
            for (int i = 0; i < T; i++) {
                int N = Integer.parseInt(br.readLine());
                returnCalledNumber(N);
                sb.append(fibonacci[N][0]).append(' ').append(fibonacci[N][1]).append('\n');
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static void returnCalledNumber(int n) {
        if (fibonacci[n][0] != 0 || fibonacci[n][1] != 0) return;
        else {
            returnCalledNumber(n-1);
            returnCalledNumber(n-2);
            fibonacci[n][0] = fibonacci[n-1][0] + fibonacci[n-2][0];
            fibonacci[n][1] = fibonacci[n-1][1] + fibonacci[n-2][1];
            return;
        }
    }
}
