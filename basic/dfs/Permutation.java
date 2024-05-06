package basic.dfs;

import java.util.*;

/*
[[ 중복 순열 ]]

 */
public class Permutation {
    static int N;
    static int M;
    static int[] permutation;
    public void dfs(int index) {
        if (index >= M) {
            for (int v : permutation) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            for (int v = 1; v <= N; v++) {
                permutation[index] = v;
                dfs(index + 1);
            }
        }
    }

    public static void main(String[] args) {
        Permutation m = new Permutation();
        Scanner in = new Scanner(System.in);

        // init
        N = in.nextInt();
        M = in.nextInt();
        permutation = new int[M];

        // basic.dfs
        m.dfs(0);
    }
}