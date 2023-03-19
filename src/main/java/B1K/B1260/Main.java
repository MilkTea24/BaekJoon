package B1K.B1260;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] edge;
    static int N, M, V;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] constants = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            N = constants[0];   M = constants[1];   V = constants[2];
            edge = new boolean[N][N];
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                edge[n1-1][n2-1] = true;
                edge[n2-1][n1-1] = true;
            }

            StringBuilder DFSResult = DFS(new boolean[N], new StringBuilder(), V-1);
            StringBuilder BFSResult = BFS(new boolean[N], new StringBuilder(), new LinkedList<>(), V-1);
            StringBuilder BFS2Result = BFS2(new boolean[N], new StringBuilder(), V-1);

            System.out.println(DFSResult);
            System.out.println(BFSResult);
            System.out.println(BFS2Result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static StringBuilder DFS(boolean[] isVisited, StringBuilder result, int start) {
        isVisited[start] = true;
        result.append(start + 1).append(' ');
        for (int i = 0; i < N; i++) {
            if (edge[start][i] && !isVisited[i]) {
                DFS(isVisited, result, i);
            }
        }
        return result;
    }

    static StringBuilder BFS(boolean[] isVisited, StringBuilder result, LinkedList<Integer> nextVisit, int start) {
        result.append(start+1).append(' ');
        isVisited[start] = true;
        for (int i = 0; i < N; i++) {
            if (edge[start][i] && !isVisited[i]) {
                nextVisit.addLast(i);
            }
        }

        while (!nextVisit.isEmpty()) {
            int next = nextVisit.removeFirst();
            if (!isVisited[next]) BFS(isVisited, result, nextVisit, next);
        }
        return result;
    }

    static StringBuilder BFS2(boolean[] isVisited, StringBuilder result, int start) {
        LinkedList<Integer> nextVisit = new LinkedList<>();
        nextVisit.addLast(start);
        isVisited[start] = true;

        while (!nextVisit.isEmpty()) {
            int current = nextVisit.removeFirst();
            result.append(current + 1).append(' ');

            for (int i = 0; i < N; i++) {
                if (edge[current][i] && !isVisited[i]) {
                    nextVisit.addLast(i);
                    isVisited[i] = true;
                }
            }
        }

        return result;
    }
}
