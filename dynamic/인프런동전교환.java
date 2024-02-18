package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인프런동전교환 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] coins = new int[n];
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(token.nextToken());
        }
        int m = Integer.parseInt(br.readLine());

        knapsack(coins, m);
    }

    public static void knapsack(int[] coins, int m) {
        Arrays.sort(coins);

        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i < m + 1; i++) {
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }

        System.out.println(dp[m]);
    }
}
