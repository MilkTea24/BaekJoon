package B1K.B1446;

import java.util.*;
import java.io.*;

public class Main {
    static int N, D;
    static Map<Pos, Integer> edges = new HashMap<>();
    static Map<Integer, Map<Integer, Integer>> nodeInfo = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        D = line[1];

        int[][] inputs = new int[N][3];
        Set<Integer> nodes = new HashSet<>();
        for (int i = 0; i < N; i++) {
            inputs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes.add(inputs[i][0]);
            nodes.add(inputs[i][1]);
        }

        for (int n : nodes) {
            nodeInfo.put(n, new HashMap<>());
        }

        for (int i = 0; i < N; i++){
            Map<Integer, Integer> nearNodeInfo = nodeInfo.get(inputs[i][0]);
            if (!nearNodeInfo.containsKey(inputs[i][1])) nearNodeInfo.put(inputs[i][1], inputs[i][2]);
            else {
                nearNodeInfo.put(inputs[i][1], Math.min(inputs[i][2], nearNodeInfo.get(inputs[i][1])));
            }

            for (int j = 1; j < N; j++) {
                if (inputs[i][0] == inputs[j][1]) continue;
                if (!nearNodeInfo.containsKey(inputs[j][1])) nearNodeInfo.put(inputs[j][1], Math.abs(inputs[j][1] - inputs[i][0]));
                else {
                    nearNodeInfo.put(inputs[j][1], Math.min(Math.abs(inputs[j][1] - inputs[i][0]), nearNodeInfo.get(inputs[j][1])));
                }
            }
        }

        System.out.println(nodeInfo);

        //Map<Integer, Integer> result = dijkstra();


    }

    static Map<Integer, Integer> dijkstra() {
        //각 지름길의 끝점을 노드로
        Map<Integer, Integer> dist = new HashMap<>();

        for (Map.Entry<Pos, Integer> e: edges.entrySet()) {
            dist.put(e.getKey().e, Integer.MAX_VALUE);
        }

        Queue<Node> queue = new LinkedList<>();
        //queue.add(new Node)
        return null;
    }

    static class Pos {
        int s;
        int e;

        Pos(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pos p) {
                return (p.s == this.s) && (p.e == this.e);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s, e);
        }
    }

    static class Node implements Comparable<Node> {
        int pos;
        int dist;

        Node (int p, int d) {
            pos = p;
            dist = d;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node n) {
                return pos == n.pos;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(pos);
        }

        @Override
        public int compareTo(Node n) {
            return dist - n.dist;
        }
    }
}
