package B1K.B1987;

import java.io.*;
import java.util.*;
public class NewMain {
    static int R;
    static int C;
    static int[][] board;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        R = line[0];
        C = line[1];

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            String lineString = br.readLine();

            for (int j = 0; j < C; j++) {
                board[i][j] = lineString.charAt(j);
            }
        }

        int result = DFS(0, 0, new boolean[26], 0);

        System.out.print(result);
    }

    public static int DFS(int a, int b, boolean[] isVisitedAlphabet, int count) {
        if (a < 0 || a >= R || b < 0 || b >= C) return count;

        if (isVisitedAlphabet[board[a][b] - 'A']) return count;

        int result = 0;
        isVisitedAlphabet[board[a][b] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            result = Integer.max(result, DFS(newI, newJ, isVisitedAlphabet, count + 1));
        }

        isVisitedAlphabet[board[a][b] - 'A'] = false;

        return result;
    }
}
