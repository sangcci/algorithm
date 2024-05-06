package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준퇴사 {

    static int n;
    static int max = 0;
    static int[] Ti;
    static int[] Pi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Ti = new int[n+1];
        Pi = new int[n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer TiPi = new StringTokenizer(br.readLine(), " ");
            Ti[i] = Integer.parseInt(TiPi.nextToken());
            Pi[i] = Integer.parseInt(TiPi.nextToken());
        }

        dfs(0, 1);
        System.out.println(max);
    }

    public static void dfs(int sum, int day) {
        if (day == n+1) {
            max = Math.max(sum, max);
        } else {
            // O X
            if (day + (Ti[day]-1) >= n+1) {
                dfs(sum, day+1); // 어쩔 수 없이 선택 X
            }
            else {
                dfs(sum + Pi[day], day + Ti[day]); // 선택
                dfs(sum, day + 1); // 선택 안하고 다음날
            }
        }
    }
}
