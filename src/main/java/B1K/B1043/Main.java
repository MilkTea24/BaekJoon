package B1K.B1043;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

//진실을 아는 사람들을 피해서 거짓말을 해야 한다
//하지만 다른 파티에 같이 동행한 사람 앞에서는 거짓말만 하거나 진실만 말해야 한다.
//진실은 아는 사람과 인접한 사람들에게는 진실만 말해야 한다.
public class Main {

    static int N;
    static int M;

    static boolean[] mustTellTruth;

    static List<Set<Integer>> nearNodeInfo = new ArrayList<>();
    static List<Integer> originalMember = new ArrayList<>();
    static List<List<Integer>> parties = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        getInput();

        //인접 노드 기록
        for (int i = 0; i < M; i++) {
            List<Integer> party = parties.get(i);

            for (int j = 0; j < party.size(); j++) {
                for (int k = 0; k < party.size(); k++) {
                    nearNodeInfo.get(party.get(j)).add(party.get(k));
                    nearNodeInfo.get(party.get(k)).add(party.get(j));
                }
            }
        }

        //진실을 아는 사람 모두 DFS 돌리기
        for (int i = 0; i < originalMember.size(); i++) {
            DFS(originalMember.get(i));
        }

        //각 파티마다 진실을 아는 사람이 하나라도 있는지 검증하기
        //파티에서 진실을 아는 사람이 하나라도 없으면 +1
        int result = 0;
        label: for (int i = 0; i < parties.size(); i++) {
            List<Integer> party = parties.get(i);

            for (int index : party) {
                if (mustTellTruth[index]) continue label;
            }

            result++;
        }

        System.out.println(result);
    }

    private static void DFS(int start) {
        if (mustTellTruth[start]) return; //이미 진실을 말해야 하는 사람으로 판명났으면 중단

        mustTellTruth[start] = true;
        Set<Integer> currentNearNodeInfo = nearNodeInfo.get(start);

        for (int index : currentNearNodeInfo) {
            DFS(index);
        }
    }

    private static void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0]; M = line[1];

        mustTellTruth = new boolean[N];
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i < 1 + line[0]; i++) {
            originalMember.add(line[i] - 1);
        }

        for (int i = 0; i < M; i++) {
            parties.add(Arrays.stream(br.readLine().split(" "))
                    .skip(1)
                    .mapToInt(str -> Integer.parseInt(str) - 1)
                    .boxed()
                    .collect(Collectors.toList()));
        }

        for (int i = 0; i < N; i++) {
            nearNodeInfo.add(new HashSet<>());
        }
    }
}
