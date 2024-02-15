package B4K.B4195;

import java.io.*;
import java.util.*;

//새로운 친구 관계를 입력받는다.
//새로운 친구 관계가 생길 때 마다 이 두 친구들의 친구 네트워크에 몇 명이 있는가? 친구의 친구 가능
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    private static void oneCase() throws Exception {
        int m = Integer.parseInt(br.readLine());
        UnionFind uf = new UnionFind();

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            uf.merge(line[0], line[1]);
        }

        uf.printAll();
    }

    private static class UnionFind {
        private List<Integer> parent = new ArrayList<>();
        private List<Integer> rank = new ArrayList<>();
        private List<Integer> rootNodeGroupNumber = new ArrayList<>();

        private Map<String, Integer> nameIndexInfo = new HashMap<>();

        private int userCount = 0;

        private StringBuilder output = new StringBuilder();

        private int find(String name) {
            if (!nameIndexInfo.containsKey(name)) {
                nameIndexInfo.put(name, userCount);
                parent.add(userCount);
                rank.add(1);
                rootNodeGroupNumber.add(1);
                userCount++;
            }

            int index = nameIndexInfo.get(name);
            return find(index);
        }

        private int find(int index) {
            if (index == parent.get(index)) return index;

            parent.set(index, find(parent.get(index)));

            return parent.get(index);
        }

        public void merge(String a, String b) {
            int findA = find(a);    int findB = find(b);

            if (findA == findB) {
                output.append(rootNodeGroupNumber.get(findA)).append('\n');
                return;
            }


            if (rank.get(findA) > rank.get(findB)) {
                int temp = findA;
                findA = findB;
                findB = temp;
            }

            parent.set(findA, findB);
            int totalGroupNumber = rootNodeGroupNumber.get(findA) + rootNodeGroupNumber.get(findB);
            output.append(totalGroupNumber).append('\n');
            rootNodeGroupNumber.set(findA, totalGroupNumber);
            rootNodeGroupNumber.set(findB, totalGroupNumber);

            if (rank.get(findA).equals(rank.get(findB))) rank.set(findB, rank.get(findB) + 1);
        }

        public void printAll() {
            System.out.print(output);
        }
    }
}
