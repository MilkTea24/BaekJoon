package B20K.B20187;

import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        String[] line = br.readLine().split(" ");

        Stack<String> stack = new Stack<>();

        for (String s : line) {
            stack.push(s);
        }

        int originalPunch = Integer.parseInt(br.readLine());

        Paper paper = new Paper(k, originalPunch);

        while (!stack.isEmpty()) {
            String op = stack.pop();
            switch (op) {
                case "R" -> paper.R();
                case "D" -> paper.D();
                case "U" -> paper.U();
                case "L" -> paper.L();
            }
        }

        paper.print();
    }

    static class Paper {
        int[][] paper; //3차원은 쌓아올린 인덱스

        int aLen = 1;
        int bLen = 1;

        int[][] openMapping = {{2, 3, 0, 1}, {1, 0, 3, 2}};
        int k;

        Paper(int _k, int originalPunch) {
            k = _k;
            paper = new int[aLen][bLen];
            paper[0][0] = originalPunch;
        }
        /*
        4 ---->
        3 ---->
        2 ---->
        1 ---->
         */
        public void D() {
            int newLen = aLen * 2;
            int[][] newPaper = new int[newLen][bLen];

            int newPaperInd = 0;
            for (int i = aLen - 1; i >= 0; i--) {
                for (int j = 0; j < bLen; j++) {
                    newPaper[newPaperInd][j] = openMapping[0][paper[i][j]];
                }
                newPaperInd += 1;
            }

            for (int i = aLen; i < newLen; i++) {
                for (int j = 0; j < bLen; j++) {
                    newPaper[i][j] = paper[i - aLen][j];
                }
            }

            paper = newPaper;
            aLen = newLen;
        }

        public void U() {
            int newLen = aLen * 2;
            int[][] newPaper = new int[newLen][bLen];

            for (int i = 0; i < aLen; i++) {
                for (int j = 0; j < bLen; j++) {
                    newPaper[i][j] = paper[i][j];
                }
            }

            int oldPaperInd = 0;
            for (int i = newLen - 1; i >= aLen; i--) {
                for (int j = 0; j < bLen; j++) {
                    newPaper[i][j] = openMapping[0][paper[oldPaperInd][j]];
                }
                oldPaperInd += 1;
            }

            paper = newPaper;
            aLen = newLen;
        }

        public void R() {
            int newLen = bLen * 2;
            int[][] newPaper = new int[aLen][newLen];


            for (int i = 0; i < aLen; i++) {
                int newPaperInd = 0;
                for (int j = bLen - 1; j >= 0; j--) {
                    newPaper[i][newPaperInd] = openMapping[1][paper[i][j]];
                    newPaperInd += 1;
                }
            }

            for (int i = 0; i < aLen; i++) {
                for (int j = bLen; j < newLen; j++) {
                    newPaper[i][j] = paper[i][j-bLen];
                }
            }

            paper = newPaper;
            bLen = newLen;
        }

        public void L() {
            int newLen = bLen * 2;
            int[][] newPaper = new int[aLen][newLen];

            for (int i = 0; i < aLen; i++) {
                for (int j = 0; j < bLen; j++) {
                    newPaper[i][j] = paper[i][j];
                }
            }


            for (int i = 0; i < aLen; i++) {
                int newPaperInd = bLen;
                for (int j = bLen - 1; j >= 0; j--) {
                    newPaper[i][newPaperInd] = openMapping[1][paper[i][j]];
                    newPaperInd += 1;
                }
            }

            paper = newPaper;
            bLen = newLen;
        }

        public void print() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[i].length; j++) {
                    sb.append(paper[i][j]).append(" ");
                }
                sb.append('\n');
            }

            System.out.println(sb);
        }

    }
}