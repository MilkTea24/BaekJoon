package B12K.B12865;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static List<Item> items;
    static boolean[] picked;
    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = line[0];
        K = line[1];


    }

    private static class Item implements Comparable<Item> {
        int W;
        int V;

        double VperW;

        Item(int _W, int _V) {
            W = _W;
            V = _V;
            VperW = (double)V / W;
        }

        @Override
        public int compareTo(Item i) {
            if (VperW >= i.VperW) return 1;
            else return -1;
        }
    }
}
