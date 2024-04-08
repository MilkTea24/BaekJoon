package B1K.B1202;

import java.util.*;
import java.io.*;

/*
보석은 무게 M과 가격 V
가방은 K개이고 각 가방에 담을 수 있는 최대 무게는 C
그런데 가방에는 최대 한 개의 보석만 넣을 수 있음
기존 가방 문제와 달리 뽑을 수 있는 보석의 개수는 K개로 정해져 있음
그리디? 제일 작은 가방부터 최대 가치 보석을 담는다.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = line[0];
        int K = line[1];

        List<Gem> gems = new ArrayList<>();
        List<Bag> bags = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            gems.add(new Gem(line[0], line[1]));
        }

        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(br.readLine());
            bags.add(new Bag(c));
        }

        Collections.sort(bags);
        Collections.sort(gems);

        long result = 0;
        int gemIndex = 0;

        //제한된 무게 안에서 가장 가치가 큰 보석을 찾고 싶다.
        //제한된 무게 안인 보석들을 다 priority queue에 담은 다음 하나만 빼면 된다.
        //제한된 무게가 증가할 때마다 priority queue에 추가로 담으면 된다.
        PriorityQueue<Gem> availableGems = new PriorityQueue<>((a, b) -> {
            if (a.V != b.V) return b.V - a.V;
            else return a.M - b.M;
        });
        for (int i = 0; i < bags.size(); i++) {
            while (gemIndex < gems.size()) {
                if (gems.get(gemIndex).M > bags.get(i).C) break;

                availableGems.add(gems.get(gemIndex));
                gemIndex++;
            }

            if (availableGems.isEmpty()) continue;

            Gem gem = availableGems.poll();
            result += gem.V;
        }

        System.out.print(result);
    }

    private static class Gem implements Comparable<Gem> {
        int M;
        int V;

        Gem(int _M, int _V) {
            M = _M;
            V = _V;
        }

        @Override
        public int compareTo(Gem g) {
            if (this.M != g.M) return this.M - g.M;
            return g.V - this.V;
        }
    }

    private static class Bag implements Comparable<Bag> {
        int C;

        Bag(int _C) {
            C = _C;
        }

        @Override
        public int compareTo(Bag b) {
            return this.C - b.C;
        }
    }
}
