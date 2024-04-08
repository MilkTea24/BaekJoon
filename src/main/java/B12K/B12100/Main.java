package B12K.B12100;

import java.util.*;
import java.io.*;

/*
12시 시작
5번 이동했을 때 얻을 수 있는 가장 큰 블록
2 2 2 2 합쳐지면 4 4
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Board board = new Board(n);

        for (int i = 0; i < n; i++) {
            board.board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = play(board, 0);

        /*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board.board[i][j] + " ");
            }
            System.out.println();
        }
         */

        System.out.println(result);
    }

    private static int play(Board board, int count) {
        if (count == 5) {
            return Arrays.stream(board.board).flatMapToInt(Arrays::stream).max().getAsInt();
        }

        int result = 0;

        Board leftBoard = Board.moveLeft(board);
        result = Integer.max(result, play(leftBoard, count + 1));

        Board rightBoard = Board.moveRight(board);
        result = Integer.max(result, play(rightBoard, count + 1));

        Board upBoard = Board.moveUp(board);
        result = Integer.max(result, play(upBoard, count + 1));

        Board downBoard = Board.moveDown(board);
        result = Integer.max(result, play(downBoard, count + 1));

        return result;
    }

    static class Board {
        int[][] board;
        int n;

        Board(int _n) {
            n = _n;
            board = new int[n][n];
        }

        Board(int[][] b) {
            board = b;
            n = board[0].length;
        }

        static Board moveLeft(Board b) {
            int[][] board = b.board;
            int n = b.n;
            int[][] newBoard = new int[n][n];
            for (int i = 0; i < n; i++) {
                //라인 하나 이동
                int beforeNumber = 0;
                int moveIndex = 0;
                for (int j = 0; j < n; j++) {
                    //숫자가 없는 경우 다음 탐색
                    if (board[i][j] == 0) continue;

                    if (beforeNumber == 0) {
                        beforeNumber = board[i][j];
                    }
                    else {
                        //만약 앞의 숫자와 같은 값인경우
                        if (beforeNumber == board[i][j]) {
                            newBoard[i][moveIndex] = board[i][j] * 2;
                            beforeNumber = 0;
                        }
                        //앞의 숫자와 다른 값인경우
                        else {
                            newBoard[i][moveIndex] = beforeNumber;
                            beforeNumber = board[i][j];
                        }
                        moveIndex += 1;
                    }
                }
                if (beforeNumber != 0) newBoard[i][moveIndex] = beforeNumber;
            }

            return new Board(newBoard);
        }

        static Board moveRight(Board b) {
            int[][] board = b.board;
            int n = b.n;
            int[][] newBoard = new int[n][n];
            for (int i = 0; i < n; i++) {
                //라인 하나 이동
                int beforeNumber = 0;
                int moveIndex = n-1;
                for (int j = n - 1; j >= 0; j--) {
                    //숫자가 없는 경우 다음 탐색
                    if (board[i][j] == 0) continue;

                    if (beforeNumber == 0) {
                        beforeNumber = board[i][j];
                    }
                    else {
                        //만약 앞의 숫자와 같은 값인경우
                        if (beforeNumber == board[i][j]) {
                            newBoard[i][moveIndex] = board[i][j] * 2;
                            beforeNumber = 0;
                        }
                        //앞의 숫자와 다른 값인경우
                        else {
                            newBoard[i][moveIndex] = beforeNumber;
                            beforeNumber = board[i][j];
                        }
                        moveIndex -= 1;
                    }
                }
                if (beforeNumber != 0) newBoard[i][moveIndex] = beforeNumber;
            }

            return new Board(newBoard);
        }

        static Board moveDown(Board b) {
            int[][] board = b.board;
            int n = b.n;
            int[][] newBoard = new int[n][n];
            for (int j = 0; j < n; j++) {
                //라인 하나 이동
                int beforeNumber = 0;
                int moveIndex = n-1;
                for (int i = n - 1; i >= 0; i--) {
                    //숫자가 없는 경우 다음 탐색
                    if (board[i][j] == 0) continue;

                    if (beforeNumber == 0) {
                        beforeNumber = board[i][j];
                    }
                    else {
                        //만약 앞의 숫자와 같은 값인경우
                        if (beforeNumber == board[i][j]) {
                            newBoard[moveIndex][j] = board[i][j] * 2;
                            beforeNumber = 0;
                        }
                        //앞의 숫자와 다른 값인경우
                        else {
                            newBoard[moveIndex][j] = beforeNumber;
                            beforeNumber = board[i][j];
                        }
                        moveIndex -= 1;
                    }
                }
                if (beforeNumber != 0) newBoard[moveIndex][j] = beforeNumber;
            }

            return new Board(newBoard);
        }

        static Board moveUp(Board b) {
            int[][] board = b.board;
            int n = b.n;
            int[][] newBoard = new int[n][n];
            for (int j = 0; j < n; j++) {
                //라인 하나 이동
                int beforeNumber = 0;
                int moveIndex = 0;
                for (int i = 0; i < n; i++) {
                    //숫자가 없는 경우 다음 탐색
                    if (board[i][j] == 0) continue;

                    if (beforeNumber == 0) {
                        beforeNumber = board[i][j];
                    }
                    else {
                        //만약 앞의 숫자와 같은 값인경우
                        if (beforeNumber == board[i][j]) {
                            newBoard[moveIndex][j] = board[i][j] * 2;
                            beforeNumber = 0;
                        }
                        //앞의 숫자와 다른 값인경우
                        else {
                            newBoard[moveIndex][j] = beforeNumber;
                            beforeNumber = board[i][j];
                        }
                        moveIndex += 1;
                    }
                }
                if (beforeNumber != 0) newBoard[moveIndex][j] = beforeNumber;
            }

            return new Board(newBoard);
        }
    }
}
