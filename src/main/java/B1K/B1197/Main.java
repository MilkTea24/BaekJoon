package B1K.B1197;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<List<Edge>> edgeInfo = new ArrayList<>();

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int V = line[0];
        int E = line[1];

        for (int i = 0; i < V; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;
            int weight = line[2];
            edgeInfo.get(start).add(new Edge(end, weight));
            edgeInfo.get(end).add(new Edge(start, weight));
        }

        long result = MinSumWeightWithPrimAlgorithm(edgeInfo, V);
        System.out.print(result);
    }

    private static long MinSumWeightWithPrimAlgorithm(List<List<Edge>> edgeInfo, int VertexNumber) {
        int subVerticesSize = 0;
        boolean[] vertices = new boolean[VertexNumber];

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        //0에서 시작
        vertices[0] = true;
        subVerticesSize += 1;
        List<Edge> startVertexEdgeInfo = edgeInfo.get(0);
        for (Edge e : startVertexEdgeInfo) {
            edges.add(e);
        }

        long sumOfWeight = 0;
        while (subVerticesSize < VertexNumber) {
            Edge minWeightEdge = edges.poll();

            //간선의 endPoint 모두 V에 포함되어 있는 경우 건너뛰기
            if (vertices[minWeightEdge.end]) continue;

            //간선 가중치 더하기
            sumOfWeight += minWeightEdge.weight;

            //V에 포함되지 않은 정점 V에 추가하기(= 방문 처리하기)
            vertices[minWeightEdge.end] = true;
            subVerticesSize += 1;

            //새로 포함되 V의 edge 전부 넣기
            List<Edge> currentVertexEdgeInfo = edgeInfo.get(minWeightEdge.end);
            for (Edge e : currentVertexEdgeInfo) {
                edges.add(e);
            }
        }

        return sumOfWeight;
    }

    public static class Edge implements Comparable<Edge> {
        int end;
        int weight;

        Edge(int e, int w) {
            end = e;
            weight = w;
        }

        @Override
        public int compareTo(Edge e) {
            return weight - e.weight;
        }
    }
}
