package B1K.B1504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int E;
    static int start;
    static int end;
    static List<List<Node>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        getInput();

        int result = -1;
        int[] distanceFromOne = dijkstra(0);
        int[] distanceFromStart = dijkstra(start);
        int[] distanceFromEnd = dijkstra(end);

        //0 -> start -> end -> n-1
        int oneToStart = distanceFromOne[start];
        int startToEnd = distanceFromStart[end];
        int endToN = distanceFromEnd[N - 1];

        if (oneToStart != Integer.MAX_VALUE &&
                startToEnd != Integer.MAX_VALUE &&
                endToN != Integer.MAX_VALUE) result = oneToStart + startToEnd + endToN;

        //0 -> end -> start -> n-1
        int oneToEnd = distanceFromOne[end];
        int EndToStart = distanceFromEnd[start];
        int StartToN = distanceFromStart[N - 1];

        if (oneToEnd != Integer.MAX_VALUE &&
                EndToStart != Integer.MAX_VALUE &&
                StartToN != Integer.MAX_VALUE) {
            if (result == -1) result = oneToEnd + EndToStart + endToN;
            else result = Math.min(result, oneToEnd + EndToStart + endToN);
        }

        System.out.println(result);
    }

    static void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];    E = line[1];

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int edgeStart = line[0] - 1; //정점 번호는 1빼기
            int edgeEnd = line[1] - 1;
            int len = line[2];

            edgeInfo.get(edgeStart).add(new Node(edgeEnd, len));
        }

        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        start = line[0] - 1;
        end = line[1] -1;
    }

    static int[] dijkstra(int start) {
        //priority queue
        PriorityQueue<Node> pq = new PriorityQueue<>();

        //거리 테이블 초기화
        int[] distanceFromStart = new int[N];
        for (int i = 0; i < N; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
        }


        distanceFromStart[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.index;

            //업데이트 되지 않은 Node인지 확인
            if (current.distance > distanceFromStart[currentIndex]) continue;

            //System.out.println("index: " + (currentIndex+1) + " ,distance: " + current.distance);

            List<Node> currentNodeEdgeInfo = edgeInfo.get(currentIndex);
            for (Node n : currentNodeEdgeInfo) {
                int nearNodeIndex = n.index;
                if (distanceFromStart[n.index] > distanceFromStart[currentIndex] + n.distance) {
                    distanceFromStart[n.index] = distanceFromStart[currentIndex] + n.distance;
                    pq.add(new Node(nearNodeIndex, distanceFromStart[n.index]));
                    //System.out.println("update index: " + (nearNodeIndex+1) + " ,distance: " + n.distance);
                }
            }
        }

        return distanceFromStart;
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        Node(int i, int d) {
            index = i;
            distance = d;
        }

        @Override
        public int compareTo(Node n) {
            return n.distance - this.distance;
        }
    }
}
