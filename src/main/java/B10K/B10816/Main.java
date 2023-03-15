package B10K.B10816;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> numberMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            int[] numberCards = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(string -> Integer.parseInt(string))
                    .toArray();

            for (int i = 0; i < numberCards.length; i++) {
                if (!numberMap.containsKey(numberCards[i])) numberMap.put(numberCards[i], 1);
                else {
                    numberMap.put(numberCards[i], numberMap.get(numberCards[i]) + 1);
                }
            }

            int M = Integer.parseInt(br.readLine());
            int[] searchNumbers = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(string -> Integer.parseInt(string))
                    .toArray();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < searchNumbers.length; i++) {
                if (numberMap.containsKey(searchNumbers[i])) sb.append(numberMap.get(searchNumbers[i])).append(' ');
                else sb.append("0 ");
            }

            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
