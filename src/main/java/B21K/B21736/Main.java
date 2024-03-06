package B21K.B21736;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//상하좌우 이동가능
//벽은 지나갈 수 없음
//O - 빈공간, X - 벽, I - 도연이, P - 사람
public class Main {
    static char[][] map;
    static int n;
    static int m;
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        map = new char[n][m];
        int startI = 0;
        int startJ = 0;
        for (int i = 0; i < n; i++) {
            String mapLine = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = mapLine.charAt(j);
                if (map[i][j] == 'I') {
                    startI = i;
                    startJ = j;
                }
            }
        }

        int result = DFS(startI, startJ, 0);

        if (result == 0) System.out.println("TT");
        else System.out.println(result);
    }

    private static int DFS(int i, int j, int result) {
        if (i < 0 || i >= n || j < 0 || j >= m) return result;
        if (map[i][j] == 'X') return result;
        if (map[i][j] == 'P') {
            result++;
        }
        map[i][j] = 'X';

        for (int k = 0; k < 4; k++) {
            int newI = i + direction[k][0];
            int newJ = j + direction[k][1];

            result = DFS(newI, newJ, result);
        }

        return result;
    }
}
