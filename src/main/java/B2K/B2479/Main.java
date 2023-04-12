package B2K.B2479;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static boolean[][] edges;
    static String[] binaryCodes;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
            edges = new boolean[N][N];
            binaryCodes = new String[N];
            for (int i = 0; i < N; i++) {
                binaryCodes[i] = br.readLine();
            }

            saveOneHammingDistanceNodes();

            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;   int end = Integer.parseInt(st.nextToken()) - 1;

            LinkedList<Integer> hammingPath = findHammingPath(start, end);

            StringBuilder sb = new StringBuilder();
            for (int i : hammingPath) {
                sb.append(i).append(' ');
            }

            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void saveOneHammingDistanceNodes() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isHammingDistanceOne(i, j)) {
                    edges[i][j] = true;
                    edges[j][i] = true;
                }
            }
        }
    }

    static boolean isHammingDistanceOne(int i, int j) {
        String binaryCode1 = binaryCodes[i];
        String binaryCode2 = binaryCodes[j];

        int distance = 0;
        for (int c = 0; c < K; c++) {
            if (binaryCode1.charAt(c) != binaryCode2.charAt(c)) {
                distance++;
            }
            if (distance >= 2) return false;
        }
        if (distance == 1) return true;
        else return false;
    }

    static LinkedList<Integer> findHammingPath(int start, int end) {
        int[] parent = new int[N];
        boolean[] isVisited = new boolean[N];
        LinkedList<Integer> nextNode = new LinkedList<>();
        nextNode.addLast(start);
        parent[start] = start;
        isVisited[start] = true;

        while (!nextNode.isEmpty()) {
            int current = nextNode.removeFirst();

            for (int i = 0; i < N; i++) {
                if (edges[current][i] && !isVisited[i]) {
                    nextNode.addLast(i);
                    isVisited[i] = true;
                    parent[i] = current;
                }
            }
        }

        if (!isVisited[end]) return new LinkedList<>(Arrays.asList(-1));

        LinkedList<Integer> returnList = new LinkedList<>();

        int goToStartInd = end;
        while (parent[goToStartInd] != goToStartInd) {
            returnList.addFirst(goToStartInd + 1);
            goToStartInd = parent[goToStartInd];
        }

        returnList.addFirst(start + 1);
        return returnList;
    }
}
