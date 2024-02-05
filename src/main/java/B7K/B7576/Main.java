package B7K.B7576;

import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int M, N;
    static Tomato[][] tomatoes;
    static final int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();

        M = Integer.parseInt(firstLine.split(" ")[0]);
        N = Integer.parseInt(firstLine.split(" ")[1]);

        tomatoes = new Tomato[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = new Tomato(i, j, Integer.parseInt(line[j]), 0);
            }
        }

        Queue<Tomato> ripenTomatoes = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j].status == 1) ripenTomatoes.add(tomatoes[i][j]);
            }
        }
        System.out.println(BFS(ripenTomatoes));
    }

    static int BFS(Queue<Tomato> queue) {
        int maxDays = 0;

        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            for (int k = 0; k < 4; k++) {
                int newI = tomato.i + directions[k][0];
                int newJ = tomato.j + directions[k][1];

                if (newI >= 0 && newI < N && newJ >= 0 && newJ < M && tomatoes[newI][newJ].status == 0) {
                    tomatoes[newI][newJ].days = tomato.days + 1;
                    tomatoes[newI][newJ].status = 1;
                    queue.add(tomatoes[newI][newJ]);
                    maxDays = Math.max(tomatoes[newI][newJ].days, maxDays);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j].status == 0) return -1;
            }
        }
        return maxDays;
    }

    static class Tomato{
        int i;
        int j;
        int status;
        int days;

        public Tomato(int i, int j, int status, int days) {
            this.i = i;
            this.j = j;
            this.status = status;
            this.days = days;
        }
    }

}
