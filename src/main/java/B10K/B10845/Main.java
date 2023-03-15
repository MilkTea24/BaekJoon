package B10K.B10845;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int orders = Integer.parseInt(br.readLine());
            MyQueue myQueue = new MyQueue(System.out);

            for (int i = 0; i < orders; i++){
                myQueue.processOrderAndAppendToBuffer(br.readLine());
            }
            myQueue.printBufferToPS();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class MyQueue {
        int size;
        LinkedList<Integer> ll;
        StringBuilder buffer;
        PrintStream ps;

        MyQueue(PrintStream _ps) {
            size = 0;
            ll = new LinkedList<>();
            buffer = new StringBuilder();
            ps = _ps;
        }


        void processOrderAndAppendToBuffer(String order) {
            String[] orders = order.split(" ");
            try {
                switch (orders[0]) {
                    case "push" -> ll.addLast(Integer.parseInt(orders[1]));
                    case "pop" -> {
                        buffer.append(ll.getFirst()).append('\n');
                        ll.removeFirst();
                    }
                    case "size" -> buffer.append(ll.size()).append('\n');
                    case "empty" -> {
                        int result = ll.isEmpty() ? 1 : 0;
                        buffer.append(result).append('\n');
                    }
                    case "front" -> buffer.append(ll.getFirst()).append('\n');
                    case "back" -> buffer.append(ll.getLast()).append('\n');
                }
            } catch (NoSuchElementException e) {
                buffer.append(-1).append('\n');
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void printBufferToPS() {
            ps.print(buffer);
        }
    }
}
