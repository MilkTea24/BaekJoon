package B1K.B1043;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//지민이는 과장해서 말해야함
//하지만 진실을 아는 사람 앞에서는 진실만을 말해야한다.
//첫째 줄에는 사람의 수와 파티 수
//둘째 줄에는 이야기의 진실을 아는 사람과 그 뒤에 그 사람들의 번호
//셋째 줄부터 M개의 줄에는 파티에 오는 사람의 수와 번호
//과장된 이야기를 할 수 있는 파티 개수의 최대 값 구하는 프로그램 작성

public class MainOld {
    static Node[] nodes;
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = (new Node(i));
        }

        line = br.readLine().split(" ");
        int truthNumber = Integer.parseInt(line[0]);
        for (int i = 0; i < truthNumber; i++) {
            int index = Integer.parseInt(line[i + 1]);
            nodes[index - 1].canTellLie = false;
        }

        List<String> party = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            party.add(br.readLine());
            line = party.get(party.size() - 1).split(" ");
            int partyNumber = Integer.parseInt(line[0]);

            int beforeIndex = 0;
            for (int j = 0; j < partyNumber; j++) {
                int index = Integer.parseInt(line[j + 1]);
                if (j >= 1) {
                    nodes[index - 1].nearNodes.add(nodes[beforeIndex - 1]);
                    nodes[beforeIndex - 1].nearNodes.add(nodes[index - 1]);
                }
                if ((j == 1) && !nodes[beforeIndex - 1].canTellLie) {
                    backtracking(beforeIndex);
                }

                beforeIndex = index;
                if (!nodes[index - 1].canTellLie) {
                    backtracking(index);
                }

            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            line = party.get(i).split(" ");
            int partyNumber = Integer.parseInt(line[0]);

            boolean flag = true;
            for (int j = 0; j < partyNumber; j++) {
                int index = Integer.parseInt(line[j + 1]);
                if (!nodes[index - 1].canTellLie) {
                    flag = false;
                    break;
                }
            }
            if (flag) result++;
        }
        System.out.println(result);
    }

    static void backtracking(int index) {
        Node currentNode = nodes[index - 1];
        if (currentNode.nearNodes.isEmpty()) return;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(currentNode);

        while (!queue.isEmpty()) {
            //if (!currentNode.canTellLie) return;
            currentNode = queue.removeFirst();
            currentNode.canTellLie = false;

            for (int i = 0; i < currentNode.nearNodes.size(); i++) {
                if (currentNode.nearNodes.get(i).canTellLie) queue.add(currentNode.nearNodes.get(i));
            }
        }
    }

    static class Node {
        int index;
        boolean canTellLie = true;
        List<Node> nearNodes = new ArrayList<>();

        Node(int index) {
            this.index = index;
        }
    }
}

/*
public class Main {
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        line = br.readLine().split(" ");
        int truthPeople = Integer.parseInt(line[0]);
        long bitMask = 0L;

        for (int i = 0; i < truthPeople; i++) {
            bitMask |= (long) 1 << Integer.parseInt(line[1 + i]);
        }

        //System.out.println(1 << 4);
        //System.out.println(1 << 2);
        //0110

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            lines.add(br.readLine());
            line = lines.get(lines.size() - 1).split(" ");
            for (int j = 0; j < line.length - 1; j++) {
                long inputMask = (long)1 << Integer.parseInt(line[1 + j]);
                //0010
                long andResult = bitMask & inputMask;
                if (andResult != 0L) {
                    for (int k = 0; k < line.length - 1; k++) {
                        bitMask |= (long)1 << Integer.parseInt(line[1 + k]);
                    }
                    break;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            line = lines.get(i).split(" ");
            boolean flag = true;
            for (int j = 0; j < line.length - 1; j++) {
                long inputMask = (long)1 << Integer.parseInt(line[1 + j]);
                //0010
                long andResult = bitMask & inputMask;
                if (andResult != 0L) {
                    flag = false;
                    break;
                }
            }
            if (flag) result++;
        }

        System.out.println(result);
    }
}
 */
