package B1K.B1654;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//랜선 자르기
public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[] wires;
    static int K, N;
    public static void main(String[] args) {
        try {
            String firstLine = bf.readLine();
            String[] temp = firstLine.split(" ");
            K = Integer.parseInt(temp[0]);
            N = Integer.parseInt(temp[1]);
            wires = new int[K];

            for (int i = 0; i < K; i++) {
                wires[i] = Integer.parseInt(bf.readLine());

            }

            long ret = findMaximumLen(1, (long)Integer.MAX_VALUE + 1);
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long findMaximumLen(long min, long max) {
        long sum = min + max;
        long mid = (sum / 2);
        int cuttedWires = 0;
        for (int i = 0; i < K; i++) {
            cuttedWires += (wires[i] / mid);
        }
        if (min + 2 == max) {
            if (cuttedWires >= N) return mid;
            else return (mid - 1);
        }
        if (min + 1 == max) {
            return min;
        }

        if (cuttedWires >= N) return findMaximumLen(mid, max);
        else return findMaximumLen(min, mid);
    }
}
