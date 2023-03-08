package B2K.B2869;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int V = scanner.nextInt();

        /*
        int height = 0;
        int days = 0;
        while (true) {
            days++;
            height += A;
            if (height >= V) {
                System.out.println(days);
                break;
            }
            height -= B;
        }
        */
        V = V - A;
        int result = 1 + (V % (A - B) == 0 ? V / (A - B) : V / (A - B) + 1);
        System.out.println(result);
    }
}
