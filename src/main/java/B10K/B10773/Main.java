package B10K.B10773;

import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(bufferedReader.readLine());
            Stack<Integer> numberStack = new Stack<>();

            for (int i = 0; i < n; i++) {
                int number = Integer.parseInt(bufferedReader.readLine());
                if (number != 0) numberStack.push(number);
                else numberStack.pop();
            }
            int result = 0;
            for (int number : numberStack) {
                result += number;
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
