package B1K.B1918;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class StackExample {
    static Map<Character, Integer> priority = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setPriority();
        StringBuilder prefix = new StringBuilder();
        StringBuilder infix = new StringBuilder(scanner.nextLine());
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char nowChar = infix.charAt(i);
            if (!priority.containsKey(nowChar) && nowChar != ')') prefix.append(nowChar);
            else if (nowChar == ')') {
                while (true) {
                    char popOperator = operatorStack.pop();
                    if (popOperator == '(') break;
                    else prefix.append(popOperator);
                }
            }
            else if (nowChar == '(') {
                operatorStack.push(nowChar);
            }
            else  {
                int priorityOfTopOperator = operatorStack.empty() ? 0 : priority.get(operatorStack.peek());
                    int priorityOfNowChar = priority.get(nowChar);
                    while (priorityOfTopOperator >= priorityOfNowChar) {
                        if (operatorStack.empty()) break;
                        char popOperator = operatorStack.pop();
                        if (popOperator != '(') prefix.append(popOperator);
                        priorityOfTopOperator = operatorStack.empty() ? 0 : priority.get(operatorStack.peek());
                    }
                    operatorStack.push(nowChar);
            }
        }

        while (!operatorStack.empty()) {
            char popOperator = operatorStack.pop();
            if (popOperator != '(') prefix.append(popOperator);
        }

        System.out.println(prefix);
    }

    static void setPriority() {
        priority.put('*', 3);
        priority.put('/', 3);
        priority.put('+', 2);
        priority.put('-', 2);
        priority.put('(', 1);
    }
}
