package B11K.B11403;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] input = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(line[j]);
            }
        }

        int[][] result = new int[n][n];

        //DFS
        for (int i = 0; i < n; i++) {
            boolean[] isVisited = new boolean[n];
            for (int j = 0; j < n; j++) {
                if (input[i][j] == 1) DFS(i, j, input, result, isVisited, n);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void DFS(int i, int currentNum, int[][] input, int[][] result, boolean[] isVisited, int n) {
        isVisited[currentNum] = true;
        result[i][currentNum] = 1;
        for (int j = 0; j < n; j++) {
            if (input[currentNum][j] == 1 && !isVisited[j]) {
                DFS(i, j, input, result, isVisited, n);
            }
        }
    }
}
