package B12K.B12095;

import java.io.*;
import java.util.*;

/*
2시 32분 시작
스도쿠를 완성하는데 81자리 수가 제일 작은 경우를 출력
 */
public class Main {
    static int[][] board = new int[9][9];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        backtracking(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static boolean backtracking(int a, int b) {
        if (a == 9 && b == 9 && board[a][b] != 0) return true;

        //System.out.println(a + " " + b);

        int i = a, j = b;
        boolean flag = true;
        label: for (i = a; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    flag = false;
                    break label;
                }

            }
        }

        if (flag) return true;


        for (int k = 1; k <= 9; k++) {
            if (duplicateRow(i, j, k)) continue;
            if (duplicateColumn(i, j, k)) continue;
            if (duplicateSquare(i, j, k)) continue;

            board[i][j] = k;
            if (backtracking(i, j)) {
                //System.out.println(i + " " + j + " " + k);
                return true;
            }
            board[i][j] = 0;
        }
        return false;
    }

    static boolean duplicateRow(int a, int b, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[a][i] == c) return true;
        }
        return false;
    }

    static boolean duplicateColumn(int a, int b, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][b] == c) return true;
        }
        return false;
    }

    static boolean duplicateSquare(int a, int b, int c) {
        int squareA = a / 3;
        int squareB = b / 3;

        for (int i = squareA * 3; i < squareA * 3 + 3; i++) {
            for (int j = squareB * 3; j < squareB * 3 + 3; j++) {
                if (board[i][j] == c) return true;
            }
        }
        return false;
    }

}
