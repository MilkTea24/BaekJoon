package B1K.B1753;

import java.util.*;
import java.io.*;

public class Main_two {
    static int V, E;

    static List<List<Node>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = line[0];
        E = line[1];

        int start = Integer.parseInt(br.readLine()) - 1;

        for (int i = 0; i < V; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startPos = line[0] - 1;
            int endPos = line[1] - 1;
            int dist = line[2];

            edgeInfo.get(startPos).add(new Node(endPos, dist));
        }

        int[] result = dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            if (i == Integer.MAX_VALUE) sb.append("INF").append('\n');
            else sb.append(i).append('\n');
        }
        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        int[] result = new int[V];
        Arrays.fill(result, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        result[start] = 0;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.dist > result[current.pos]) continue;

            List<Node> nearNodeInfo = edgeInfo.get(current.pos);

            for (Node n : nearNodeInfo) {
                if (current.dist + n.dist < result[n.pos]) {
                    result[n.pos] = current.dist + n.dist;
                    queue.add(new Node(n.pos, result[n.pos]));
                }
            }
        }

        return result;
    }

    static class Node implements Comparable<Node> {
        int pos;
        int dist;

        Node(int p, int d) {
            pos = p;
            dist = d;
        }

        @Override
        public int compareTo(Node n) {
            return dist - n.dist;
        }

        @Override
        public String toString() {
            return "(" + pos + ", " + dist + ")";
        }
    }
}
