package B10K.B10815;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] current;
    static int[] check;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        current = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(current);

        M = Integer.parseInt(br.readLine());
        check = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();

        if (N < 3) {
            Set<Integer> currentSet = new HashSet<>();
            for (int i = 0; i < N; i++) {
                currentSet.add(current[i]);
            }

            for (int i = 0; i < M; i++) {
                if (currentSet.contains(check[i])) sb.append('1').append(' ');
                else sb.append('0').append(' ');
            }
        }
        else {
            for (int i = 0; i < M; i++) {
                sb.append(checkExist(check[i])).append(' ');
            }
        }

        System.out.print(sb);

    }

    private static int checkExist(int a) {
        int low = 0;
        int high = N - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (current[mid] < a) low = mid + 1;
            else high = mid - 1;
        }

        if (low < 0 || low >= N) return 0;
        if (current[low] == a) return 1;
        else return 0;
    }
}
