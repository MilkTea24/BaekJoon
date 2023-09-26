package B11K.B11399;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] withdrawTimes;

        try {
            int n = Integer.parseInt(br.readLine());
            withdrawTimes = new int[n];
            withdrawTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            int[] partialSum = new int[n];
            partialSum[0] = withdrawTimes[0];
            for (int i = 1; i < n; i++) {
                partialSum[i] = partialSum[i-1] + withdrawTimes[i];
            }

            int result = Arrays.stream(partialSum).sum();
            System.out.println(result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

//ATM 1대에 N명이 줄서있다
//사람마다 인출하는데 걸리는 시간 다름
//각 사람이 기다리는 시간의 합이 최소가 되게
