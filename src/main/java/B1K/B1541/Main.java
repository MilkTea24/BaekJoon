package B1K.B1541;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String expression = br.readLine();
            /*
            String[] splitByPlus = expression.split("\\+");
            LinkedList<Integer> numbers = new LinkedList<>();

            for (String s : splitByPlus) {
                String[] splitByMinus = s.split("-");
                for (String s1 : splitByMinus) {
                    numbers.addLast(Integer.parseInt(s1));
                }
            }
            */
            String[] operands = expression.split("[\\+\\-]");
            LinkedList<Integer> numbers = new LinkedList<>();
            for (String operand : operands) {
                numbers.addLast(Integer.parseInt(operand));
            }


            int result = 0;
            LinkedList<Character> operators = new LinkedList<>();
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                    operators.addLast(expression.charAt(i));
                }
            }

            boolean minusFlag = false;
            int sum = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                if (minusFlag) {
                    sum -= numbers.get(i + 1);
                }
                else if(operators.get(i) == '+') {
                    sum += numbers.get(i + 1);
                }
                else {
                    minusFlag = true;
                    sum -= numbers.get(i + 1);
                }
            }

        System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
