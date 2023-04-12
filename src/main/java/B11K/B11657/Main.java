package B11K.B11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static LinkedList<Node>[] allBusInfo;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            allBusInfo = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                allBusInfo[i] = new LinkedList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int length = Integer.parseInt(st.nextToken());
                allBusInfo[from].add(new Node(to, length));
            }

            long[] distanceFromFirstCity = bellmanFord();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < distanceFromFirstCity.length; i++) {
                if (i == 0 && distanceFromFirstCity[i] != -1) continue;
                sb.append(distanceFromFirstCity[i] == Integer.MAX_VALUE ? "-1" : distanceFromFirstCity[i]).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long[] bellmanFord() {
        long[] distanceFromStart = new long[N];
        for (int i = 0; i < N; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
        }
        distanceFromStart[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < allBusInfo[i].size(); j++) {
                Node surroundNode = allBusInfo[i].get(j);
                int surPos = surroundNode.position;
                int edgeLen = surroundNode.distance;
                if (distanceFromStart[i] != Integer.MAX_VALUE && distanceFromStart[surPos] > distanceFromStart[i] + edgeLen) {
                    distanceFromStart[surPos] = distanceFromStart[i] + edgeLen;
                    if (i == N - 1) {
                        distanceFromStart = new long[1];
                        distanceFromStart[0] = -1;
                        return distanceFromStart;
                    }
                }
            }
        }
        return distanceFromStart;
    }

    static class Node {
        int position;
        int distance;

        Node(int _p, int _d) {
            position = _p;
            distance = _d;
        }
    }
}
