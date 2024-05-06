package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준동전 {
    static int n;
    static int k;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        k = Integer.parseInt(input.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int answer = dynamic();

        System.out.println(answer);

    }

    public static int dynamic() {
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int won = 1; won <= k; won++) {
                if (won >= coins[i]) dp[won] += dp[won - coins[i]];
            }
        }

        return dp[k];
    }

    public static void testPrint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
