package programmers.carpool_taxi_fare;

//두 명이 집에 도착할 때까지 최소 택시비 구하기
//만약에 따로 가는게 더 낮다면 따로 가도 됨
//n은 노드 개수
//각자 DFS를 해서 중복되는 경우를 제외하기
//11시 50분

import java.util.*;

class OldSolution {
    static Map<String, Integer> routeA = new HashMap<>();
    static Map<String, Integer> routeB = new HashMap<>();
    static int[][] fareInfo;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        s -= 1;
        a -= 1;
        b -= 1;
        //fareInfo 초기화
        fareInfo = new int[n][n];

        //fareInfo 넣기
        for (int i = 0; i < fares.length; i++) {
            fareInfo[fares[i][0] - 1][fares[i][1] - 1] = fares[i][2];
            fareInfo[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];
        }

        StringBuilder sbA = new StringBuilder();
        sbA.append(s);
        boolean[] isVisitedA = new boolean[n];
        isVisitedA[s] = true;
        DFSA(s, a, isVisitedA, sbA, 0);

        StringBuilder sbB = new StringBuilder();
        sbB.append(s);
        boolean[] isVisitedB = new boolean[n];
        isVisitedB[s] = true;
        DFSB(s, b, isVisitedB, sbB, 0);

        int result = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> eA : routeA.entrySet()) {
            for (Map.Entry<String, Integer> eB : routeB.entrySet()) {
                String strRouteA = eA.getKey();
                String strRouteB = eB.getKey();
                int distA = eA.getValue();
                int distB = eB.getValue();

                int partialResult = distA + distB;
                for (int i = 1; i < Math.min(strRouteA.length(), strRouteB.length()); i++) {
                    if (strRouteA.charAt(i) == strRouteB.charAt(i)) {
                        int end = strRouteA.charAt(i) - '0';
                        int start = strRouteA.charAt(i - 1) - '0';
                        partialResult -= fareInfo[start][end];
                    }
                    else break;
                }
                result = Math.min(partialResult, result);
            }
        }

        return result;
    }

    public void DFSA(int current, int a, boolean[] isVisited,
                     StringBuilder result, int count) {
        if (current == a) {
            routeA.put(result.toString(), count);
            return;
        }

        int[] nearFareInfo = fareInfo[current];

        for (int i = 0; i < nearFareInfo.length; i++) {
            if (nearFareInfo[i] == 0) continue;

            if (isVisited[i]) continue;

            isVisited[i] = true;
            result.append(i);
            DFSA(i, a, isVisited, result, count + nearFareInfo[i]);
            isVisited[i] = false;
            result.delete(result.length() - 1, result.length());
        }

    }

    public void DFSB(int current, int b, boolean[] isVisited,
                     StringBuilder result, int count) {
        if (current == b) {
            routeB.put(result.toString(), count);
            return;
        }

        int[] nearFareInfo = fareInfo[current];

        for (int i = 0; i < nearFareInfo.length; i++) {
            if (nearFareInfo[i] == 0) continue;

            if (isVisited[i]) continue;

            isVisited[i] = true;
            result.append(i);
            DFSB(i, b, isVisited, result, count + nearFareInfo[i]);
            isVisited[i] = false;
            result.delete(result.length() - 1, result.length());
        }

    }

    class Node {
        int end;
        int dist;

        Node (int e, int d) {
            end = e;
            dist = d;
        }
    }
}
