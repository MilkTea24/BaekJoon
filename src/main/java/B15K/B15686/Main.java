package B15K.B15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//치킨 거리 x 좌표 절대값 + y 좌표 절대값
//치킨집이 두개 이상 -> 이 집에서 어느 치킨집이 제일 가까운지 알아야 한다.
public class Main {
    static int[][] map;
    static int[][] chickenPos;
    static int N;
    static int M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];    M = line[1];

        chickenPos = new int[13][2];
        map = new int[N][N];

        int chickenIndex = 0;
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) {
                    chickenPos[chickenIndex][0] = i;
                    chickenPos[chickenIndex][1] = j;
                    chickenIndex++;
                }
            }
        }

        int result = pickRemainChicken(chickenIndex, 0, new LinkedList<>());

        System.out.print(result);
    }

    private static int pickRemainChicken(int chickenCount, int startIndex, LinkedList<Integer> pickedIndex) {
        if (pickedIndex.size() == M) {
            int result = calculateChickenDistance(pickedIndex);
            return result;
        }

        if (startIndex >= chickenCount) return Integer.MAX_VALUE;

        int result = Integer.MAX_VALUE;
        for (int i = startIndex; i < chickenCount; i++) {
            pickedIndex.add(i);
            result = Math.min(result, pickRemainChicken(chickenCount, i + 1, pickedIndex));
            pickedIndex.removeLast();
        }

        return result;
    }

    private static int calculateChickenDistance(LinkedList<Integer> pickedIndex) {
        int[][] distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 1) continue;
                for (int k = 0; k < M; k++) {
                    distance[i][j] = Math.min(distance[i][j],
                            Math.abs(chickenPos[pickedIndex.get(k)][0] - i) + Math.abs(chickenPos[pickedIndex.get(k)][1] - j));
                }
            }
        }

        return Arrays.stream(distance).flatMapToInt(Arrays::stream).filter(i -> i != Integer.MAX_VALUE).sum();
    }
}
