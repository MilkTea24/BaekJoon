package B10K.B10830;

import java.io.*;
import java.util.*;

//B가 천억 -> 무조건 log N 알고리즘 써야 함
public class Main {
    static int[][] input;
    static int N;
    static long B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        B = Long.parseLong(line[1]);

        input = new int[N][N];
        for (int i = 0; i < N; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] result = square(B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] + " ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static int[][] square(long B) {
        int[][] result;
        if (B == 1L) {
            result = input;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result[i][j] = result[i][j] % 1000;
                }
            }
            return result;
        }

        result = multi(input, input);
        if (B == 2L) return result;
        if (B == 3L) return multi(result, input);

        if (B % 2L == 0L) {
            int[][] half = square(B/2L);
            return multi(half, half);
        }
        else {
            int[][] half = square(B/2L);
            return multi(multi(half, half), input);
        }
    }

    private static int[][] multi(int[][] a, int[][] b) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += (a[i][k] * b[k][j]) % 1000;
                }
                result[i][j] = (result[i][j]) % 1000;
            }
        }

        return result;
    }
}
