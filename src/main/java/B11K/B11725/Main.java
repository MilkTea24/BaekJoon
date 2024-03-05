package B11K.B11725;

import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> nearNodeInfo = new ArrayList<>();
    static int[] result;
    static boolean[] isVisited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) - 1;
            int b = Integer.parseInt(line[1]) - 1;

            nearNodeInfo.get(a).add(b);
            nearNodeInfo.get(b).add(a);
        }

        result = new int[n];
        isVisited = new boolean[n];
        BFS(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(result[i] + 1).append('\n');
        }

        System.out.print(sb);
    }

    private static void BFS(int i) {
        LinkedList<Node> queue = new LinkedList<>();

        isVisited[i] = true;
        queue.add(new Node(i, i));

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            result[current.i] = current.p;

            for (int near : nearNodeInfo.get(current.i)) {
                if (!isVisited[near]) {
                    isVisited[near] = true;
                    queue.add(new Node(near, current.i));
                }
            }
        }

    }

    private static class Node{
        int i;
        int p;

        Node(int _i, int _p) {
            i = _i;
            p = _p;
        }
    }
}
