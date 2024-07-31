package B2K.B2525;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hour = scanner.nextInt();
        int minute = scanner.nextInt();
        int plusMinute = scanner.nextInt();

        minute = minute + plusMinute;
        if (minute >= 60) {
            hour = hour + (minute / 60);
            minute = minute % 60;
        }

        if (hour >= 24) {
            hour = hour % 24;
        }
        System.out.println(hour + " " + minute);
    }
}
