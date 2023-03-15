package B9K.B9012;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String string = scanner.nextLine();
            if (isVPS(string)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static boolean isVPS(String str) {
        try {
            Stack<Character> parenthesisStack = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') parenthesisStack.push('(');
                if (str.charAt(i) == ')') parenthesisStack.pop();
            }
            if (parenthesisStack.empty()) return true;
            else return false;
        } catch (EmptyStackException e) {
            return false;
        }
    }
}
