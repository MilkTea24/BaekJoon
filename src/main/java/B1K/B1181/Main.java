package B1K.B1181;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        TreeSet<String> stringTreeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() < s2.length()) return -1;
                else if (s1.length() == s2.length()) return (s1.compareTo(s2));
                else return 1;
            }
        });
        int N = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < N; i++) {
            String s = scanner.nextLine();
            if (!stringTreeSet.contains(s)) {
                stringTreeSet.add(s);
            }
        }

        for (String s : stringTreeSet) {
            System.out.println(s);
        }
    }
}
