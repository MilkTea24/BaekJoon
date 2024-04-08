package B1K.B1647;

import java.util.*;
import java.io.*;

/*
12시 37분 시작
N개의 집과 M개의 길 -> 양방향
길은 유지비가 존재한다.

마을을 분할할 때 내부 모든 길이 연결되도록 분할해야 한다.
그리고 모든 길이 연결된 길 외의 나머지 길은 전부 없앤다.
이 후 유지비가 최소로 되게 한다.
log N 써야 한다.
최소 스패닝 트리
최소 스패닝 트리를 만든 후 길 하나를 제거하면 된다.
 */
public class Main {
    static int N, M;

    static List<List<Node>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            edgeInfo.get(line[0] - 1).add(new Node(line[1] - 1, line[2]));
            edgeInfo.get(line[1] - 1).add(new Node(line[0] - 1, line[2]));
        }

        int result = prim();

        System.out.println(result);
    }

    private static int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int totalDist = 0;
        int maxDist = 0;
        int count = 0;

        boolean[] isVisited = new boolean[N];
        isVisited[0] = true;
        count += 1;

        for (Node n : edgeInfo.get(0)) {
            pq.add(n);
        }

        while (count < N) {
            Node current = pq.poll();

            if (isVisited[current.pos]) continue;

            totalDist += current.dist;
            maxDist = Math.max(current.dist, maxDist);
            isVisited[current.pos] = true;
            count += 1;

            for (Node n : edgeInfo.get(current.pos)) {
                pq.add(n);
            }
        }

        return totalDist - maxDist;
    }

    static class Node implements Comparable<Node> {
        int pos;
        int dist;

        Node(int p, int d) {
            pos = p;
            dist = d;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }

        @Override
        public String toString() {
            return pos + ", " + dist;
        }
    }
}
