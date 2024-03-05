package B11K.B11779;

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static List<List<Node>> nearNodeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;

            nearNodeInfo.get(start).add(new Node(end, line[2]));
        }

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = line[0] - 1;
        int end = line[1] - 1;

        dijkstra(start, end);


    }

    private static void dijkstra(int start, int end) {
        int[] result = new int[n];
        int[] beforeIndex = new int[n];
        Arrays.fill(beforeIndex, -1);

        for (int i = 0; i < n; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        result[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.dist > result[current.pos]) continue;

            List<Node> currentNearNodeInfo = nearNodeInfo.get(current.pos);

            for (Node n : currentNearNodeInfo) {
                if (n.dist + current.dist < result[n.pos]) {
                    result[n.pos] = n.dist + current.dist;
                    beforeIndex[n.pos] = current.pos;
                    pq.add(new Node(n.pos, result[n.pos]));
                }
            }
        }

        int index = end;
        int count = 1;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while (beforeIndex[index] != -1) {
            stack.add(index + 1);
            index = beforeIndex[index];
            count++;
        }

        sb.append(start + 1).append(' ');
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(result[end]);
        System.out.println(count);
        System.out.println(sb);

    }

    private static class Node implements Comparable<Node> {
        int pos;
        int dist;

        Node (int p, int d) {
            pos = p;
            dist = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
}


