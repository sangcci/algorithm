package array;

import java.util.Scanner;
/*
[[ 덧칠하기 - 엔드포인터 ]]
프로그래머스 1단계 문제
 */

public class TopCoat {
    public int solution(int N, int M, int[] section) {
        int cnt = 1;
        int tmp = section[0];

        for(int e : section) {
            if(e > tmp+M-1) {
                tmp = e;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        TopCoat m = new TopCoat();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();
        int s = in.nextInt();
        int[] section = new int[s];
        for(int i = 0; i < s; i++) section[i] = in.nextInt();

        System.out.println(m.solution(N, M, section));
    }
}
