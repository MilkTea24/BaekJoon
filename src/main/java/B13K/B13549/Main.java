package B13K.B13549;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int minTime = BFS(N, K);
        System.out.println(minTime);
    }

    private static int BFS(int N, int K) {
        if (N == K) return 0;

        boolean[] isVisited = new boolean[200000];
        isVisited[N] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(N, 0, null));


        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.pos == K) {
                int result = current.time;

                while (current.beforeNode != null) {
                    System.out.print(current.pos + " ");
                    current = current.beforeNode;
                }

                System.out.println();

                return result;
            }

            if (current.pos < K && current.pos < 100000 && !isVisited[current.pos * 2]) {
                isVisited[current.pos * 2] = true;
                pq.add(new Node(current.pos * 2, current.time, current));
            }
            if (current.pos < K && !isVisited[current.pos + 1]) {
                isVisited[current.pos + 1] = true;
                pq.add(new Node(current.pos + 1, current.time + 1, current));
            }
            if (current.pos > 0 && !isVisited[current.pos - 1]) {
                isVisited[current.pos + 1] = true;
                pq.add(new Node(current.pos - 1, current.time + 1, current));
            }


        }

        return -1;
    }

    private static class Node implements Comparable<Node> {
        int pos;
        int time;
        Node beforeNode;

        Node(int p, int t, Node n) {
            pos = p;
            time = t;
            beforeNode = n;
        }

        @Override
        public int compareTo(Node n) {
            return this.time - n.time;
        }
    }
}
