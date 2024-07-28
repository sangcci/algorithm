import java.util.*;

class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        // init
        int[][] visitedCnt = new int[n][m];
        visitedCnt[0][0] = 1;

        // add puddles info
        for (int i = 0; i < puddles.length; i++) {
            int y = puddles[i][1];
            int x = puddles[i][0];
            visitedCnt[y - 1][x - 1] = -1;
        }

        // bfs
        dp(visitedCnt, n, m);

        // circulate remain
        int result = visitedCnt[n - 1][m - 1] % 1000000007;

        return result;
    }
    
    public void dp(int[][] dp, int n, int m) {
        dp[0][0] = 1;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (dp[y][x] == -1) continue;
                int preUp = 0;
                int preLeft = 0;
                if (y-1 >= 0 && dp[y-1][x] != -1) {
                    preUp = dp[y-1][x];
                }
                if (x-1 >= 0 && dp[y][x-1] != -1) {
                    preLeft = dp[y][x-1];
                }
                dp[y][x] += (preUp + preLeft) % 1000000007;
                //testPrint(dp);
            }
        }
    }
}