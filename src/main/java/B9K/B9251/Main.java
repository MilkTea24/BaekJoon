package B9K.B9251;

import java.util.*;

//길이가 6일 때 부분 수열의 수 6 + 15 + 20 + 15 + 6 + ({}) = 2^6 - 1 완전 탐색은 불가능

//비교 1          비교 2
//ACAYKP          ACAYKP
//CAPCAK           CAPCAK

//비교 3          비교 4
//ACAYKP          ACAYKP
//  CAPCAK           CAPCAK
 public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();
        String b = scanner.nextLine();

        int m = a.length();
        int n = b.length();

        int[][] c = new int[m+1][n+1];

        //i = 0일 때 빈 수열이고 i = 1일 때 String a의 charAt(0)과 동일하므로 charAt에서 인덱스를 1 빼줘야 한다.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) c[i][j] = c[i-1][j-1] + 1;
                else c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
            }
        }

        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while (i != 0 && j != 0) {
            //1번
            if (c[i][j] != c[i-1][j] && c[i][j] != c[i][j-1]) {
                sb.insert(0, a.charAt(i-1));
                i--;
                j--;
            }
            //2번
            else if (c[i][j] != c[i][j-1]) i--;
            else j--;
        }

        System.out.println(c[m][n] + " " + sb);
    }
}
