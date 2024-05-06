package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 백준2655가장높은탑쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Rock> rocks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            int width = Integer.parseInt(token.nextToken());
            int height = Integer.parseInt(token.nextToken());
            int weight = Integer.parseInt(token.nextToken());
            rocks.add(new Rock(i+1, width, height, weight));
        }

        dynamic(n, rocks);
    }
    public static void dynamic(int n, List<Rock> rocks) {
        rocks.sort(Rock::compareTo);

        int[] dp = new int[n];
        dp[0] = rocks.get(0).height;
        for (int i = 1; i < n; i++) {
            Rock cur = rocks.get(i);
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                Rock prev = rocks.get(j);
                if (cur.weight < prev.weight) max = Math.max(max, dp[j]);
            }
            if (max == 0) dp[i] = cur.height;
            else dp[i] = max + cur.height;
        }

        ArrayList<Integer> result = new ArrayList<>();
        int maxHeight = Arrays.stream(dp).max().getAsInt();
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxHeight) maxIndex = i;
        }
        for (int i = maxIndex; i >= 0; i--) {
            Rock cur = rocks.get(i);
            if (maxHeight == dp[i]) {
                result.add(cur.num);
                maxHeight -= cur.height;
            }
        }
        System.out.println(result.size());
        result.stream().forEach(each -> System.out.println(each));
    }
}
