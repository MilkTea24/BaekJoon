package B17K.B17070;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;

    enum Direction {
        VERTICAL, HORIZONTAL, DIAGONAL
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Pipe pipe = new Pipe();

        int result = DFS(pipe, 0);
        System.out.print(result);

    }

    private static int DFS(Pipe pipe, int count) {
        if (pipe.x == N-1 && pipe.y == N-1) return count + 1;

        int originalX = pipe.x;
        int originalY = pipe.y;
        Direction originalDirection = pipe.direction;

        boolean isMoved = true;

        isMoved = pipe.moveDown();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalX, originalY);
        pipe.direction = originalDirection;

        isMoved = pipe.moveRight();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalX, originalY);
        pipe.direction = originalDirection;

        isMoved = pipe.moveDiagonal();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalX, originalY);
        pipe.direction = originalDirection;

        return count;
    }

    private static class Pipe {
        int x;
        int y;

        Direction direction;

        Pipe() {
            x = 0;
            y = 1;
            direction = Direction.HORIZONTAL;
        }

        void setPosition(int _x, int _y) {
            x = _x;
            y = _y;
        }

        boolean moveDown() {
            if (x + 1 >= N) return false;
            if (map[x + 1][y] == 1) return false;
            if (direction == Direction.HORIZONTAL) return false;

            x = x + 1;
            direction = Direction.VERTICAL;
            return true;
        }

        boolean moveRight() {
            if (y + 1 >= N) return false;
            if (map[x][y + 1] == 1) return false;
            if (direction == Direction.VERTICAL) return false;

            y = y + 1;
            direction = Direction.HORIZONTAL;
            return true;
        }

        boolean moveDiagonal() {
            if (y + 1 >= N || x + 1 >= N) return false;
            if (map[x + 1][y] == 1 || map[x][y + 1] == 1 || map[x + 1][y + 1] == 1) return false;

            x = x + 1;
            y = y + 1;
            direction = Direction.DIAGONAL;
            return true;
        }
    }
}
