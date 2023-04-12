package B1K.B1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

//BFS 사용
public class BFS {
    static int[] distancesFromInput = new int[1000001];
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int input = Integer.parseInt(br.readLine());
            for (int i = 1; i < 1000001; i++) {
                distancesFromInput[i] = -1;
            }

            LinkedList<Integer> queue = new LinkedList<>();
            queue.addLast(input);
            distancesFromInput[input] = 0;

            while (!queue.isEmpty()) {
                int current = queue.removeFirst();

                addNextDestToQueue(current, queue);
                if (distancesFromInput[1] != -1) break;
            }
            System.out.println(distancesFromInput[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addNextDestToQueue(int in, LinkedList<Integer> q) {
        if (in % 2 == 0 && in/2 >= 1 && distancesFromInput[in/2] == -1) {
            q.addLast(in/2);
            distancesFromInput[in/2] = distancesFromInput[in] + 1;
        }
        if (in % 3 == 0 && in/3 >= 1 && distancesFromInput[in/3] == -1) {
            q.addLast(in/3);
            distancesFromInput[in/3] = distancesFromInput[in] + 1;
        }
        if (in-1 >= 1 && distancesFromInput[in-1] == -1) {
            q.addLast(in - 1);
            distancesFromInput[in-1] = distancesFromInput[in] + 1;
        }
    }
}
