package B15K.B15650;

import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        pick(N, M, new LinkedList<>());

        System.out.print(sb);
    }

    private static void pick(int N, int M, LinkedList<Integer> picked) {
        if (picked.size() == M) {
            for (int i : picked) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
            return;
        }

        int startIndex = picked.size() != 0 ? picked.getLast() : 0;
        for (int i = startIndex + 1; i <= N; i++) {
            picked.add(i);
            pick(N, M, picked);
            picked.removeLast();
        }
    }
}
