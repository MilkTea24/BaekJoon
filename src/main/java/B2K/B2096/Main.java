package B2K.B2096;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] memoHigh;
    static int[][] memoLow;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        memoHigh = new int[2][3];
        memoLow = new int[2][3];

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        memoHigh[0][0] = line[0];   memoHigh[0][1] = line[1];   memoHigh[0][2] = line[2];
        memoLow[0][0] = line[0];    memoLow[0][1] = line[1]; memoLow[0][2] = line[2];

        for (int i = 1; i < N; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //제일 왼쪽
            memoHigh[1][0] = Integer.max(memoHigh[0][0], memoHigh[0][1]) + line[0];
            memoLow[1][0] = Integer.min(memoLow[0][0], memoLow[0][1]) + line[0];

            //중간
            memoHigh[1][1] = Integer.max(memoHigh[0][0], Integer.max(memoHigh[0][1], memoHigh[0][2])) + line[1];
            memoLow[1][1] = Integer.min(memoLow[0][0], Integer.min(memoLow[0][1], memoLow[0][2])) + line[1];

            //끝
            memoHigh[1][2] = Integer.max(memoHigh[0][1], memoHigh[0][2]) + line[2];
            memoLow[1][2] = Integer.min(memoLow[0][1], memoLow[0][2]) + line[2];

            //이동
            for (int j = 0; j < 3; j++) {
                memoHigh[0][j] = memoHigh[1][j];
                memoLow[0][j] = memoLow[1][j];
            }
        }

        int maxResult = Integer.max(memoHigh[0][0], Integer.max(memoHigh[0][1], memoHigh[0][2]));
        int minResult = Integer.min(memoLow[0][0], Integer.min(memoLow[0][1], memoLow[0][2]));

        System.out.print(maxResult + " " + minResult);
    }
}
