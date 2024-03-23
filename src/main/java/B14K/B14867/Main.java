package B14K.B14867;

import java.util.*;

//물통 A와 B
//BFS
public class Main {
    static Set<TwoBottle> isVisited = new HashSet<>();
    static int a, b, c, d;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<TwoBottle> queue = new LinkedList<>();

        isVisited.add(new TwoBottle(0, 0, 0));
        queue.add(new TwoBottle(0, 0, 0));

        while (!queue.isEmpty()) {
            TwoBottle current = queue.poll();
            //System.out.println(current.count + ": " + current.x + " " + current.y);

            if (current.x == c && current.y == d) return current.count;

            //1번
            TwoBottle first = current.fillA();
            if (!isVisited.contains(first) && current.x != a) {
                isVisited.add(first);
                queue.add(first);
            }

            TwoBottle second = current.fillB();
            if (!isVisited.contains(second) && current.y != b) {
                isVisited.add(second);
                queue.add(second);
            }

            TwoBottle third = current.emptyA();
            if (!isVisited.contains(third) && current.x != 0) {
                isVisited.add(third);
                queue.add(third);
            }

            TwoBottle fourth = current.emptyB();
            if (!isVisited.contains(fourth) && current.y != 0) {
                isVisited.add(fourth);
                queue.add(fourth);
            }
            TwoBottle fifth = current.moveAtoB();
            if (!isVisited.contains(fifth) && current.y != b) {
                isVisited.add(fifth);
                queue.add(fifth);
            }
            TwoBottle sixth = current.moveBtoA();
            if (!isVisited.contains(sixth) && current.x != a) {
                isVisited.add(sixth);
                queue.add(sixth);
            }
        }

        return -1;
    }

    static class TwoBottle {
        int x;
        int y;

        int count;

        TwoBottle(int _x, int _y, int _c) {
            x = _x;
            y = _y;
            count = _c;
        }

        TwoBottle fillA() {
            return new TwoBottle(a, y, count + 1);
        }
        TwoBottle fillB() {
            return new TwoBottle(x, b, count + 1);
        }
        TwoBottle emptyA() {
            return new TwoBottle(0, y, count+ 1);
        }

        TwoBottle emptyB() {
            return new TwoBottle(x, 0, count + 1);
        }

        TwoBottle moveAtoB() {
            if (x <= (b-y)) return new TwoBottle(0, x + y, count + 1);
            else return new TwoBottle(x - (b - y), b, count + 1);

        }

        TwoBottle moveBtoA() {
            if (y <= (a-x)) return new TwoBottle(x+y, 0, count + 1);
            else return new TwoBottle(a, y - (a - x), count + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof TwoBottle) {
                TwoBottle tb = (TwoBottle) o;
                return (x == tb.x) && (y == tb.y);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
