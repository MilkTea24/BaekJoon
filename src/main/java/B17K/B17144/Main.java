package B17K.B17144;

import java.util.*;
import java.io.*;

//미세먼지 확산은 상하좌우 네 방향
//인접한 방향에 공기청정기가 있거나 칸이 없으면 확산하지 않음
//공기청정기는 1번 열에 두칸을 차지함
//미세먼지가 한쪽으로 확산되는 양은 A/5
//공기청정기 위쪽은 반시계
//공기청정기 아래쪽은 시계
//바람이 불면 미세먼지는 한 칸씩 이동
//미세먼지 확산이 먼저고 이후 공기청정기 바람 이동이 다음 차례임
public class Main {
    static int R, C, T;
    static int[][] map;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        R = line[0];    C = line[1];    T = line[2];

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int purifierPos = 0;
        for (int i = 0; i < R; i++) {
            if (map[i][0] != -1) continue;

            purifierPos = i;
            break;
        }

        for (int i = 0; i < T; i++) {
            spread(purifierPos);

            /*
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    System.out.print(map[j][k] + " ");
                }
                System.out.println();
            }

            System.out.println();
            */

            operatePurifier(purifierPos);

            /*
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    System.out.print(map[j][k] + " ");
                }
                System.out.println();
            }

            System.out.println();
             */

        }

        int sum = Arrays.stream(map)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i != -1)
                .sum();


        System.out.print(sum);
    }

    private static void spread(int purifierPos) {
        int[][] newMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;

                int extractAmount = map[i][j] / 5;
                if (extractAmount == 0) {
                    newMap[i][j] += map[i][j];
                    continue;
                }

                int remainAmount = map[i][j];
                for (int k = 0; k < 4; k++) {
                    int newI = i + direction[k][0];
                    int newJ = j + direction[k][1];

                    if (newI < 0 || newJ < 0 || newI >= R || newJ >= C) continue;
                    if (map[newI][newJ] == -1) continue;


                    newMap[newI][newJ] += extractAmount;
                    remainAmount -= extractAmount;
                }

                newMap[i][j] += remainAmount;
            }
        }

        newMap[purifierPos][0] = -1;
        newMap[purifierPos + 1][0] = -1;

        map = newMap;
    }

    private static void operatePurifier(int pos) {
        //오른쪽
        int temp = map[pos][C-1];
        for (int i = C - 1; i > 1 ; i--) {
            map[pos][i] = map[pos][i-1];
        }
        map[pos][1] = 0;

        //위쪽
        int temp2 = map[0][C-1];
        for (int i = 0; i < pos - 1; i++) {
            map[i][C-1] = map[i+1][C-1];
        }
        map[pos - 1][C-1] = temp;

        //왼쪽
        temp = map[0][0];
        for (int i = 0; i < C - 2; i++) {
            map[0][i] = map[0][i + 1];
        }
        map[0][C-2] = temp2;

        //아래쪽
        for (int i = pos - 1; i > 1; i--) {
            map[i][0] = map[i-1][0];
        }
        map[1][0] = temp;

        //오른쪽
        temp = map[pos+1][C-1];
        for (int i = C-1; i > 1; i--) {
            map[pos + 1][i] = map[pos + 1][i-1];
        }
        map[pos + 1][1] = 0;

        //아래쪽
        temp2 = map[R-1][C-1];
        for (int i = R-1; i > pos + 2; i--) {
            map[i][C-1] = map[i-1][C-1];
        }
        map[pos + 2][C-1] = temp;

        //왼쪽
        temp = map[R-1][0];
        for (int i = 0; i < C-2; i++) {
            map[R-1][i] = map[R-1][i+1];
        }
        map[R-1][C-2] = temp2;


        for (int i = pos + 2; i < R-1; i++) {
            map[i][0] = map[i + 1][0];
        }
        map[R-2][0] = temp;
    }
}