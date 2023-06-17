import java.util.*;
/*
[[ 피보나치 수열 - 재귀 ]]

 */
public class Main {
    public static int[] fibo;

    public int DFS(int N) {
        if(fibo[N] > 0) return fibo[N];

        if(N < 2) return fibo[N] = 1;
        else return fibo[N] = DFS(N-1) + DFS(N-2);
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        fibo = new int[N+1];

        m.DFS(N);
        for(int i = 0; i < N; i++) {
            System.out.print(fibo[i] + " ");
        }
    }
}