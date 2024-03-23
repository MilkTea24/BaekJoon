package B16K.B16236;

import java.io.*;
import java.util.*;

//물고기 M마리와 상어 1마리
//처음 아기 상어 크기는 2
//1초 상하좌우 이동
//자기보다 큰 물고기는 지나갈 수 없고 작은 물고기는 먹으면서 지나갈 수 있다.
//크기가 같으면 먹을 수 없고 지나갈 수 있다.
//거리가 가까운 물고기를 먹으러 간다 -> BFS
//크기가 M일 때 M마리 물고기를 먹으면 크기는 1 증가한다.
//더 이상 물고기를 먹을 수 없을 때까지 한다. 이 때까지 걸린 시간 출력
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        Position startPosition = null;

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 9) {
                    startPosition = new Position(i, j);
                    map[i][j] = 0;
                }
            }
        }

        Shark shark = new Shark();
        while (true) {
            startPosition = shark.findFish(startPosition);
        }
    }

    private static class Shark {
        int size;
        int currentExp;

        int playTime;

        static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public Shark() {
            size = 2;
            currentExp = 0;
            playTime = 0;
        }

        public Position findFish(Position p) {
            BFSPosition eatFish = BFS(p);

            if (Objects.isNull(eatFish)) gameOver();
            else getExp(eatFish);

            playTime += eatFish.time;

            //System.out.println(eatFish.x + " " + eatFish.y + ", time : " + eatFish.time + ", total time : " + playTime + ", size : " + size + ", exp : " + currentExp);

            return eatFish;
        }

        private BFSPosition BFS(Position p) {
            boolean[][] isVisited = new boolean[N][N];
            LinkedList<BFSPosition> queue = new LinkedList<>();

            isVisited[p.x][p.y] = true;
            queue.add(new BFSPosition(p.x, p.y, 0));

            BFSPosition eatFish = null;
            boolean findFishFlag = false;

            while (!queue.isEmpty()) {
                BFSPosition current = queue.removeFirst();

                //가장 가까운 물고기를 찾으면 그 이상의 거리는 탐색하지 않음
                if (!Objects.isNull(eatFish) && eatFish.time < current.time) continue;


                //먹을 물고기를 찾음
                if (map[current.x][current.y] != 0 && map[current.x][current.y] < size) {
                    //처음 찾은 물고기
                    if (Objects.isNull(eatFish)) eatFish = new BFSPosition(current.x, current.y, current.time);
                    else {
                        //거리가 동일한 최단거리 물고기가 있다면 가장 위에 있는 물고기를 먹는다.
                        if (eatFish.x > current.x) eatFish = new BFSPosition(current.x, current.y, current.time);
                            //위에 있는 물고기가 여러 마리라면 가장 왼쪽에 있는 물고기를 먹는다.
                        else if (eatFish.x == current.x && eatFish.y > current.y) eatFish = new BFSPosition(current.x, current.y, current.time);
                    }
                }

                for (int i = 0; i < 4; i++) {
                    int newI = current.x + direction[i][0];
                    int newJ = current.y + direction[i][1];

                    if (newI < 0 || newJ < 0|| newI >= N || newJ >= N) continue;
                    if (isVisited[newI][newJ]) continue;
                    //물고기가 본인 크기보다 크면 통과 못함
                    if (map[newI][newJ] > size) continue;

                    isVisited[newI][newJ] = true;
                    queue.add(new BFSPosition(newI, newJ, current.time + 1));
                }
            }

            return eatFish;
        }

        private void gameOver() {
            System.out.print(playTime);
            System.exit(0);
        }

        private void getExp(Position eatFish) {
            map[eatFish.x][eatFish.y] = 0;
            currentExp++;
            if (currentExp == size) {
                size++;
                currentExp = 0;
            }
        }
    }

    private static class Position {
        int x;
        int y;

        public Position(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    private static class BFSPosition extends Position {
        int time;
        public BFSPosition(int _x, int _y, int _t) {
            super(_x, _y);
            time = _t;
        }
    }
}
