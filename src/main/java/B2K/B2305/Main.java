package B2K.B2305;

import java.util.*;

/*
6시 9분 시작
N-1개는 지정석, 1개는 자유석
지정석에 앉거나 왼쪽 오른쪽 자리 앉거나 자유석에 앉을 수 있다
규칙을 만족하는 모든 경우의 수 N은 40

1 2 3 4 5 O 7 8 9 10
자유석 기준으로 쪼개기

1. N번째 사람이 N번째 자리에 앉는 경우(자유석 안씀)
X X X X 5 0 -> fibo[n-1]
2. N번째 사람이 N-1번째 자리에 앉는 경우(자유석 안씀)
X X X 5 4 0 -> fibo[n-2]
3. N번째 사람이 자유석에 앉는 경우(자유석 씀)
X X X X 0 5
X X X 0 4 5
X X 0 3 4 5
X 0 2 3 4 5
0 1 2 3 4 5
fibo[n-1] + fibo[n-2] + fibo[n-3] + ... fibo[0]
4. N번째 사람이 N-1번째 자리에 앉고 N번 자리가 공석(자유석 씀)
X X X 5 0 4
X X 4 5 0 3
X 3 4 5 0 2
2 3 4 5 0 1
fibo[n-2] + fibo[n-1] + ... + fibo[0]

자유석 쓰지 않을 때 : fibo[n-1] + fibo[n-2]
자유석 쓸 때 : fibo[0] + fibo[1] + ... + fibo[n-1] + fibo[0] + fibo[1] + ... + fibo[n-2]
dp[n] = (fibo[0] + fibo[1] + ... + fibo[n]



 */
public class Main {
    static int N, K;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();

        int[] fibo = new int[N + 2];
        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i < N + 2; i++) {
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }

        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + fibo[i] - 1 + fibo[i - 1] - 1;
        }

        System.out.println(dp[N-K + 1] * fibo[K - 1] + dp[K] * fibo[N-K] - fibo[K-1] * fibo[N - K]);
    }

    public static int assignSeats(int[] seats, int result, int count) {
        if (count == K) count = count + 1;
        if (count == N) {
            /*
            for (int i = 0; i < seats.length; i++) {
                System.out.print((seats[i] + 1) + " ");
            }
            System.out.println();
             */
            return result + 1;
        }

        //제자리에 앉기
        if (seats[count] == -1) {
            seats[count] = count;
            result = assignSeats(seats, result, count + 1);
            seats[count] = -1;
        }

        //왼쪽에 앉기
        if (count > 0 && seats[count - 1] == -1) {
            seats[count - 1] = count;
            result = assignSeats(seats, result, count + 1);
            seats[count - 1] = -1;
        }

        //오른쪽에 앉기
        if (count < N - 1 && seats[count + 1] == -1) {
            seats[count + 1] = count;
            result = assignSeats(seats, result, count + 1);
            seats[count + 1] = -1;
        }

        //자유석에 앉기
        if (seats[K] == -1 && count + 1 != K && count - 1 != K) {
            seats[K] = count;
            result = assignSeats(seats, result, count + 1);
            seats[K] = -1;
        }

        return result;
    }
}
