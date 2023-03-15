package B10K.B10814;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfUsers = Integer.parseInt(br.readLine());
            User[] users = new User[numberOfUsers];
            for (int i = 0; i < numberOfUsers; i++) {
                String[] oneLine = br.readLine().split(" ");
                users[i] = new User(Integer.parseInt(oneLine[0]), oneLine[1], i);
            }

            Arrays.sort(users);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfUsers; i++) {
                sb.append(users[i]).append('\n');
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class User implements Comparable<User> {
        int old;
        String name;
        int ind;

        User(int o, String n, int i) {
            old = o;
            name = n;
            ind = i;
        }

        @Override
        public String toString() {
            return old + " " + name;
        }

        @Override
        public int compareTo(User U1) {
            if (this.old - U1.old != 0) return (this.old - U1.old) * (1);
            else return (this.ind - U1.ind) * (1);
        }
    }
}
