package basic.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 [[ 동전 교환 ]]
 */

public class ChangeCoin {
    static int n;
    static int[] kinds;
    static int change;
    static int count;
    static int[] chk;
    public int bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int e : kinds) {
            q.offer(e);
        }

        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int money = q.poll();
                if (chk[money] == 1) {
                    continue;
                }
                //System.out.println("money: " + money);
                if (money == change) {
                    //System.out.println("money = change 구간: " + count);
                    return count;
                } else {
                    chk[money] = 1;
                    for (int e : kinds) {
                        q.offer(money + e);
                    }
                }
            }
            count++;
        }
        return 0;
    }
    public static void main(String[] args) {
        ChangeCoin m = new ChangeCoin();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        kinds = new int[n];
        for (int i = 0; i < n; i++) {
            kinds[i] = in.nextInt();
        }
        change = in.nextInt();
        count = 1;
        chk = new int[10001];

        System.out.println(m.bfs());
    }
}
