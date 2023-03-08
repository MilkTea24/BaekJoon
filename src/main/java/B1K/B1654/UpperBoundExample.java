package B1K.B1654;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//이분 탐색 공부 후 푼 문제
public class UpperBoundExample {
    static int[] wires;
    static int k;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String firstLine = br.readLine();
            String[] KN = firstLine.split(" ");
            k = Integer.parseInt(KN[0]);
            int n = Integer.parseInt(KN[1]);
            wires = new int[k];

            for (int i = 0; i < k; i++) {
                wires[i] = Integer.parseInt(br.readLine());
            }
            long result = cutWiresUpperBound(0, Integer.MAX_VALUE, n);
            System.out.println(result - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long cutWiresUpperBound(long low, long high, int target) {
        if (low > high) return low;
        long mid = (low + high) / 2;
        int cuttedWires = 0;
        for (int i = 0; i < k; i++) {
            cuttedWires += wires[i] / mid;
        }
        if (cuttedWires < target) return cutWiresUpperBound(low, mid - 1, target);
        //cuttedWires가 요구한 랜선 수보다 작으면 더 짧게 잘라야 하므로 high 값을 낮춘다.
        else return cutWiresUpperBound(mid + 1, high, target);
        //cuttedWires가 요구한 랜선 수보다 크거나 같으면 더 길게 잘라도 되므로 low 값을 올린다.

    }
}
