package B17K.B17839;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> objects = new HashMap<>();

        String[][] commands = new String[n][3];

        int totalSize = 0;
        int babaInd = -1;
        for (int i = 0; i < n; i++) {
            commands[i] = br.readLine().split(" ");
            if (!objects.containsKey(commands[i][0])) {
                if (commands[i][0].equals("Baba")) babaInd = totalSize;
                objects.put(commands[i][0], totalSize);
                totalSize = totalSize + 1;
            }
            if (!objects.containsKey(commands[i][2])) {
                if (commands[i][2].equals("Baba")) babaInd = totalSize;
                objects.put(commands[i][2], totalSize);
                totalSize = totalSize + 1;
            }
        }

        if (babaInd == -1) return;

        UnionFind uf = new UnionFind(totalSize, babaInd);

        for (int i = 0; i < n; i++) {
            uf.union(objects.get(commands[i][0]), objects.get(commands[i][2]));
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : objects.entrySet()) {
            if (entry.getKey().equals("Baba")) continue;
            if (uf.find(entry.getValue()) == babaInd) result.add(entry.getKey());
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i)).append('\n');
        }

        System.out.println(sb);

    }

    static class UnionFind {
        int[] parent;

        int babaInd;

        int size;

        UnionFind(int size, int babaInd) {
            parent = new int[size];
            this.babaInd = babaInd;

            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int ind) {
            if (parent[ind] == babaInd) return babaInd;
            if (parent[ind] == ind) return ind;
            return parent[ind] = find(parent[ind]);
        }

        void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);

            //System.out.println(parentA + " " + parentB);

            if (parentB == babaInd) return;
            if (parentA == parentB) return;

            parent[parentB] = parentA;
        }
    }
}
