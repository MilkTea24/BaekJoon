package B2K.B2665;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isVisited;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        isVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] result = dijkstra(0);
        System.out.print(result[N-1][N-1]);
    }

    private static int[][] dijkstra(int start) {
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], 10000);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[start][start] = 0;
        for (int i = 0; i < 4; i++) {
            int newI = direction[i][0];
            int newJ = direction[i][1];
            if (!isValidIndex(newI, newJ)) continue;
            int dist = 0;
            if (map[newI][newJ] == 0) dist = 1;
            pq.add(new Node(newI, newJ, dist));
            distance[newI][newJ] = dist;
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            //System.out.println(current.endA + ", " + current.endB + " : " + current.dist);

            if (current.dist > distance[current.endA][current.endB]) continue;

            for (int i = 0; i < 4; i++) {
                int newI = current.endA + direction[i][0];
                int newJ = current.endB + direction[i][1];
                if (!isValidIndex(newI, newJ)) continue;
                int dist = current.dist;
                if (map[newI][newJ] == 0) dist += 1;
                //System.out.println("new Distance : " + dist + ", old : " + distance[newI][newJ]);
                if (distance[newI][newJ] > dist) {
                    pq.add(new Node(newI, newJ, dist));
                    distance[newI][newJ] = dist;
                }
            }
        }

        return distance;
    }

    private static boolean isValidIndex(int a, int b) {
        return a >= 0 && a < N && b >= 0 && b < N;
    }

    static class Node implements Comparable<Node> {
        int endA;

        int endB;

        int dist;

        Node(int eA, int eB, int d) {
            endA = eA;
            endB = eB;
            dist = d;
        }

        @Override
        public int compareTo(Node n) {
            return dist - n.dist;
        }
    }
}
