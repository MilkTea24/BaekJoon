package B1K.B1208;

import java.io.*;
import java.util.*;

/*
-7/-3/-2/5/8
-7, -3/ -7, -2/ -7, -5/ -7, 8/....
일일히 하지 않고 찾을 수 있는 방법?
DP, 분할정복, 유니온 파인드
부분 문제?

 */
public class Main {
    static int N;
    static int S;
    static int[] inputs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];
        S = line[1];

        long bitMask = 1;

        for (int i = 0; i < N; i++) {
            bitMask *= 2;
        }

        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long count = 0;
        for (long i = 1; i < bitMask; i++) {
            long result = 0;
            for (int j = 0; j < N; j++) {
                if ((i >> j  & 1) == 1) {
                    //System.out.print(j + " ");
                    result += inputs[j];
                }
            }
            //System.out.println("total = " + result);
            if (result == S) count++;
        }

        System.out.println(count);
    }
}
