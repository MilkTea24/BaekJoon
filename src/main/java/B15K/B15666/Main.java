package B15K.B15666;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int M;
    static List<Integer> inputs = new ArrayList<>();
    static List<String> results = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            inputs.add(scanner.nextInt());
        }

        Collections.sort(inputs);

        pick(0, new LinkedList<>());

        String result = results.stream().distinct().collect(Collectors.joining("\n"));
        System.out.print(result);
    }

    private static void pick(int startIndex, LinkedList<Integer> pickedIndex) {
        if (pickedIndex.size() == M) {
            StringBuilder sb = new StringBuilder();
            for (int i : pickedIndex) {
                sb.append(inputs.get(i)).append(' ');
            }
            results.add(sb.toString());
            return;
        }

        for (int i = startIndex; i < N; i++) {
            pickedIndex.add(i);
            pick(i, pickedIndex);
            pickedIndex.removeLast();
        }
    }
}
