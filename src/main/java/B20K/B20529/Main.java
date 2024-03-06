package B20K.B20529;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//테스트케이스는 1~50개
//N은 최대 100000
//100000 * 99999 * 99998 해서 시간 초과가 날 것 같지만 결국 제공되는 종류는 16가지
//16가지 mbti 내에서 거리만 구하면 끝이다
//다만 중첩되는 mbti가 있으면 거리가 0이기 때문에 이도 고려해주어야 한다.
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    private static void oneCase() throws Exception {
        int n = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> mbti = new TreeMap<>();
        String[] inputs = br.readLine().split(" ");

        for (int i = 0; i < inputs.length; i++) {
            if (!mbti.containsKey(inputs[i])) mbti.put(inputs[i], 1);
            else {
                if (mbti.get(inputs[i]) + 1 == 3) {
                    System.out.println("0");
                    return;
                }
                mbti.put(inputs[i], mbti.get(inputs[i]) + 1);
            }
        }

        List<String> mbtiList = new ArrayList<>();
        for (Map.Entry<String, Integer> e : mbti.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                mbtiList.add(e.getKey());
            }

        }

        int sum = 0;
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < mbtiList.size(); i++) {
            for (int j = i + 1; j < mbtiList.size(); j++) {
                sum += calculateDistance(mbtiList.get(i), mbtiList.get(j));
                for (int k = j + 1; k < mbtiList.size(); k++) {
                    sum += calculateDistance(mbtiList.get(i), mbtiList.get(k));
                    sum += calculateDistance(mbtiList.get(j), mbtiList.get(k));
                    minSum = Math.min(minSum, sum);
                    sum -= calculateDistance(mbtiList.get(i), mbtiList.get(k));
                    sum -= calculateDistance(mbtiList.get(j), mbtiList.get(k));
                }
                sum -= calculateDistance(mbtiList.get(i), mbtiList.get(j));
            }
        }

        System.out.println(minSum);
    }

    private static int calculateDistance(String a, String b) {
        if (a.equals(b)) return 0;

        int sum = 0;
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        for (int i = 0; i < charA.length; i++) {
            if (charA[i] != charB[i]) sum++;
        }

        return sum;
    }
}
