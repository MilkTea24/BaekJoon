package B16K.B16227;

import java.util.*;
import java.io.*;

/*
12시 21분 시작 ~ 12시 57분 첫 제출
100분 이전에 모래를 한번 씻어야 한다
모래 씻는데는 5분이 걸림
모래를 씻는 특수 장비는 곳곳에 있음
0은 보내는 개척지 N+1은 받는 개척지 그 사이는 특수 장비

100분이 넘는 경로는 추가하지 않음
A -> B 30 B -> C 60이라면 B에서 굳이 안씻어도 된다.
A -> B 60 B -> C 80이라면 B에서 한번 씻고 가야한다.
 */
public class Main {
    static int N, K;
    static List<List<Node>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        N = line[0] + 2;
        K = line[1];

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (line[2] > 100) continue;
            edgeInfo.get(line[0]).add(new Node(line[1], line[2], 0));
            edgeInfo.get(line[1]).add(new Node(line[0], line[2], 0));
        }

        int[] result = dijkstra(0);

        System.out.println(result[N-1]);
    }

    private static int[] dijkstra(int s) {
        int[] result = new int[N];

        Arrays.fill(result, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0, 0));
        result[s] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            //System.out.println(current + " =============");

            if (current.weight > result[current.pos]) continue;

            List<Node> currentEdgeInfo = edgeInfo.get(current.pos);

            for (Node n : currentEdgeInfo) {
                int time = current.weight + n.weight;
                int timeAfterCleaned = current.timeAfterCleaned + n.weight;
                if (timeAfterCleaned > 100) {
                    time += 5;
                    timeAfterCleaned = n.weight;
                }

                if (result[n.pos] > time) {
                    result[n.pos] = time;
                    pq.add(new Node(n.pos, time, timeAfterCleaned));
                }

            }

            //System.out.println(pq);
        }

        return result;
    }
    
    static class Node implements Comparable<Node> {
        int pos;
        int weight;
        int timeAfterCleaned;
        
        Node (int e, int w, int t){
            pos = e;
            weight = w;
            timeAfterCleaned = t;
        }
        
        @Override
        public int compareTo(Node n){
            return this.weight - n.weight;
        }

        @Override
        public String toString() {
            return "(" + pos + ", " + weight + ", " + timeAfterCleaned + ")";
        }
    }
}
