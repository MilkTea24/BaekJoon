package B10K.B10866;

import java.util.LinkedList;
import java.io.*;

public class Main {
    static LinkedList<Integer> deque;
    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        deque = new LinkedList<>();
        try {
            int orders = Integer.parseInt(br.readLine());
            for (int i = 0; i < orders; i++) {
                int result = processOrders(br);
                if (result != -2) sb.append(result).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int processOrders(BufferedReader br) throws Exception  {
        String[] order = br.readLine().split(" ");
        switch(order[0]) {
            case "push_back" -> deque.addLast(Integer.parseInt(order[1]));
            case "push_front" -> deque.addFirst(Integer.parseInt(order[1]));
            case "front" -> {
                if (deque.isEmpty()) return -1;
                else return deque.getFirst();
            }
            case "back" -> {
                if (deque.isEmpty()) return -1;
                else return deque.getLast();
            }
            case "size" -> {return deque.size();}
            case "pop_front" -> {
                if (deque.isEmpty()) return -1;
                else return deque.removeFirst();
            }
            case "pop_back" -> {
                if (deque.isEmpty()) return -1;
                else return deque.removeLast();
            }
            case "empty" -> {return deque.isEmpty() ? 1 : 0;}
        }
        return -2;
    }
}
