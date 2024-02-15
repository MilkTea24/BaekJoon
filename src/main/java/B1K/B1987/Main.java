package B1K.B1987;

import java.io.*;
import java.util.*;

//세로 R칸, 가로 C칸 보드
//보드의 각 칸에는 알파벳이 하나 적혀져 있고 좌측 상단에서 말이 시작
//상하좌우 인접한 네 칸 중 한 칸으로 이동할 수 있는데 새로 이동한 칸의 알파벳은 새로운 알파벳이여야 한다.
//말이 최대 몇 칸 지나갈 수 있는가?
public class Main {
    static int R;
    static int C;

    static char[][] board;
    static int[][] near = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String oneLine = br.readLine();
            for (int j = 0; j < oneLine.length(); j++) {
                board[i][j] = oneLine.charAt(j);
            }
        }

        int result = BFS(0, 0);

        System.out.println(result);
    }

    private static int BFS(int a, int b) {
        Node n = new Node(a, b, 1, null);
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(n);

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            int newI = 0;
            int newJ = 0;

            for (int i = 0; i < 4; i++) {
                newI = current.a + near[i][0];
                newJ = current.b + near[i][1];

                //보드 범위 밖을 넘어가면 안됨
                if (newI < 0 || newI >= R || newJ < 0 || newJ >= C) continue;

                //만약 한번도 방문한 적이 없는 알파벳이면
                Node node = new Node(newI, newJ, current.distance + 1, current);
                if (isNewAlphabet(node)) {
                    queue.add(node);
                    maxDistance = current.distance + 1;
                }
            }
        }

        return maxDistance;
    }

    private static boolean isNewAlphabet(Node n) {
        Node prevNode = n.prevNode;

        while (!Objects.isNull(prevNode)) {
            if (board[prevNode.a][prevNode.b] == board[n.a][n.b]) return false;
            prevNode = prevNode.prevNode;
        }

        return true;
    }


    private static class Node {
        int a;
        int b;

        int distance;

        Node prevNode;

        Node(int a, int b, int d, Node pN) {
            this.a = a;
            this.b = b;
            this.distance = d;
            this.prevNode = pN;
        }
    }
}
