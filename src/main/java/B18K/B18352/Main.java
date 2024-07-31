package B18K.B18352;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K, X;
    static List<List<Edge>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];    M = line[1];    K = line[2];    X = line[3] - 1;

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;

            edgeInfo.get(start).add(new Edge(end, 1));
        }

        int[] result = dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (result[i] == K) {
                sb.append(i + 1).append('\n');
            }
        }
        if (sb.isEmpty()) sb.append(-1);
        System.out.print(sb);
    }

    private static int[] dijkstra() {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[X] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(X, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (dist[current.ind] < current.dist) continue;

            List<Edge> nearEdgeInfo = edgeInfo.get(current.ind);

            for (Edge e : nearEdgeInfo) {
                if (dist[e.ind] > dist[current.ind] + e.dist) {
                    dist[e.ind] = dist[current.ind] + e.dist;
                    pq.add(new Edge(e.ind, dist[e.ind]));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int ind;
        int dist;

        Edge(int i, int d) {
            ind = i;
            dist = d;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }
}
