package B15K.B15654;

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

        pick(new LinkedList<>());

        System.out.print(sb);
    }

    private static void pick(LinkedList<Integer> picked) {
        if (picked.size() == M) {
            for (int i : picked) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < inputs.size(); i++) {
            if (picked.contains(inputs.get(i))) continue;
            picked.addLast(inputs.get(i));
            pick(picked);
            picked.removeLast();
        }
    }
}
