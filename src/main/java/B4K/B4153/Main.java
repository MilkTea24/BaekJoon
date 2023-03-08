package B4K.B4153;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                int[] lengths = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(string -> Integer.parseInt(string))
                        .sorted()
                        .toArray();
                if (lengths[0] + lengths[1] + lengths[2] == 0) {
                    System.exit(0);
                }
                if (lengths[0] * lengths[0] + lengths[1] * lengths[1] == lengths[2] * lengths[2])
                    System.out.println("right");
                else System.out.println("wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
