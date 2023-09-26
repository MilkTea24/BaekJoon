package B7K.B7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    static int M;
    static int N;
    static int[][] box;
    static final int[][] direction = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int[] MN = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            MN[0] = M;  MN[1] = N;

            box = new int[N][M];
            for (int i = 0; i < N; i++) {
                int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                box[i] = line;
            }

            int result = BFS();


            boolean isAllMatured = true;
            label : for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++){
                    if (box[i][j] == 0) {
                        isAllMatured = false;
                        break label;
                    }
                }
            }

            if (!isAllMatured) {
                System.out.println(-1);
            }
            else System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int BFS() {
        int maxMaturedTime = 0;
        LinkedList<Tomato> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (box[i][j] == 1) {
                    queue.addLast(new Tomato(i, j, 0));
                }
            }
        }
        while (!queue.isEmpty()) {
            Tomato popTomato = queue.removeFirst();
            maxMaturedTime = Integer.max(popTomato.maturedAt, maxMaturedTime);
            for (int i = 0; i < 4; i++) {
                int a = popTomato.i + direction[i][0];
                int b = popTomato.j + direction[i][1];

                if (a >= 0 && a < N && b >= 0 && b < M && box[a][b] == 0) {
                    queue.addLast(new Tomato(a, b, popTomato.maturedAt + 1));
                    box[a][b] = 1;
                }
            }
        }

        return maxMaturedTime;
    }


    static class Tomato {
        public int i;
        public int j;
        public int maturedAt;

        public Tomato(int i, int j, int maturedAt){
            this.i = i;
            this.j = j;
            this.maturedAt = maturedAt;
        }
    }
}
