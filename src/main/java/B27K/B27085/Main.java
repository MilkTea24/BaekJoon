package B27K.B27085;

import java.util.*;
import java.io.*;


/*
일렬로 배열된 N 개의 방
리프는 초기에 s번째 방
초기 방 제외 모든 방에는 몬스터가 존재
각 몬스터마다 받을 수 있는 점수 존재 -> -도 있음
3개의 선택 -> 앞으로 가기, 뒤로 가기, 탈출하기
점수가 0 미만이 되면 게임오버
게임 오버가 일어나지 않게 점수의 최대값을 구하기
 */
public class Main {
    static int N, s;
    static int[] map;
    static Set<Pos> isVisited = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    s = line[1] - 1;

        map = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();



        long result = BFS();
        System.out.println(result);
    }

    private static long BFS() {
        long maxScore = 0;

        Queue<Player> queue = new LinkedList<>();
        queue.add(new Player(0, s, s));

        while (!queue.isEmpty()) {
            Player current = queue.poll();

            Player caseLeft = current.goLeft();
            if (!Objects.isNull(caseLeft)) {
                maxScore = Math.max(maxScore, caseLeft.score);
                queue.add(caseLeft);
            }

            Player caseRight = current.goRight();
            if (!Objects.isNull(caseRight)) {
                maxScore = Math.max(maxScore, caseRight.score);
                queue.add(caseRight);
            }
        }
        return maxScore;
    }

    static class Player {
        long score;

        int leftInd;
        int rightInd;

        Player(long sc, int left, int right) {
            score = sc;
            leftInd = left;
            rightInd = right;
        }

        public Player goLeft() {
            if (leftInd - 1 < 0) return null;

            if (score + map[leftInd - 1] < 0) return null;

            Pos pos = new Pos(leftInd - 1, rightInd);
            if (isVisited.contains(pos)) return null;

            isVisited.add(pos);
            return new Player(score + map[leftInd - 1], leftInd - 1, rightInd);
        }

        public Player goRight() {
            if (rightInd + 1 >= N) return null;

            //System.out.println(leftInd + " ~ " + (rightInd + 1) + ": " + (score + map[rightInd + 1]));
            if (score + map[rightInd + 1] < 0) return null;

            Pos pos = new Pos(leftInd, rightInd + 1);
            if (isVisited.contains(pos)) return null;

            isVisited.add(pos);
            return new Player(score + map[rightInd + 1], leftInd, rightInd + 1);
        }
    }

    static class Pos {
        int left;
        int right;

        Pos(int l, int r) {
            left = l;
            right = r;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Pos) {
                Pos p = (Pos) o;
                return left == p.left && right == p.right;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }


}
