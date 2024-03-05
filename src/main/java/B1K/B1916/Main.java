package B1K.B1916;

import java.io.*;
import java.util.*;

//A -> B로 가는 버스 비용을 최소화
//시작도시 도착도시 버스 비용이 주어짐
public class Main {
    static int N;
    static int M;

    static List<List<Node>> busInfo = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            busInfo.add(new ArrayList<>());
        }

        int[] line;

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int start = line[0] -1;
            int end = line[1] - 1;
            int price = line[2];

            busInfo.get(start).add(new Node(end, price));
        }

        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = line[0] - 1;
        int end = line[1] - 1;

        int[] distance = Dijkstra(start);

        System.out.println(distance[end]);
    }

    private static int[] Dijkstra(int start) {
        int[] distance = new int[N];

        for (int i = 0; i < N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentIndex = currentNode.pos;

            if (currentNode.weight > distance[currentIndex]) continue;

            List<Node> currentBusInfo = busInfo.get(currentIndex);

            for (Node n : currentBusInfo) {
                int nearIndex = n.pos;
                int nearWeight = n.weight;

                if (distance[nearIndex] > distance[currentIndex] + nearWeight) {
                    distance[nearIndex] = distance[currentIndex] + nearWeight;
                    pq.add(new Node(nearIndex, distance[nearIndex]));
                }
            }
        }

        return distance;
    }

    private static class Node implements Comparable<Node> {
        int pos;
        int weight;

        Node(int p, int w) {
            pos = p;
            weight = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }
}
