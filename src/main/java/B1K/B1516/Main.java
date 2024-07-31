package B1K.B1516;

import java.io.*;
import java.util.*;

/*
9시 2분 시작
건물의 종류 첫째 줄
처음에 건설 시간, 두번째부터 건물의 번호
각 건물이 완성되기 까지 걸리는 최소 시간
 */
public class Main {
    static int N;
    static int[] buildTime;
    static int[] inDegree;
    static int[] result;
    static List<List<Integer>> nearNode = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        buildTime = new int[N];
        inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            nearNode.add(new ArrayList<>());
        }
        result = new int[N];


        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            buildTime[i] = line[0];
            for (int j = 1; j < line.length - 1; j++) {
                nearNode.get(line[j] - 1).add(i);
                inDegree[i] = inDegree[i] + 1;
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            System.out.println("i : " + i);
            for (int j = 0; j < nearNode.get(i).size(); j++) {
                System.out.print(nearNode.get(i).get(j) + " ");
            }
            System.out.println();
        }
         */

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                topologicalSort();
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append('\n');
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                inDegree[i] = -1;
                result[i] = buildTime[i];

                List<Integer> currentNearNode = nearNode.get(i);
                for (int num : currentNearNode) {
                    result[num] = Math.max(result[num], result[i] + buildTime[num]);
                }
            }
        }


        while (!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> currentNearNode = nearNode.get(current);

            for (int num : currentNearNode) {
                inDegree[num] = inDegree[num] -1;
            }

            for (int i = 0; i < N; i++) {
                if (inDegree[i] == 0){
                    queue.add(i);
                    inDegree[i] = -1;

                    currentNearNode = nearNode.get(i);
                    for (int num : currentNearNode) {
                        result[num] = Math.max(result[num], result[i] + buildTime[num]);
                    }
                }
            }
        }
    }
}
