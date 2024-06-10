package basic.stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
[[ 공주 구하기 ]]

 */
public class SavingPrincess {
    public int solution(int n, int k) {
        // init
        int cnt = 1;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            q.offer(i);
        }

        /*
         logic
         - 반복: poll 다음 끝에 offer
         - 검사 및 적출: poll하고 다시 넣지 않음. count==0
         */
        while(q.size() > 1) {
            int king = q.poll();
            if(cnt == k) {
                cnt = 1;
                continue;
            } else {
                q.offer(king);
                cnt++;
            }
            // test output
            //System.out.println("q: " + q.toString());
        }

        // output
        return q.poll();
    }

    public static void main(String[] args) {
        SavingPrincess m = new SavingPrincess();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(); // 왕자님 수
        int k = in.nextInt(); // 특정 숫자

        // result
        System.out.println(m.solution(n, k));
    }
}
