package B1K.B1764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        try {
            TreeSet<String> people = new TreeSet<>();
            TreeSet<String> people2 = new TreeSet<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = NM[0];  int M = NM[1];

            for (int i = 0; i < N; i++) {
                people.add(br.readLine());
            }

            for (int i = 0; i < M; i++) {
                String temp = br.readLine();
                if (people.contains(temp)) {
                    people2.add(temp);
                }
            }

            StringBuilder sb = new StringBuilder().append(people2.size()).append('\n');
            Iterator<String> iter = people2.iterator();
            while (iter.hasNext()) {
                sb.append(iter.next()).append('\n');
            }
            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
