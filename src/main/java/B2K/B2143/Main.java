package B2K.B2143;

import java.io.*;
import java.util.*;

/*
5 = 1 + 4, 2 + 3
부분합 더해서 1이 되는 것
부분합 더해서 2가 되는 것
오후 2시 35분 시작
 */
public class Main {
    static Map<Long, Long> aSumCount = new HashMap<>();
    static Map<Long, Long> bSumCount = new HashMap<>();

    static int[] arrayA;
    static int[] arrayB;
    static int T;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        br.readLine();

        arrayA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        br.readLine();

        arrayB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < arrayA.length; i++) {
            long partialSum = 0;
            for (int j = i; j < arrayA.length; j++) {
                partialSum += arrayA[j];
                if (!aSumCount.containsKey(partialSum)) {
                    aSumCount.put(partialSum, 1L);
                }
                else {
                    aSumCount.put(partialSum, aSumCount.get(partialSum) + 1);
                }
            }
        }

        for (int i = 0; i < arrayB.length; i++) {
            long partialSum = 0;
            for (int j = i; j < arrayB.length; j++) {
                partialSum += arrayB[j];
                if (!bSumCount.containsKey(partialSum)) {
                    bSumCount.put(partialSum, 1L);
                }
                else {
                    bSumCount.put(partialSum, bSumCount.get(partialSum) + 1);
                }

            }
        }
        //System.out.println(aSumCount);
        //System.out.println(bSumCount);

        long result = 0;
        for (Map.Entry<Long, Long> eA : aSumCount.entrySet()) {
            long partialB = T - eA.getKey();

            if (!bSumCount.containsKey(partialB)) continue;

            result += eA.getValue() * bSumCount.get(partialB);
        }

        System.out.println(result);
    }
}
