package B10K.B10828;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            MyStack myStack = new MyStack(System.out);
            int orders = Integer.parseInt(br.readLine());
            for (int i = 0; i < orders; i++) {
                String order = br.readLine();
                myStack.processOrderAndAppendResult(order);
            }

            myStack.printAllResultToPS();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyStack {
        PrintStream ps;
        private int[] stack;
        private int size;
        private StringBuilder sb;

        MyStack(PrintStream _os) {
            stack = new int[10000];
            size = 0;
            ps = _os;
            sb = new StringBuilder();
        }

        void processOrderAndAppendResult(String line) {
            String[] order = line.split(" ");
            switch (order[0]) {
                case "push" -> {
                    stack[size++] = Integer.parseInt(order[1]);
                }
                case "pop" -> {
                    if (size == 0) sb.append(-1).append('\n');
                    else {
                        int returnValue = stack[size - 1];
                        stack[size - 1] = 0;
                        size--;
                        sb.append(returnValue).append('\n');
                    }
                }
                case "size" -> sb.append(size).append('\n');
                case "empty" -> {
                    if (size == 0) sb.append(1).append('\n');
                    else sb.append(0).append('\n');
                }
                case "top" -> {
                    if (size == 0) sb.append(-1).append('\n');
                    else {
                        sb.append(stack[size - 1]).append('\n');
                    }
                }

            }
        }

        void printAllResultToPS() throws IOException {
            ps.print(sb);
            ps.flush();
        }
    }
}


