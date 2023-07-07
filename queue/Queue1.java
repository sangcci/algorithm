package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
[[ 백준 11866번 요새푸스 문제 ]]

 */

public class Queue1 {
    static int n, k;

    public void solution(int n, int k) {

        // init
        Queue<Integer> permutation = new LinkedList<>();
        for (int i = 1; i <= n; i++) permutation.add(i);
        ArrayList<Integer> answer = new ArrayList<>();
        int count = 1;

        // logic
        while (!permutation.isEmpty()) {
            for (int i = 0; i < permutation.size(); i++) {
                // poll
                int poll = permutation.poll();
                // count
                if (count == k) {
                    answer.add(poll); // no offer
                    count = 1;
                } else {
                    permutation.offer(poll); // offer
                    count++;
                }
            }
        }

        // print
        System.out.print("<");
        for (int i = 0; i < answer.size(); i++) {
            if (i == answer.size() - 1) System.out.print(answer.get(i));
            else System.out.print(answer.get(i) + ", ");
        }
        System.out.print(">");
    }

    public static void main(String[] args) {
        Queue1 m = new Queue1();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        k = in.nextInt();

        m.solution(n, k);
    }
}
