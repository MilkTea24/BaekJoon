package B1K.B1967;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<List<Node>> nearNodeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int start = line[0] - 1;
            int end = line[1] - 1;
            int len = line[2];
            nearNodeInfo.get(start).add(new Node(end, len));
            nearNodeInfo.get(end).add(new Node(start, len));
        }

        int[] firstResult = dijkstra(0);

        int maxIndex = -1;
        int maxValue = -1;
        for (int i = 0; i < firstResult.length; i++) {
            if (maxValue < firstResult[i]) {
                maxIndex = i;
                maxValue = firstResult[i];
            }
        }

        int[] secondResult = dijkstra(maxIndex);

        int result = 0;
        for (int i = 0; i < secondResult.length; i++) {
            result = Integer.max(result, secondResult[i]);
        }

        System.out.println(result);
    }

    private static int[] dijkstra(int start) {
        int[] distance = new int[n];

        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.pos;
            int currentDistance = current.len;

            //갱신 전 노드이면 패스
            if (currentDistance > distance[currentIndex]) continue;

            List<Node> currentNearNodeInfo = nearNodeInfo.get(currentIndex);

            for(Node n : currentNearNodeInfo) {
                int nearNodeIndex = n.pos;
                int nearNodeLength = n.len;//이 떄 길이는 두 노드 사이의 길이임

                if (distance[nearNodeIndex] > distance[currentIndex] + nearNodeLength) {
                    distance[nearNodeIndex] = distance[currentIndex] + nearNodeLength;
                    pq.add(new Node(nearNodeIndex, distance[nearNodeIndex]));
                }
            }
        }

        return distance;
    }

    private static class Node implements Comparable<Node> {
        int pos;
        int len;

        Node(int p, int l) {
            pos = p;
            len = l;
        }

        @Override
        public int compareTo(Node n) {
            return this.len - n.len;
        }
    }
}
