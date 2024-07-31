package B2K.B2754;

import java.util.*;

public class Main {
    static Map<String, Double> map = new HashMap<>();
    public static void main(String[] args) {
        map.put("A+", 4.3);
        map.put("A0", 4.0);
        map.put("A-", 3.7);
        map.put("B+", 3.3);
        map.put("B0", 3.0);
        map.put("B-", 2.7);
        map.put("C+", 2.3);
        map.put("C0", 2.0);
        map.put("C-", 1.7);
        map.put("D+", 1.3);
        map.put("D0", 1.0);
        map.put("D-", 0.7);
        map.put("F", 0.0);

        Scanner scanner = new Scanner(System.in);
        System.out.println(map.get(scanner.nextLine()));
    }
}
