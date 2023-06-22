package dfs;
/*
[[ 부분집합 구하기 - DFS ]]
재귀
 */
import java.util.Scanner;

public class SubSet {
    public static int n;
    public static int[] chkArr;
    public void DFS(int level) {
        if(level == n + 1) {
            for(int i = 1; i < chkArr.length; i++) {
                if(chkArr[i] == 1) System.out.print(i + " ");
            }
            System.out.println();
        } else {
            chkArr[level] = 1;
            DFS(level+1);
            chkArr[level] = 0;
            DFS(level+1);
        }
    }

    public static void main(String[] args) {
        SubSet m = new SubSet();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        chkArr = new int[n + 1];

        m.DFS(1);
    }
}