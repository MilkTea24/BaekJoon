package B1K.B1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int inputNum = Integer.parseInt(br.readLine());
            int result = operationToOne(inputNum, 0);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int operationToOne(int in, int count) {
        int result = Integer.MAX_VALUE;
        if (in == 1) return count;
        result = Integer.min(result, divideTwoOrThree(in, count));

        int temp1 = in - 1;
        result = Integer.min(result, divideTwoOrThree(temp1, count + 1));

        int temp2 = in - 2;
        result = Integer.min(result, divideTwoOrThree(temp2, count + 2));

        return result;
    }

    static int divideTwoOrThree(int in, int count) {
        int result = Integer.MAX_VALUE;
        if (in == 1) return count;
        if (in < 1) return Integer.MAX_VALUE;
        if (in % 3 == 0) result = Integer.min(result, operationToOne(in / 3, count + 1));
        if (in % 2 == 0) result = Integer.min(result, operationToOne(in / 2, count + 1));
        return result;
    }
}
