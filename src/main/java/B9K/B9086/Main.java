package B9K.B9086;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();
            sb.append(input.charAt(0)).append(input.charAt(input.length() - 1)).append('\n');
        }
        System.out.println(sb);
    }
}
