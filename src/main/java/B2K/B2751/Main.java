package B2K.B2751;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(br.readLine());
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                treeSet.add(Integer.parseInt(br.readLine()));
            }
            Iterator<Integer> iter = treeSet.iterator();
            StringBuilder sb = new StringBuilder();
            while (iter.hasNext()) {
                int a = iter.next();
                sb.append(a).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {}
    }
}
