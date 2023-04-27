package B2K.B2178;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    static int N, M;
    static int miro[][];
    static int distance[][];
    static int near[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] NM = br.readLine().split(" ");
            N = Integer.parseInt(NM[0]);  M = Integer.parseInt(NM[1]);
            miro = new int[N][M];
            distance = new int[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    miro[i][j] = line.charAt(j) - '0';
                }
            }

            System.out.println(minDistanceBFS());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int minDistanceBFS() {
        Position start = new Position(0, 0);
        LinkedList<Position> queue = new LinkedList<>();
        miro[start.x][start.y] = -1;
        queue.addLast(start);

        while (!queue.isEmpty()) {
            Position current = queue.removeFirst();
            for (int i = 0; i < 4; i++) {
                int nearNodeX = current.x + near[i][0];
                int nearNodeY = current.y + near[i][1];
                if (nearNodeX < 0 || nearNodeX >= N) continue;
                if (nearNodeY < 0 || nearNodeY >= M) continue;
                if (miro[nearNodeX][nearNodeY] == 1) {
                    miro[nearNodeX][nearNodeY] = -1;
                    distance[nearNodeX][nearNodeY] = distance[current.x][current.y] + 1;
                    queue.add(new Position(nearNodeX, nearNodeY));
                }
            }

            if (miro[N-1][M-1] == -1) {
                return distance[N-1][M-1] + 1;
            }
        }
        return distance[N-1][M-1] + 1;
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
