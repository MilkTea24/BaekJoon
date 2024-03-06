package B9K.B9375;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {

        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() throws Exception {
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> clothes = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            if (clothes.containsKey(line[1])) clothes.put(line[1], clothes.get(line[1]) + 1);
            else clothes.put(line[1], 1);
        }

        Collection<Integer> valueSet = clothes.values();
        int result = 1;
        for (int i : valueSet) {
            result *= (i + 1);
        }
        System.out.println(result - 1);
    }
}
