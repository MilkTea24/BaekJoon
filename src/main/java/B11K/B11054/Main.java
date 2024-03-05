package B11K.B11054;

import java.io.*;
import java.util.*;

public class Main {
    static int[] upMemo;
    static int[] downMemo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        upMemo = new int[n];
        downMemo = new int[n];

        upMemo[0] = 1;
        downMemo[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i]) {
                    upMemo[i] = Math.max(upMemo[i], upMemo[j] + 1);
                    downMemo[i] = Math.max(1, downMemo[i]);
                }
                else if (input[j] > input[i]) {
                    upMemo[i] = Math.max(1, upMemo[i]);
                    downMemo[i] = Math.max(downMemo[i], Math.max(upMemo[j] + 1, downMemo[j] + 1));
                }
                else upMemo[i] = Math.max(1, upMemo[i]);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, upMemo[i]);
            result = Math.max(result, downMemo[i]);
        }

        System.out.print(result);
    }
}