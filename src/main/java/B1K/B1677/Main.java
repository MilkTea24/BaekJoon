package B1K.B1677;

import java.util.*;

/*
A& &A &AC& A&C
&F F& F&C& &FC
A& &A &A&F AF&
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String music1 = scanner.nextLine();
        String music2 = scanner.nextLine();
        String music3 = scanner.nextLine();

        int maxLen = Math.max(music1.length(), Math.max(music2.length(), music3.length()));
        int minLen = Math.min(music1.length(), Math.min(music2.length(), music3.length()));

        int[][][] dp = new int[music1.length() + 1][music2.length() + 1][music3.length() + 1];
        dp
    }
}
