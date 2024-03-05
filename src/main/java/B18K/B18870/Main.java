package B18K.B18870;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sortedInputs = new int[inputs.length];
        System.arraycopy(inputs, 0, sortedInputs, 0, inputs.length);

        Arrays.sort(sortedInputs);

        int sum = 0;
        int startInput = sortedInputs[0];
        for (int i = 0; i < sortedInputs.length; i++) {
            if (startInput == sortedInputs[i]) continue;
            map.put(startInput, sum++);
            startInput = sortedInputs[i];
        }

        map.put(startInput, sum);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputs.length; i++) {
            sb.append(map.get(inputs[i])).append(" ");
        }

        System.out.println(sb);
    }
}
