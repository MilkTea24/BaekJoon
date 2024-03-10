package B15K.B15652;

import java.util.*;

public class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

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

        int startIndex = picked.size() != 0 ? picked.getLast() : 1;
        for (int i = startIndex; i <= N; i++) {
            picked.addLast(i);
            pick(picked);
            picked.removeLast();
        }
    }
}
