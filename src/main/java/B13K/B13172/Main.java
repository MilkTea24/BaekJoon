package B13K.B13172;

import java.io.*;
import java.util.*;

public class Main {
    //n면체 주사위에서 기대값은 (N1 + N2 + .... Nn) / n = Sn / n
    //주사위 M 개를 던질 것임 이 때 답은 S1/N1 + S2/N2 + ... + SM/NM
    //기약 분수가 a/b = a * b^-1 mod X
    //이 때 b^-1는 모듈러 역원으로 b^-1 * b = 1(mod x)
    //b^(X-2) = b^-1도 성립함
    //이러한 주어진 식을 바탕으로 주사위 M개를 던질 때 기대값을 구하는 문제
    //내 답에서 분모를 곱한다음 나머지 계산을 하면 분자가 나온다. 그 답을 찾기
    //(S1/N1) + (S2/N2) = (S1N2 + N1S2) / (N1N2) -> ((S1N2 + N1S2) * (N1N2)^(X-2) % X) % X
    static int[][] input;
    static long[] result;

    private static final long MOD = 1000000007L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        input = new int[M][2];
        result = new long[M];

        for (int i = 0; i < M; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            result[i] = (long) input[i][1] * (pow(input[i][0], MOD - 2, new HashMap<>()) % MOD) % MOD;
        }

        long sumResult = 0;
        for (int i = 0; i < M; i++) {
            sumResult = (sumResult + result[i]) % MOD;
        }

        System.out.println(sumResult);
    }

    private static long pow(long a, long b, Map<Long, Long> memo) {
        if (b == 0) return 1;
        if (b == 1) return a;
        if (memo.containsKey(b)) return memo.get(b);

        long result;
        if (b % 2 == 0)
            result = pow(a, b/2, memo) * pow(a, b/2, memo) % MOD;
        else
            result = pow(a, b/2, memo) * pow(a, b/2 + 1, memo) % MOD;

        memo.put(b, result);
        return result;
    }
}
