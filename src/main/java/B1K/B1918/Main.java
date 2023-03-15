package B1K.B1918;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                int Ind = s.indexOf(s.charAt(i), i);
                if (Ind != -1) {
                    s = addBrackets(Ind, s);
                    i = Ind + 1;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                int Ind = s.indexOf(s.charAt(i), i);
                if (Ind != -1) {
                    s = addBrackets(Ind, s);
                    i = Ind + 1;
                }
            }
        }

        String result = postFix(s);
        System.out.println(result);
    }

    static String addBrackets(int Ind, String s) {
        StringBuilder sb = new StringBuilder(s);


        int leftInd = Ind - 1, rightInd = Ind + 1;

        Stack<Character> leftBrackets = new Stack<>();
        while (leftInd >= 0) {
            if (sb.charAt(leftInd) == ')') leftBrackets.add(')');
            if (sb.charAt(leftInd) == '(') leftBrackets.pop();
            if (leftBrackets.empty()) {
                sb.insert(leftInd, '(');
                rightInd++;
                break;
            }
            leftInd--;
        }

        Stack<Character> rightBrackets = new Stack<>();
        while (rightInd < sb.length()) {
            if (sb.charAt(rightInd) == '(') rightBrackets.add('(');
            if (sb.charAt(rightInd) == ')') rightBrackets.pop();
            if (rightBrackets.empty()) {
                sb.insert(rightInd + 1, ')');
                break;
            }
            rightInd++;
        }




        return sb.toString();
    }

    static String postFix(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Character> operatorStack = new Stack<>();
        int i = 0;
        while (i < sb.length()) {
            if (sb.charAt(i) == '+' || sb.charAt(i) == '-' || sb.charAt(i) == '*' || sb.charAt(i) == '/' || sb.charAt(i) == '(') {
                operatorStack.push(sb.charAt(i));
                sb.deleteCharAt(i);
            }
            else if (sb.charAt(i) == ')') {
                sb.deleteCharAt(i);
                while (true) {
                    char c = operatorStack.pop();
                    if (c != '(') {
                        sb.insert(i, c);
                        i++;
                    }
                    else break;
                }
            }
            else i++;
        }

        return sb.toString();
    }
}
