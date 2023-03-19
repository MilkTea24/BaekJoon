package B7K.B7568;

import java.util.*;

public class Main {
    static int[][] students;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        students = new int[N][2];
        for (int i = 0; i < N; i++) {
            students[i][0] = scanner.nextInt();
            students[i][1] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int rank = 0;
            int numberOfLargePeople = 0;
            for (int j = 0; j < N; j++) {
                if (students[i][0] < students[j][0] && students[i][1] < students[j][1]) numberOfLargePeople++;
            }
            System.out.println(numberOfLargePeople + 1);
        }
    }
}


/*
public class Main {
    static int n;
    static Student[] students;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        students = new Student[n];
        for (int i = 0; i < n; i++) {
            int weight = scanner.nextInt();
            int height = scanner.nextInt();
            students[i] = new Student(i, weight, height);
        }
        Arrays.sort(students);

        int ranking = 1;
        int same = 1;
        int i;
        for (i = 0; i < students.length - 1; i++) {
            if (students[i].compareTo(students[i+1]) == -1) {
                students[i].ranking = ranking;
                ranking+= same;
            }
            else {
                students[i].ranking = ranking;
                same++;
            }
        }

        students[i].ranking = ranking;

        Arrays.sort(students, new Comparator<Student>() {
           @Override
           public int compare(Student s1, Student s2) {
                if (s1.ind > s2.ind) {
                    return 1;
                } else return -1;
           }
        });

        StringBuilder stringBuilder = new StringBuilder();
        for (i = 0; i < students.length; i++) {
            stringBuilder.append(students[i].ranking).append(' ');
        }
        System.out.println(stringBuilder);
    }

    static class Student implements Comparable<Student> {
        int ind;
        int weight;
        int height;
        int ranking;

        Student(int _i, int _w, int _h){
            ind = _i;
            weight = _w;
            height = _h;
        }

        @Override
        public int compareTo(Student s) {
            if (this.height > s.height && this.weight > s.weight) {
                return -1;
            } else if (this.height < s.height && this.weight < s.weight) {
                return 1;
            } else return 0;
        }
    }
}
*/

