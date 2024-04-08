package B2K.B2342;

import java.util.*;
import java.io.*;

/*
10시 5분 시작 ~ 11시 9분 해결
처음에는 두 발을 0에 두고 시작
게임이 시작 하면 한 턴에 둘 중 하나의 발만 움직일 수 있다.
시작 후 두 발이 같은 지점에 있으면 안된다.
같은 곳 한번 더 -> 1의 힘 사용
중앙에서 다른 곳 -> 2의 힘 사용
다른 지점에서 인접 -> 3의 힘 사용
반대쪽 -> 4의 힘 사용

최대 10만개 입력(nlogn) 그리디 -> 안됨

2, 4에 있을 때 3이 들어오는 경우 2, 3 또는 3, 4가 되는 선택지가 있다.
1, 2에 있을 때 3 들어오는 경우 2, 3(4) / 1, 3(3) 그 다음 2가 들어오는 경우 2, 3 -> 2, 3(4 + 1), 1, 3 -> 1, 2(3 + 3)
 */
public class Main {
    static int[][] cost = {{1, 2, 2, 2, 2}, //0에서 시작
            {2, 1, 3, 4, 3},//1에서 시작
            {2, 3, 1, 3, 4},
            {2, 4, 3, 1, 3},
            {2, 3, 4, 3, 1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Map<Step, Integer>> dp = new ArrayList<>(); //왼발로 밟을 때 비용, 오른발로 밟을 때 비용 저장

        for (int i = 0; i < input.length; i++) {
            dp.add(new HashMap<>());
        }

        int index = 1;
        dp.get(0).put(new Step(input[0], 0), cost[0][input[0]]);

        while (input[index] != 0) {
            //이전 단계들 최소값 모두 가져오기
            for (Map.Entry<Step, Integer> current : dp.get(index - 1).entrySet()) {
                Step currentStep = current.getKey();
                int previousPower = current.getValue();

                //한 발 이동
                Step nextStep = new Step(input[index], currentStep.right);
                int power = previousPower + cost[currentStep.left][input[index]];

                if (dp.get(index).containsKey(nextStep)) {
                    dp.get(index).put(nextStep, Math.min(power, dp.get(index).get(nextStep)));
                }
                else dp.get(index).put(nextStep, power);

                //다른 발 이동
                Step anotherStep = new Step(currentStep.left, input[index]);
                int anotherPower = previousPower + cost[currentStep.right][input[index]];

                if (dp.get(index).containsKey(anotherStep)) {
                    dp.get(index).put(anotherStep, Math.min(anotherPower, dp.get(index).get(anotherStep)));
                }
                else dp.get(index).put(anotherStep, anotherPower);
            }

            index += 1;
        }

        /*
        for (int i = 0; i < dp.size(); i++) {
            for (Map.Entry e : dp.get(i).entrySet()) {
                System.out.print(e.getKey().toString() + "->"  + e.getValue() + "/ ");
            }
            System.out.println();
        }
         */

        Map<Step, Integer> resultMap = dp.get(index - 1);

        int result = Integer.MAX_VALUE;
        for (Map.Entry e : resultMap.entrySet()) {
            result = Math.min((int)e.getValue(), result);
        }

        System.out.println(result);
    }

    static class Step {
        int left;
        int right;


        Step(int l, int r) {
            left = l;
            right = r;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Step) {
                Step s = (Step) o;
                //(2, 3), (3, 2) 동일함
                return (s.left == left && s.right == right) || (s.left == right && s.right == left);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }

        @Override
        public String toString() {
            return "(" + left + ", " + right + ")";
        }
    }
}
