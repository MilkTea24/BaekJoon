package B1K.B1389;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[][] distance;
    static int N;
    public static void main(String[] args) {
        try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        distance = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            distance[from][to] = 1;
            distance[to][from] = 1;
        }

        int result = minBaconPerson();
        System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int minBaconPerson() {
        int sum = Integer.MAX_VALUE;
        int minInd = 0;
        for (int i = 0; i < N; i++) {
            int temp = SumDistancesToStart(i);
            if (sum > temp) {
                minInd = i;
                sum = temp;
            }
        }
        return minInd + 1;
    }

    static int SumDistancesToStart(int start) {
        boolean[] isVisited = new boolean[N];
        LinkedList<Integer> visitedOrder = new LinkedList<>();
        LinkedList<Integer> nextNode = new LinkedList<>();
        nextNode.addLast(start);
        isVisited[start] = true;

        while (!nextNode.isEmpty()){
            int current = nextNode.removeFirst();
            visitedOrder.addLast(current);

            for (int next = 0; next < N; next++) {
                if (distance[current][next] == 1 && !isVisited[next]) {
                    distance[start][next] = distance[start][visitedOrder.getLast()] + 1;
                    nextNode.addLast(next);
                    isVisited[next] = true;
                }
            }
        }

        int sum = 0;

        for (int j = 0; j < N; j++) {
            sum += distance[start][j];
        }
        return sum;
    }

    /*
    static int minBaconPerson() {
        int[][] baconNumbers = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                baconNumbers[i][j] = calculateBacon(baconNumbers, i, j);
            }
        }

        int minBaconNumberSum = Integer.MAX_VALUE;
        int minInd = 0;
        for (int i = 0; i < N; i++) {
            int baconNumberSum = 0;
            for (int j = 0; j < N; j++) {
                baconNumberSum += baconNumbers[i][j];
            }
            if (minBaconNumberSum > baconNumberSum) {
                minBaconNumberSum = baconNumberSum;
                minInd = i;
            }
        }

        return minInd + 1;
    }

    //기존값과 최소값 비교해야함
    static int calculateBacon(int[][] baconNumbers, int f, int t) {
        boolean[] isVisited = new boolean[N];
        LinkedList<Integer> visitNext = new LinkedList<>();
        visitNext.addLast(f);
        visitNext.addLast(-1);
        isVisited[f] = true;
        int step = 1;

        while (!visitNext.isEmpty()) {
            int current = visitNext.removeFirst();
            if (current == -1) {
                step++;
                continue;
            }

            for (int i = 0; i < N; i++) {
                if (isFriend[current][i] && !isVisited[i]) {
                    visitNext.addLast(i);
                    isVisited[i] = true;
                    baconNumbers[current][i] = 1;
                    baconNumbers[i][current] = 1;
                    if (step < baconNumbers[f][i]) {
                        baconNumbers[f][i]  = step;
                        baconNumbers[i][f] = step;
                    }

                    if (i == t) return baconNumbers[f][t];
                }
            }
            visitNext.addLast(-1);
        }
        return Integer.MAX_VALUE;
    }
    */
}
