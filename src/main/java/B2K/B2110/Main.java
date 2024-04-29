package B2K.B2110;

/*
11시 58분 시작
집 N개가 수직선 위에 있다
공유기 C개
한 집에 공유기 하나해서 공유기 사이의 거리를 최대한 크게
공유기 거리를 1 -> 2 -> 3 늘리기
 */

import java.io.*;
import java.util.*;
public class Main {
    static int N, C;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);

        List<Integer> houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            houses.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(houses);

        System.out.println(findDist(houses));
    }


    static int findDist(List<Integer> houses) {
        int low = 0;
        int high = houses.get(houses.size() - 1);

        while (low <= high) {
            int mid = (low + high) / 2;
            //System.out.println(mid);
            //설치할 수 있으면 low를 높이기
            if (canInstall(mid, houses)) low = mid + 1;
            //설치할 수 없으면 high를 낮추기
            else high = mid - 1;
        }

        return low - 1;
    }

    static private boolean canInstall(int mid, List<Integer> houses) {
        int count = 1;
        int beforeIndex = 0;
        int index = 1;

        while (count < C && index < houses.size()) {
            if (houses.get(index) - houses.get(beforeIndex) >= mid) {
                count += 1;
                beforeIndex = index;

                if (count == C) return true;
            }
            index += 1;
        }

        return false;
    }
}
