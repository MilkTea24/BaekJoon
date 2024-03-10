package B15K.B15657;

import java.util.*;

public class Main {
    static int N;
    static int M;

    static List<Integer> inputs = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            inputs.add(scanner.nextInt());
        }

        Collections.sort(inputs);

        pick(0, new LinkedList<>());

        System.out.print(sb);
    }

    private static void pick(int startIndex, LinkedList<Integer> picked) {
        if (picked.size() == M) {
            for (int i : picked) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        if (startIndex >= inputs.size()) return;

        for (int i = startIndex; i < inputs.size(); i++) {
            picked.add(inputs.get(i));
            pick(i, picked);
            picked.removeLast();
        }
    }
}
