package B18K.B18111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] times;
    static int[][] groundHeight;
    static int N, M, B;
    public static void main(String[] args) {
         times = new int[257];
         try {
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             int[] constants = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
             N = constants[0];  M = constants[1];   B = constants[2];

             groundHeight = new int[N][M];

             for (int i = 0; i < N; i++) {
                 groundHeight[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
             }

             for (int i = 0; i <= 256; i++) {
                 if (!isFlatted(i)) times[i] = Integer.MAX_VALUE;
             }

             int maxHeight = 0;
             int minTime = Integer.MAX_VALUE;
             for (int i = 0; i <= 256; i++) {
                 if (minTime >= times[i])  {
                     minTime = times[i];
                     maxHeight = i;
                 }
             }

             System.out.println(minTime + " " + maxHeight);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }


    static boolean isFlatted(int targetHeight) {
        int inventory = B;
        int time = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (groundHeight[i][j] >= targetHeight) {
                    inventory += (groundHeight[i][j] - targetHeight);
                    time += (groundHeight[i][j] - targetHeight) * 2;
                }
                else {
                    inventory -= (targetHeight - groundHeight[i][j]);
                    time += (targetHeight - groundHeight[i][j]);
                }
            }
        }

        times[targetHeight] = time;
        if (inventory >= 0) return true;
        return false;
    }
}
