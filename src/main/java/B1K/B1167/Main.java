package B1K.B1167;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static List<Map<Integer, Integer>> nearNodes = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        getInput(br);

        //임의의 점은 0으로 설정후 BFS
        int[] firstResult = BFS(0);

        //찾은 점에서 BFS로 트리 길이 구하기
        int[] secondResult = BFS(firstResult[0]);

        System.out.println(secondResult[1]);
    }

    static void getInput(BufferedReader br) throws Exception {
        V = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            nearNodes.add(new HashMap<>());
        }

        for (int i = 0; i < V; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int j = 1;
            while (line[j] != -1) {
                //모든 인덱스는 1을 빼고 저장
                nearNodes.get(line[0] - 1).put(line[j] - 1, line[j+1]);
                j += 2;
            }
        }
    }

    static int[] BFS(int start) {
        int maxDistance = 0;
        int maxIndex = start;
        boolean[] isVisited = new boolean[V];
        LinkedList<Node> queue = new LinkedList<>();

        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            Map<Integer, Integer> nearNodeInfo = nearNodes.get(current.index);
            isVisited[current.index] = true;

            if (maxDistance < current.distanceFromStart) {
                maxDistance = current.distanceFromStart;
                maxIndex = current.index;
            }

            for (Map.Entry<Integer, Integer> e : nearNodeInfo.entrySet()) {
                int nearNodeIndex = e.getKey();
                if (!isVisited[nearNodeIndex]) {
                    queue.add(new Node(nearNodeIndex, current.distanceFromStart + e.getValue()));
                }
            }
        }

        return new int[]{maxIndex, maxDistance};
    }

    static class Node {
        int index;
        int distanceFromStart;

        Node(int index, int distanceFromStart) {
            this.index = index;
            this.distanceFromStart = distanceFromStart;
        }
    }
}
