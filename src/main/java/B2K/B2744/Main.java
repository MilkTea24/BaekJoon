package B2K.B2744;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        char[] charArr = str.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] >= 'a' && charArr[i] <= 'z') charArr[i] += (char)('A' - 'a');
            else charArr[i] -= (char)('A' - 'a');
        }

        String result = new String(charArr);
        System.out.println(result);
    }
}
