package dynamic;

import java.util.Scanner;
/*
[[ 계단 오르기 ]]
Java 알고리즘 강의

 */
public class ClimbStairs {
    public int dynamic(int n) {
        int[] dynamicTable = new int[n+1];
        dynamicTable[1] = 1;
        dynamicTable[2] = 2;

        for (int i = 3; i <= n; i++) {
            dynamicTable[i] = dynamicTable[i-1] + dynamicTable[i-2];
        }
        return dynamicTable[n];
    }
    public static void main(String[] args) {
        ClimbStairs m = new ClimbStairs();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int answer = m.dynamic(n);
        System.out.println(answer);
    }
}
