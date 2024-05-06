package basic.dfs;
/*
[[ 바둑이 승차 ]]

 */
import java.util.Scanner;

public class RidingBaduk {
    static int C, N;
    static int[] wArr;
    static int answer = 0;
    public void dfs(int l, int sum) {
        if(sum > C) return;
        if(l == N) {
            if(sum < C && sum > answer) {
                answer = sum;
            }
        } else {
            dfs(l+1, sum + wArr[l]);
            dfs(l+1, sum);
        }
    }

    public static void main(String[] args) {
        RidingBaduk m = new RidingBaduk();
        Scanner in = new Scanner(System.in);

        C = in.nextInt();
        N = in.nextInt();
        wArr = new int[N];
        for(int i = 0; i < wArr.length; i++) {
            wArr[i] = in.nextInt();
        }

        m.dfs(0, 0);
        System.out.print(answer);
    }
}
