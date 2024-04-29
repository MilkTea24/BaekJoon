package B25K.B25971;

import java.io.*;
import java.util.*;

/*
11시 46분 시작
N일 중 관측일의 태풍 위치를 알고 있음
하루에 x축 또는 y축 방향으로 1씩 움직임
관측일 사이에는 방향을 바꾸지 않음
진행 방향의 왼쪽은 안전, 진행 방향의 오른쪽은 위험
관측일은 K개 주어지고 사람의 일과 좌표는 Q개 주어짐
 */
public class Main {
    static int N, K, R, Q;
    static int[][] typhoonPos;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];    K = line[1];    R = line[2];    Q = line[3];

        typhoonPos = new int[K][3];

        for (int i = 0; i < K; i++) {
            typhoonPos[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < Q; i++) {
            int[] personPos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int lowerBound = findLowerBound(personPos[0]);
            System.out.println(lowerBound);
        }


    }

    private static int findLowerBound(int target) {
        int low = 0;
        int high = K - 1;

        while (low <= high) {
            int mid = (low + high)/ 2;
            if (typhoonPos[mid][0] < target) low = mid + 1;
            else high = mid - 1;
        }

        return low;
    }
}
