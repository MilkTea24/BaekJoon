package B5K.B5639;

import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder print = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        int i = Integer.parseInt(br.readLine());

        Node root = new Node(i);


        while (true) {
            line = br.readLine();

            if (Objects.isNull(line) || line.isBlank()) break;

            i = Integer.parseInt(line);

            Node current = root;
            while (true) {
                if (i > current.index) {
                    //더이상 이동할 수 없는 노드가 없으면 추가
                    if (Objects.isNull(current.right)) {
                        current.right = new Node(i);
                        break;
                    }
                    else current = current.right;
                }
                else {
                    if (Objects.isNull(current.left)) {
                        current.left = new Node(i);
                        break;
                    }
                    else current = current.left;
                }
            }
        }

        traverse(root);

        System.out.print(print);


    }

    private static void traverse(Node n) {
        if (Objects.isNull(n)) return;

        traverse(n.left);

        traverse(n.right);

        print.append(n.index).append('\n');
    }


    private static class Node {
        int index;
        Node left;
        Node right;

        Node (int _i) {
            index = _i;
        }
    }
}
