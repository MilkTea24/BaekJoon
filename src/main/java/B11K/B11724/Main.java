package B11K.B11724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            nodes[a-1].connected.add(nodes[b-1]);
            nodes[b-1].connected.add(nodes[a-1]);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (!nodes[i].isVisited) {
                result++;
                DFS(nodes, i);
            }
        }

        System.out.println(result);
    }

    public static void DFS(Node[] nodes, int current) {
        if (nodes[current].isVisited) return;
        nodes[current].isVisited = true;
        for (int i = 0; i < nodes[current].connected.size(); i++) {
            DFS(nodes, nodes[current].connected.get(i).num);
        }
    }

    static class Node {
        int num;
        List<Node> connected = new ArrayList<>();
        boolean isVisited = false;

        public Node(int num) {
            this.num = num;
        }
    }
}
