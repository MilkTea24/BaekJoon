package B15K.B15663;

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

        pick(new LinkedList<>());

        StringBuilder result = results.stream().distinct().collect(Collectors.collectingAndThen(
                Collectors.joining("\n"),
                StringBuilder::new
        ));

        System.out.print(result);
    }

    private static void pick(LinkedList<Integer> pickedIndex) {
        if (pickedIndex.size() == M) {
            StringBuilder sb = new StringBuilder();
            for (int i : pickedIndex) {
                sb.append(inputs.get(i)).append(' ');
            }
            results.add(sb.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (pickedIndex.contains(i)) continue;

            pickedIndex.add(i);
            pick(pickedIndex);
            pickedIndex.removeLast();
        }

    }
}
