package B1K.B1922;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Edge>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;
            int dist = line[2];

            edgeInfo.get(start).add(new Edge(end, dist));
            edgeInfo.get(end).add(new Edge(start, dist));
        }

        int result = Prim(0);

        System.out.print(result);
    }

    static int Prim(int start) {
        int result = 0;
        boolean[] isVisited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        isVisited[start] = true;

        List<Edge> startEdgeInfo = edgeInfo.get(start);
        for (int i = 0; i < startEdgeInfo.size(); i++) {
            pq.add(startEdgeInfo.get(i));
        }

        int count = 0;
        while (!pq.isEmpty()) {
            if (count == N) break;

            Edge edge = pq.poll();

            //시작 끝 노드 모두 방문한 간선이면 패스
            if (isVisited[edge.ind]) continue;

            result += edge.dist;
            isVisited[edge.ind] = true;

            List<Edge> currentEdgeInfo = edgeInfo.get(edge.ind);
            for (int i = 0; i < currentEdgeInfo.size(); i++) {
                pq.add(currentEdgeInfo.get(i));
            }
        }

        return result;
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
            return dist - e.dist;
        }
    }
}
