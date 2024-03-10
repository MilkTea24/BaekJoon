package B17K.B17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;

    static enum Direction {
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

    private static int BFS(Pipe pipe) {
        Queue<Pipe> queue = new LinkedList<>();
        queue.add(pipe);

        int result = 0;
        while (!queue.isEmpty()) {
            Pipe current = queue.poll();

            if (current.end.x == N-1 && current.end.y == N-1) {
                result++;
                continue;
            }

            Pos originalStart = current.start;
            Pos originalEnd = current.end;

            boolean flag = current.moveDown();
            if (flag) queue.add(new Pipe(current));
            current.setPosition(originalStart, originalEnd);

            flag = current.moveDiagonal();
            if (flag) queue.add(new Pipe(current));
            current.setPosition(originalStart, originalEnd);

            flag = current.moveRight();
            if (flag) queue.add(new Pipe(current));
            current.setPosition(originalStart, originalEnd);
        }

        return result;
    }

    private static int DFS(Pipe pipe, int count) {
        if (pipe.end.x == N-1 && pipe.end.y == N-1) return count + 1;

        Pos originalStart = pipe.start;
        Pos originalEnd = pipe.end;

        boolean isMoved = true;

        isMoved = pipe.moveDown();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalStart, originalEnd);

        isMoved = pipe.moveRight();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalStart, originalEnd);

        isMoved = pipe.moveDiagonal();
        if (isMoved) count = DFS(pipe, count);
        pipe.setPosition(originalStart, originalEnd);

        return count;
    }

    private static class Pipe {
        Pos start;
        Pos end;

        Direction direction;

        Pipe() {
            start = new Pos(0, 0);
            end = new Pos(0, 1);
            direction = Direction.HORIZONTAL;
        }

        Pipe (Pipe pipe) {
            start = pipe.start;
            end = pipe.end;
            direction = pipe.direction;
        }

        void setPosition(Pos s, Pos e) {
            start = s;
            end = e;
        }

        boolean moveDown() {
            int x = end.x;
            int y = end.y;
            if (x + 1 >= N) return false;
            if (map[x + 1][y] == 1) return false;
            if (direction == Direction.HORIZONTAL) return false;

            start = end;
            end = new Pos(x + 1, y);
            direction = Direction.VERTICAL;
            return true;
        }

        boolean moveRight() {
            int x = end.x;
            int y = end.y;
            if (y + 1 >= N) return false;
            if (map[x][y + 1] == 1) return false;
            if (direction == Direction.VERTICAL) return false;

            start = end;
            end = new Pos(x, y + 1);
            direction = Direction.HORIZONTAL;
            return true;
        }

        boolean moveDiagonal() {
            int x = end.x;
            int y = end.y;
            if (y + 1 >= N || x + 1 >= N) return false;
            if (map[x + 1][y] == 1 || map[x][y + 1] == 1 || map[x + 1][y + 1] == 1) return false;

            start = end;
            end = new Pos(x + 1, y + 1);
            direction = Direction.DIAGONAL;
            return true;
        }
    }

    private static class Pos {
        int x;
        int y;

        Pos(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }
}
