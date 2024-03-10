package B16K.B16953;

import java.util.*;
public class Main {
    static Set<Integer> isVisited = new TreeSet<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if (a == b) {
            System.out.print(2);
            return;
        }

        LinkedList<Num> queue = new LinkedList<>();
        queue.add(new Num(a, 1));

        int dist = -1;
        while (!queue.isEmpty()) {
            Num current = queue.removeFirst();

            if (current.pos == b) {
                dist = current.dist;
                break;
            }

            if (current.pos * 2 <= b && !isVisited.contains(current.pos * 2)) {
                isVisited.add(current.pos * 2);
                queue.add(new Num(current.pos * 2, current.dist + 1));
            }
            int addOne = addOne(current.pos);
            if (addOne != -1 && addOne <= b && !isVisited.contains(addOne)) {
                isVisited.add(addOne);
                queue.add(new Num(addOne, current.dist + 1));
            }
        }

        System.out.print(dist);

    }

    private static int addOne(int pos) {
        if (pos > (Integer.MAX_VALUE - 1) / 10) return -1;
        return pos * 10 + 1;
    }

    private static class Num {
        int pos;
        int dist;

        public Num(int p, int d) {
            pos = p;
            dist = d;
        }
    }
}
