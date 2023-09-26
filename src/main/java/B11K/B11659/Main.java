package B11K.B11659;

import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = nums[0];
            int m = nums[1];

            int[] inputNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            long[] sumNumbers = new long[n];
            sumNumbers[0] = inputNumbers[0];
            for (int i = 1; i < n; i++) {
                sumNumbers[i] = sumNumbers[i - 1] + inputNumbers[i];
            }

            for (int i = 0; i < m; i++) {
                nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                if (nums[0] == 1) System.out.println(sumNumbers[nums[1] - 1]);
                else System.out.println(sumNumbers[nums[1] - 1] - sumNumbers[nums[0] - 2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
