package B1K.B1043;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int m;
    static int truthNumber;
    static boolean[] canTellLie;
    static boolean[] groupCanTellLie;
    static int partyNumber;
    static List<String> inputs = new ArrayList<>();
    public static void main(String[] args) throws Exception {

    }

    static class Node {
        int index;
        boolean canTellLie = true;

        List<Node> nearNode = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }
    }
}
