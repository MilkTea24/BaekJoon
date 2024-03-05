package B7K.B7662;

import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        try {
            int cases = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < cases; i++) {
                oneCase(sb);
            }

            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void oneCase(StringBuilder sb) throws Exception {
        int n = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> numbers = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int input = Integer.parseInt(line[1]);

            if (line[0].equals("I")) {
                if (numbers.containsKey(input)) {
                    numbers.put(input, numbers.get(input) + 1);
                }
                else {
                    numbers.put(input, 1);
                }
            }
            if (line[0].equals("D") && !numbers.isEmpty()) {
                Map.Entry<Integer, Integer> removeEntry;
                if (input == -1) {
                    removeEntry = numbers.firstEntry();
                } else {
                    removeEntry = numbers.lastEntry();
                }

                int removeKey = removeEntry.getKey();
                int removeValue = removeEntry.getValue();

                if (removeValue == 1) {
                    numbers.remove(removeKey);
                }
                else {
                    numbers.put(removeKey, removeValue - 1);
                }
            }
        }

        if (numbers.isEmpty()) sb.append("EMPTY\n");
        else {
            int max = numbers.lastEntry().getKey();
            int min = numbers.firstEntry().getKey();
            sb.append(max + " " + min + "\n");
        }
    }
}


    /*
    static void oneCase(StringBuilder sb) throws Exception {
        int n = Integer.parseInt(br.readLine());

        LinkedList<Integer> sortedList = new LinkedList<>();



        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");

            if (line[0].equals("I")) insert(sortedList, Integer.parseInt(line[1]));
            else {
                if (line[1].equals("-1") && !sortedList.isEmpty()) sortedList.removeFirst();
                else if (line[1].equals("1") && !sortedList.isEmpty()) sortedList.removeLast();
            }
        }


        if (sortedList.isEmpty()) sb.append("EMPTY\n");
        else {
            sb.append(sortedList.getLast() + " " + sortedList.getFirst() + "\n");
        }
    }

    static void insert(LinkedList<Integer> sortedList, int input) {
        if (sortedList.isEmpty()) {
            sortedList.add(input);
            return;
        }

        long low = 0;
        long high = sortedList.size() - 1;


        while (low <= high) {
            long mid = (low + high) / 2;
            if (sortedList.get((int)mid) < input) low = mid + 1;
            else high = mid - 1;
        }

        sortedList.add((int)low, input);
    }
    */

