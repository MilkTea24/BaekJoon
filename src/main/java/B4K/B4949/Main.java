package B4K.B4949;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[] sentence;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String s = br.readLine();
                if (s.equals(".")) break;
                sentence = new char[s.length()];
                for (int i = 0; i < s.length(); i++) {
                    sentence[i] = s.charAt(i);
                }

                if (isCompletedSentence(0, ' ') == sentence.length) System.out.println("yes");
                else System.out.println("no");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int isCompletedSentence(int start, char startChar) {
        int i;
        for (i = start; i < sentence.length; i++) {
            switch(sentence[i]) {
                case '(' : i = isCompletedSentence(i+1, '('); break;
                case '[' : i = isCompletedSentence(i+1, '['); break;
                case ')' : {
                    if (startChar == '(') return i;
                    else return -1;
                }
                case ']' : {
                    if (startChar == '[') return i;
                    else return -1;
                }
                default : continue;
            }
            if (i == -1) return -1;
        }

        if (startChar == ' ' && sentence[i - 1] == '.') return sentence.length;
        else return -1;
    }
}
