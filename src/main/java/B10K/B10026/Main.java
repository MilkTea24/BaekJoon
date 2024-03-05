package B10K.B10026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[][] paint;
    static char[][] paint2;
    static int n;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        paint = new char[n][n];
        paint2 = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                paint[i][j] = line.charAt(j);
                paint2[i][j] = line.charAt(j);
            }
        }

        int result1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (paint[i][j] != 'X') {
                    result1++;
                    DFS(paint, i, j, paint[i][j]);
                }
            }
        }

        int result2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (paint2[i][j] != 'X') {
                    result2++;
                    DFS2(paint2, i, j, paint2[i][j]);
                }

            }

        }

        System.out.print(result1 + " " + result2);
    }

    private static void DFS(char[][] paint, int a, int b, char startChar) {
        if (a < 0 || b < 0 || a >= n || b >= n) return;
        if (paint[a][b] == 'X' || paint[a][b] != startChar) return;

        paint[a][b] = 'X';
        for (int i = 0; i < direction.length; i++) {
            DFS(paint, a + direction[i][0], b + direction[i][1], startChar);
        }
    }

    private static void DFS2(char[][] paint, int a, int b, char startChar) {
        if (a < 0 || b < 0 || a >= n || b >= n) return;
        if (paint[a][b] == 'X') return;
        if ((startChar == 'R' || startChar == 'G') && paint[a][b] == 'B') return;
        if (startChar == 'B' && paint[a][b] != 'B')  return;

        paint[a][b] = 'X';
        for (int i = 0; i < direction.length; i++) {
            DFS2(paint, a + direction[i][0], b + direction[i][1], startChar);
        }
    }

}
