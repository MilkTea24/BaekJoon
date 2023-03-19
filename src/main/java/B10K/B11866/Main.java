package B10K.B11866;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> roundList;
        LinkedList<Integer> resultList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        roundList = IntStream.rangeClosed(0, N-1).boxed().collect(Collectors.toList());

        int ind = (K-1);

        while (!roundList.isEmpty()) {
            while (ind >= roundList.size()) ind -= roundList.size();
            resultList.add(roundList.remove(ind));
            ind--;
            ind += K;
        }

        StringBuilder sb = new StringBuilder();
        sb.append('<');
        for (int i = 0; i < N - 1; i++) {
            sb.append(resultList.get(i) + 1).append(", ");
        }
        sb.append(resultList.getLast() + 1).append('>');

        System.out.print(sb);

    }
}
