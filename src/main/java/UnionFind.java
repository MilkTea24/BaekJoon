public class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    int find(int n) {
        if (n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }

    void merge(int a, int b) {
        a = find(a);    b = find(b);
        if (a == b) return;
        if (rank[a] > rank[b]) {
            //swap
            int temp = a;
            a = b;
            b = temp;
        }
        parent[a] = b;
        if (rank[a] == rank[b]) ++rank[b];
    }
}
