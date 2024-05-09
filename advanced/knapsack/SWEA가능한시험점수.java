package advanced.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA가능한시험점수 {

    static int n;
    static int[] scores;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());

            sum = 0;
            scores = new int[n];
            StringTokenizer input = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(input.nextToken());
                sum += scores[i];
            }

            //int num = dynamic();
            int num = knapsack();

            answer.append("#" + test_case + " " + num + "\n");
        }
        System.out.println(answer);
    }

    public static int knapsack() {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            boolean[] sample = dp.clone();
            for (int j = scores[i]; j <= sum; j++) {
                if (dp[j - scores[i]]) {
                    sample[j] = true;
                }
            }
            dp = sample.clone();
        }

        int cnt = 0;
        for (boolean each : dp) {
            if (each) cnt++;
        }
        return cnt;
    }

    public static int dynamic() {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        if (scores[1] == scores[2]) {
            dp[2] = dp[1] + 1;
        } else {
            dp[2] = dp[1] + 2;
        }

        for (int i = 3; i <= n; i++) {
            int tmp = dp[i - 1] - dp[i - 2];
            if (scores[i] == scores[i - 1]) {
                dp[i] = dp[i - 1] + tmp;
            } else {
                dp[i] = dp[i - 1] + (tmp * 2);
            }
        }

        return dp[n];
    }
}
