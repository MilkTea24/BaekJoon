package B2K.B2338;

import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.nextLine();
        String b = scanner.nextLine();

        BigInteger numA = new BigInteger(a);
        BigInteger numB = new BigInteger(b);

        System.out.println(numA.add(numB));
        System.out.println(numA.add(numB.negate()));
        System.out.println(numA.multiply(numB));
    }
}
