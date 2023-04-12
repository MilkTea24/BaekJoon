package B11K.B11779;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static LinkedList<Node>[] allBusInfo;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            inputAllBusInfo(br);

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken())-1;    int end = Integer.parseInt(st.nextToken())-1;
            LinkedList<Integer> dijkstraPath = new LinkedList<>();
            int[] result = dijkstra(start, end, dijkstraPath);

            StringBuilder sb = new StringBuilder();
            sb.append(result[0] + "\n" + result[1] + "\n");
            for (int i : dijkstraPath) {
                sb.append(i+1).append(' ');
            }

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void inputAllBusInfo(BufferedReader br) throws Exception {
        allBusInfo = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            allBusInfo[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int width = Integer.parseInt(st.nextToken());
            allBusInfo[from].add(new Node(to, width));
        }
    }

    static int[] dijkstra(int start, int end, LinkedList<Integer> resultPath) {
        int isUsedEdge = 0;
        int[] distanceFromStart = new int[N];
        int[] prevNodeNumber = new int[N];
        for (int i = 0; i < N; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
            prevNodeNumber[i] = -1;
        }
        distanceFromStart[start] = 0;
        prevNodeNumber[start] = start;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (end == current.position) break;
            if (distanceFromStart[current.position] < current.distance) continue;

            for (int i = 0; i < allBusInfo[current.position].size(); i++) {
                Node surroundNode = allBusInfo[current.position].get(i);
                if (distanceFromStart[surroundNode.position] > distanceFromStart[current.position] + surroundNode.distance) {
                    distanceFromStart[surroundNode.position] = distanceFromStart[current.position] + surroundNode.distance;
                    pq.offer(new Node(surroundNode.position, distanceFromStart[surroundNode.position]));
                    prevNodeNumber[surroundNode.position] = current.position;
                }
            }
        }

        int[] resultArray = new int[2];
        resultArray[0] = distanceFromStart[end];

        int goToStartInd = end;
        while (prevNodeNumber[goToStartInd] != goToStartInd) {
            resultPath.addFirst(goToStartInd);
            goToStartInd = prevNodeNumber[goToStartInd];
        }
        resultPath.addFirst(start);

        resultArray[1] = resultPath.size();
        return resultArray;
    }

    static class Node implements Comparable<Node> {
        int position;
        int distance;

        Node(int _po, int _d) {
            position = _po;
            distance = _d;
        }

        @Override
        public int compareTo(Node n1) {
            return this.distance - n1.distance;
        }
    }
}
