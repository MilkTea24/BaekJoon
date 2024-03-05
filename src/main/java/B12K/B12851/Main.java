package B12K.B12851;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        if (N > K) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }
        else if (N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        //BFS
        boolean[] isVisited = new boolean[150000];

        LinkedList<Node> queue = new LinkedList<>();

        queue.add(new Node(N, 0));

        int count = 0;
        int distance = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            isVisited[current.pos] = true;

            if (current.pos == K) {
                distance = current.dis;
                count++;

                while (!queue.isEmpty()) {
                    Node remain = queue.removeFirst();
                    if (remain.pos == K && remain.dis == distance) {
                        count++;
                    }
                }
            }

            //2 곱하기
            if (current.dis + 1 <= distance && current.pos * 2 < 150000 && !isVisited[current.pos * 2] && current.pos < K) {
                queue.add(new Node(current.pos * 2, current.dis + 1));
            }
            //1 더하기
            if (current.dis + 1 <= distance && !isVisited[current.pos + 1] && current.pos < K) {
                queue.add(new Node(current.pos + 1, current.dis + 1));
            }
            //1 빼기
            if (current.dis + 1 <= distance && current.pos > 0 && !isVisited[current.pos - 1] ) {
                queue.add(new Node(current.pos - 1, current.dis + 1));
            }
        }

        System.out.println(distance);
        System.out.println(count);
    }

    private static class Node {
        int pos;
        int dis;

        Node(int p, int d) {
            pos = p;
            dis = d;
        }
    }
}
