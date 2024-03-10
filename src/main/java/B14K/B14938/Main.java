package B14K.B14938;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int r;

    static int[][] distances;

    static int[] items;

    private static final int INF = 100000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = line[0];    m = line[1];    r = line[2];

        //아이템 수 얻기
        items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //길 정보 얻기
        distances = new int[n][n];

        for (int i = 0; i < r; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;
            int len = line[2];
            distances[start][end] = len;
            distances[end][start] = len;
        }

        //초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && distances[i][j] == 0) distances[i][j] = INF;
            }
        }

        floydWarshall();

        //최대 아이템 수 계산하기
        int maxRoot = 0;
        for (int i = 0; i < n; i++) {
            int currentMaxRoot = 0;
            for (int j = 0; j < n; j++) {
                if (distances[i][j] <= m) currentMaxRoot += items[j];
            }
            maxRoot = Math.max(maxRoot, currentMaxRoot);
        }

        System.out.print(maxRoot);
    }

    private static void floydWarshall() {
        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    distances[s][e] = Math.min(distances[s][e], distances[s][m] + distances[m][e]);
                }
            }
        }
    }
}
