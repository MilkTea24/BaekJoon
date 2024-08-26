package B32K.B32032;

import java.io.*;
import java.util.*;

/*
매일 D 걷기 성공하면 포인트 얻음
걷기 대행해줌 1m 간격으로 보관함을 설치
사옥의 보관함 번호 0 왼쪽 -A 오른쪽 +A
Xi 보관함에서 휴대폰을 집고 D 이상 움직인 다음 Xi 보관함에 반납해야 한다
여러 휴대폰을 동시에 집을 수 있다
N 1000000개 N log N 써야 한다
휴대폰 위치는 겹칠 수 있고 위치가 0일 수 있다
 */
public class Main {
    static int N, D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];    D = line[1];
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxValue = Arrays.stream(line).max().orElse(0);
        int minValue = Arrays.stream(line).min().orElse(0);

        int result = 0;
        if (minValue < 0 && maxValue > 0 && (maxValue - minValue) * 2 <= D) {
            result = Math.abs(minValue) * 2 + maxValue * 2 + D;
        }
        else {
            result = Integer.MAX_VALUE;
        }

        int newResult = 0;

        if (maxValue > 0) {
            newResult = maxValue * 2 + D;
        }
        if (minValue < 0) {
            newResult = newResult + Math.abs(minValue) * 2 + D;
        }
        if (maxValue == 0 && minValue == 0) {
            newResult = D;
        }

        System.out.println(Math.min(result, newResult));
    }
}
