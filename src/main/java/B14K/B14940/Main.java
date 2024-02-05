package B14K.B14940;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    static int[][] map;
    static int[][] result;
    static int N;
    static int M;
    static int[][] direction = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        map = new int[N][M];
        result = new int[N][M];
        int startI = 0;
        int startJ = 0;
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    startI = i;
                    startJ = j;
                }
                if (map[i][j] == 1) result[i][j] = -1;
            }
        }


        BFS(startI, startJ);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void BFS(int i, int j) {
        LinkedList<Node> queue = new LinkedList<>();
        map[i][j] = 0;
        queue.addLast(new Node(i, j, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            for (int k = 0; k < 4; k++) {
                int newI = currentNode.i + direction[k][0];
                int newJ = currentNode.j + direction[k][1];

                if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;
                if (result[newI][newJ] == -1) {
                    map[newI][newJ] = 0;
                    result[newI][newJ] = currentNode.currentDistance + 1;
                    queue.add(new Node(newI, newJ, currentNode.currentDistance + 1));
                }
            }

        }
    }

    static class Node {
        int i;
        int j;
        int currentDistance;

        public Node(int i, int j, int currentDistance) {
            this.i = i;
            this.j = j;
            this.currentDistance = currentDistance;
        }
    }
}
