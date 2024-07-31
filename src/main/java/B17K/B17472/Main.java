package B17K.B17472;

import java.util.*;
import java.io.*;

/*
10시 20분 시작
 */
public class Main {
    static int N, M;
    static int[][] map;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //1 ~ N까지 인덱스임
    static List<List<Edge>> bridges = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str) * (-1)).toArray();
        }

        int groupCount = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -1) {
                    findGroup(i, j, groupCount);
                    groupCount = groupCount + 1;
                }
            }
        }


        for (int i = 0; i < groupCount; i++) {
            bridges.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) findBridge(i, j, map[i][j]);
            }
        }

        /*
        for (int i = 1; i < groupCount; i++) {
            List<Edge> currentBridges = bridges.get(i);
            System.out.println("시작: " + i);
            for (Edge e : currentBridges) {
                System.out.print("끝 : " + e.end + ", 거리: " + e.dist + " / ");
            }
            System.out.println();
        }
         */



        boolean flag = false;
        for (int i = 1; i < groupCount; i++) {
            if (!bridges.get(i).isEmpty()) continue;

            if (flag) {
                System.out.print(-1);
                return;
            }

            flag = true;
        }

        System.out.print(prim(1, groupCount));
    }

    private static int prim(int start, int groupCount) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int count = 0;
        int result = 0;
        boolean[] isVisited = new boolean[groupCount];
        isVisited[start] = true;

        List<Edge> currentBridges = bridges.get(start);

        for (Edge e : currentBridges) {
            pq.add(e);
        }

        while (count < groupCount - 2) {
            Edge currentEdge = pq.poll();

            if (currentEdge == null) {
                return -1;
            }

            if (isVisited[currentEdge.end]) continue;

            count = count + 1;
            result = result + currentEdge.dist;
            isVisited[currentEdge.end] = true;
            //System.out.println(currentEdge.end);

            currentBridges = bridges.get(currentEdge.end);

            for (Edge e : currentBridges) {
                pq.add(e);
            }


        }

        return result;
    }

    private static void findGroup(int a, int b, int group) {
        map[a][b] = group;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) continue;
            if (map[newI][newJ] == -1) findGroup(newI, newJ, group);
        }
    }

    private static void findBridge(int a, int b, int startGroup) {
        for (int i = 0; i < 4; i++) {
            int count = -1;
            int newI = a;
            int newJ = b;
            label: while (true) {
                newI = newI + direction[i][0];
                newJ = newJ + direction[i][1];
                count = count + 1;

                if (newI < 0 || newI >= N || newJ < 0 || newJ >= M) break;
                if (map[newI][newJ] == startGroup) break;
                if (map[newI][newJ] != 0) {
                    if (count < 2) break;
                    List<Edge> currentBridge = bridges.get(startGroup);
                    for (Edge e : currentBridge) {
                        if (e.end != map[newI][newJ]) continue;

                        e.dist = Math.min(count, e.dist);
                        break label;
                    }
                    currentBridge.add(new Edge(map[newI][newJ], count));
                    break;
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int end;
        int dist;

        Edge(int e, int d) {
            end = e;
            dist = d;
        }
        @Override
        public int compareTo(Edge e) {
            return dist - e.dist;
        }
    }
}
