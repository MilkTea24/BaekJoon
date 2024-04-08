package B2K.B2252;

import java.util.*;
import java.io.*;
public class Main {
    static List<List<Integer>> nearNodeInfo = new ArrayList<>();
    static int N;
    static int M;
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];

        inDegree = new int[N];

        for (int i = 0; i < N; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int low = line[0] - 1;
            int high = line[1] - 1;

            nearNodeInfo.get(low).add(high);
            inDegree[high] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                inDegree[i] = -1;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + 1).append(' ');

            List<Integer> currentNearNodeInfo = nearNodeInfo.get(current);

            for (int i : currentNearNodeInfo) {
                inDegree[i] -= 1;
            }

            for (int i = 0; i < N; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    inDegree[i] = -1;
                }
            }
        }

        System.out.print(sb);
    }
}