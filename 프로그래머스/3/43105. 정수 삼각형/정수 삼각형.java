import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[] dp = new int[triangle.length];

        dp[0] = triangle[0][0];
        
        for (int level = 1; level < triangle.length; level++) {
            int[] cur = triangle[level];
            int[] pre = dp.clone();
            //System.out.println("변화 전");
            //testPrint(pre);
            //testPrint(cur);
            for (int i = 0; i < cur.length; i++) {
                int max = 0;
                if (i == 0) {
                    max = pre[i];
                } else if (i == cur.length - 1) {
                    max = pre[i-1];
                } else {
                    max = Math.max(pre[i-1], pre[i]);
                }
                dp[i] = cur[i] + max;
            }
            //System.out.println("변화 후");
            //testPrint(dp);
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public void testPrint(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb);
    }
}