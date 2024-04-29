package B2K.B2107;

import java.util.*;
import java.io.*;
/*
오후 2시 25분
 */
public class Main {
    static int N;
    static Set<Integer> isVisited = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        List<Range> ranges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ranges.add(new Range(line[0], line[1]));
        }
        
        Collections.sort(ranges);

        int maxNum = 0;

        Queue<Integer> longRangesIndex = new LinkedList<>();
        longRangesIndex.add(0);

        label: while (!longRangesIndex.isEmpty()) {
            int  longRangePointer = longRangesIndex.poll();
            int shortRangePointer = longRangePointer + 1;
            Range longRange = ranges.get(longRangePointer);
            int count = 0;

            while (true) {
                if (shortRangePointer >= N) {
                    maxNum = Math.max(maxNum, count);
                    continue label;
                }

                Range shortRange = ranges.get(shortRangePointer);

                if (shortRange.start > longRange.end) {
                    maxNum = Math.max(maxNum, count);
                    if (!isVisited.contains(shortRangePointer)) {
                        longRangesIndex.add(shortRangePointer);
                        isVisited.add(shortRangePointer);
                    }
                    continue label;
                }

                if (shortRange.end > longRange.end) {
                    if (!isVisited.contains(shortRangePointer)) {
                        longRangesIndex.add(shortRangePointer);
                        isVisited.add(shortRangePointer);
                    }
                }

                if (shortRange.end < longRange.end) {
                    count += 1;
                }
                //System.out.println("long : " + longRange.start + " ~ " + longRange.end);
                //System.out.println("short : " + shortRange.start + " ~ " + shortRange.end);
                //System.out.println("count : " + count);
                shortRangePointer += 1;
            }
        }

        System.out.println(maxNum);
    }

    static class Range implements Comparable<Range> {
        int start;
        int end;

        Range(int s, int e) {
            start = s;
            end = e;
        }

        
        //start가 작은 것 부터 나열
        //start가 동일하면 end가 큰 것부터 나열
        @Override
        public int compareTo(Range r) {
            if (start != r.start) return start - r.start;
            else return r.end - end;
        }
    }
}
