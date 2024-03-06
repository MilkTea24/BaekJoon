package B16K.B16928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] distance = new int[100];
    static Map<Integer, Integer> node = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        for (int i = 0; i < N + M; i++) {
            line = br.readLine().split(" ");
            node.put(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1);
        }

        for (int i = 0; i < 100; i++) {
            distance[i] = -1;
        }

        System.out.println(BFS());
    }

    static int BFS() {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        distance[0] = 0;

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();

            //그냥 주사위 굴리기
            if (current + 6 >= 99) return (distance[current] + 1);

            boolean[] flags = new boolean[6];
            for (int i = current + 1; i <= current + 6; i++) {
                if (node.containsKey(i) && distance[node.get(i)] == -1) {
                    distance[node.get(i)] = distance[current] + 1;
                    queue.addLast(node.get(i));
                }
                else if (!node.containsKey(i)) flags[i - current - 1] = true;
            }

            for (int i = 5; i >= 0; i--) {
                if (flags[i] && distance[current + i + 1] == -1) {
                    distance[current + i + 1] = distance[current] + 1;
                    queue.addLast(current + i + 1);
                    break;
                }
            }
        }
        return -1;
    }
}
