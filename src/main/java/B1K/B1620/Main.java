package B1K.B1620;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static int N, M;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = NM[0];  M = NM[1];

            HashMap<String, Integer> catalogue = new HashMap<>();
            String[] catalogue2 = new String[N + 1];

            for (int i = 1; i <= N; i++) {
                String input = br.readLine();
                catalogue.put(input, i);
                catalogue2[i] = input;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                String question = br.readLine();
                if (question.matches("[a-zA-Z]+")) {
                    sb.append(catalogue.get(question)).append('\n');
                }
                else sb.append(catalogue2[Integer.parseInt(question)]).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
