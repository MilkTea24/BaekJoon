package B13K.B13549;

import java.util.*;

public class NewMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[] result = dijkstra(N, K);
        System.out.println(result[K]);
    }

    private static int[] dijkstra(int N, int K) {
        int[] distance = new int[150000];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(N, 0));
        distance[N] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.dis > distance[current.pos]) continue;

            if (current.pos < K && current.pos < 75000 && current.dis < distance[current.pos * 2]) {
                distance[current.pos * 2] = current.dis;
                pq.add(new Node(current.pos * 2, current.dis));
            }
            if (current.pos < K && current.dis + 1 < distance[current.pos + 1]) {
                distance[current.pos + 1] = current.dis + 1;
                pq.add(new Node(current.pos + 1, current.dis + 1));
            }
            if (current.pos > 0 && current.dis + 1 < distance[current.pos - 1]) {
                distance[current.pos - 1] = current.dis  +1;
                pq.add(new Node(current.pos - 1, current.dis + 1));
            }
        }

        return distance;
    }

    private static class Node implements Comparable<Node> {
        int pos;
        int dis;

        Node (int p, int d) {
            pos = p;
            dis = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.dis - n.dis;
        }
    }
}
