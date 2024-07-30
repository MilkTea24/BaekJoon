package B23K.B23310;
/*
9시 48분 시작
매일매일 다른 과제를 한다
과제한지 M일 될 때마다 휴식을 취함
휴식을 취할 때 원래 해야할 과제는 다음날 하지 않고 순서 넘어간다
가장 먼저 끝나는 과제의 번호
7(M) % 5(N) = 2, 14 % 5 = 4, 21 % 5 = 1, 28 % 5 = 3, 35 % 5 = 0(5)
7 - 1

13 % 3 = 1, 26 % 3 = 2, 39 % 3 = 0(5)
13 - 1

11 % 3 = 2, 22 % 3 = 1, 33 % 3 = 0
11 - 1

8 % 4 = 0(4), 16 % 4 = 0(4)
8일마다 마지막만 1 나머지는 2

7 % 4 = 3, 14 % 4 = 2, 21 % 4 = 1, 28 % 4 = 0

8 % 6 = 2, 16 % 6 = 4, 24 % 6 = 0(6)
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] assignments;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        M = line[1];
        assignments = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //최소공배수 구하기
        int lcm = lcm(N, M);
        int[] oneCycle = new int[N];
        if (lcm / N == 0) {
            Arrays.fill(oneCycle, 1);
            oneCycle[N-1] = 0;
        }
        else Arrays.fill(oneCycle, lcm / N);
        int index = M;
        while (true) {
            int remain = index % N - 1;
            if (remain == -1) remain = N - 1;
            oneCycle[remain] = oneCycle[remain] - 1;
            if (index == lcm) break;
            index = index + M;
        }

        //사이클마다 끝난 과제가 있는지 검사
        int minCycle = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (oneCycle[i] == 0) continue;
            minCycle = Math.min(minCycle, assignments[i] / oneCycle[i]);
        }

        //System.out.println(minCycle);

        if (minCycle != 0) minCycle = minCycle - 1;
        for (int i = 0; i < N; i++) {
            assignments[i] = assignments[i] - minCycle * oneCycle[i];
        }

        /*
        while (true) {
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                assignments[i] = assignments[i] - oneCycle[i];
                if (assignments[i] <= 0) flag = true;
            }
            if (flag) {
                for (int i = 0; i < N; i++) {
                    assignments[i] = assignments[i] + oneCycle[i];
                }
                break;
            }
        }
         */

        //1일씩 돌려보며 먼저 끝난 과제 알아내기
        index = 0;
        int Mcount = 1;
        while (true) {
            if (Mcount == M) {
                index = (index + 1) % N;
                Mcount = 1;
                continue;
            }
            assignments[index] = assignments[index] - 1;
            if (assignments[index] == 0 || assignments[index] == -1) break;
            index = (index + 1) % N;
            Mcount = Mcount + 1;
        }
        System.out.println(index + 1);
    }

    static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    static int gcd(int a, int b) {
        if (b < a) {
            return gcd(b, a);
        }

        int result = 0;
        while (b != 0) {
            result = a % b;
            a = b;
            b = result;
        }
        return a;
    }
}
