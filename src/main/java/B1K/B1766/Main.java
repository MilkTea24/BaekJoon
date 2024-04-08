package B1K.B1766;

import java.io.*;
import java.util.*;

/*
9시 59분 시작 ~ 10시 20분 해결
1번이 가장 쉬운 문제 -> N번이 가장 어려운 문제
위상정렬 + 가능하면 쉬운 문제부터 풀어야 한다.

5 4
5 3
4 3
1 4
2 5

1 2 4 5 3
 */
public class Main {
    static int N, M;
    static List<List<Integer>> edgeInfo = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];

        for (int i = 0; i < N; i++) {
            edgeInfo.add(new ArrayList<>());
        }

        int[] inDegree = new int[N];

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edgeInfo.get(line[0] - 1).add(line[1] - 1);
            inDegree[line[1] - 1] += 1;
        }


        List<Integer> results = topologicalSort(inDegree);

        StringBuilder sb = new StringBuilder();

        for (int i : results) {
            sb.append(i + 1).append(' ');
        }

        System.out.print(sb);
    }

    private static List<Integer> topologicalSort(int[] inDegree) {
        List<Integer> result = new ArrayList<>();

        //시작점 찾기
        int start = N-1;
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0 && start > i) {
                start = i;
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            System.out.print(inDegree[i] + " ");
        }
         */

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(start);
        inDegree[start] = -1;

        while (!pq.isEmpty()) {
            //선수 문제
            int current = pq.poll();
            result.add(current);

            //선수 문제 풀었으므로 연결된 진입차수 1 빼기
            List<Integer> currentEdgeInfo = edgeInfo.get(current);
            for (int i : currentEdgeInfo) {
                inDegree[i] -= 1;
            }

            //진입차수가 0인 문제 찾기
            for (int i = 0; i < N; i++) {
                if (inDegree[i] == 0) {
                    pq.add(i);
                    inDegree[i] = -1;
                }
            }
        }

        return result;
    }
}
