package B1K.B1717;

import java.io.*;
import java.util.*;

//초기에는 0~n 까지 n+1개의 집합이 있음
//합집합은 0 1 3의 형태로 주어짐(1이 포함되어있는 집합과 3이 포함되어 있는 집합 합치기)
//두 원소가 같은 집합에 포함되어있는지를 확인하는 연산은 1 1 7로 주어짐
public class Main {
    static int n;
    static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0];    m = line[1];

        UnionFind unionFind = new UnionFind(n+1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //합집합
            if (line[0] == 0) {
                unionFind.merge(line[1], line[2]);
            }
            //동일한 집합인지 확인
            else {
                String result = unionFind.find(line[1]) == unionFind.find(line[2]) ? "YES" : "NO";
                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        int find(int a) {
            if (parent[a] == a) return a;

            return parent[a] = find(parent[a]);
        }

        void merge(int a, int b) {
            a = find(a);    b = find(b);

            if (a == b) return;

            if (rank[a] > rank[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            parent[a] = b;

            if (rank[a] == rank[b]) rank[b]++;
        }
    }
}
