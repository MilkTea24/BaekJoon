package B11K.B11723;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean[] set = new boolean[20]; //1~20 -> 0~19

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            StringBuilder sb = new StringBuilder();
            int number = 0;
            if (line.length == 2) number = Integer.parseInt(line[1]);
                    if (line[0].equals("add")) {
                        set[number-1] = true;
                    }
                    if (line[0].equals("remove")) {
                        set[number-1] = false;
                    }
                    if (line[0].equals("check")) {
                        sb.append(set[number-1] ? 1 : 0).append("\n");
                    }
                    if (line[0].equals("toggle")) {
                        set[number-1] = !set[number-1];
                    }
                    if (line[0].equals("all")) {
                        for (int j = 0; j < 20; j++) {
                            set[j] = true;
                        }
                    }
                    if (line[0].equals("empty")) {
                        set = new boolean[20];
                    }
        }
        System.out.print(br);
    }
}
