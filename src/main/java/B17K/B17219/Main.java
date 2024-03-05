package B17K.B17219;

import java.io.*;
import java.util.*;

public class Main {
    static Map<String, String> memo = new HashMap<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            memo.put(line[0], line[1]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(memo.get(br.readLine())).append('\n');
        }

        System.out.println(sb);
    }
}
