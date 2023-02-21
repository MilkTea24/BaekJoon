package B2K.B2292;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        if (input == 1) {
            System.out.println(1);
            System.exit(0);
        }
        int start = 1;
        int ind = 1;
        while (true) {
            start += (ind++) * 6;
            if (start >= input) break;
        }
        System.out.println(ind);
    }
}
