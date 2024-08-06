package B16K.B16201;

import java.util.*;
import java.io.*;

public class Main {
    static int R,C,K;
    static final long DIV = 1000000007;
    public static void main(String[] args) throws Exception {
        List<Pos> blocks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        R = Integer.parseInt(line[0]);
        C = Integer.parseInt(line[1]);
        K = Integer.parseInt(line[2]);

        for (int i = 0; i < K; i++) {
            line = br.readLine().split(" ");
            blocks.add(new Pos(Integer.parseInt(line[0]) - 1, Integer.parseInt(line[1]) - 1));
        }

        Collections.sort(blocks);

        long tileNum = 0;
        long result = 1;

        if (blocks.isEmpty()) {
            tileNum = (long) C / 2 * R;
            if (C % 2 != 0) result = result * pow(C / 2 + 1 % DIV ,R) % DIV;
            System.out.println(tileNum + " " + result);
            return;
        }

        Pos p = blocks.get(0);

        if (p.a > 0) {
            tileNum = tileNum + (long) C / 2 * p.a;
            if (C % 2 != 0) result = result * pow((C / 2 + 1) % DIV, p.a) % DIV;
        }

        tileNum = tileNum + p.b / 2;
        if (p.b % 2 != 0) result = result * (p.b / 2 + 1) % DIV;

        int previousA = p.a;
        int previousB = p.b;

        //System.out.println("before first block");
        //System.out.println("tile Num : " + tileNum + ", " + "result : " + result);

        for (int i = 1; i < K; i++) {
            p = blocks.get(i);
            if (p.a > previousA) {
                tileNum = tileNum + (long) (C - previousB - 1) / 2;
                if ((C - previousB - 1) % 2 != 0) result = result * ((C - previousB - 1) / 2 + 1) % DIV;
                previousA = previousA + 1;
                previousB = -1;
            }
            if (p.a > previousA) {
                tileNum = tileNum + (long) C / 2 * (p.a - previousA);
                if (C % 2 != 0) result = result * pow((C / 2 + 1) % DIV,p.a - previousA) % DIV;
                previousA = p.a;
            }
            tileNum = tileNum + (p.b - previousB - 1) / 2;
            if ((p.b - previousB - 1) % 2 != 0) result = result * ((p.b - previousB - 1) / 2 + 1) % DIV;
            previousB = p.b;

            //System.out.println("before " + (i+1) + " block");
            //System.out.println("tile Num : " + tileNum + ", " + "result : " + result);
        }
        if (R > previousA) {
            tileNum = tileNum + (long) (C - previousB - 1) / 2;
            if ((C - previousB - 1) % 2 != 0) result = result * ((C - previousB - 1) / 2 + 1) % DIV;
            previousA = previousA + 1;
        }

        if (R > previousA) {
            tileNum = tileNum + (long) C / 2 * (R - previousA);
            if (C % 2 != 0) result = result * pow((C / 2 + 1) % DIV ,R - previousA) % DIV;
        }

        System.out.println(tileNum + " " + result);
    }

    static class Pos implements Comparable<Pos> {
        int a, b;

        Pos(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pos p) {
            if (a == p.a) {
                return b - p.b;
            }
            return a - p.a;
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
        }
    }

    static long pow(long a, long b) {
        if (b == 0) return 0;
        if (b == 1) return a;
        long halfResult = pow(a, b/2);
        if (b % 2 == 0) return halfResult * halfResult % DIV;
        else return halfResult * halfResult % DIV * a % DIV;
    }
}
