package B1K.B1107;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static List<Integer> buttons;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        buttons = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList());
        int target = scanner.nextInt();
        int M = scanner.nextInt();
        for (int i = 0; i < M; i++) {
            int broken = scanner.nextInt();
            buttons.remove((Integer) broken);
        }

        int current = 100;

        int count = goToTarget(current, target);

        System.out.println(count);
    }

    static int goToTarget(int current, int target){
        //이미 현재 채널이 원하는 채널일 경우 바로 리턴
        if (current == target) return 0;

        //숫자 버튼이 다 부러지면 그냥 +, -만 누르기
        if (buttons.isEmpty()) return Math.abs(target-current);

        //숫자 버튼이 남아있으면 원하는 채널과 가장 가까운 채널로 이동하기
        //또 +, -만 눌러서 뭐가 더 적게 버튼을 누르는지 비교하기
        int[] targetChannelNumbers = Arrays.stream(Integer.toString(target).split("")).mapToInt(s -> Integer.parseInt(s)).toArray();
        int pressNumber = 0;
        int closest = combineButtonsToClosestTarget(targetChannelNumbers.length, new LinkedList<>(), target);
        pressNumber = targetChannelNumbers.length + Math.abs(target - closest);

        if (targetChannelNumbers.length >= 2) {
            closest = combineButtonsToClosestTarget(targetChannelNumbers.length - 1, new LinkedList<>(), target);
            pressNumber = Integer.min(pressNumber, targetChannelNumbers.length - 1 + Math.abs(target - closest));
        }

        closest = combineButtonsToClosestTarget(targetChannelNumbers.length + 1, new LinkedList<>(), target);
        pressNumber = Integer.min(pressNumber, targetChannelNumbers.length + 1 + Math.abs(target - closest));


        int pressPlusOrMinus = Math.abs(target - current);

        return Integer.min(pressNumber, pressPlusOrMinus);
    }

    static int combineButtonsToClosestTarget(int N, LinkedList<Integer> picked, int target) {
        int closest = 100;
        if (N == picked.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i : picked) sb.append(i);
            int result = Integer.parseInt(sb.toString());
            return result;
        }

        for (int i = 0; i < buttons.size(); i++) {
            picked.addLast(buttons.get(i));
            int temp = combineButtonsToClosestTarget(N, picked, target);
            if (temp == target) return temp;
            if (Math.abs(target - temp) < Math.abs(target - closest)) closest = temp;
            picked.removeLast();
        }

        return closest;
    }
}
