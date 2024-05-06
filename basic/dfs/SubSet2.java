package basic.dfs;
import java.util.*;
/*
[[ 합이 같은 부분집합 (DFS : 아마존 인터뷰) ]]

 */
public class SubSet2 {
    public static int N;
    public static int setSum;
    public static int[] set;
    public static String answer;
    public void DFS(int l, int sum) {
        if(answer.equals("YES") || sum > (setSum-sum)) return;
        if(setSum - sum == sum) {
            answer = "YES";
        }
        else {
            if(l < N) {
                DFS(l+1, sum + set[l]);
                DFS(l+1, sum);
            }
        }
    }
    public static void main(String[] args) {
        SubSet2 m = new SubSet2();
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        set = new int[N];
        for(int i = 0; i < N; i++) {
            set[i] = in.nextInt();
            setSum += set[i];
        }
        answer = "NO";

        m.DFS(0, 0);

        System.out.println(answer);
    }
}