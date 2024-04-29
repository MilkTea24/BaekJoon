package B16K.B16566;

/*
철수는 1~N개의 빨간색 카드 중 M개의 카드를 고른다. 이 때 철수는 낼 때 아무 카드를 마음대로 낼 수 있다.
민수는 철수가 고른 카드만 골라야 한다. 하지만 민수는 철수가 어떤 것을 낼 지 알고 있다.
민수는 철수가 낸 것보다 큰 카드 중 가장 작은 카드를 낸다.
민수는 항상 카드를 낼 수 있다.
둘 째 민수가 고른 카드(다 다름)
셋째 줄 철수가 낸 카드

먼저 정렬
2 3 4 5 7 8 9
4보다 큰 5 냄 -> 5를 없애야 한다
1보다 큰 2 냄 -> 2 없애기
TreeSet? -> 시간초과
unionfind -> 2를 선택하면 뒤의 3을 parent로
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];    K = line[2];

        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(line);
        
        UnionFind uf = new UnionFind();

        StringBuilder sb = new StringBuilder();
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //System.out.println(uf);
        for (int i = 0; i < K; i++) {
            int left = 0;
            int right = line.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (line[mid] <= input[i]) left = mid + 1;
                else right = mid;
            }

            int parent = uf.find(right);
            sb.append(line[parent]).append('\n');
            if (parent != M-1) uf.union(parent, parent + 1);
        }
        System.out.print(sb);

    }
    
    static class UnionFind {
        int[] parent;
        
        UnionFind() {
            parent = new int[M];

            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }
        
        int find(int i) {
            if (i == parent[i]) return i;
            
            return parent[i] = find(parent[i]);
        }
        
        void union(int a, int b) { //a가 낸 카드 b가 다음에 낼 카드
            int parentA = find(a);
            int parentB = find(b);
            
            if (parentA == parentB) return;
            
            parent[parentA] = parentB;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parent.length; i++) {
                sb.append(parent[i]).append(' ');
            }
            return sb.toString();
        }
    }
}
