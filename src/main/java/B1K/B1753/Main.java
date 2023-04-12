package B1K.B1753;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static ArrayList<Node>[] list;
    static int V, E;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] constants = returnIntArrayFromReadLine(br);
            V = constants[0];   E = constants[1];
            int start = Integer.parseInt(br.readLine()) - 1;
            list = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                list[i] = new ArrayList<>();
            }

            AddNodes(br);

            int[] distanceFromStart = dijkstra(start);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(distanceFromStart[i] == Integer.MAX_VALUE ? "INF" : distanceFromStart[i]).append('\n');
            }

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static int[] returnIntArrayFromReadLine(BufferedReader br) throws Exception {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static void AddNodes(BufferedReader br) throws Exception {
        for (int i = 0; i < E; i++) {
            int[] constants = returnIntArrayFromReadLine(br);
            list[constants[0] - 1].add(new Node(constants[1] - 1, constants[2]));
        }
    }

    static int[] dijkstra(int start) {
        int[] distanceFromStart = new int[V];
        for (int i = 0; i < V; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));
        distanceFromStart[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distanceFromStart[current.position] < current.distance) {
                continue;
            }

            for (int i = 0; i < list[current.position].size(); i++) {
                Node surroundNode = list[current.position].get(i);
                if (distanceFromStart[surroundNode.position] > distanceFromStart[current.position] + surroundNode.distance) {
                    distanceFromStart[surroundNode.position] = distanceFromStart[current.position] + surroundNode.distance;
                    pq.offer(new Node(surroundNode.position, distanceFromStart[surroundNode.position]));
                }
            }
        }

        return distanceFromStart;
    }

    static class Node implements Comparable<Node> {
        int position;
        int distance;

        Node(int _p, int _d) {
            position = _p;
            distance = _d;
        }

        @Override
        public int compareTo(Node n1) {
            return this.distance - n1.distance;
        }
    }
}
