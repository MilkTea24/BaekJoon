package B17K.B17143;

import java.io.*;
import java.util.*;

/*
3시 10분 시작
낚시왕 1번 열의 한칸 왼쪽
땅과 제일 가까운 상어를 잡은 후 상어는 이동함
상어의 속도가 주어지는데 격자판의 경계를 넘으면 방향을 바꿔서 이동
상어가 이동을 마친 후 같은 칸에 상어가 두마리 있을 때 둘 중 큰 상어만 살아남음
 */
public class NewMain {
    static int R, C, M;
    static List<Shark> sharks = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        R = line[0];
        C = line[1];
        M = line[2];

        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            sharks.add(new Shark(line[0] - 1, line[1] - 1, line[2], line[3], line[4]));
        }



        int fisherman = -1;
        int result = 0;

        while (fisherman < C) {
            fisherman += 1;

            //땅과 제일 가까운 상어 잡기
            int caughtShark = -1;

            for (int i = 0; i < sharks.size(); i++) {
                if (sharks.get(i).c == fisherman) {
                    if (caughtShark == -1) caughtShark = i;
                    else if (sharks.get(i).r < sharks.get(caughtShark).r) caughtShark = i;
                }
            }

            //잡을 수 있는 상어가 없는 경우
            if (caughtShark != -1) {
                result += sharks.get(caughtShark).size;
                sharks.remove(caughtShark);
            }

            //상어 이동하기
            List<Shark> removeShark = new ArrayList<>();
            if (!sharks.isEmpty()) sharks.get(0).move();
            for (int i = 1; i < sharks.size(); i++) {
                sharks.get(i).move();

                //두 상어가 같은 칸에 있는지 조사하고 먹힐 상어들의 인덱스 구하기
                for (int j = 0; j < i; j++) {
                    if ((sharks.get(i).r == sharks.get(j).r) && (sharks.get(i).c == sharks.get(j).c)) {
                        if (sharks.get(i).size > sharks.get(j).size) removeShark.add(sharks.get(j));
                        else removeShark.add(sharks.get(i));
                    }
                }
            }

            for (int i = 0; i < removeShark.size(); i++) {
                sharks.remove(removeShark.get(i));
            }
        }

        System.out.println(result);
    }

    private static class Shark {
        int r, c, velocity, direction, size;

        Shark(int _r, int _c, int v, int d, int s) {
            r = _r;
            c = _c;
            velocity = v;
            direction = d;
            size = s;
        }

        void move() {
            int remainDistance = velocity;

            while (true) {
                switch (direction) {
                    case 1 -> remainDistance = moveUp(remainDistance, r);
                    case 2 -> remainDistance = moveDown(remainDistance, r);
                    case 3 -> remainDistance = moveRight(remainDistance, c);
                    case 4 -> remainDistance = moveLeft(remainDistance, c);
                }


                if (remainDistance == 0) {
                    //System.out.println(size + " : " + r + " " + c);
                    break;
                }
            }
        }

        int moveUp(int remainDistance, int currentR) {
            if (remainDistance < currentR) {
                r = currentR - remainDistance;
                return 0;
            }
            else {
                r = 0;
                direction = 2;
                return remainDistance - currentR;
            }
        }

        int moveDown(int remainDistance, int currentR) {
            if (remainDistance < (R - 1) - currentR) {
                r = currentR + remainDistance;
                return 0;
            }
            else {
                r = R - 1;
                direction = 1;
                return remainDistance - ((R - 1) - currentR);
            }
        }

        int moveLeft(int remainDistance, int currentC) {
            if (remainDistance < currentC) {
                c = currentC - remainDistance;
                return 0;
            }
            else {
                c = 0;
                direction = 3;
                return remainDistance - currentC;
            }
        }

        int moveRight(int remainDistance, int currentC) {
            if (remainDistance < (C - 1) - currentC) {
                c = currentC + remainDistance;
                return 0;
            }
            else {
                c = C - 1;
                direction = 4;
                return remainDistance - ((C - 1) - currentC);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Shark) {
                Shark s = (Shark) o;
                return size == s.size;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(size);
        }
    }
}
