package B11K.B11286;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        Heap maxHeap = new Heap() {
            @Override
            protected boolean isAHigherThanB(int a, int b) {
                if (Math.abs(a) == Math.abs(b)) {
                    return a < b;
                }
                else return Math.abs(a) < Math.abs(b);
            }
        };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int inputNum = Integer.parseInt(br.readLine());
            if (inputNum == 0) {
                sb.append(maxHeap.delete()).append('\n');
            }
            else maxHeap.insert(inputNum);
        }

        System.out.println(sb);
    }

    static abstract class Heap {
        private final int[] array = new int[100000];
        private static final int SUPER_PARENT = 0;
        private int size = -1;


        protected abstract boolean isAHigherThanB(int a, int b);

        public void insert(int num) {
            array[++size] = num;

            int currentChildPointer = size;
            int currentParentPointer = ((currentChildPointer + 1) / 2) - 1;
            while (currentChildPointer != SUPER_PARENT && isAHigherThanB(array[currentChildPointer], array[currentParentPointer])) {
                changeChildAndParent(currentChildPointer, currentParentPointer);
                currentChildPointer = currentParentPointer;
                currentParentPointer = ((currentChildPointer + 1) / 2) - 1;
            }
        }

        public int delete() {
            if (size == -1) return 0;

            int result = array[0];
            array[0] = array[size--];

            int currentParentPointer = 0;
            int currentChildPointer = selectOneFromChildren(currentParentPointer);
            while(currentChildPointer != -1 && isAHigherThanB(array[currentChildPointer], array[currentParentPointer])) {
                changeChildAndParent(currentChildPointer, currentParentPointer);
                currentParentPointer = currentChildPointer;
                currentChildPointer = selectOneFromChildren(currentParentPointer);
            }

            return result;
        }



        private int selectOneFromChildren(int parentPointer) {
            int oneChildPointer = parentPointer * 2 + 1;
            if (oneChildPointer > size) return -1;

            int anotherChildPointer = oneChildPointer + 1;
            if (anotherChildPointer > size) return oneChildPointer;

            if (isAHigherThanB(array[oneChildPointer], array[anotherChildPointer])) return oneChildPointer;
            else return anotherChildPointer;
        }

        private void changeChildAndParent(int childPointer, int parentPointer) {
            int temp = array[parentPointer];
            array[parentPointer] = array[childPointer];
            array[childPointer] = temp;
        }

    }
}
