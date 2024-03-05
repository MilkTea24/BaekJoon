package B14K.B14500;

import java.util.*;
import java.io.*;

public class Main {
    static int M, N;
    static int[][] map;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int maxValue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                LinkedList<Node> visited = new LinkedList<>();
                visited.addLast(new Node(i, j));
                maxValue = Math.max(maxValue, findMaxValues(i, j, 1, map[i][j], visited));

                maxValue = Math.max(maxValue, specialCaseSum(i, j));
            }
        }

        System.out.println(maxValue);

    }

    static int specialCaseSum(int i, int j) {
        int maxSum = 0;
        boolean flag = true;

        if (j + 2 < M) {
            int partialSum = map[i][j] + map[i][j+1] + map[i][j+2];
            if (i >= 1) maxSum = Math.max(maxSum, partialSum + map[i-1][j+1]);
            if  (i < N-1) maxSum = Math.max(maxSum, partialSum + map[i+1][j+1]);
        }

        if (i + 2 < N) {
            int partialSum = map[i][j] + map[i+1][j] + map[i+2][j];
            if (j >= 1) maxSum = Math.max(maxSum, partialSum + map[i+1][j-1]);
            if (j < M-1) maxSum = Math.max(maxSum, partialSum + map[i+1][j+1]);
        }
        return maxSum;

    }

    static int findMaxValues(int i, int j, int depth, int maxValue, LinkedList<Node> visited) {
        int result = 0;
        if (depth == 4) {
            /*
            for (int k = 0; k < 4; k++) {
                System.out.printf("(%d, %d) ", visited.get(k).i, visited.get(k).j);
            }
            System.out.print(": " + maxValue);
            System.out.println();
             */

            return maxValue;
        }

        for (int k = 0; k < 4; k++) {
            int newI = i + direction[k][0];
            int newJ = j + direction[k][1];

            if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;
            if (visited.contains(new Node(newI, newJ))) continue;

            visited.addLast(new Node(newI, newJ));
            result = Math.max(result, findMaxValues(newI, newJ, depth + 1, maxValue + map[newI][newJ], visited));
            visited.removeLast();
        }

        return result;
    }

    static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node)  {
                Node n = (Node)obj;
                return (i == n.i) && (j == n.j);
            }
            return false;
        }
    }
}
