package B1K.B1022;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        System.out.println(a.compareTo(b) <= 0 ? "go" : "no");
    }
}
