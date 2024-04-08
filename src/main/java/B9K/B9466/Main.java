package B9K.B9466;

import java.util.*;
import java.io.*;

/*
11시 4분 시작
프로젝트 팀원 수는 제한이 없다
학생은 프로젝트를 하고 싶은 학생을 선택해야 함(자기 자신도 가능)
서로 연결되어 있어아먄 한팀 그런데 방향성이 있음
사이클이 형성되어야만 한팀 parent가 4를 가르켜야 한다
어느 프로젝트 팀에도 속하지 않는 -> 혼자서 팀 하는 경우와 구분 해야 함
유니온 파인드
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s) - 1).toArray();

        UnionFind unionFind = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            unionFind.union(i, input[i]);
        }

        boolean[] isGroup = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isGroup[i]) continue;

            if (input[i] == i) {
                isGroup[i] = true;
                continue;
            }

            //System.out.println("i : " + i);
            int parent = unionFind.find(i);
            if (input[parent] == i) {
                //System.out.println("parent: " + parent + " , i: " + i);
                int check = i;
                int originalI = i;
                //int count = 0;
                while (true) {
                    //System.out.println(check);
                    isGroup[check] = true;

                    check = input[check];

                    //if (count++ == 10) break;

                    if (check == originalI) break;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!isGroup[i]) result += 1;
        }

        System.out.println(result);
    }

    static class UnionFind {
        int[] parent;
        int n;

        UnionFind (int _n) {
            n = _n;
            parent = new int[_n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            if (i == parent[i]) return i;

            return parent[i] = find(parent[i]);
        }

        /*
        1 2 3
        3 1 2
         */
        void union(int a, int b) {
            int findA = find(a);
            int findB = find(b);

            if (findA == findB) return;

            parent[findA] = findB;
        }

    }
}
