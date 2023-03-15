package B4K.B4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                char[] sentence = br.readLine().toCharArray();
                if (sentence.length == 1 && sentence[0] == '.') System.exit(0);
                Stack<Character> brackets = new Stack<>();
                for (char c : sentence) {
                    if (c == '(' || c == '[') brackets.push(c);
                    if (c == ')' || c == ']') {
                        char popChar = brackets.pop();
                        if (popChar == '(' && c == ']') throw new EmptyStackException();
                        if (popChar == '[' && c == ')') throw new EmptyStackException();
                    }
                }
                if (brackets.isEmpty() && sentence[sentence.length-1] == '.') System.out.println("yes");
                else System.out.println("no");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EmptyStackException e) {
                System.out.println("no");
            }
        }
    }
}
