package B7K.B7569;

import java.io.*;
import java.util.*;
public class Main {
    static int[][][] tomatoes;
    static int[][] direction = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    static int M;
    static int N;
    static int H;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //박스 크기 입력 받기
        String[] tempLine = br.readLine().split(" ");
        M = Integer.parseInt(tempLine[0]);
        N = Integer.parseInt(tempLine[1]);
        H = Integer.parseInt(tempLine[2]);

        tomatoes = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                tomatoes[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        boolean isAllTomatoesRipened = true;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomatoes[i][j][k] == 0) {
                        isAllTomatoesRipened = false;
                    }
                }
            }
        }

        if (isAllTomatoesRipened) {
            System.out.println(0);
            System.exit(0);
        }


        int day = BFS();


        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomatoes[i][j][k] == 0) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println(day);
    }

    static int BFS() {
        int result = 0;
        LinkedList<Tomato> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomatoes[i][j][k] == 1) {
                        queue.add(new Tomato(i, j, k, 0));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Tomato tomato = queue.removeFirst();
            for (int ind = 0; ind < 6; ind++) {
                Integer[] nextTomatoPosition = {tomato.i + direction[ind][0], tomato.j + direction[ind][1], tomato.k + direction[ind][2]};
                int nextI = nextTomatoPosition[0];
                int nextJ = nextTomatoPosition[1];
                int nextK = nextTomatoPosition[2];

                if (isValidPosition(nextTomatoPosition) && tomatoes[nextI][nextJ][nextK] == 0) {
                    queue.add(new Tomato(nextI, nextJ, nextK, tomato.ripenedDate + 1));
                    //System.out.println(nextI + " " + nextJ + " " + nextK + " " + (tomato.ripenedDate + 1));
                    result = Integer.max(result, tomato.ripenedDate + 1);
                    tomatoes[nextI][nextJ][nextK] = 1;
                }
            }
        }

        return result;
    }

    static boolean isValidPosition(Integer[] direction) {
        int i = direction[0];
        int j = direction[1];
        int k = direction[2];
        return (i >= 0 && i < H && j >= 0 && j < N && k >= 0 && k < M);
    }

    static class Tomato {
        int i;
        int j;
        int k;
        int ripenedDate;

        Tomato(int i, int j, int k, int r) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.ripenedDate = r;
        }
    }
}
