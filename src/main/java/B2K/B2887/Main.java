package B2K.B2887;

import java.util.*;
import java.io.*;

/*
10시 47분 시작
1000000000

//미리 저장은 못함

 */
public class Main {
    static int N;

    static List<Pos> originalInput = new ArrayList<>();
    static List<Pos> sortedByX = new ArrayList<>();
    static List<Pos> sortedByY = new ArrayList<>();
    static List<Pos> sortedByZ = new ArrayList<>();
    static Map<Pos, Integer> getXIndex = new HashMap<>();
    static Map<Pos, Integer> getYIndex = new HashMap<>();
    static Map<Pos, Integer> getZIndex = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Pos startPos = null;

        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Pos pos = new Pos(i, line[0], line[1], line[2]);

            if (Objects.isNull(startPos)) startPos = pos;
            sortedByX.add(pos);
            sortedByY.add(pos);
            sortedByZ.add(pos);
        }

        Collections.sort(sortedByX, (p1, p2) -> {
            return p1.x - p2.x;
        });

        Collections.sort(sortedByY, (p1, p2) -> {
            return p1.y - p2.y;
        });

        Collections.sort(sortedByZ, (p1, p2) -> {
            return p1.z - p2.z;
        });

        for (int i = 0; i < sortedByX.size(); i++) {
            getXIndex.put(sortedByX.get(i), i);
            getYIndex.put(sortedByY.get(i), i);
            getZIndex.put(sortedByZ.get(i), i);
        }


        long result = prim(startPos);
        System.out.println(result);
    }

    private static long prim(Pos startPos) {
        boolean[] isSelected = new boolean[N];

        PriorityQueue<Edge> edges = new PriorityQueue<>();

        int count = 1;

        //x, y, z 별로 가장 거리가 가까운 두개만 탐색
        addNearNode(edges, startPos);
        isSelected[0] = true;

        long total = 0;
        while (count < N) {
            Edge current = edges.poll();

            if (isSelected[current.endPos.originalIndex]) continue;

            //System.out.println(current.endPos.originalIndex + " : " + current.dist);

            //가장 가까운 행성 선택
            isSelected[current.endPos.originalIndex] = true;
            count += 1;
            total += current.dist;

            //선택한 행성 주변 가장 거리가 가까운 행성 두개씩 추가
            addNearNode(edges, current.endPos);

            //x좌표 기준 가장 가까운 노드 선택했다면 그 다음으로 가장 가까운 노드를 후보로 추가
            //addOneNode(isSelected, edges, current);
        }

        return total;
    }

    private static void addNearNode(PriorityQueue<Edge> edges, Pos pos) {
        //int xIndex = sortedByX.indexOf(pos);
        int xIndex = getXIndex.get(pos);
        if (xIndex < N - 1) {
            Pos xPos = sortedByX.get(xIndex + 1);
            Edge xEdge = new Edge(xPos, Math.abs(xPos.x - pos.x));
            edges.add(xEdge);
        }
        if (xIndex >= 1) {
            Pos xPos = sortedByX.get(xIndex - 1);
            Edge xEdge = new Edge(xPos, Math.abs(xPos.x - pos.x));
            edges.add(xEdge);
        }

        //int yIndex = sortedByY.indexOf(pos);
        int yIndex = getYIndex.get(pos);
        if (yIndex < N - 1) {
            Pos yPos = sortedByY.get(yIndex + 1);
            Edge yEdge = new Edge(yPos, Math.abs(yPos.y - pos.y));
            edges.add(yEdge);
        }
        if (yIndex >= 1) {
            Pos yPos = sortedByY.get(yIndex - 1);
            Edge yEdge = new Edge(yPos, Math.abs(yPos.y - pos.y));
            edges.add(yEdge);
        }

        //int zIndex = sortedByZ.indexOf(pos);
        int zIndex = getZIndex.get(pos);
        if (zIndex < N - 1) {
            Pos zPos = sortedByZ.get(zIndex + 1);
            Edge zEdge = new Edge(zPos, Math.abs(zPos.z - pos.z));
            edges.add(zEdge);
        }
        if (zIndex >= 1) {
            Pos zPos = sortedByZ.get(zIndex - 1);
            Edge zEdge = new Edge(zPos, Math.abs(zPos.z - pos.z));
            edges.add(zEdge);
        }
    }

    static class Pos {
        int originalIndex;
        int x;
        int y;
        int z;
        Pos (int _o, int _a, int _b, int _c) {
            originalIndex = _o;
            x = _a;
            y = _b;
            z = _c;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pos) {
                Pos p = (Pos) o;
                return this.originalIndex == p.originalIndex;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    static class Edge implements Comparable<Edge> {
        Pos endPos;
        int dist;



        Edge(Pos e, int d) {
            endPos = e;
            dist = d;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }
}
