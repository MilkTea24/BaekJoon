package B16K.B16724;

import java.util.*;
import java.io.*;

/*
11시 시작
피리를 불면 U/D/L/R 로 이동
모든 사람이 Safe Zone으로 이동할 수 있도록
완전 탐색하여 그룹의 수를 세기 -> 시작점을 찾아야한다.
유니온 파인드?
 */
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        N = Integer.parseInt(line.split(" ")[0]);
        M = Integer.parseInt(line.split(" ")[1]);

        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        UnionFind uf = new UnionFind();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int newI = i;
                int newJ = j;
                switch(map[i][j]) {
                    case 'D' -> newI += 1;
                    case 'U' -> newI -= 1;
                    case 'L' -> newJ -= 1;
                    case 'R' -> newJ += 1;
                }

                uf.union(newI, newJ, i, j);
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(uf.parent[i][j] + " ");
            }
            System.out.println();
        }
         */

        Set<Integer> group = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                group.add(uf.find(i,j));
            }
        }

        System.out.println(group.size());
    }

    static class UnionFind {
        int[][] parent; //1000 * i + j

        UnionFind() {
            parent = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    parent[i][j] = 1000 * i + j;
                }
            }
        }

        int find(int i, int j) {
            if (parent[i][j] == i * 1000 + j) return parent[i][j];

            int parentI = parent[i][j] / 1000;
            int parentJ = parent[i][j] % 1000;

            return parent[i][j] = find(parentI, parentJ);
        }

        void union(int i1, int j1, int i2, int j2) { //2 -> 1이 진행방향 1이 부모가 되어야 함
            int parent1 = find(i1, j1);
            int parent2 = find(i2, j2);

            if (parent1 == parent2) return;

            int parent2I = parent2 / 1000;
            int parent2J = parent2 % 1000;

            parent[parent2I][parent2J] = parent1;
        }



    }

    class Pos {
        int i;
        int j;

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pos) {
                Pos p = (Pos) o;
                return i == p.i && j == p.j;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
