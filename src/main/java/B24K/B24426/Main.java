package B24K.B24426;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] result;
    static int[][] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        result = new int[n][n];
        input = new int[n][n];

        for (int i = 0; i < n; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = line[0] - 1;
        int c = line[1] - 1;

        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], Integer.MIN_VALUE);
        }

        result[0][0] = input[0][0];

        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (i == r && j == c) break;
                if (i + 1 <= r) result[i + 1][j] = Math.max(result[i+1][j], result[i][j] + input[i+1][j]);
                if (j + 1 <= c) result[i][j + 1] = Math.max(result[i][j+1], result[i][j] + input[i][j+1]);
            }
        }

        for (int i = r; i < n; i++) {
            for (int j = c; j < n; j++) {
                if (i + 1 < n) result[i + 1][j] = Math.max(result[i+1][j], result[i][j] + input[i+1][j]);
                if (j + 1 < n) result[i][j + 1] = Math.max(result[i][j+1], result[i][j] + input[i][j+1]);
            }
        }

        System.out.print(result[n-1][n-1] + " ");

        result = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], Integer.MIN_VALUE);
        }

        result[0][0] = input[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == r && j == c) continue;
                if (i + 1 < n) {
                    if (i + 1 != r || j != c) result[i + 1][j] = Math.max(result[i+1][j], result[i][j] + input[i+1][j]);
                }
                if (j + 1 < n) {
                    if (i != r || j + 1 != c) result[i][j + 1] = Math.max(result[i][j+1], result[i][j] + input[i][j+1]);
                }
            }
        }

        System.out.print(result[n-1][n-1]);
    }
}
