package B9K.B9328;

/*
10시 반 시작
소문자 열쇠는 대문자 문을 열 수 있음
처음에 열쇠가 주어질 수도 있음
유니온 파인드? -> 열쇠를 가지고 있으면 union
 */

import java.util.*;
import java.io.*;
public class Main {

    static Set<Character> keyInfo; //갖고 있는 키

    static List<Integer> entrances;
    static int N, M;
    static int count;
    static boolean isChanged;
    static char[][] map;

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() throws Exception {
        int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = ints[0];    M = ints[1];

        map = new char[N][M];

        entrances = new ArrayList<>(); //진입구 정보
        keyInfo = new HashSet<>(); //가진 키 정보
        count = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                int pos = i * 100 + j;
                //진입로에 추가
                if (map[i][j] != '*' && (i == 0 || i == N-1 || j == 0 || j == M-1)) entrances.add(pos);
            }
        }

        String line = br.readLine();
        if (!line.equals("0")) {
            for (int i = 0; i < line.length(); i++) {
                keyInfo.add(line.charAt(i));
            }
        }

        while (true) {
            boolean[][] isVisited = new boolean[N][M];
            isChanged = false;
            for (int i = 0; i < entrances.size(); i++) {
                int entranceI = entrances.get(i) / 100;
                int entranceJ = entrances.get(i) % 100;

                DFS(entranceI, entranceJ, isVisited);
            }

            if (!isChanged) {
                System.out.println(count);
                return;
            }
        }
    }

    static void DFS(int a, int b, boolean[][] isVisited) {
        if (a < 0 || a >= N || b < 0 || b >= M) return;
        if (isVisited[a][b]) return;
        if (map[a][b] == '*') return;

        isVisited[a][b] = true;

        for (int i = 0; i < 4; i++) {
            int newI = a + direction[i][0];
            int newJ = b + direction[i][1];

            if ('A' <= map[a][b] && map[a][b] <= 'Z') {
                char lowerCase = (char)('a' + (map[a][b] - 'A'));
                if (!keyInfo.contains(lowerCase)) continue;
                map[a][b] = '.';
            }
            if ('a' <= map[a][b] && map[a][b] <= 'z') {
                keyInfo.add(map[a][b]);
                map[a][b] = '.';
                isChanged = true; // 새로운 키를 얻었으면 한번 더 탐색할 수 있다.
            }
            if (map[a][b] == '$') {
                count += 1;
                map[a][b] = '.';
            }
            DFS(newI, newJ, isVisited);
        }

        if (map[a][b] == '.') {
            for (int k = 0; k < 4; k++) {
                int newI = a + direction[k][0];
                int newJ = b + direction[k][1];
                DFS(newI, newJ, isVisited);
            }
        }
    }
}
