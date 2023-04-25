package B1K.B1927;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        try {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < N; i++) {
                int inputNum = Integer.parseInt(br.readLine());
                if (inputNum != 0) pq.offer(inputNum);
                else {
                    if (pq.isEmpty()) sb.append("0\n");
                    else {
                        int result = pq.poll();
                        sb.append(result).append('\n');
                    }
                }
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
