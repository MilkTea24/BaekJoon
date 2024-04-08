package B1K.B1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewMain {
    static int N, K;
    static int[][] memo;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            //oneCase();
        }


    }

    /*
    private static void oneCase() throws Exception {
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];
        K = line[1];
        nearNodeInfo = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nearNodeInfo.add(new ArrayList<>());
        }

        //시간 얻기
        int[] time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < K; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //화살표 뒤집기
            int start = line[1] - 1;
            int end = line[0] - 1;
            int distance = time[end];
            nearNodeInfo.get(start).add(new Main.Node(end, distance));
        }

        int W = Integer.parseInt(br.readLine()) - 1;

        int[] dist = customDijkstra(W);

        System.out.println(Arrays.stream(dist)
                .max()
                .getAsInt() + time[W]);
    }

     */
}
