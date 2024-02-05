package B1K.B1238;

import java.io.*;
import java.util.*;

//N개의 마을에 각각 한명의 학생이 살고 있다.
//N명의 학생이 특정 한 마을에 모여서 파티를 하기로 함
//마을 사이에는 '단'방향 도로들이 있음, 그리고 도로마다 소요 시간이 다름
//N명의 학생들이 오고 가는데 가장 많은 시간을 소비한 학생은? -> 학생들의 최소 경로 중 MAX 값 구하기
//도로의 시작점과 끝 점, 소요시간이 주어짐
public class Main {
    static int N; //마을 수
    static int M; //도로 수
    static int X; //파티 장소

    static List<List<Node>> roadInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        getInput();

        int[] goToHome = dijkstra(X);

        flipRoad();

        int[] goToParty = dijkstra(X);

        int maxTime = 0;
        for (int i = 0; i < N; i++) {
            maxTime = Math.max(maxTime, goToParty[i] + goToHome[i]);
        }

        System.out.println(maxTime);
    }

    static void flipRoad() throws Exception {
        List<List<Node>> flipRoadInfo = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            flipRoadInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < roadInfo.size(); i++) {
            List<Node> currentRoadInfo = roadInfo.get(i);

            for (int j = 0; j < currentRoadInfo.size(); j++) {
                flipRoadInfo.get(currentRoadInfo.get(j).position).add(new Node(i, currentRoadInfo.get(j).time));
            }
        }

        roadInfo = flipRoadInfo;
    }

    static void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];
        M = line[1];
        X = line[2] - 1;

        for (int i = 0; i < N; i++) {
            roadInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1; //인덱스는 1 빼기
            int end = line[1] - 1;
            int time = line[2];
            roadInfo.get(start).add(new Node(end, time));
        }
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] minTable = new int[N];
        for (int i = 0; i < N; i++) {
            minTable[i] = Integer.MAX_VALUE;
        }

        minTable[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (minTable[current.position] < current.time) {
                continue;
            }

            List<Node> currentRoadInfo = roadInfo.get(current.position);
            for (Node n : currentRoadInfo) {
                int nearNodePosition = n.position;
                int roadTime = n.time;

                if (minTable[nearNodePosition] > current.time + roadTime) {
                    minTable[nearNodePosition] = current.time + roadTime;
                    pq.add(new Node(nearNodePosition, minTable[nearNodePosition]));
                }
            }
        }

        return minTable;
    }

    static class Node implements Comparable<Node> {
        int position;
        int time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }


        @Override
        public int compareTo(Node n) {
            return n.time - this.time;
        }
    }
}
