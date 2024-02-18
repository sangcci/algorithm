package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인프런최대점수구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer firstToken = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(firstToken.nextToken());
        int m = Integer.parseInt(firstToken.nextToken());

        Solved[] solveds = new Solved[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            int score = Integer.parseInt(token.nextToken());
            int time = Integer.parseInt(token.nextToken());
            solveds[i] = new Solved(score, time);
        }

        knapsack(solveds, m);
    }

    private static void knapsack(Solved[] solveds, int m) {
        Arrays.sort(solveds);

        int[] dp = new int[m + 1];
        for (Solved solved : solveds) {
            for (int i = m; i >= solved.time; i--) {
                dp[i] = Math.max(dp[i - solved.time] + solved.score, dp[i]);
            }
        }

        System.out.println(dp[m]);
    }
}

class Solved implements Comparable<Solved> {

    int score;
    int time;

    public Solved(int score, int time) {
        this.score = score;
        this.time = time;
    }

    @Override
    public int compareTo(Solved o) {
        return this.time - o.time;
    }
}
