package B1K.B1167;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int V;
    static List<Map<Integer, Integer>> nearNodes = new ArrayList<>();;
    static List<Integer> endPoint = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        for (int i = 0; i < V; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            nearNodes.add(new HashMap<>());
            int j = 1;
            int endPointFlag = 0;
            while (line[j] != -1) {
                //모든 인덱스는 1을 빼고 저장
                nearNodes.get(i).put(line[j] - 1, line[j+1]);
                j += 2;
                endPointFlag++;
            }

            if (endPointFlag == 1) endPoint.add(i);
        }

        int maxDistance = 0;
        for (int endIndex : endPoint) {
            maxDistance = Math.max(maxDistance, BFS(endIndex));
        }

        System.out.println(maxDistance);
    }

    static int BFS(int start) {
       int maxDistance = 0;
       boolean[] isVisited = new boolean[V];
       LinkedList<Node> queue = new LinkedList<>();

       queue.add(new Node(start, 0));

       while (!queue.isEmpty()) {
           Node current = queue.removeFirst();
           Map<Integer, Integer> nearNodeInfo = nearNodes.get(current.index);
           isVisited[current.index] = true;

           for (Map.Entry<Integer, Integer> e : nearNodeInfo.entrySet()) {
               int nearNodeIndex = e.getKey();
               boolean isEndPoint = true;
               if (!isVisited[nearNodeIndex]) {
                   queue.add(new Node(nearNodeIndex, current.distanceFromStart + e.getValue()));
                   isEndPoint = false;
               }

               if (isEndPoint) maxDistance = Math.max(maxDistance, current.distanceFromStart);
           }
       }

       return maxDistance;
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
