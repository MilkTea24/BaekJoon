package B2K.B2206;

import java.io.*;
import java.util.*;

//N과 M이 주어짐
//0은 이동할 수 있는 곳
//1은 이동할 수 없는 벽이 있는 곳
//이동하는 도중에 벽을 단 한번만 부술 수 있다.(안 부숴도 되긴 된다)
public class Main {
    static int N, M;
    static int[][] board;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static boolean[][][] isVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        board = new int[N][M];
        isVisited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int result = BFS(0, 0);

        if (result == Integer.MAX_VALUE) result = -1;

        System.out.println(result);
    }

    private static int BFS(int a, int b) {
        board[a][b] = 2;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(a, b, 1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.a==N-1 && current.b == M-1) return current.distance;

            //System.out.println(current.a + " " + current.b + ": " + current.distance + " " + current.isBroken + ", " + queue.toString());

            for (int i = 0; i < 4; i++) {
                int newI = current.a + direction[i][0];
                int newJ = current.b + direction[i][1];

                if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;

                if (current.isBroken && isVisited[newI][newJ][1]) continue;

                if (!current.isBroken && isVisited[newI][newJ][0]) continue;

                if (board[newI][newJ] == 1) {
                    //벽을 이전에 이미 부쉈으면
                    if (current.isBroken) continue;

                    //벽을 처음 부쉈다면
                    queue.add(new Node(newI, newJ, current.distance + 1, true));
                }
                else queue.add(new Node(newI, newJ, current.distance + 1, current.isBroken));

                if (current.isBroken) isVisited[newI][newJ][1] = true;
                else isVisited[newI][newJ][0] = true;
            }
        }

        return -1;
    }

    private static class Node {
        int a;
        int b;

        int distance;

        boolean isBroken;

        Node(int _a, int _b, int _d){
            a = _a;
            b = _b;
            distance = _d;
        }

        Node(int _a, int _b, int _d, boolean _is) {
            a = _a;
            b = _b;
            distance = _d;
            isBroken = _is;
        }

        @Override
        public String toString() {
            return String.format("{%s, %s}", a, b);
        }
    }

    private static int DFS(int a, int b, int count, boolean breakChance) {
        int previousStatus = 0;
        if (a < 0 || a >= N || b < 0 || b >= M) return Integer.MAX_VALUE;

        //이미 벽을 한번 부쉈고 벽에 도달한 경우 실패 종료
        if (board[a][b] == 1 && !breakChance) return Integer.MAX_VALUE;

        //이미 방문한 경로인 경우 실패 종료
        if (board[a][b] == 2) return Integer.MAX_VALUE;

        //최종 목적지에 도달한 경우 성공 종료
        if (a == N-1 && b == M-1) return count;

        //벽을 안부쉈는데 벽에 도달한 경우 벽을 부숨
        if (board[a][b] == 1) {
            breakChance = false;
            previousStatus = 1;
        }

        int result = Integer.MAX_VALUE;

        board[a][b] = 2;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];
            result = Integer.min(result, DFS(newI, newJ, count + 1, breakChance));
        }

        board[a][b] = previousStatus;

        return result;
    }
}
