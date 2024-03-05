package B9K.B9935;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class NewMain {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        for (int i = 0; i < s1.length(); i++) {
            stack.push(s1.charAt(i));

            int count = 0;
           if (stack.size() >= s2.length()) {
               for (int j = 0; j < s2.length(); j++) {
                   if (stack.get(stack.size() - s2.length() + j) != s2.charAt(j)) {
                       break;
                   }
                   count++;
               }

               if (count == s2.length()) {
                   for (int j = 0; j < s2.length(); j++) {
                       stack.pop();
                   }
               }
           }
        }

        if (stack.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder result = new StringBuilder();

            for (char c : stack) {
                result.append(c);
            }

            System.out.println(result);
        }
    }
}
