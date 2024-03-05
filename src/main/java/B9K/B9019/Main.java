package B9K.B9019;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cases; i++) {
            sb.append(oneCase()).append("\n");
        }
        System.out.println(sb);
    }
    static String oneCase() throws Exception {
        String[] line = br.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);

        String operations = BFS(start, end);

        return operations;
    }

    static String BFS(int start, int end)  {
        boolean[] isVisited = new boolean[10000];

        LinkedList<Node> queue = new LinkedList<>();
        Node startNode = new Node(start);
        startNode.beforeNode = startNode;
        startNode.operator = "";

        isVisited[start] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            int D = D(node.number);
            int S = S(node.number);
            int L = L(node.number);
            int R = R(node.number);


            if (!isVisited[D]) {
                if (D == end) {
                    return node.operator + "D";
                }

                isVisited[D] = true;
                queue.add(new Node(D, node, node.operator + "D"));
            }
            if (!isVisited[S]) {
                if (S == end) {
                    return node.operator + "S";
                }

                isVisited[S] = true;
                queue.add(new Node(S, node, node.operator + "S"));
            }

            if (!isVisited[L]) {
                if (L == end) {
                    return node.operator + "L";
                }

                isVisited[L] = true;
                queue.add(new Node(L, node, node.operator + "L"));
            }

            if (!isVisited[R]) {
                if (R == end) {
                    return node.operator + "R";
                }
                isVisited[R] = true;
                queue.add(new Node(R, node, node.operator + "R"));
            }


        }

        return "";
    }

    /*
    static String printOperators(Node node, String lastOperator) {
        StringBuilder sb = new StringBuilder(lastOperator);
        Node beforeNode = node.beforeNode;
        while (node.number != beforeNode.number) {
            sb.insert(0, node.beforeOperator);
            node = beforeNode;
            beforeNode = beforeNode.beforeNode;
        }
        return sb.toString();
    }
     */

    static int D(int operand) {
        return (operand * 2) % 10000;
    }

    static int S(int operand) {
        return operand - 1 == -1? 9999 : operand - 1;
    }

    static int L(int operand) {
        /*
        int resultNum = 0;
        try {
            String numberString = String.format("%04d", operand);
            String[] numberSplit = numberString.split("");

            String result = new StringBuilder(numberSplit[1])
                    .append(numberSplit[2])
                    .append(numberSplit[3])
                    .append(numberSplit[0])
                    .toString();
            resultNum = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println(operand);
        }
        return resultNum;

         */
        return (operand % 1000) * 10 + operand / 1000;
    }

    static int R(int operand) {
        /*
        String numberString = String.format("%04d", operand);
        String[] numberSplit = numberString.split("");

        String result = new StringBuilder(numberSplit[3])
                .append(numberSplit[0])
                .append(numberSplit[1])
                .append(numberSplit[2]).toString();
        return Integer.parseInt(result);
         */
        return (operand % 10) * 1000 + operand / 10;
    }

    static class Node {
        int number;
        Node beforeNode;
        String operator;

        public Node(int number) {
            this.number = number;
        }

        public Node(int number, Node beforeNode, String s) {
            this.number = number;
            this.beforeNode = beforeNode;
            this.operator = s;
        }
    }
}
