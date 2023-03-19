package B10K.B11651;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            int[][] locations = new int[N][2];

            for (int i = 0; i< N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                locations[i][0] = X;
                locations[i][1] = Y;
            }

            Arrays.sort(locations, (o1, o2) -> {
                if (o1[1] != o2[1]) return (o1[1] - o2[1]);
                else return (o1[0] - o2[0]);
            });

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(locations[i][0]).append(' ').append(locations[i][1]).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
