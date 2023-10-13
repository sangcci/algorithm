package dynamic;

import java.util.Scanner;

/*
[[ 계단오르기 ]]
백준 문제

 */
public class ClimbStairsBaekjoon {
    public int dynamic(int n, int[] stairScores) {
        int[] dynamicTable;
        if (n < 4) {
            dynamicTable = new int[4];
        } else {
            dynamicTable = new int[n+1];
        }

        dynamicTable[1] = stairScores[1];
        dynamicTable[2] = stairScores[1]+stairScores[2];
        dynamicTable[3] = Math.max(stairScores[2]+stairScores[3], stairScores[1]+stairScores[3]);

        for (int i = 4; i <= n; i++) {
            int case1 = dynamicTable[i-3] + stairScores[i-1] + stairScores[i];
            int case2 = dynamicTable[i-2] + stairScores[i];
            dynamicTable[i] = Math.max(case1, case2);
        }
        return dynamicTable[n];
    }
    public static void main(String[] args) {
        ClimbStairsBaekjoon m = new ClimbStairsBaekjoon();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] stairScores;
        if (n < 4) {
            stairScores = new int[]{0, 0, 0, 0};
        } else {
            stairScores = new int[n+1];
        }
        for (int i = 1; i <= n; i++) {
            stairScores[i] = in.nextInt();
        }
        int answer = m.dynamic(n, stairScores);
        System.out.print(answer);
    }
}

