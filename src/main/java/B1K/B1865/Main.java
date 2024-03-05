package B1K.B1865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//N개의 지점 사이에 M개의 도로와 W개의 웜홀이 있다.
//도로는 방향성 X, 웜홀은 방향성 O
//웜홀은 들어가면 시간이 뒤로 간다 -> 음의 간선
//출발 지점 -> 다시 출발 지점 이 경우 시간이 되돌아 가 있는 경우는 있을까?
//음의 간선
public class Main {
    static int m;
    static int n;
    static int w;

    //안에 도로와 웜홀 모두 포함
    static int[][] roadInfo;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    private static void oneCase() throws Exception {
        getInput();

        floydWarshall();

        for (int i = 0; i < n; i++) {
            //System.out.println(i + " : " + roadInfo[i][i]);
            if (roadInfo[i][i] >= 0) continue;
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    private static void getInput() throws Exception {
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = line[0];    m = line[1];    w = line[2];

        roadInfo = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) roadInfo[i][j] = 500 * 10000;
            }
        }

        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = line[0] - 1;
            int end = line[1] - 1;
            int weight = line[2];

            //도로는 양방향
            //그리고 처음과 끝이 동일한 여러 도로가 있을 수 있다.
            roadInfo[start][end] = Math.min(roadInfo[start][end], weight);
            roadInfo[end][start] = Math.min(roadInfo[end][start], weight);
        }

        for (int i = 0; i < w; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int start = line[0] - 1;
            int end = line[1] - 1;
            int weight = - line[2]; //웜홀은 음의 간선

            //웜홀은 순방향
            roadInfo[start][end] = weight;
        }
    }

    private static void floydWarshall() {
        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    //System.out.printf("s: %d, m: %d, e: %d, se: %d, sm + me: %d\n", s, m, e, roadInfo[s][e], roadInfo[s][m] + roadInfo[m][e]);
                    if (roadInfo[s][e] > roadInfo[s][m] + roadInfo[m][e])
                        roadInfo[s][e] = roadInfo[s][m] + roadInfo[m][e];
                }
            }
        }
    }
}
