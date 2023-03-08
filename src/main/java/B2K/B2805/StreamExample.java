package B2K.B2805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//스트림 사용하여 푼 문제
public class StreamExample {
    static long[] trees;
    static int n;
    static int m;
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String firstLine = bf.readLine();
            n = Integer.parseInt(firstLine.split(" ")[0]);
            m = Integer.parseInt(firstLine.split(" ")[1]);

            String secondLine = bf.readLine();
            trees = Arrays.stream(secondLine.split(" "))
                    .mapToLong(String -> Long.parseLong(String))
                    .toArray();


            long result = cutTrees(0L, 1000000000L, m);
            System.out.println(result - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long cutTrees(long low, long high, long target){
        if (low > high) return low;
        long mid = (low + high) / 2;
        long timber = 0;
        for (int i = 0; i < n; i++) {
            timber += ((trees[i] - mid) < 0 ? 0 : trees[i] - mid);
        }
        if (timber < target) return cutTrees(low, mid - 1, target);
        else return cutTrees(mid + 1, high, target);
    }
}
