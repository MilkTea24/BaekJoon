package B30K.B30517;

import java.io.*;
import java.util.*;

/*
2k 번째 줄 k번째 방법에서 각각의 묶음의 사람 수(2, 4, 6, 8, 10)
2K + 1 번째 줄 k번째 방법에서 묶음의 순서를 바꿨을 때 묶음의 번호(3, 5, 7, 9)
아무거나 100개 출력하면 된다
 */
public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();

    static List<Group> groups = new ArrayList<>();
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[] partialExist;
        int index = 0;
        int groupIndex = 1;
        while (index < N) {
            partialExist = new boolean[1001];
            int groupIterator = 1;
            int groupCount = 1;
            if (input[index] != 0) partialExist[input[index]] = true;
            int start = input[index];
            while (index + groupIterator < N) {
                if (partialExist[input[index + groupIterator]]) break;

                if (input[index + groupIterator] != 0) partialExist[input[index + groupIterator]] = true;

                groupCount += 1;
                groupIterator += 1;
            }
            groups.add(new Group(groupIndex, start, input[index + groupIterator - 1], groupCount, Arrays.copyOfRange(input, index, index + groupIterator)));
            index = index + groupIterator;
            groupIndex = groupIndex + 1;
        }

        M = groups.size();

        for (int i = 0; i < M; i++) {
            System.out.println(groups.get(i));
        }

        System.out.println(M);

        if (groups.size() == 1) {
            System.out.println(groups.get(0).groupNum);
            System.out.println(groups.get(0).index);
            System.out.println(-1);
        }
        else {
            for (int i = 0; i < M-1; i++) {
                if (groups.get(i).end == 0) {
                    int groupZeroIndex = i;
                }
            }

            permutation(new LinkedList<>());
            sb.append(-1);
            System.out.println(sb);
        }
    }

    private static void permutation(LinkedList<Group> picked) {
        if (count >= 100) {
            System.out.print(sb);
            System.exit(0);
        }

        if (picked.size() == M) {
            StringBuilder line = new StringBuilder();
            StringBuilder secondLine = new StringBuilder();
            for (int i = 0; i < M; i++) {
                line.append(groups.get(i).groupNum).append(' ');
                secondLine.append(picked.get(i).index).append(' ');
            }
            sb.append(line).append('\n');
            sb.append(secondLine).append('\n');

            count = count + 1;
        }

        for (int i = 0; i < groups.size(); i++) {
            if (picked.contains(groups.get(i))) continue;

            if (picked.isEmpty() || (picked.get(picked.size() - 1).end != groups.get(i).start)){
                picked.add(groups.get(i));
                permutation(picked);
                picked.removeLast();
            }
        }
    }

    static class Group {
        int index;
        int start;
        int end;
        int groupNum;
        int[] groupList;

        Group(int i, int s, int e, int gN, int[] gl) {
            index = i;
            start = s;
            end = e;
            groupNum = gN;
            groupList = gl;
        }

        @Override
        public String toString() {
            String iter = "[";
            for (int i = 0; i < groupList.length; i++) {
                iter = iter + groupList[i] + ",";
            }
            iter = iter + "]";
            return index + ": " + start + " ~ " + end + ", " + groupNum + " " + iter;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Group) {
                Group group = (Group) o;
                return this.index == group.index;
            }
            return false;
        }
    }
}
