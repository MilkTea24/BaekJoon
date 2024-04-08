package B10K.B10775;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        UnionFind uf = new UnionFind(N);

        int result = 0;
        for (int i = 0; i < M; i++) {
            int gate = Integer.parseInt(br.readLine());

            int parent = uf.find(gate);

            if (parent == 0) break;

            uf.union(parent - 1, parent);
            result += 1;

            /*
            for (int j = 0; j < uf.parent.length; j++) {
                System.out.print(uf.parent[j] + " ");
            }
            System.out.println();
             */
        }

        System.out.println(result);
    }

    static class UnionFind {
        int[] parent;
        int n;

        UnionFind(int _n) {
            n = _n + 1;
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            if (i == parent[i]) return i;

            i = parent[i];
            return parent[i] = find(i);
        }

        void union(int a, int b) {
            int findA = find(a);
            int findB = find(b);

            if (findA == findB) return;

            parent[findB] = findA;

        }
    }
}
