package B4K.B4101;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (line[0] == 0 && line[1] == 0) break;

            sb.append(line[0] > line[1] ? "Yes" : "No").append('\n');
        }
        System.out.print(sb);
    }
}
