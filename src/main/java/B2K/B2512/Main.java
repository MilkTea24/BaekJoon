package B2K.B2512;

import java.io.*;
import java.util.*;

/*
국가 예산은 정해져 있어 모든 예산 요청을 다 들어줄 수는 없다
모든 요청이 배정될 수 없는 경우 특정한 정수 상한액을 계산하여 그 이상인 예산 요청은 상한액까지만 준다.
상한액 이하의 예산은 그대로 준다.
이 때 상한액은 정해진 총액 이하에서 가능한 총 예산이 최대가 되도록 잡음
배정된 예산 중 최대값을 구하기
 */
public class Main {
    static int N;
    static int[] request;
    static int totalBound;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        request = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int maxValue = request[request.length - 1];
        totalBound = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(0, maxValue));
    }

    private static int binarySearch(int low, int high) {
        int maxValue = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            int currentMax = calculateTotalWithUpperBound(mid);
            if (currentMax > totalBound) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
                maxValue = Math.max(maxValue, currentMax);
            }
        }
        return checkMaxValue(low - 1);
    }

    private static int calculateTotalWithUpperBound(int upperBound) {
        int totalValue = 0;
        //System.out.print(upperBound + ": ");
        for (int i = 0; i < request.length; i++) {
            //System.out.print(request[i] > upperBound ? upperBound : request[i] + " ");
            if (request[i] > upperBound) totalValue += upperBound;
            else totalValue += request[i];
        }
        //System.out.println();
        return totalValue;
    }

    private static int checkMaxValue(int upperBound) {
        int maxValue = 0;
        for (int i = 0; i < request.length; i++) {
            if (request[i] > upperBound) return upperBound;
            maxValue = Math.max(request[i], maxValue);
        }
        return maxValue;
    }
}
