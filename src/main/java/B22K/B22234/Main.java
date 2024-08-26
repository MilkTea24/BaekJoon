package B22K.B22234;

import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        int T = line[1];
        W = line[2];
        Queue<Customer> customers = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            customers.add(new Customer(line[0], line[1]));
        }
        int M = Integer.parseInt(br.readLine());
        Map<Integer, Customer> visitedCustomers = new HashMap<>();
        for (int i = 0; i < M; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            visitedCustomers.put(line[2], new Customer(line[0], line[1]));
        }
        CustomerQueue customerQueue = new CustomerQueue(customers, T);
        customerQueue.processUntilW(W, visitedCustomers);


    }

    static class CustomerQueue {
        private final int T;
        private Queue<Customer> customers;

        CustomerQueue(Queue<Customer> customers, int T) {
            this.customers = customers;
            this.T = T;
        }

        void processUntilW(int W, Map<Integer, Customer> visitedCustomers) {
            StringBuilder sb = new StringBuilder();
            int time = 0;

            //System.out.println("T: " + T);

            while (time < W) {
               System.out.println(customers);
                Customer firstCustomer = customers.poll();
                int customerSpendTime = 0;
                while ((customerSpendTime < T) && (firstCustomer.remainTime != 0)) {
                    if (time >= W) break;
                    //System.out.println(firstCustomer.id + " " + time);
                    sb.append(firstCustomer.id).append('\n');
                    if (visitedCustomers.containsKey(time))
                        customers.add(visitedCustomers.get(time));
                    time = time + 1;
                    customerSpendTime = customerSpendTime + 1;
                    firstCustomer.remainTime = firstCustomer.remainTime - 1;
                }
                if (firstCustomer.remainTime != 0)
                    customers.add(firstCustomer);
            }
            System.out.print(sb);
        }

    }

    static class Customer {
        private int id;
        private int remainTime;

        Customer(int id, int rt) {
            this.id = id;
            this.remainTime = rt;
        }

        @Override
        public String toString() {
            return "(" + id + ", " + remainTime + ")";
        }
    }
}
