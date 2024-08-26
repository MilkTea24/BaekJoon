package B16K.B16933;


import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];
        K = line[2];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        BFS();
    }

    static void BFS() {
        boolean[][][] isVisited = new boolean[N][M][K+1];
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(0, 0, 0, false, true, 1));
        isVisited[0][0][0] = true;

        while (!queue.isEmpty()) {
            State s = queue.poll();

            //System.out.println(s);

            if ((s.i == N-1) && (s.j == M-1)) {
                System.out.println(s.dist);
                return;
            }

            //두 번 연속 머물러 있지 않았다면 한번 같은 칸 머무를 수 있다
            if (!s.isRemainedState) {
                queue.add(new State(s.i, s.j, s.breakWallCount, true, !s.isDayTime, s.dist + 1));
            }

            for (int i = 0; i < 4; i++) {
                int newI = s.i + direction[i][0];
                int newJ = s.j + direction[i][1];

                if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;


                //벽이고 낮에만 벽 부술 수 있다
                if (map[newI][newJ] == 1 && s.isDayTime) {
                    if (s.breakWallCount + 1 <= K) {
                        if (isVisited[newI][newJ][s.breakWallCount + 1]) continue;
                        queue.add(new State(newI, newJ, s.breakWallCount + 1, false, !s.isDayTime, s.dist + 1));
                        isVisited[newI][newJ][s.breakWallCount + 1] = true;
                    }
                }
                if (map[newI][newJ] == 0) {
                    if (isVisited[newI][newJ][s.breakWallCount]) continue;
                    queue.add(new State(newI, newJ, s.breakWallCount, false, !s.isDayTime, s.dist + 1));
                    isVisited[newI][newJ][s.breakWallCount] = true;
                }
            }
        }

        System.out.println(-1);
    }

    static class State {
        int i, j;
        int breakWallCount;
        boolean isDayTime;

        boolean isRemainedState;

        int dist;

        State(int i, int j, int b, boolean r, boolean day, int d) {
            this.i = i;
            this.j = j;
            isRemainedState = r;
            breakWallCount = b;
            isDayTime = day;
            dist = d;
        }

        @Override
        public String toString() {
            return "지점: (" + i + ", " + j + ")" + '\n' + "낮 여부: " + isDayTime + '\n' + "거리: " + dist + '\n' + "벽 부순 횟수: " + breakWallCount + '\n' + "======================";
        }
    }
}