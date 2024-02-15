package B1K.B1991;

import java.util.*;
import java.io.*;

//전위 순회 출력 -> 왼쪽 이동 -> 오른쪽 이동
//중위 순회 왼쪽 이동 -> 출력 -> 오른쪽 이동
//후위 순회 왼쪽 이동 -> 오른쪽 이동 -> 출력
public class Main {
    static int N;
    static List<Node> node = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        getInput();

        Traversal preOrder = new Traversal() {
            @Override
            public void traverse(Node n) {
                if (Objects.isNull(n)) return;

                print(n);

                traverse(n.left);

                traverse(n.right);
            }
        };

        Traversal inOrder = new Traversal() {
            @Override
            public void traverse(Node n) {
                if (Objects.isNull(n)) return;

                traverse(n.left);

                print(n);

                traverse(n.right);
            }
        };

        Traversal postOrder = new Traversal() {
            @Override
            public void traverse(Node n) {
                if (Objects.isNull(n)) return;

                traverse(n.left);

                traverse(n.right);

                print(n);
            }
        };

        preOrder.traverse(node.get(0));
        preOrder.printAll();

        inOrder.traverse(node.get(0));
        inOrder.printAll();

        postOrder.traverse(node.get(0));
        postOrder.printAll();
    }

    private static void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            node.add(new Node((char)('A' + i)));
        }

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int parentIndex = line[0].charAt(0) - 'A';
            int leftIndex = line[1].charAt(0) != '.' ? line[1].charAt(0) - 'A' : -1;
            int rightIndex = line[2].charAt(0) != '.' ? line[2].charAt(0) - 'A' : -1;

            Node parent = node.get(parentIndex);
            if (leftIndex != -1) parent.left = node.get(leftIndex);
            if (rightIndex != -1) parent.right = node.get(rightIndex);
        }
    }

    private abstract static class Traversal {
        StringBuilder sb = new StringBuilder();

        protected void print(Node n) {
            sb.append(n.index);
        }

        public void printAll() {
            System.out.println(sb);
        }

        abstract public void traverse(Node n);
    }

    private static class Node {
        char index;
        Node left;
        Node right;

        Node(char i) {
            index = i;
        }
    }
}
