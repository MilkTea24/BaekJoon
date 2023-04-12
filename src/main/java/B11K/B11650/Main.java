package B11K.B11650;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            Location[] locations = new Location[N];

            for (int i = 0; i < N; i++) {
                int[] XY = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
                locations[i] = new Location(XY[0], XY[1]);
            }

            Arrays.sort(locations);

            StringBuilder sb = new StringBuilder();
            for (Location l : locations) {
                sb.append(l).append('\n');
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static class Location implements Comparable<Location> {
        int x, y;
        Location(int _x, int _y) {
            x = _x;
            y = _y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        @Override
        public int compareTo(Location l1) {
            if  (this.x != l1.x) return (this.x - l1.x) * (1);
            else return (this.y - l1.y) * (1);
        }
    }
}
