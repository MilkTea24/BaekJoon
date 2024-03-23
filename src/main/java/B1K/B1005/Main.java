package B1K.B1005;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K;

    static List<List<Node>> nearNodeInfo;
    static StringBuilder sb = new StringBuilder();

    static int[] time;
    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            oneCase();
        }
        System.out.print(sb);
    }

    private static void oneCase() throws Exception {
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];    K = line[1];

        time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        nearNodeInfo = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        int[] inDegree = new int[N];
        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int reverseStart = line[1] - 1;
            int reverseEnd = line[0] - 1;
            inDegree[reverseEnd] += 1;

            nearNodeInfo.get(reverseStart).add(new Node(reverseEnd, time[reverseEnd]));
        }

        int W = Integer.parseInt(br.readLine()) - 1;

        int[] dist = topologicalSortMaxDistance(W, inDegree);

        sb.append(Arrays.stream(dist).max().getAsInt()).append('\n');
    }

    private static int[] topologicalSortMaxDistance(int W, int[] inDegree) {
        Queue<Node> queue = new LinkedList<>();
        int[] result = new int[N];

        queue.add(new Node(W, time[W]));
        result[W] = time[W];
        inDegree[W] = -1;
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                inDegree[i] = -1;
                List<Node> removeNodeInfo = nearNodeInfo.get(i);
                for (Node n : removeNodeInfo) {
                    inDegree[n.pos] -= 1;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            List<Node> currentNearNodeInfo = nearNodeInfo.get(current.pos);

            for (Node n : currentNearNodeInfo) {
                inDegree[n.pos] -= 1;
                result[n.pos] = Math.max(result[n.pos], current.dist + n.dist);
                if (inDegree[n.pos] == 0) {
                    queue.add(new Node(n.pos, result[n.pos]));
                    inDegree[n.pos] = -1;
                }
            }
        }

        return result;


    }

    private static class Node {
        int pos;
        int dist;

        Node(int _p, int _d) {
            pos = _p;
            dist = _d;
        }
    }
}
