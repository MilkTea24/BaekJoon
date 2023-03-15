package B10K.B10989;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numbers = Integer.parseInt(br.readLine());
            int[] arr = new int[numbers];
            for (int i = 0; i < numbers; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers; i++) {
                sb.append(arr[i]).append('\n');
            }

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
