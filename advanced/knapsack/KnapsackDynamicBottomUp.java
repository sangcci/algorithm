package advanced.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackDynamicBottomUp {

    static int n, k;
    static class Node {
        int w;
        int v;
        public Node(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
    static Node[] nodes;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(input.nextToken());
        k = Integer.parseInt(input.nextToken());

        nodes = new Node[n+1];

        nodes[0] = new Node(0, 0);
        for (int i = 1; i <= n; i++) {
            StringTokenizer inputNode = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(inputNode.nextToken());
            int v = Integer.parseInt(inputNode.nextToken());
            nodes[i] = new Node(w, v);
        }

        dp = new int[n+1][k+1];

        knapsack();

        System.out.println(dp[n][k]);
    }

    public static void knapsack() {
        for (int num = 1; num <= n; num++) {
            for (int w = 1; w <= k; w++) {
                if (w - nodes[num].w >= 0) {
                    dp[num][w] = Math.max(dp[num-1][w-nodes[num].w] + nodes[num].v, dp[num-1][w]);
                    //System.out.println(num + " " + w + " " + dp[num][w]);
                } else {
                    dp[num][w] = dp[num-1][w];
                }
            }
        }
    }
}
