package B14K.B14630;


import java.io.*;
import java.util.*;

/*
한상태 -> 다른 상태로 가는데 드는 비용은 차이의 제곰
한 상태 -> 특정 다른 상태까지의 최소 비용
 */
public class Main {
    static int N;

    static String[] posInfo;

    static int[][] edgeInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        posInfo = new String[N];

        for (int i = 0; i < N; i++) {
            posInfo[i] = br.readLine();
        }

        edgeInfo = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) edgeInfo[i][j] = Integer.MAX_VALUE;
                edgeInfo[i][j] = calculateDist(i, j);
            }
        }

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] result = dijkstra(line[0] - 1);

        System.out.println(result[line[1] - 1]);
    }

    static int[] dijkstra(int start) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        result[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            //System.out.println("current pos: " + current.pos + ", current dist: " + current.dist);

            if (current.dist > result[current.pos]) continue;

            int[] currentEdgeInfo = edgeInfo[current.pos];

            for (int i = 0; i < N; i++) {
                //System.out.println("====================" + (current.dist + currentEdgeInfo[i]) + ", " + result[current.pos]);
                if (currentEdgeInfo[i] == Integer.MAX_VALUE) continue;

                if (current.dist + currentEdgeInfo[i] < result[i]) {

                    result[i] = current.dist + currentEdgeInfo[i];
                    pq.add(new Node(i, result[i]));
                }
            }
        }

        return result;
    }

    static int calculateDist(int a, int b) {
        if (edgeInfo[b][a] != 0) return edgeInfo[b][a];

        int result = 0;
        for (int i = 0; i < posInfo[a].length(); i++) {
            result = result + (posInfo[a].charAt(i) - posInfo[b].charAt(i)) * (posInfo[a].charAt(i) - posInfo[b].charAt(i));
        }
        return result;
    }

    static class Node implements Comparable<Node> {
        int pos;
        int dist;

        Node (int p, int d) {
            pos = p;
            dist = d;
        }

        @Override
        public int compareTo(Node n) {
            return dist - n.dist;
        }
    }
}
