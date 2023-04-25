package B1K.B1992;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] image;
    static int N;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            image = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    image[i][j] = line.charAt(j) == '1';
                }
            }

            System.out.println(zipImage(N, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String zipImage(int len, int x, int y) {
        if (len == 1) return image[x][y] ? "1" : "0";

        boolean startColor = image[x][y];
        boolean isAllSameColor = true;
        label : for (int i = x; i < x + len; i++) {
            for (int j = y; j < y + len; j++) {
                if (image[i][j] != startColor) {
                    isAllSameColor = false;
                    break label;
                }
            }
        }

        if (isAllSameColor) return image[x][y] ? "1" : "0";

        StringBuilder retStringBuilder = new StringBuilder();
        int halfLen = len / 2;
        retStringBuilder.append("(")
                .append(zipImage(halfLen, x, y))
                .append(zipImage(halfLen, x, y + halfLen))
                .append(zipImage(halfLen, x + halfLen, y))
                .append(zipImage(halfLen, x + halfLen, y + halfLen))
                .append(")");

        return retStringBuilder.toString();
    }
}
