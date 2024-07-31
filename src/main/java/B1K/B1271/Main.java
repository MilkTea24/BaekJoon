package B1K.B1271;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] twoNum = br.readLine().split(" ");

        BigInteger a = new BigInteger(twoNum[0]);
        BigInteger b = new BigInteger(twoNum[1]);

        System.out.println(a.divide(b));
        System.out.println(a.mod(b));
    }
}
