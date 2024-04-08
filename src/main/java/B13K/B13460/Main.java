package B13K.B13460;

import java.util.*;
import java.io.*;

/*
10시 15분 시작
 */
public class Main {
    static int N, M;
    static int[][] direction = {{0, 1},{0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        Board b = new Board();
        b.board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            for (int j = 0; j < M; j++) {
                b.board[i][j] = s.charAt(j);
                if (b.board[i][j] == 'B') {
                    b.blueI = i;
                    b.blueJ = j;
                }
                if (b.board[i][j] == 'R') {
                    b.redI = i;
                    b.redJ = j;
                }
                if (b.board[i][j] == 'O') {
                    Board.holeI = i;
                    Board.holeJ = j;
                }
            }
        }

        int result = BFS(b);

        System.out.println(result);
    }

    private static int BFS(Board b) {
        Queue<Board> queue = new LinkedList<>();
        queue.add(b);

        while (!queue.isEmpty()) {
            Board current = queue.poll();

            Board left = Board.moveLeft(current);
            if (!left.gameOver) {
                if (left.gameClear) return left.count;
                if (left.count > 10) return -1;
                if (!isSame(left, current)) queue.add(left);
            }

            Board right = Board.moveRight(current);
            if (!right.gameOver) {
                if (right.gameClear) return right.count;
                if (!isSame(right, current)) queue.add(right);
            }

            Board up = Board.moveUp(current);
            if (!up.gameOver) {
                if (up.gameClear) return up.count;
                if (!isSame(up, current)) queue.add(up);
            }

            Board down = Board.moveDown(current);
            if (!down.gameOver) {
                if (down.gameClear) return down.count;
                if (!isSame(down, current)) queue.add(down);
            }
        }

        return -1;
    }

    private static boolean isSame(Board a, Board b) {
        return a.redI == b.redI && a.blueI == b.blueI && a.redJ == b.redJ && a.blueJ == b.blueJ;
    }

    static class Board {
        boolean gameOver;

        boolean gameClear;
        static int holeI, holeJ;
        int blueI, blueJ, redI, redJ;

        int count;
        char[][] board;

        static Board moveLeft(Board board) {
            Board newBoard = createNewBoard(board);

            //한 줄에 같이 있고 빨간 공이 왼쪽에 있다면 빨간 공 먼저
            if (newBoard.redJ < newBoard.blueJ && newBoard.redI == newBoard.blueI) {
                move(1, newBoard, newBoard.redI, newBoard.redJ, 'R');
                move(1, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
            }
            else {
                move(1, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
                move(1, newBoard, newBoard.redI, newBoard.redJ, 'R');
            }

            return newBoard;
        }

        static Board moveRight(Board board) {
            Board newBoard = createNewBoard(board);

            //한 줄에 같이 있고 빨간 공이 오른쪽에 있다면 빨간 공 먼저
            if (newBoard.redJ > newBoard.blueJ && newBoard.redI == newBoard.blueI) {
                move(0, newBoard, newBoard.redI, newBoard.redJ, 'R');
                move(0, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
            }
            else {
                move(0, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
                move(0, newBoard, newBoard.redI, newBoard.redJ, 'R');
            }

            return newBoard;
        }

        static Board moveUp(Board board) {
            Board newBoard = createNewBoard(board);

            //한 줄에 같이 있고 빨간 공이 위쪽에 있다면 빨간 공 먼저
            if (newBoard.redI < newBoard.blueI && newBoard.redJ == newBoard.blueJ) {
                move(3, newBoard, newBoard.redI, newBoard.redJ, 'R');
                move(3, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
            }
            else {
                move(3, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
                move(3, newBoard, newBoard.redI, newBoard.redJ, 'R');
            }

            return newBoard;
        }

        static Board moveDown(Board board) {
            Board newBoard = createNewBoard(board);

            //한 줄에 같이 있고 빨간 공이 아래쪽에 있다면 빨간 공 먼저
            if (newBoard.redI > newBoard.blueI && newBoard.redJ == newBoard.blueJ) {
                move(2, newBoard, newBoard.redI, newBoard.redJ, 'R');
                move(2, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
            }
            else {
                move(2, newBoard, newBoard.blueI, newBoard.blueJ, 'B');
                move(2, newBoard, newBoard.redI, newBoard.redJ, 'R');
            }

            return newBoard;
        }

        static  Board createNewBoard(Board board) {
            Board newBoard = new Board();
            newBoard.board = new char[N][M];
            newBoard.count = board.count + 1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newBoard.board[i][j] = board.board[i][j];
                }
            }

            newBoard.blueI = board.blueI;   newBoard.blueJ = board.blueJ;
            newBoard.redI = board.redI;    newBoard.redJ = board.redJ;

            return newBoard;
        }

        static void move(int directionIndex, Board b, int rI, int rJ, char type) {
            char[][] board = b.board;
           board[rI][rJ] = '.';

            while (true) {
                if (board[rI][rJ] != '.') {
                    //게임 클리어
                    if (type == 'R' && board[rI][rJ] == 'O') {
                        b.gameClear = true;
                        return;
                    }
                    if (type == 'B' && board[rI][rJ] == 'O') {
                        b.gameOver = true;
                        return;
                    }

                    //벽이나 파란 공에 도달
                    rI -= direction[directionIndex][0];
                    rJ -= direction[directionIndex][1];

                    break;
                }

                rI += direction[directionIndex][0];
                rJ += direction[directionIndex][1];
            }

            if (type == 'R') {
                b.redI = rI;
                b.redJ = rJ;
                board[rI][rJ] = 'R';
            }
            else {
                b.blueI = rI;
                b.blueJ = rJ;
                board[rI][rJ] = 'B';
            }
        }
    }
}
