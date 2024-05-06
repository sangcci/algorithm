package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준스티커 {

    static int n;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            n = Integer.parseInt(br.readLine());

            scores = new int[2][n];
            for (int y = 0; y < 2; y++) {
                StringTokenizer input = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < n; x++) {
                    scores[y][x] = Integer.parseInt(input.nextToken());
                }
            }

            int max = dynamic();
            answer.append(max + "\n");
        }
        System.out.print(answer);
    }

    public static int dynamic() {
        // init
        int[][] dp = new int[2][n];
        dp[0][0] = scores[0][0];
        dp[1][0] = scores[1][0];
        if (n >= 2) {
            dp[0][1] = scores[1][0] + scores[0][1];
            dp[1][1] = scores[0][0] + scores[1][1];

            // dp
            for (int i = 2; i < n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1] + scores[0][i], dp[1][i - 2] + scores[0][i]);
                dp[1][i] = Math.max(dp[0][i - 1] + scores[1][i], dp[0][i - 2] + scores[1][i]);
            }
        }
        //testPrint(dp);

        return Math.max(dp[0][n-1], dp[1][n-1]);
    }

    public static void testPrint(int[][] arr) {
        System.out.println();
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < n; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }
}
