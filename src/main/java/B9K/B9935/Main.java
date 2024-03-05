package B9K.B9935;

import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder result = new StringBuilder();
    static String s;
    static String s2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        s2 = br.readLine();

        int s2Index = 0;
        int index = 0;


        while (index < s.length() && s2Index < s2.length()) {
            if (s.charAt(index) == s2.charAt(0)) index = checkBomb(++index, new StringBuilder()) + 1;
            else result.append(s.charAt(index++));
        }

        if (result.isEmpty()) System.out.println("FRULA");
        else System.out.println(result);
    }

    //CCC344

    //index 두 개 있어야 할 듯
    //폭발 문자열 끝을 반환
    private static int checkBomb(int index, StringBuilder cache) {
        cache.append(s2.charAt(0));
        int s2Index = 1;

        while (s2Index < s2.length()) {
            if (s.charAt(index) != s2.charAt(s2Index)) {
                if (s.charAt(index) == s2.charAt(0)) {
                    int beforeSize = result.length();
                    index = checkBomb(++index, cache);
                    if (result.length() != beforeSize) {
                        result.append(s.charAt(++index));
                        return index;
                    }
                    s2Index--;
                }
                else {
                    result.append(cache.append(s.charAt(index)));
                    return index;
                }
            }
            else cache.append(s.charAt(index));

            index++;
            s2Index++;
        }

        cache.delete(cache.length() - s2.length(), cache.length());
        return index - 1;
    }
}
